package korrawit.cms.error;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(FormValidationException.class)
    public ResponseEntity<Map<String, Object>> handleFormValidation(FormValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("errors", e.getErrors()));
    }

    @ExceptionHandler(MailDeliveryException.class)
    public ResponseEntity<String> handleMailDelivery(MailDeliveryException e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
    }

    @ExceptionHandler(MediaStorageException.class)
    public ResponseEntity<String> handleMediaStorage(MediaStorageException e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
    }
}
