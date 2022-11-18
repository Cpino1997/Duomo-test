package cl.pinolabs.backend.model.domain.service;

import cl.pinolabs.backend.model.domain.dto.ComunaDTO;
import cl.pinolabs.backend.model.domain.repository.ComunaDTORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComunaService {
    private final ComunaDTORepository repo;
    public ComunaService(ComunaDTORepository repo) {
        this.repo = repo;
    }
    public Optional<List<ComunaDTO>> findAll(){
        return repo.findAll();
    }
    public Optional<ComunaDTO> findById(int idRegion){
        return repo.findById(idRegion);
    }
    public ComunaDTO save(ComunaDTO comunaDTO){
        return repo.save(comunaDTO);
    }
    public Optional<List<ComunaDTO>> findByIdRegion(int idRegion){
        return repo.findByIdRegion(idRegion);
    }
    public boolean delete(int idComuna){
        return findById(idComuna)
                .map(comunaDTO -> {
                    repo.delete(idComuna);
                    return true;})
                .orElse(false);
    }
}
