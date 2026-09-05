package korrawit.cms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import korrawit.cms.domain.entity.ContactMethod;
import korrawit.cms.service.ContactMethodService;

@RestController
@RequestMapping("/api/contact-methods")
public class ContactMethodController {

    private final ContactMethodService contactMethodService;

    public ContactMethodController(ContactMethodService contactMethodService) {
        this.contactMethodService = contactMethodService;
    }

    @GetMapping
    public List<ContactMethod> findAll() {
        return contactMethodService.findAll();
    }

    @GetMapping("/{id}")
    public ContactMethod findById(@PathVariable int id) {
        return contactMethodService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactMethod create(@RequestBody ContactMethod contactMethod) {
        return contactMethodService.create(contactMethod);
    }

    @PutMapping("/{id}")
    public ContactMethod update(@PathVariable int id, @RequestBody ContactMethod contactMethod) {
        return contactMethodService.update(id, contactMethod);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        contactMethodService.delete(id);
    }
}
