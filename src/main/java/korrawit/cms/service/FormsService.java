package korrawit.cms.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import korrawit.cms.domain.dto.FormSubmissionResult;
import korrawit.cms.domain.entity.FormFields;
import korrawit.cms.domain.entity.Forms;
import korrawit.cms.domain.repository.FormsRepository;
import korrawit.cms.error.FormValidationException;
import korrawit.cms.error.ResourceNotFoundException;

@Service
public class FormsService {

    private final FormsRepository formsRepository;
    private final MailService mailService;
    private final String recipient;

    public FormsService(FormsRepository formsRepository, MailService mailService,
            @Value("${app.mail.to}") String recipient) {
        this.formsRepository = formsRepository;
        this.mailService = mailService;
        this.recipient = recipient;
    }

    public List<Forms> findAll() {
        return formsRepository.findAll();
    }

    public Forms findById(int id) {
        return formsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found: " + id));
    }

    public Forms create(Forms form) {
        Instant now = Instant.now();
        form.setId(null);
        form.setCreatedAt(now);
        form.setUpdatedAt(now);
        stampFieldTimestamps(form, null, now);
        return formsRepository.save(form);
    }

    public Forms update(int id, Forms form) {
        Forms existing = findById(id);
        Instant now = Instant.now();
        form.setId(id);
        form.setCreatedAt(existing.getCreatedAt());
        form.setUpdatedAt(now);
        stampFieldTimestamps(form, existing, now);
        return formsRepository.save(form);
    }

    public void delete(int id) {
        findById(id);
        formsRepository.deleteById(id);
    }

    public FormSubmissionResult submit(String slug, Map<String, String> values) {
        Forms form = formsRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found: " + slug));
        validate(form, values);

        String body = buildBody(form, values);
        mailService.send(recipient, "New \"%s\" submission".formatted(slug), body);

        return new FormSubmissionResult(UUID.randomUUID().toString(), recipient, Instant.now());
    }

    private void validate(Forms form, Map<String, String> values) {
        if (form.getFields() == null) {
            return;
        }
        Map<String, String> errors = new LinkedHashMap<>();
        for (FormFields field : form.getFields()) {
            String value = values == null ? null : values.get(field.getFieldKey());
            if (field.isRequired() && (value == null || value.isBlank())) {
                errors.put(field.getFieldKey(),
                        field.getRequiredError() != null ? field.getRequiredError()
                                : field.getLabel() + " is required");
                continue;
            }
            if (field.getMaxLength() != null && value != null && value.length() > field.getMaxLength()) {
                errors.put(field.getFieldKey(),
                        field.getMaxLengthError() != null ? field.getMaxLengthError()
                                : field.getLabel() + " must be at most " + field.getMaxLength() + " characters");
            }
        }
        if (!errors.isEmpty()) {
            throw new FormValidationException(errors);
        }
    }

    private String buildBody(Forms form, Map<String, String> values) {
        StringBuilder body = new StringBuilder("New submission for form \"").append(form.getSlug()).append("\"\n\n");
        if (form.getFields() != null) {
            for (FormFields field : form.getFields()) {
                String value = values == null ? null : values.get(field.getFieldKey());
                body.append(field.getLabel()).append(": ").append(value == null ? "" : value).append('\n');
            }
        }
        return body.toString();
    }

    private void stampFieldTimestamps(Forms form, Forms existing, Instant now) {
        if (form.getFields() == null) {
            return;
        }
        Map<Integer, FormFields> existingById = existing == null || existing.getFields() == null ? Map.of()
                : Arrays.stream(existing.getFields()).collect(Collectors.toMap(FormFields::getId, f -> f));
        for (FormFields field : form.getFields()) {
            FormFields matched = field.getId() != null ? existingById.get(field.getId()) : null;
            field.setCreatedAt(matched != null ? matched.getCreatedAt() : now);
            field.setUpdatedAt(now);
        }
    }
}
