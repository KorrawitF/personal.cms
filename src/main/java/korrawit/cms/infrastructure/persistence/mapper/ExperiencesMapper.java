package korrawit.cms.infrastructure.persistence.mapper;

import korrawit.cms.domain.entity.Experiences;

public final class ExperiencesMapper {

    private ExperiencesMapper() {
    }

    public static Experiences toDomain(korrawit.cms.infrastructure.persistence.model.Experiences entity) {
        if (entity == null) {
            return null;
        }
        Integer skillId = entity.getSkill() != null ? entity.getSkill().getId() : null;
        return new Experiences(entity.getId(), skillId, entity.getTitle(), entity.getOrg(), entity.getDetail());
    }

    public static korrawit.cms.infrastructure.persistence.model.Experiences toEntity(Experiences domain,
            korrawit.cms.infrastructure.persistence.model.Skills skill) {
        if (domain == null) {
            return null;
        }
        return new korrawit.cms.infrastructure.persistence.model.Experiences(domain.getId(), skill,
                domain.getTitle(), domain.getOrg(), domain.getDetail());
    }
}
