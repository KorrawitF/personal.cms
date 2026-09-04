package korrawit.cms.domain.entity;

public class Skills {
    private Integer id;
    private Integer domainId;
    private Integer level;
    private String summary;
    private String[] tools;
    private String[] useCases;
    private String parent;
    private String icon;

    public Skills() {
    }

    public Skills(Integer id, Integer domainId, Integer level, String summary, String[] tools, String[] useCases,
            String parent, String icon) {
        this.id = id;
        this.domainId = domainId;
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
}
