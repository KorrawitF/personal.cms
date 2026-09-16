package korrawit.cms.domain.repository;

import java.util.Optional;

import korrawit.cms.domain.entity.MediaObject;

public interface MediaRepository {

    Optional<MediaObject> fetch(String key);
}
