package korrawit.cms.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import korrawit.cms.domain.entity.MediaRecord;
import korrawit.cms.domain.repository.MediaRecordRepository;
import korrawit.cms.infrastructure.persistence.mapper.MediaRecordMapper;

@Repository
public class HibernateMediaRecordRepository implements MediaRecordRepository {

    private final SessionFactory sessionFactory;

    public HibernateMediaRecordRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<MediaRecord> findById(int id) {
        return sessionFactory.fromTransaction(session -> Optional
                .ofNullable(session.find(korrawit.cms.infrastructure.persistence.model.MediaRecord.class, id))
                .map(MediaRecordMapper::toDomain));
    }

    @Override
    public List<MediaRecord> findAll() {
        return sessionFactory.fromTransaction(session -> session
                .createSelectionQuery("from MediaRecord", korrawit.cms.infrastructure.persistence.model.MediaRecord.class)
                .getResultList()
                .stream()
                .map(MediaRecordMapper::toDomain)
                .toList());
    }

    @Override
    public MediaRecord save(MediaRecord record) {
        return sessionFactory.fromTransaction(session -> {
            korrawit.cms.infrastructure.persistence.model.MediaRecord merged = session
                    .merge(MediaRecordMapper.toEntity(record));
            return MediaRecordMapper.toDomain(merged);
        });
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.inTransaction(session -> {
            korrawit.cms.infrastructure.persistence.model.MediaRecord entity = session
                    .find(korrawit.cms.infrastructure.persistence.model.MediaRecord.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        });
    }
}
