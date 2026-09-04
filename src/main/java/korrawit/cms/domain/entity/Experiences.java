package korrawit.cms.domain.entity;

public class Experiences {
    private Integer id;
    private Integer skillId;
    private String title;
    private String org;
    private String detail;

    public Experiences() {
    }

    public Experiences(Integer id, Integer skillId, String title, String org, String detail) {
        this.id = id;
        this.skillId = skillId;
        this.title = title;
        this.org = org;
        this.detail = detail;
    }

    public Integer getId() {
        return id;
    }
}
