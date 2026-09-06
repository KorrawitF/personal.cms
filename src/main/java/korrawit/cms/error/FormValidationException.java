package korrawit.cms.error;

import java.util.Map;

public class FormValidationException extends RuntimeException {

    private final Map<String, String> errors;

    public FormValidationException(Map<String, String> errors) {
        super("Form submission failed validation");
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
