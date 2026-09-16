package korrawit.cms.infrastructure.persistence.mapper;

import korrawit.cms.domain.entity.MediaRecord;

public final class MediaRecordMapper {

    private MediaRecordMapper() {
    }

    public static MediaRecord toDomain(korrawit.cms.infrastructure.persistence.model.MediaRecord entity) {
        if (entity == null) {
            return null;
        }
        return new MediaRecord(entity.getId(), entity.getKey(), entity.getFileName(), entity.getContentType(),
                entity.getSize(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

    public static korrawit.cms.infrastructure.persistence.model.MediaRecord toEntity(MediaRecord domain) {
        if (domain == null) {
            return null;
        }
        return new korrawit.cms.infrastructure.persistence.model.MediaRecord(domain.getId(), domain.getKey(),
                domain.getFileName(), domain.getContentType(), domain.getSize(), domain.getCreatedAt(),
                domain.getUpdatedAt());
    }
}
