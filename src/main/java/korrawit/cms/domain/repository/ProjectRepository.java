package korrawit.cms.domain.repository;

import java.util.List;
import java.util.Optional;

import korrawit.cms.domain.entity.Project;

public interface ProjectRepository {

    Optional<Project> findById(int id);

    List<Project> findAll();

    Project save(Project project);

    void deleteById(int id);
}
