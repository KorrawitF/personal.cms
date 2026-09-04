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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
