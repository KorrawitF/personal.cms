package korrawit.cms.infrastructure.persistence.mapper;

import korrawit.cms.domain.entity.WorkExperiences;

public final class WorkExperiencesMapper {

    private WorkExperiencesMapper() {
    }

    public static WorkExperiences toDomain(korrawit.cms.infrastructure.persistence.model.WorkExperiences entity) {
        if (entity == null) {
            return null;
        }
        return new WorkExperiences(entity.getId(), entity.getJobTitle(), entity.getCompanyName(), entity.getDetail(),
                entity.getTechStack(), entity.getIcon(), entity.getStartDate(), entity.getEndDate(),
                entity.getCreatedAt(), entity.getUpdatedAt());
    }

    public static korrawit.cms.infrastructure.persistence.model.WorkExperiences toEntity(WorkExperiences domain) {
        if (domain == null) {
            return null;
        }
        return new korrawit.cms.infrastructure.persistence.model.WorkExperiences(domain.getId(),
                domain.getJobTitle(), domain.getCompanyName(), domain.getDetail(), domain.getTechStack(),
                domain.getIcon(), domain.getStartDate(), domain.getEndDate(), domain.getCreatedAt(),
                domain.getUpdatedAt());
    }
}
