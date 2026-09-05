package korrawit.cms.domain.entity;

import java.time.Instant;

public class Skills {
    private Integer id;
    private String name;
    private Integer domainId;
    private Integer level;
    private String summary;
    private Experiences[] experiences;
    private String[] tools;
    private String[] useCases;
    private String parent;
    private String icon;
    private Instant createdAt;
    private Instant updatedAt;

    public Skills() {
    }

    public Skills(Integer id, String name, Integer domainId, Integer level, String summary, Experiences[] experiences,
            String[] tools, String[] useCases, String parent, String icon, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.domainId = domainId;
        this.level = level;
        this.summary = summary;
        this.experiences = experiences;
        this.tools = tools;
        this.useCases = useCases;
        this.parent = parent;
        this.icon = icon;
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

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Experiences[] getExperiences() {
        return experiences;
    }

    public void setExperiences(Experiences[] experiences) {
        this.experiences = experiences;
    }

    public String[] getTools() {
        return tools;
    }

    public void setTools(String[] tools) {
        this.tools = tools;
    }

    public String[] getUseCases() {
        return useCases;
    }

    public void setUseCases(String[] useCases) {
        this.useCases = useCases;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
