package korrawit.cms.infrastructure.persistence.mapper;

import korrawit.cms.domain.entity.SkillDomains;

public final class SkillDomainsMapper {

    private SkillDomainsMapper() {
    }

    public static SkillDomains toDomain(korrawit.cms.infrastructure.persistence.model.SkillDomains entity) {
        if (entity == null) {
            return null;
        }
        return new SkillDomains(entity.getId(), entity.getName(), entity.getColor(), entity.getSummary(),
                entity.getCreatedAt(), entity.getUpdatedAt());
    }

    public static korrawit.cms.infrastructure.persistence.model.SkillDomains toEntity(SkillDomains domain) {
        if (domain == null) {
            return null;
        }
        return new korrawit.cms.infrastructure.persistence.model.SkillDomains(domain.getId(), domain.getName(),
                domain.getColor(), domain.getSummary(), domain.getCreatedAt(), domain.getUpdatedAt());
    }
}
