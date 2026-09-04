package korrawit.cms.domain.repository;

import java.util.List;
import java.util.Optional;

import korrawit.cms.domain.entity.SkillDomains;

public interface SkillDomainsRepository {

    Optional<SkillDomains> findById(int id);

    List<SkillDomains> findAll();

    SkillDomains save(SkillDomains skillDomain);

    void deleteById(int id);
}
