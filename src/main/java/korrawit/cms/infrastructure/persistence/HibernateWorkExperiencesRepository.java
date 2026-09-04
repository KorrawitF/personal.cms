package korrawit.cms.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import korrawit.cms.domain.entity.WorkExperiences;
import korrawit.cms.domain.repository.WorkExperiencesRepository;
import korrawit.cms.infrastructure.persistence.mapper.WorkExperiencesMapper;

@Repository
public class HibernateWorkExperiencesRepository implements WorkExperiencesRepository {

    private final SessionFactory sessionFactory;

    public HibernateWorkExperiencesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<WorkExperiences> findById(int id) {
        return sessionFactory.fromTransaction(session -> Optional
                .ofNullable(session.find(korrawit.cms.infrastructure.persistence.model.WorkExperiences.class, id))
                .map(WorkExperiencesMapper::toDomain));
    }

    @Override
    public List<WorkExperiences> findAll() {
        return sessionFactory.fromTransaction(session -> session
                .createSelectionQuery("from WorkExperiences",
                        korrawit.cms.infrastructure.persistence.model.WorkExperiences.class)
                .getResultList()
                .stream()
                .map(WorkExperiencesMapper::toDomain)
                .toList());
    }

    @Override
    public WorkExperiences save(WorkExperiences workExperience) {
        return sessionFactory.fromTransaction(session -> {
            korrawit.cms.infrastructure.persistence.model.WorkExperiences merged = session
                    .merge(WorkExperiencesMapper.toEntity(workExperience));
            return WorkExperiencesMapper.toDomain(merged);
        });
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.inTransaction(session -> {
            korrawit.cms.infrastructure.persistence.model.WorkExperiences entity = session
                    .find(korrawit.cms.infrastructure.persistence.model.WorkExperiences.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        });
    }
}
