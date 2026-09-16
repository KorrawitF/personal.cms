package korrawit.cms.domain.entity;

import java.time.Instant;

public class MediaRecord {

    private Integer id;
    private String key;
    private String fileName;
    private String contentType;
    private Long size;
    private Instant createdAt;
    private Instant updatedAt;

    public MediaRecord() {
    }

    public MediaRecord(Integer id, String key, String fileName, String contentType, Long size, Instant createdAt,
            Instant updatedAt) {
        this.id = id;
        this.key = key;
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
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
