package korrawit.cms.domain.dto;

import java.time.Instant;

public record FormSubmissionResult(String id, String to, Instant deliveredAt) {
}
