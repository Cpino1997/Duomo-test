package cl.pinolabs.backend.model.persistence.crud;

import cl.pinolabs.backend.model.persistence.enitty.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaCrud extends CrudRepository<Persona,Integer> {
    Optional<Persona> findByCorreo(String correo);
    Optional<List<Persona>> findByIdComuna(Integer idComuna);
}
