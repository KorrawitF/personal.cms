package korrawit.cms.infrastructure.storage;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import korrawit.cms.domain.entity.MediaObject;
import korrawit.cms.domain.repository.MediaRepository;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

@Repository
public class S3MediaRepository implements MediaRepository {

    private final S3Client s3Client;
    private final String bucket;

    public S3MediaRepository(S3Client s3Client, @Value("${app.s3.bucket}") String bucket) {
        this.s3Client = s3Client;
        this.bucket = bucket;
    }

    @Override
    public Optional<MediaObject> fetch(String key) {
        GetObjectRequest request = GetObjectRequest.builder().bucket(bucket).key(key).build();

        try {
            ResponseInputStream<GetObjectResponse> object = s3Client.getObject(request);
            GetObjectResponse metadata = object.response();
            String contentType = metadata.contentType() != null ? metadata.contentType() : "application/octet-stream";
            long contentLength = metadata.contentLength() != null ? metadata.contentLength() : -1L;
            return Optional.of(new MediaObject(key, null, contentType, contentLength, object));
        } catch (NoSuchKeyException e) {
            return Optional.empty();
        }
    }
}
