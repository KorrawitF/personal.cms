package korrawit.cms.domain.repository;

import java.util.List;
import java.util.Optional;

import korrawit.cms.domain.entity.Content;

public interface ContentRepository {

    Optional<Content> findById(int id);

    List<Content> findAll();

    Content save(Content content);

    void deleteById(int id);
}
