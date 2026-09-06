package korrawit.cms.domain.entity;

import java.time.Instant;

public class MailTransaction {

    private Integer id;
    private String receiverHash;
    private String subject;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;

    public MailTransaction() {
    }

    public MailTransaction(Integer id, String receiverHash, String subject, String content, Instant createdAt,
            Instant updatedAt) {
        this.id = id;
        this.receiverHash = receiverHash;
        this.subject = subject;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceiverHash() {
        return receiverHash;
    }

    public void setReceiverHash(String receiverHash) {
        this.receiverHash = receiverHash;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
