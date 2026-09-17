package korrawit.cms.controller;

import java.util.List;
import java.util.Map;

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

import korrawit.cms.domain.dto.FormSubmissionResult;
import korrawit.cms.domain.dto.MailTemplate;
import korrawit.cms.domain.entity.Forms;
import korrawit.cms.service.FormsService;

@RestController
@RequestMapping("/api/forms")
public class FormsController {

    private final FormsService formsService;

    public FormsController(FormsService formsService) {
        this.formsService = formsService;
    }

    @GetMapping
    public List<Forms> findAll() {
        return formsService.findAll();
    }

    @GetMapping("/{id}")
    public Forms findById(@PathVariable int id) {
        return formsService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Forms create(@RequestBody Forms form) {
        return formsService.create(form);
    }

    @PutMapping("/{id}")
    public Forms update(@PathVariable int id, @RequestBody Forms form) {
        return formsService.update(id, form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        formsService.delete(id);
    }

    @PostMapping("/{slug}/submissions")
    @ResponseStatus(HttpStatus.CREATED)
    public FormSubmissionResult submit(@PathVariable String slug, @RequestBody Map<String, String> values) {
        return formsService.submit(slug, values);
    }

    @GetMapping("/{id}/mail-template")
    public MailTemplate getMailTemplate(@PathVariable int id) {
        return formsService.getMailTemplate(id);
    }

    @PutMapping("/{id}/mail-template")
    public MailTemplate updateMailTemplate(@PathVariable int id, @RequestBody MailTemplate template) {
        return formsService.updateMailTemplate(id, template);
    }

    @DeleteMapping("/{id}/mail-template")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMailTemplate(@PathVariable int id) {
        formsService.deleteMailTemplate(id);
    }
}
