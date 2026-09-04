package korrawit.cms.domain.repository;

import java.util.List;
import java.util.Optional;

import korrawit.cms.domain.entity.WorkExperiences;

public interface WorkExperiencesRepository {

    Optional<WorkExperiences> findById(int id);

    List<WorkExperiences> findAll();

    WorkExperiences save(WorkExperiences workExperience);

    void deleteById(int id);
}
