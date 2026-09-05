package korrawit.cms.domain.repository;

import java.util.List;
import java.util.Optional;

import korrawit.cms.domain.entity.ContactMethod;

public interface ContactMethodRepository {

    Optional<ContactMethod> findById(int id);

    List<ContactMethod> findAll();

    ContactMethod save(ContactMethod contactMethod);

    void deleteById(int id);
}
