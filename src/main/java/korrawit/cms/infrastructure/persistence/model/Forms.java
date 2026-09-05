package korrawit.cms.infrastructure.persistence.model;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "forms")
public class Forms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(name = "submit_label", nullable = false)
    private String submitLabel;

    @Column(name = "sending_label", nullable = false)
    private String sendingLabel;

    private String note;

    @Column(name = "optional_label", nullable = false)
    private String optionalLabel;

    @Column(name = "invalid_message", nullable = false)
    private String invalidMessage;

    @Column(name = "failed_message", nullable = false)
    private String failedMessage;

    @Column(name = "success_message", nullable = false)
    private String successMessage;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormFields> fields;

    protected Forms() {
    }

    public Forms(Integer id, String slug, String submitLabel, String sendingLabel, String note, String optionalLabel,
            String invalidMessage, String failedMessage, String successMessage, Instant createdAt,
            Instant updatedAt) {
        this.id = id;
        this.slug = slug;
        this.submitLabel = submitLabel;
        this.sendingLabel = sendingLabel;
        this.note = note;
        this.optionalLabel = optionalLabel;
        this.invalidMessage = invalidMessage;
        this.failedMessage = failedMessage;
        this.successMessage = successMessage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
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

    public List<FormFields> getFields() {
        return fields;
    }

    public void setFields(List<FormFields> fields) {
        this.fields = fields;
    }
}
