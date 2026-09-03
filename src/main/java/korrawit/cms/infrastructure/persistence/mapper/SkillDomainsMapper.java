package korrawit.cms.infrastructure.persistence.mapper;

import korrawit.cms.domain.model.SkillDomains;
import korrawit.cms.infrastructure.persistence.entity.SkillDomainsEntity;

public final class SkillDomainsMapper {

    private SkillDomainsMapper() {
    }

    public static SkillDomains toDomain(SkillDomainsEntity entity) {
        if (entity == null) {
            return null;
        }
        return new SkillDomains(entity.getId(), entity.getName(), entity.getColor(), entity.getSummary(),
                entity.getCreatedAt(), entity.getUpdatedAt());
    }

    public static SkillDomainsEntity toEntity(SkillDomains domain) {
        if (domain == null) {
            return null;
        }
        return new SkillDomainsEntity(domain.getId(), domain.getName(), domain.getColor(), domain.getSummary(),
                domain.getCreatedAt(), domain.getUpdatedAt());
    }
}
