package korrawit.cms.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import korrawit.cms.domain.entity.WorkExperiences;
import korrawit.cms.domain.repository.WorkExperiencesRepository;
import korrawit.cms.error.ResourceNotFoundException;

@Service
public class WorkExperiencesService {

    private final WorkExperiencesRepository workExperiencesRepository;

    public WorkExperiencesService(WorkExperiencesRepository workExperiencesRepository) {
        this.workExperiencesRepository = workExperiencesRepository;
    }

    public List<WorkExperiences> findAll() {
        return workExperiencesRepository.findAll();
    }

    public WorkExperiences findById(int id) {
        return workExperiencesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work experience not found: " + id));
    }

    public WorkExperiences create(WorkExperiences workExperience) {
        Instant now = Instant.now();
        workExperience.setId(null);
        workExperience.setCreatedAt(now);
        workExperience.setUpdatedAt(now);
        return workExperiencesRepository.save(workExperience);
    }

    public WorkExperiences update(int id, WorkExperiences workExperience) {
        WorkExperiences existing = findById(id);
        workExperience.setId(id);
        workExperience.setCreatedAt(existing.getCreatedAt());
        workExperience.setUpdatedAt(Instant.now());
        return workExperiencesRepository.save(workExperience);
    }

    public void delete(int id) {
        findById(id);
        workExperiencesRepository.deleteById(id);
    }
}
