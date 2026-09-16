package korrawit.cms.service;

import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import korrawit.cms.domain.entity.MediaObject;
import korrawit.cms.domain.entity.MediaRecord;
import korrawit.cms.domain.repository.MediaRecordRepository;
import korrawit.cms.domain.repository.MediaRepository;
import korrawit.cms.error.MediaStorageException;
import korrawit.cms.error.ResourceNotFoundException;
import software.amazon.awssdk.core.exception.SdkException;

@Service
public class MediaService {

    private static final Logger log = LoggerFactory.getLogger(MediaService.class);

    private final MediaRecordRepository mediaRecordRepository;
    private final MediaRepository mediaRepository;

    public MediaService(MediaRecordRepository mediaRecordRepository, MediaRepository mediaRepository) {
        this.mediaRecordRepository = mediaRecordRepository;
        this.mediaRepository = mediaRepository;
    }

    public List<MediaRecord> findAll() {
        return mediaRecordRepository.findAll();
    }

    public MediaRecord findById(int id) {
        return mediaRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media record not found: " + id));
    }

    public MediaRecord create(MediaRecord record) {
        Instant now = Instant.now();
        record.setId(null);
        record.setCreatedAt(now);
        record.setUpdatedAt(now);
        return mediaRecordRepository.save(record);
    }

    public MediaRecord update(int id, MediaRecord record) {
        MediaRecord existing = findById(id);
        record.setId(id);
        record.setCreatedAt(existing.getCreatedAt());
        record.setUpdatedAt(Instant.now());
        return mediaRecordRepository.save(record);
    }

    public void delete(int id) {
        findById(id);
        mediaRecordRepository.deleteById(id);
    }

    /**
     * Callers never supply an S3 key directly: the key only ever comes from the
     * looked-up DB record, so the API surface never exposes raw bucket paths.
     */
    public MediaObject fetchFile(int id) {
        MediaRecord record = findById(id);
        try {
            MediaObject stored = mediaRepository.fetch(record.getKey())
                    .orElseThrow(() -> new ResourceNotFoundException("Media object not found in storage: " + id));

            String contentType = hasText(record.getContentType()) ? record.getContentType() : stored.getContentType();
            String fileName = hasText(record.getFileName()) ? record.getFileName() : record.getKey();
            return new MediaObject(record.getKey(), fileName, contentType, stored.getContentLength(),
                    stored.getContent());
        } catch (SdkException e) {
            log.error("Failed to fetch media file for id {}: {}", id, e.getMessage());
            throw new MediaStorageException("Could not fetch media file for id " + id, e);
        }
    }

    private static boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}
