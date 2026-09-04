package korrawit.cms.domain.entity;

import java.time.Instant;
import java.time.LocalDate;

public class WorkExperiences {

    private Integer id;
    private String jobTitle;
    private String companyName;
    private String detail;
    private String[] techStack;
    private String icon;
    private LocalDate startDate;
    private LocalDate endDate;
    private Instant createdAt;
    private Instant updatedAt;

    public WorkExperiences() {
    }

    public WorkExperiences(Integer id, String jobTitle, String companyName, String detail, String[] techStack,
            String icon, LocalDate startDate, LocalDate endDate, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.detail = detail;
        this.techStack = techStack;
        this.icon = icon;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String[] getTechStack() {
        return techStack;
    }

    public void setTechStack(String[] techStack) {
        this.techStack = techStack;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
