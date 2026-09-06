package korrawit.cms.domain.entity;

import java.time.Instant;

public class MailTransaction {

    private Integer id;
    private String receiverHash;
    private Instant createdAt;
    private Instant updatedAt;

    public MailTransaction() {
    }

    public MailTransaction(Integer id, String receiverHash, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.receiverHash = receiverHash;
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
