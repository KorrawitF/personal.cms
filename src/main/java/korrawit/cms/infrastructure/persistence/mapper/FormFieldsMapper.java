package korrawit.cms.infrastructure.persistence.mapper;

import korrawit.cms.domain.entity.FormFields;

public final class FormFieldsMapper {

    private FormFieldsMapper() {
    }

    public static FormFields toDomain(korrawit.cms.infrastructure.persistence.model.FormFields entity) {
        if (entity == null) {
            return null;
        }
        Integer formId = entity.getForm() != null ? entity.getForm().getId() : null;
        return new FormFields(entity.getId(), formId, entity.getFieldKey(), entity.getKind(), entity.getLabel(),
                entity.getPlaceholder(), entity.isRequired(), entity.getMaxLength(), entity.getRequiredError(),
                entity.getInvalidError(), entity.getMaxLengthError(), entity.getSortOrder(), entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public static korrawit.cms.infrastructure.persistence.model.FormFields toEntity(FormFields domain,
            korrawit.cms.infrastructure.persistence.model.Forms formEntity) {
        if (domain == null) {
            return null;
        }
        return new korrawit.cms.infrastructure.persistence.model.FormFields(domain.getId(), formEntity,
                domain.getFieldKey(), domain.getKind(), domain.getLabel(), domain.getPlaceholder(),
                domain.isRequired(), domain.getMaxLength(), domain.getRequiredError(), domain.getInvalidError(),
                domain.getMaxLengthError(), domain.getSortOrder(), domain.getCreatedAt(), domain.getUpdatedAt());
    }
}
