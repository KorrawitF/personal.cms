package korrawit.cms.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import korrawit.cms.domain.entity.Content;
import korrawit.cms.domain.repository.ContentRepository;
import korrawit.cms.error.ResourceNotFoundException;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    public Content findById(int id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found: " + id));
    }

    public Content create(Content content) {
        Instant now = Instant.now();
        content.setId(null);
        content.setCreatedAt(now);
        content.setUpdatedAt(now);
        return contentRepository.save(content);
    }

    public Content update(int id, Content content) {
        Content existing = findById(id);
        content.setId(id);
        content.setCreatedAt(existing.getCreatedAt());
        content.setUpdatedAt(Instant.now());
        return contentRepository.save(content);
    }

    public void delete(int id) {
        findById(id);
        contentRepository.deleteById(id);
    }
}
