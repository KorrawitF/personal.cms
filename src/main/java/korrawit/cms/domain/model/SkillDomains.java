package korrawit.cms.domain.model;

import java.time.Instant;

public class SkillDomains {

    private Integer id;
    private String name;
    private String color;
    private String summary;
    private Instant createdAt;
    private Instant updatedAt;

    public SkillDomains() {
    }

    public SkillDomains(Integer id, String name, String color, String summary, Instant createdAt,
            Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.summary = summary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
