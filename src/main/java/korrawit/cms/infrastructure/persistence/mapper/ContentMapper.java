package korrawit.cms.infrastructure.persistence.mapper;

import korrawit.cms.domain.entity.Content;

public final class ContentMapper {

    private ContentMapper() {
    }

    public static Content toDomain(korrawit.cms.infrastructure.persistence.model.Content entity) {
        if (entity == null) {
            return null;
        }
        return new Content(entity.getId(), entity.getPage(), entity.getContent(), entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public static korrawit.cms.infrastructure.persistence.model.Content toEntity(Content domain) {
        if (domain == null) {
            return null;
        }
        return new korrawit.cms.infrastructure.persistence.model.Content(domain.getId(), domain.getPage(),
                domain.getContent(), domain.getCreatedAt(), domain.getUpdatedAt());
    }
}
