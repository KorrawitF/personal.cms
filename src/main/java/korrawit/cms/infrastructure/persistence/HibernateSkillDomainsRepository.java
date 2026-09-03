package korrawit.cms.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import korrawit.cms.domain.model.SkillDomains;
import korrawit.cms.domain.repository.SkillDomainsRepository;
import korrawit.cms.infrastructure.persistence.entity.SkillDomainsEntity;
import korrawit.cms.infrastructure.persistence.mapper.SkillDomainsMapper;

@Repository
public class HibernateSkillDomainsRepository implements SkillDomainsRepository {

    private final SessionFactory sessionFactory;

    public HibernateSkillDomainsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<SkillDomains> findById(int id) {
        return sessionFactory.fromTransaction(session -> Optional
                .ofNullable(session.find(SkillDomainsEntity.class, id))
                .map(SkillDomainsMapper::toDomain));
    }

    @Override
    public List<SkillDomains> findAll() {
        return sessionFactory.fromTransaction(session -> session
                .createSelectionQuery("from SkillDomainsEntity", SkillDomainsEntity.class)
                .getResultList()
                .stream()
                .map(SkillDomainsMapper::toDomain)
                .toList());
    }

    @Override
    public SkillDomains save(SkillDomains skillDomain) {
        return sessionFactory.fromTransaction(session -> {
            SkillDomainsEntity merged = session.merge(SkillDomainsMapper.toEntity(skillDomain));
            return SkillDomainsMapper.toDomain(merged);
        });
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.inTransaction(session -> {
            SkillDomainsEntity entity = session.find(SkillDomainsEntity.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        });
    }
}
