package korrawit.cms.infrastructure.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "experiences")
public class Experiences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skills skill;

    private String title;

    private String org;

    private String detail;

    protected Experiences() {
    }

    public Experiences(Integer id, Skills skill, String title, String org, String detail) {
        this.id = id;
        this.skill = skill;
        this.title = title;
        this.org = org;
        this.detail = detail;
    }

    public Integer getId() {
        return id;
    }

    public Skills getSkill() {
        return skill;
    }

    public String getTitle() {
        return title;
    }

    public String getOrg() {
        return org;
    }

    public String getDetail() {
        return detail;
    }
}
