package korrawit.cms.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import korrawit.cms.domain.entity.Forms;
import korrawit.cms.domain.repository.FormsRepository;
import korrawit.cms.infrastructure.persistence.mapper.FormsMapper;

@Repository
public class HibernateFormsRepository implements FormsRepository {

    private final SessionFactory sessionFactory;

    public HibernateFormsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Forms> findById(int id) {
        return sessionFactory.fromTransaction(session -> Optional
                .ofNullable(session.find(korrawit.cms.infrastructure.persistence.model.Forms.class, id))
                .map(FormsMapper::toDomain));
    }

    @Override
    public Optional<Forms> findBySlug(String slug) {
        return sessionFactory.fromTransaction(session -> session
                .createSelectionQuery("from Forms where slug = :slug",
                        korrawit.cms.infrastructure.persistence.model.Forms.class)
                .setParameter("slug", slug)
                .uniqueResultOptional()
                .map(FormsMapper::toDomain));
    }

    @Override
    public List<Forms> findAll() {
        return sessionFactory.fromTransaction(session -> session
                .createSelectionQuery("from Forms", korrawit.cms.infrastructure.persistence.model.Forms.class)
                .getResultList()
                .stream()
                .map(FormsMapper::toDomain)
                .toList());
    }

    @Override
    public Forms save(Forms form) {
        return sessionFactory.fromTransaction(session -> {
            korrawit.cms.infrastructure.persistence.model.Forms merged = session.merge(FormsMapper.toEntity(form));
            return FormsMapper.toDomain(merged);
        });
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.inTransaction(session -> {
            korrawit.cms.infrastructure.persistence.model.Forms entity = session
                    .find(korrawit.cms.infrastructure.persistence.model.Forms.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        });
    }
}
