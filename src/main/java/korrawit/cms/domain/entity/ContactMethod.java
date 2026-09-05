package korrawit.cms.domain.entity;

import java.time.Instant;

public class ContactMethod {

    private Integer id;
    private String slug;
    private String name;
    private String handle;
    private String summary;
    private String detail;
    private String status;
    private String type;
    private String href;
    private Integer sortOrder;
    private Instant createdAt;
    private Instant updatedAt;

    public ContactMethod() {
    }

    public ContactMethod(Integer id, String slug, String name, String handle, String summary, String detail,
            String status, String type, String href, Integer sortOrder, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.handle = handle;
        this.summary = summary;
        this.detail = detail;
        this.status = status;
        this.type = type;
        this.href = href;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
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
