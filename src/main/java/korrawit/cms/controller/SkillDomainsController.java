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

import korrawit.cms.domain.entity.SkillDomains;
import korrawit.cms.service.SkillDomainsService;

@RestController
@RequestMapping("/api/skill-domains")
public class SkillDomainsController {

    private final SkillDomainsService skillDomainsService;

    public SkillDomainsController(SkillDomainsService skillDomainsService) {
        this.skillDomainsService = skillDomainsService;
    }

    @GetMapping
    public List<SkillDomains> findAll() {
        return skillDomainsService.findAll();
    }

    @GetMapping("/{id}")
    public SkillDomains findById(@PathVariable int id) {
        return skillDomainsService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillDomains create(@RequestBody SkillDomains skillDomain) {
        return skillDomainsService.create(skillDomain);
    }

    @PutMapping("/{id}")
    public SkillDomains update(@PathVariable int id, @RequestBody SkillDomains skillDomain) {
        return skillDomainsService.update(id, skillDomain);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        skillDomainsService.delete(id);
    }
}
