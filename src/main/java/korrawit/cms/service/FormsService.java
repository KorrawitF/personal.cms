package korrawit.cms.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import korrawit.cms.domain.dto.FormSubmissionResult;
import korrawit.cms.domain.dto.MailTemplate;
import korrawit.cms.domain.entity.FormFields;
import korrawit.cms.domain.entity.Forms;
import korrawit.cms.domain.repository.FormsRepository;
import korrawit.cms.error.FormValidationException;
import korrawit.cms.error.ResourceNotFoundException;

@Service
public class FormsService {

    private final FormsRepository formsRepository;
    private final MailService mailService;

    public FormsService(FormsRepository formsRepository, MailService mailService) {
        this.formsRepository = formsRepository;
        this.mailService = mailService;
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

    public MailTemplate getMailTemplate(int id) {
        Forms form = findById(id);
        return new MailTemplate(form.getMailSubjectTemplate(), form.getMailBodyTemplate(), form.getMailSenderName(),
                form.getMailAttachmentMediaId());
    }

    public MailTemplate updateMailTemplate(int id, MailTemplate template) {
        Forms form = findById(id);
        form.setMailSubjectTemplate(template == null ? null : template.subject());
        form.setMailBodyTemplate(template == null ? null : template.body());
        form.setMailSenderName(template == null ? null : template.senderName());
        form.setMailAttachmentMediaId(template == null ? null : template.attachmentMediaId());
        form.setUpdatedAt(Instant.now());
        Forms saved = formsRepository.save(form);
        return new MailTemplate(saved.getMailSubjectTemplate(), saved.getMailBodyTemplate(),
                saved.getMailSenderName(), saved.getMailAttachmentMediaId());
    }

    public void deleteMailTemplate(int id) {
        Forms form = findById(id);
        form.setMailSubjectTemplate(null);
        form.setMailBodyTemplate(null);
        form.setMailSenderName(null);
        form.setMailAttachmentMediaId(null);
        form.setUpdatedAt(Instant.now());
        formsRepository.save(form);
    }

    public FormSubmissionResult submit(String slug, Map<String, String> values) {
        Forms form = formsRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found: " + slug));
        validate(form, values);

        String recipient = resolveRecipient(form, values);
        String subject = renderSubject(form, slug, recipient, values);
        String body = renderBody(form, recipient, values);
        String senderName = renderSenderName(form, recipient, values);
        mailService.send(recipient, subject, body, senderName, form.getMailAttachmentMediaId());

        return new FormSubmissionResult(UUID.randomUUID().toString(), recipient, Instant.now());
    }

    private String resolveRecipient(Forms form, Map<String, String> values) {
        if (form.getFields() != null) {
            for (FormFields field : form.getFields()) {
                if ("email".equalsIgnoreCase(field.getKind())) {
                    String value = values == null ? null : values.get(field.getFieldKey());
                    if (value != null && !value.isBlank()) {
                        return value;
                    }
                }
            }
        }
        throw new IllegalStateException("Form \"" + form.getSlug() + "\" has no email field to send the reply to");
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

    private String renderSubject(Forms form, String slug, String recipient, Map<String, String> values) {
        String template = form.getMailSubjectTemplate();
        if (template == null || template.isBlank()) {
            return "New \"%s\" submission".formatted(slug);
        }
        return renderTemplate(template, form, recipient, values);
    }

    private String renderBody(Forms form, String recipient, Map<String, String> values) {
        String template = form.getMailBodyTemplate();
        if (template == null || template.isBlank()) {
            return buildBody(form, values);
        }
        return renderTemplate(template, form, recipient, values);
    }

    private String renderSenderName(Forms form, String recipient, Map<String, String> values) {
        String template = form.getMailSenderName();
        if (template == null || template.isBlank()) {
            return null;
        }
        return renderTemplate(template, form, recipient, values);
    }

    private String renderTemplate(String template, Forms form, String recipient, Map<String, String> values) {
        String result = template.replace("{{slug}}", form.getSlug())
                .replace("{{recipient}}", recipient == null ? "" : recipient);
        if (form.getFields() != null) {
            StringBuilder fieldsBlock = new StringBuilder();
            for (FormFields field : form.getFields()) {
                String value = values == null ? null : values.get(field.getFieldKey());
                fieldsBlock.append(field.getLabel()).append(": ").append(value == null ? "" : value).append('\n');
                result = result.replace("{{field." + field.getFieldKey() + "}}", value == null ? "" : value);
            }
            result = result.replace("{{fields}}", fieldsBlock.toString().stripTrailing());
        }
        return result;
    }

    private String buildBody(Forms form, Map<String, String> values) {
        StringBuilder body = new StringBuilder("Hi,\n\n")
                .append("You've received a new submission on the \"").append(form.getSlug())
                .append("\" form. Here are the details:\n\n");
        if (form.getFields() != null) {
            for (FormFields field : form.getFields()) {
                String value = values == null ? null : values.get(field.getFieldKey());
                body.append(field.getLabel()).append(": ").append(value == null ? "" : value).append('\n');
            }
        }
        body.append("\nBest,\nKorrawit's Personal CMS");
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
