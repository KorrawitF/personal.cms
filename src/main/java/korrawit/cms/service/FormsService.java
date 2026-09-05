package korrawit.cms.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import korrawit.cms.domain.entity.FormFields;
import korrawit.cms.domain.entity.Forms;
import korrawit.cms.domain.repository.FormsRepository;
import korrawit.cms.error.ResourceNotFoundException;

@Service
public class FormsService {

    private final FormsRepository formsRepository;

    public FormsService(FormsRepository formsRepository) {
        this.formsRepository = formsRepository;
    }

    public List<Forms> findAll() {
        return formsRepository.findAll();
    }

    public Forms findById(int id) {
        return formsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found: " + id));
    }

    public Forms create(Forms form) {
        Instant now = Instant.now();
        form.setId(null);
        form.setCreatedAt(now);
        form.setUpdatedAt(now);
        stampFieldTimestamps(form, null, now);
        return formsRepository.save(form);
    }

    public Forms update(int id, Forms form) {
        Forms existing = findById(id);
        Instant now = Instant.now();
        form.setId(id);
        form.setCreatedAt(existing.getCreatedAt());
        form.setUpdatedAt(now);
        stampFieldTimestamps(form, existing, now);
        return formsRepository.save(form);
    }

    public void delete(int id) {
        findById(id);
        formsRepository.deleteById(id);
    }

    private void stampFieldTimestamps(Forms form, Forms existing, Instant now) {
        if (form.getFields() == null) {
            return;
        }
        Map<Integer, FormFields> existingById = existing == null || existing.getFields() == null ? Map.of()
                : Arrays.stream(existing.getFields()).collect(Collectors.toMap(FormFields::getId, f -> f));
        for (FormFields field : form.getFields()) {
            FormFields matched = field.getId() != null ? existingById.get(field.getId()) : null;
            field.setCreatedAt(matched != null ? matched.getCreatedAt() : now);
            field.setUpdatedAt(now);
        }
    }
}
