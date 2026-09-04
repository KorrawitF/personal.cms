package korrawit.cms.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import korrawit.cms.domain.entity.Project;
import korrawit.cms.domain.repository.ProjectRepository;
import korrawit.cms.error.ResourceNotFoundException;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found: " + id));
    }

    public Project create(Project project) {
        Instant now = Instant.now();
        project.setId(null);
        project.setCreatedAt(now);
        project.setUpdatedAt(now);
        return projectRepository.save(project);
    }

    public Project update(int id, Project project) {
        Project existing = findById(id);
        project.setId(id);
        project.setCreatedAt(existing.getCreatedAt());
        project.setUpdatedAt(Instant.now());
        return projectRepository.save(project);
    }

    public void delete(int id) {
        findById(id);
        projectRepository.deleteById(id);
    }
}
