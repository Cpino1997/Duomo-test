package cl.pinolabs.backend.model.persistence.crud;

import cl.pinolabs.backend.model.persistence.enitty.Comuna;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface ComunaCrud extends CrudRepository<Comuna,Integer> {
    Optional<List<Comuna>> findByIdRegion(Integer idregion);
}
