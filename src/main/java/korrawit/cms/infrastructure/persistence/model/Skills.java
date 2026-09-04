package korrawit.cms.infrastructure.persistence.model;

import java.time.Instant;
import java.util.List;

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

    @OneToMany(mappedBy = "skill")
    private List<Experiences> experiences;

    protected Skills() {
    }

    public Skills(Integer id, SkillDomains domain, Integer level, String summary, String[] tools, String[] useCases,
            String parent, String icon) {
        this.id = id;
        this.domain = domain;
        this.level = level;
        this.summary = summary;
        this.tools = tools;
        this.useCases = useCases;
        this.parent = parent;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public SkillDomains getDomain() {
        return domain;
    }

    public List<Experiences> getExperiences() {
        return experiences;
    }

}
