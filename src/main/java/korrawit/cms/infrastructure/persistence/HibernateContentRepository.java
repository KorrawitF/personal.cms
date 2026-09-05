package korrawit.cms.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import korrawit.cms.domain.entity.Content;
import korrawit.cms.domain.repository.ContentRepository;
import korrawit.cms.infrastructure.persistence.mapper.ContentMapper;

@Repository
public class HibernateContentRepository implements ContentRepository {

    private final SessionFactory sessionFactory;

    public HibernateContentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Content> findById(int id) {
        return sessionFactory.fromTransaction(session -> Optional
                .ofNullable(session.find(korrawit.cms.infrastructure.persistence.model.Content.class, id))
                .map(ContentMapper::toDomain));
    }

    @Override
    public List<Content> findAll() {
        return sessionFactory.fromTransaction(session -> session
                .createSelectionQuery("from Content", korrawit.cms.infrastructure.persistence.model.Content.class)
                .getResultList()
                .stream()
                .map(ContentMapper::toDomain)
                .toList());
    }

    @Override
    public Content save(Content content) {
        return sessionFactory.fromTransaction(session -> {
            korrawit.cms.infrastructure.persistence.model.Content merged = session
                    .merge(ContentMapper.toEntity(content));
            return ContentMapper.toDomain(merged);
        });
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.inTransaction(session -> {
            korrawit.cms.infrastructure.persistence.model.Content entity = session
                    .find(korrawit.cms.infrastructure.persistence.model.Content.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        });
    }
}
