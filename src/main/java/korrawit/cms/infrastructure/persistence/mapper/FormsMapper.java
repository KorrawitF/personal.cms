package korrawit.cms.infrastructure.persistence.mapper;

import java.util.Arrays;

import korrawit.cms.domain.entity.FormFields;
import korrawit.cms.domain.entity.Forms;

public final class FormsMapper {

    private FormsMapper() {
    }

    public static Forms toDomain(korrawit.cms.infrastructure.persistence.model.Forms entity) {
        if (entity == null) {
            return null;
        }
        Forms domain = new Forms(entity.getId(), entity.getSlug(), entity.getSubmitLabel(), entity.getSendingLabel(),
                entity.getNote(), entity.getOptionalLabel(), entity.getInvalidMessage(), entity.getFailedMessage(),
                entity.getSuccessMessage(), entity.getMailSubjectTemplate(), entity.getMailBodyTemplate(),
                entity.getMailSenderName(), entity.getCreatedAt(), entity.getUpdatedAt());
        if (entity.getFields() != null) {
            domain.setFields(entity.getFields().stream().map(FormFieldsMapper::toDomain).toArray(FormFields[]::new));
        }
        return domain;
    }

    public static korrawit.cms.infrastructure.persistence.model.Forms toEntity(Forms domain) {
        if (domain == null) {
            return null;
        }
        korrawit.cms.infrastructure.persistence.model.Forms entity = new korrawit.cms.infrastructure.persistence.model.Forms(
                domain.getId(), domain.getSlug(), domain.getSubmitLabel(), domain.getSendingLabel(),
                domain.getNote(), domain.getOptionalLabel(), domain.getInvalidMessage(), domain.getFailedMessage(),
                domain.getSuccessMessage(), domain.getMailSubjectTemplate(), domain.getMailBodyTemplate(),
                domain.getMailSenderName(), domain.getCreatedAt(), domain.getUpdatedAt());
        if (domain.getFields() != null) {
            entity.setFields(
                    Arrays.stream(domain.getFields()).map(field -> FormFieldsMapper.toEntity(field, entity)).toList());
        }
        return entity;
    }
}
