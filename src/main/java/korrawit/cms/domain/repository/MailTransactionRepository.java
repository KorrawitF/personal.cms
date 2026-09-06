package korrawit.cms.domain.repository;

import korrawit.cms.domain.entity.MailTransaction;

public interface MailTransactionRepository {

    MailTransaction save(MailTransaction mailTransaction);
}
