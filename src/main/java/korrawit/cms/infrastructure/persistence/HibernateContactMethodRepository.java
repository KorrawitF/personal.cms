package korrawit.cms.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import korrawit.cms.domain.entity.ContactMethod;
import korrawit.cms.domain.repository.ContactMethodRepository;
import korrawit.cms.infrastructure.persistence.mapper.ContactMethodMapper;

@Repository
public class HibernateContactMethodRepository implements ContactMethodRepository {

    private final SessionFactory sessionFactory;

    public HibernateContactMethodRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<ContactMethod> findById(int id) {
        return sessionFactory.fromTransaction(session -> Optional
                .ofNullable(session.find(korrawit.cms.infrastructure.persistence.model.ContactMethod.class, id))
                .map(ContactMethodMapper::toDomain));
    }

    @Override
    public List<ContactMethod> findAll() {
        return sessionFactory.fromTransaction(session -> session
                .createSelectionQuery("from ContactMethod order by sortOrder",
                        korrawit.cms.infrastructure.persistence.model.ContactMethod.class)
                .getResultList()
                .stream()
                .map(ContactMethodMapper::toDomain)
                .toList());
    }

    @Override
    public ContactMethod save(ContactMethod contactMethod) {
        return sessionFactory.fromTransaction(session -> {
            korrawit.cms.infrastructure.persistence.model.ContactMethod merged = session
                    .merge(ContactMethodMapper.toEntity(contactMethod));
            return ContactMethodMapper.toDomain(merged);
        });
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.inTransaction(session -> {
            korrawit.cms.infrastructure.persistence.model.ContactMethod entity = session
                    .find(korrawit.cms.infrastructure.persistence.model.ContactMethod.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        });
    }
}
