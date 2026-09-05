package korrawit.cms.infrastructure.persistence.model;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "skills")
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "domain_id", nullable = false)
    private SkillDomains domain;

    private Integer level;

    private String summary;

    private String[] tools;

    private String[] useCases;

    private String parent;

    private String icon;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experiences> experiences;

    protected Skills() {
    }

    public Skills(Integer id, String name, SkillDomains domain, Integer level, String summary, String[] tools,
            String[] useCases, String parent, String icon, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.domain = domain;
        this.level = level;
        this.summary = summary;
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

    public String getName() {
        return name;
    }

    public SkillDomains getDomain() {
        return domain;
    }

    public Integer getLevel() {
        return level;
    }

    public String getSummary() {
        return summary;
    }

    public String[] getTools() {
        return tools;
    }

    public String[] getUseCases() {
        return useCases;
    }

    public String getParent() {
        return parent;
    }

    public String getIcon() {
        return icon;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public List<Experiences> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experiences> experiences) {
        this.experiences = experiences;
    }

}
