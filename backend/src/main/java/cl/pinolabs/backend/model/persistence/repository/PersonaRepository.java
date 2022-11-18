package cl.pinolabs.backend.model.persistence.repository;

import cl.pinolabs.backend.model.domain.dto.PersonaDTO;
import cl.pinolabs.backend.model.domain.repository.PersonaDTORepository;
import cl.pinolabs.backend.model.persistence.crud.PersonaCrud;
import cl.pinolabs.backend.model.persistence.enitty.Persona;
import cl.pinolabs.backend.model.persistence.mapper.PersonaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonaRepository implements PersonaDTORepository {

    private final PersonaCrud crud;
    private final PersonaMapper mapper;
    public PersonaRepository(PersonaCrud crud, PersonaMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }
    @Override
    public Optional<List<PersonaDTO>> findAll() {
        return Optional.of(mapper.toPersonasDTO((List<Persona>) crud.findAll()));
    }
    @Override
    public Optional<PersonaDTO> findById(int idPersona) {
        return crud.findById(idPersona)
                .map(mapper::toPersonaDTO);
    }
    @Override
    public Optional<List<PersonaDTO>> findByIdComuna(Integer idComuna) {
        return crud.findByIdComuna(idComuna)
                .map(mapper::toPersonasDTO);
    }
    @Override
    public PersonaDTO save(PersonaDTO personaDTO) {
        return mapper.toPersonaDTO(crud.save(mapper.toPersona(personaDTO)));}
    @Override
    public void delete(int idPersona) {
        crud.deleteById(idPersona);
    }
}