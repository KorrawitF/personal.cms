package korrawit.cms.infrastructure.persistence.mapper;

import korrawit.cms.domain.entity.MailTransaction;

public final class MailTransactionMapper {

    private MailTransactionMapper() {
    }

    public static MailTransaction toDomain(korrawit.cms.infrastructure.persistence.model.MailTransaction entity) {
        if (entity == null) {
            return null;
        }
        return new MailTransaction(entity.getId(), entity.getReceiverHash(), entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public static korrawit.cms.infrastructure.persistence.model.MailTransaction toEntity(MailTransaction domain) {
        if (domain == null) {
            return null;
        }
        return new korrawit.cms.infrastructure.persistence.model.MailTransaction(domain.getId(),
                domain.getReceiverHash(), domain.getCreatedAt(), domain.getUpdatedAt());
    }
}
