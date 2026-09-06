package korrawit.cms.domain.repository;

import java.util.List;
import java.util.Optional;

import korrawit.cms.domain.entity.Forms;

public interface FormsRepository {

    Optional<Forms> findById(int id);

    Optional<Forms> findBySlug(String slug);

    List<Forms> findAll();

    Forms save(Forms form);

    void deleteById(int id);
}
