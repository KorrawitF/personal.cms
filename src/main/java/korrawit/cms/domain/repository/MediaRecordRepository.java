package korrawit.cms.domain.repository;

import java.util.List;
import java.util.Optional;

import korrawit.cms.domain.entity.MediaRecord;

public interface MediaRecordRepository {

    Optional<MediaRecord> findById(int id);

    List<MediaRecord> findAll();

    MediaRecord save(MediaRecord record);

    void deleteById(int id);
}
