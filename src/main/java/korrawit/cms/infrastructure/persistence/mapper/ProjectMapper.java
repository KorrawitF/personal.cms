package korrawit.cms.infrastructure.persistence.mapper;

import korrawit.cms.domain.entity.Project;

public final class ProjectMapper {

    private ProjectMapper() {
    }

    public static Project toDomain(korrawit.cms.infrastructure.persistence.model.Project entity) {
        if (entity == null) {
            return null;
        }
        return new Project(entity.getId(), entity.getName(), entity.getRole(), entity.getIcon(), entity.getBanner(),
                entity.getSummary(), entity.getDetail(), entity.getTechStack(), entity.getHighlights(),
                entity.getRepoUrl(), entity.getDemoUrl(), entity.getStatus(), entity.getConfidential(),
                entity.getStartDate(), entity.getEndDate(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

    public static korrawit.cms.infrastructure.persistence.model.Project toEntity(Project domain) {
        if (domain == null) {
            return null;
        }
        return new korrawit.cms.infrastructure.persistence.model.Project(domain.getId(), domain.getName(),
                domain.getRole(), domain.getIcon(), domain.getBanner(), domain.getSummary(), domain.getDetail(),
                domain.getTechStack(), domain.getHighlights(), domain.getRepoUrl(), domain.getDemoUrl(),
                domain.getStatus(), domain.getConfidential(), domain.getStartDate(), domain.getEndDate(),
                domain.getCreatedAt(), domain.getUpdatedAt());
    }
}
