package korrawit.cms.infrastructure.persistence.mapper;

import java.util.Arrays;

import korrawit.cms.domain.entity.Skills;
import korrawit.cms.domain.entity.SkillDomains;

public final class SkillDomainsMapper {

    private SkillDomainsMapper() {
    }

    public static SkillDomains toDomain(korrawit.cms.infrastructure.persistence.model.SkillDomains entity) {
        if (entity == null) {
            return null;
        }
        SkillDomains domain = new SkillDomains(entity.getId(), entity.getName(), entity.getColor(),
                entity.getSummary(), entity.getCreatedAt(), entity.getUpdatedAt());
        if (entity.getSkills() != null) {
            domain.setSkills(entity.getSkills().stream().map(SkillsMapper::toDomain).toArray(Skills[]::new));
        }
        return domain;
    }

    public static korrawit.cms.infrastructure.persistence.model.SkillDomains toEntity(SkillDomains domain) {
        if (domain == null) {
            return null;
        }
        korrawit.cms.infrastructure.persistence.model.SkillDomains entity = new korrawit.cms.infrastructure.persistence.model.SkillDomains(
                domain.getId(), domain.getName(), domain.getColor(), domain.getSummary(), domain.getCreatedAt(),
                domain.getUpdatedAt());
        if (domain.getSkills() != null) {
            entity.setSkills(Arrays.stream(domain.getSkills()).map(skill -> SkillsMapper.toEntity(skill, entity)).toList());
        }
        return entity;
    }
}
