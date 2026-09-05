package korrawit.cms.infrastructure.persistence.mapper;

import korrawit.cms.domain.entity.ContactMethod;

public final class ContactMethodMapper {

    private ContactMethodMapper() {
    }

    public static ContactMethod toDomain(korrawit.cms.infrastructure.persistence.model.ContactMethod entity) {
        if (entity == null) {
            return null;
        }
        return new ContactMethod(entity.getId(), entity.getSlug(), entity.getName(), entity.getHandle(),
                entity.getSummary(), entity.getDetail(), entity.getStatus(), entity.getType(), entity.getHref(),
                entity.getSortOrder(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

    public static korrawit.cms.infrastructure.persistence.model.ContactMethod toEntity(ContactMethod domain) {
        if (domain == null) {
            return null;
        }
        return new korrawit.cms.infrastructure.persistence.model.ContactMethod(domain.getId(), domain.getSlug(),
                domain.getName(), domain.getHandle(), domain.getSummary(), domain.getDetail(), domain.getStatus(),
                domain.getType(), domain.getHref(), domain.getSortOrder(), domain.getCreatedAt(),
                domain.getUpdatedAt());
    }
}
