package korrawit.cms.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import korrawit.cms.domain.entity.MediaObject;
import korrawit.cms.domain.entity.MediaRecord;
import korrawit.cms.service.MediaService;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping
    public List<MediaRecord> findAll() {
        return mediaService.findAll();
    }

    @GetMapping("/{id}")
    public MediaRecord findById(@PathVariable int id) {
        return mediaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MediaRecord create(@RequestBody MediaRecord record) {
        return mediaService.create(record);
    }

    @PutMapping("/{id}")
    public MediaRecord update(@PathVariable int id, @RequestBody MediaRecord record) {
        return mediaService.update(id, record);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        mediaService.delete(id);
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<InputStreamResource> fetchFile(@PathVariable int id) {
        MediaObject media = mediaService.fetchFile(id);

        ContentDisposition disposition = ContentDisposition.inline()
                .filename(media.getFileName(), StandardCharsets.UTF_8)
                .build();

        ResponseEntity.BodyBuilder response = ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(media.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, disposition.toString());
        if (media.getContentLength() >= 0) {
            response.contentLength(media.getContentLength());
        }
        return response.body(new InputStreamResource(media.getContent()));
    }
}
