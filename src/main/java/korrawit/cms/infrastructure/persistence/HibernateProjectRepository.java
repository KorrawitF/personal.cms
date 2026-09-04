package korrawit.cms.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import korrawit.cms.domain.entity.Project;
import korrawit.cms.domain.repository.ProjectRepository;
import korrawit.cms.infrastructure.persistence.mapper.ProjectMapper;

@Repository
public class HibernateProjectRepository implements ProjectRepository {

    private final SessionFactory sessionFactory;

    public HibernateProjectRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Project> findById(int id) {
        return sessionFactory.fromTransaction(session -> Optional
                .ofNullable(session.find(korrawit.cms.infrastructure.persistence.model.Project.class, id))
                .map(ProjectMapper::toDomain));
    }

    @Override
    public List<Project> findAll() {
        return sessionFactory.fromTransaction(session -> session
                .createSelectionQuery("from Project", korrawit.cms.infrastructure.persistence.model.Project.class)
                .getResultList()
                .stream()
                .map(ProjectMapper::toDomain)
                .toList());
    }

    @Override
    public Project save(Project project) {
        return sessionFactory.fromTransaction(session -> {
            korrawit.cms.infrastructure.persistence.model.Project merged = session
                    .merge(ProjectMapper.toEntity(project));
            return ProjectMapper.toDomain(merged);
        });
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.inTransaction(session -> {
            korrawit.cms.infrastructure.persistence.model.Project entity = session
                    .find(korrawit.cms.infrastructure.persistence.model.Project.class, id);
            if (entity != null) {
                session.remove(entity);
            }
        });
    }
}
