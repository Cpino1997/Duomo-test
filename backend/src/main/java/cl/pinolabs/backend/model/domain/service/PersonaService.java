package cl.pinolabs.backend.model.domain.service;

import cl.pinolabs.backend.model.domain.dto.PersonaDTO;
import cl.pinolabs.backend.model.domain.repository.PersonaDTORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    private final PersonaDTORepository repo;
    public PersonaService(PersonaDTORepository repo) {
        this.repo = repo;
    }
    public Optional<List<PersonaDTO>> findAll(){
        return repo.findAll();
    }
    public Optional<PersonaDTO> findById(int idPersona){
        return repo.findById(idPersona);
    }
    public Optional<List<PersonaDTO>> findByIdComuna(int idComuna){return repo.findByIdComuna(idComuna);}
    public PersonaDTO save(PersonaDTO personaDTO){return repo.save(personaDTO);}
    public boolean delete(int idPersona){
        return findById(idPersona)
                .map(personaDTO -> {
                    repo.delete(idPersona);
                    return true;})
                .orElse(false);
    }
}
