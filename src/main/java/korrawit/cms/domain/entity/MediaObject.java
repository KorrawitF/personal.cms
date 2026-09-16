package korrawit.cms.domain.entity;

import java.io.InputStream;

public class MediaObject {

    private final String key;
    private final String fileName;
    private final String contentType;
    private final long contentLength;
    private final InputStream content;

    public MediaObject(String key, String fileName, String contentType, long contentLength, InputStream content) {
        this.key = key;
        this.fileName = fileName;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.content = content;
    }

    public String getKey() {
        return key;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public long getContentLength() {
        return contentLength;
    }

    public InputStream getContent() {
        return content;
    }
}
