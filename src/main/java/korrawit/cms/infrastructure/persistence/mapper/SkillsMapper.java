package korrawit.cms.infrastructure.persistence.mapper;

import java.util.Arrays;

import korrawit.cms.domain.entity.Experiences;
import korrawit.cms.domain.entity.Skills;

public final class SkillsMapper {

    private SkillsMapper() {
    }

    public static Skills toDomain(korrawit.cms.infrastructure.persistence.model.Skills entity) {
        if (entity == null) {
            return null;
        }
        Integer domainId = entity.getDomain() != null ? entity.getDomain().getId() : null;
        Experiences[] experiences = entity.getExperiences() == null ? new Experiences[0]
                : entity.getExperiences().stream().map(ExperiencesMapper::toDomain).toArray(Experiences[]::new);
        return new Skills(entity.getId(), entity.getName(), domainId, entity.getLevel(), entity.getSummary(),
                experiences, entity.getTools(), entity.getUseCases(), entity.getParent(), entity.getIcon(),
                entity.getCreatedAt(), entity.getUpdatedAt());
    }

    public static korrawit.cms.infrastructure.persistence.model.Skills toEntity(Skills domain,
            korrawit.cms.infrastructure.persistence.model.SkillDomains domainEntity) {
        if (domain == null) {
            return null;
        }
        korrawit.cms.infrastructure.persistence.model.Skills entity = new korrawit.cms.infrastructure.persistence.model.Skills(
                domain.getId(), domain.getName(), domainEntity, domain.getLevel(), domain.getSummary(),
                domain.getTools(), domain.getUseCases(), domain.getParent(), domain.getIcon(), domain.getCreatedAt(),
                domain.getUpdatedAt());
        if (domain.getExperiences() != null) {
            entity.setExperiences(
                    Arrays.stream(domain.getExperiences()).map(exp -> ExperiencesMapper.toEntity(exp, entity)).toList());
        }
        return entity;
    }
}
