package korrawit.cms.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import korrawit.cms.domain.entity.MediaObject;
import korrawit.cms.service.MediaService;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/{*key}")
    public ResponseEntity<InputStreamResource> fetch(@PathVariable String key) {
        String normalizedKey = key.startsWith("/") ? key.substring(1) : key;
        MediaObject media = mediaService.fetch(normalizedKey);

        ResponseEntity.BodyBuilder response = ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(media.getContentType()));
        if (media.getContentLength() >= 0) {
            response.contentLength(media.getContentLength());
        }
        return response.body(new InputStreamResource(media.getContent()));
    }
}
