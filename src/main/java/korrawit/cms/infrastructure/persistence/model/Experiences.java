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
    @JoinColumn(name = "experience_id", nullable = false)
    private Experiences experience;

    private String title;

    private String org;

    private String detail;

    protected Experiences() {
    }

    public Experiences(Integer id, Experiences exp, String title, String org, String detail) {
        this.id = id;
        this.experience = exp;
        this.title = title;
        this.org = org;
        this.detail = detail;
    }
}
