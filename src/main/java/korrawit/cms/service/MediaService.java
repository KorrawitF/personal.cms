package korrawit.cms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import korrawit.cms.domain.entity.MediaObject;
import korrawit.cms.domain.repository.MediaRepository;
import korrawit.cms.error.MediaStorageException;
import korrawit.cms.error.ResourceNotFoundException;
import software.amazon.awssdk.core.exception.SdkException;

@Service
public class MediaService {

    private static final Logger log = LoggerFactory.getLogger(MediaService.class);

    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public MediaObject fetch(String key) {
        try {
            return mediaRepository.fetch(key)
                    .orElseThrow(() -> new ResourceNotFoundException("Media not found: " + key));
        } catch (SdkException e) {
            log.error("Failed to fetch media {}: {}", key, e.getMessage());
            throw new MediaStorageException("Could not fetch media " + key, e);
        }
    }
}
