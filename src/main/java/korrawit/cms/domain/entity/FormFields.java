package korrawit.cms.domain.entity;

import java.time.Instant;

public class FormFields {

    private Integer id;
    private Integer formId;
    private String fieldKey;
    private String kind;
    private String label;
    private String placeholder;
    private boolean required;
    private Integer maxLength;
    private String requiredError;
    private String invalidError;
    private String maxLengthError;
    private Integer sortOrder;
    private Instant createdAt;
    private Instant updatedAt;

    public FormFields() {
    }

    public FormFields(Integer id, Integer formId, String fieldKey, String kind, String label, String placeholder,
            boolean required, Integer maxLength, String requiredError, String invalidError, String maxLengthError,
            Integer sortOrder, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.formId = formId;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getRequiredError() {
        return requiredError;
    }

    public void setRequiredError(String requiredError) {
        this.requiredError = requiredError;
    }

    public String getInvalidError() {
        return invalidError;
    }

    public void setInvalidError(String invalidError) {
        this.invalidError = invalidError;
    }

    public String getMaxLengthError() {
        return maxLengthError;
    }

    public void setMaxLengthError(String maxLengthError) {
        this.maxLengthError = maxLengthError;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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
}
