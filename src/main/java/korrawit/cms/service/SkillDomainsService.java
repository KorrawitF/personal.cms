package korrawit.cms.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import korrawit.cms.domain.entity.SkillDomains;
import korrawit.cms.domain.entity.Skills;
import korrawit.cms.domain.repository.SkillDomainsRepository;
import korrawit.cms.error.ResourceNotFoundException;

@Service
public class SkillDomainsService {

    private final SkillDomainsRepository skillDomainsRepository;

    public SkillDomainsService(SkillDomainsRepository skillDomainsRepository) {
        this.skillDomainsRepository = skillDomainsRepository;
    }

    public List<SkillDomains> findAll() {
        return skillDomainsRepository.findAll();
    }

    public SkillDomains findById(int id) {
        return skillDomainsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill domain not found: " + id));
    }

    public SkillDomains create(SkillDomains skillDomain) {
        Instant now = Instant.now();
        skillDomain.setId(null);
        skillDomain.setCreatedAt(now);
        skillDomain.setUpdatedAt(now);
        stampSkillTimestamps(skillDomain, null, now);
        return skillDomainsRepository.save(skillDomain);
    }

    public SkillDomains update(int id, SkillDomains skillDomain) {
        SkillDomains existing = findById(id);
        Instant now = Instant.now();
        skillDomain.setId(id);
        skillDomain.setCreatedAt(existing.getCreatedAt());
        skillDomain.setUpdatedAt(now);
        stampSkillTimestamps(skillDomain, existing, now);
        return skillDomainsRepository.save(skillDomain);
    }

    public void delete(int id) {
        findById(id);
        skillDomainsRepository.deleteById(id);
    }

    private void stampSkillTimestamps(SkillDomains skillDomain, SkillDomains existing, Instant now) {
        if (skillDomain.getSkills() == null) {
            return;
        }
        Map<Integer, Skills> existingById = existing == null || existing.getSkills() == null ? Map.of()
                : Arrays.stream(existing.getSkills()).collect(Collectors.toMap(Skills::getId, s -> s));
        for (Skills skill : skillDomain.getSkills()) {
            Skills matched = skill.getId() != null ? existingById.get(skill.getId()) : null;
            skill.setCreatedAt(matched != null ? matched.getCreatedAt() : now);
            skill.setUpdatedAt(now);
        }
    }
}
