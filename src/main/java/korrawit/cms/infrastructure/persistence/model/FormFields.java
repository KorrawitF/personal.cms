package korrawit.cms.infrastructure.persistence.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "form_fields", uniqueConstraints = {
        @UniqueConstraint(name = "uk_form_fields_form_field_key", columnNames = { "form_id", "field_key" })
})
public class FormFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Forms form;

    @Column(name = "field_key", nullable = false)
    private String fieldKey;

    @Column(nullable = false)
    private String kind;

    @Column(nullable = false)
    private String label;

    private String placeholder;

    @Column(nullable = false)
    private boolean required;

    @Column(name = "max_length")
    private Integer maxLength;

    @Column(name = "required_error")
    private String requiredError;

    @Column(name = "invalid_error")
    private String invalidError;

    @Column(name = "max_length_error")
    private String maxLengthError;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected FormFields() {
    }

    public FormFields(Integer id, Forms form, String fieldKey, String kind, String label, String placeholder,
            boolean required, Integer maxLength, String requiredError, String invalidError, String maxLengthError,
            Integer sortOrder, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.form = form;
        this.fieldKey = fieldKey;
        this.kind = kind;
        this.label = label;
        this.placeholder = placeholder;
        this.required = required;
        this.maxLength = maxLength;
        this.requiredError = requiredError;
        this.invalidError = invalidError;
        this.maxLengthError = maxLengthError;
        this.sortOrder = sortOrder;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public Forms getForm() {
        return form;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public String getKind() {
        return kind;
    }

    public String getLabel() {
        return label;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public boolean isRequired() {
        return required;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public String getRequiredError() {
        return requiredError;
    }

    public String getInvalidError() {
        return invalidError;
    }

    public String getMaxLengthError() {
        return maxLengthError;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
