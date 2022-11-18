package cl.pinolabs.backend.model.domain.repository;

import cl.pinolabs.backend.model.domain.dto.PersonaDTO;

import java.util.List;
import java.util.Optional;

public interface PersonaDTORepository {
    Optional<List<PersonaDTO>> findAll();
    Optional<PersonaDTO> findById(int idPersona);
    Optional<List<PersonaDTO>> findByIdComuna(Integer idComuna);
    PersonaDTO save(PersonaDTO personaDTO);
    void delete(int idPersona);

}
