package korrawit.cms.domain.entity;

import java.time.Instant;

public class Forms {

    private Integer id;
    private String slug;
    private String submitLabel;
    private String sendingLabel;
    private String note;
    private String optionalLabel;
    private String invalidMessage;
    private String failedMessage;
    private String successMessage;
    private String mailSubjectTemplate;
    private String mailBodyTemplate;
    private FormFields[] fields;
    private Instant createdAt;
    private Instant updatedAt;

    public Forms() {
    }

    public Forms(Integer id, String slug, String submitLabel, String sendingLabel, String note, String optionalLabel,
            String invalidMessage, String failedMessage, String successMessage, String mailSubjectTemplate,
            String mailBodyTemplate, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.slug = slug;
        this.submitLabel = submitLabel;
        this.sendingLabel = sendingLabel;
        this.note = note;
        this.optionalLabel = optionalLabel;
        this.invalidMessage = invalidMessage;
        this.failedMessage = failedMessage;
        this.successMessage = successMessage;
        this.mailSubjectTemplate = mailSubjectTemplate;
        this.mailBodyTemplate = mailBodyTemplate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSubmitLabel() {
        return submitLabel;
    }

    public void setSubmitLabel(String submitLabel) {
        this.submitLabel = submitLabel;
    }

    public String getSendingLabel() {
        return sendingLabel;
    }

    public void setSendingLabel(String sendingLabel) {
        this.sendingLabel = sendingLabel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOptionalLabel() {
        return optionalLabel;
    }

    public void setOptionalLabel(String optionalLabel) {
        this.optionalLabel = optionalLabel;
    }

    public String getInvalidMessage() {
        return invalidMessage;
    }

    public void setInvalidMessage(String invalidMessage) {
        this.invalidMessage = invalidMessage;
    }

    public String getFailedMessage() {
        return failedMessage;
    }

    public void setFailedMessage(String failedMessage) {
        this.failedMessage = failedMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getMailSubjectTemplate() {
        return mailSubjectTemplate;
    }

    public void setMailSubjectTemplate(String mailSubjectTemplate) {
        this.mailSubjectTemplate = mailSubjectTemplate;
    }

    public String getMailBodyTemplate() {
        return mailBodyTemplate;
    }

    public void setMailBodyTemplate(String mailBodyTemplate) {
        this.mailBodyTemplate = mailBodyTemplate;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public FormFields[] getFields() {
        return fields;
    }

    public void setFields(FormFields[] fields) {
        this.fields = fields;
    }
}
