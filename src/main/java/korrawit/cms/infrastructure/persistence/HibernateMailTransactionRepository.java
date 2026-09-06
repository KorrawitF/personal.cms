package korrawit.cms.infrastructure.persistence;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import korrawit.cms.domain.entity.MailTransaction;
import korrawit.cms.domain.repository.MailTransactionRepository;
import korrawit.cms.infrastructure.persistence.mapper.MailTransactionMapper;

@Repository
public class HibernateMailTransactionRepository implements MailTransactionRepository {

    private final SessionFactory sessionFactory;

    public HibernateMailTransactionRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MailTransaction save(MailTransaction mailTransaction) {
        return sessionFactory.fromTransaction(session -> {
            korrawit.cms.infrastructure.persistence.model.MailTransaction merged = session
                    .merge(MailTransactionMapper.toEntity(mailTransaction));
            return MailTransactionMapper.toDomain(merged);
        });
    }
}
