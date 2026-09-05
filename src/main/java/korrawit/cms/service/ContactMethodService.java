package korrawit.cms.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import korrawit.cms.domain.entity.ContactMethod;
import korrawit.cms.domain.repository.ContactMethodRepository;
import korrawit.cms.error.ResourceNotFoundException;

@Service
public class ContactMethodService {

    private final ContactMethodRepository contactMethodRepository;

    public ContactMethodService(ContactMethodRepository contactMethodRepository) {
        this.contactMethodRepository = contactMethodRepository;
    }

    public List<ContactMethod> findAll() {
        return contactMethodRepository.findAll();
    }

    public ContactMethod findById(int id) {
        return contactMethodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContactMethod not found: " + id));
    }

    public ContactMethod create(ContactMethod contactMethod) {
        Instant now = Instant.now();
        contactMethod.setId(null);
        contactMethod.setCreatedAt(now);
        contactMethod.setUpdatedAt(now);
        return contactMethodRepository.save(contactMethod);
    }

    public ContactMethod update(int id, ContactMethod contactMethod) {
        ContactMethod existing = findById(id);
        contactMethod.setId(id);
        contactMethod.setCreatedAt(existing.getCreatedAt());
        contactMethod.setUpdatedAt(Instant.now());
        return contactMethodRepository.save(contactMethod);
    }

    public void delete(int id) {
        findById(id);
        contactMethodRepository.deleteById(id);
    }
}
