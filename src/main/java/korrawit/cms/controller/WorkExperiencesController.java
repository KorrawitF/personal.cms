package korrawit.cms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import korrawit.cms.domain.entity.WorkExperiences;
import korrawit.cms.service.WorkExperiencesService;

@RestController
@RequestMapping("/api/work-experiences")
public class WorkExperiencesController {

    private final WorkExperiencesService workExperiencesService;

    public WorkExperiencesController(WorkExperiencesService workExperiencesService) {
        this.workExperiencesService = workExperiencesService;
    }

    @GetMapping
    public List<WorkExperiences> findAll() {
        return workExperiencesService.findAll();
    }

    @GetMapping("/{id}")
    public WorkExperiences findById(@PathVariable int id) {
        return workExperiencesService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkExperiences create(@RequestBody WorkExperiences workExperience) {
        return workExperiencesService.create(workExperience);
    }

    @PutMapping("/{id}")
    public WorkExperiences update(@PathVariable int id, @RequestBody WorkExperiences workExperience) {
        return workExperiencesService.update(id, workExperience);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        workExperiencesService.delete(id);
    }
}
