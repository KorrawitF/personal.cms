package korrawit.cms.domain.entity;

import java.time.Instant;
import java.util.Map;

public class Content {

    private Integer id;
    private String page;
    private Map<String, Object> content;
    private Instant createdAt;
    private Instant updatedAt;

    public Content() {
    }

    public Content(Integer id, String page, Map<String, Object> content, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.page = page;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
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
