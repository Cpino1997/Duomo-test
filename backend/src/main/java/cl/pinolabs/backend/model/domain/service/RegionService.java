package cl.pinolabs.backend.model.domain.service;

import cl.pinolabs.backend.model.domain.dto.RegionDTO;
import cl.pinolabs.backend.model.domain.repository.RegionDTORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    private final RegionDTORepository repo;

    public RegionService(RegionDTORepository repo) {
        this.repo = repo;
    }
    public Optional<List<RegionDTO>> findAll(){
        return repo.findAll();
    }
    public Optional<RegionDTO> findById(int idRegion){
        return repo.findById(idRegion);
    }
    public RegionDTO save(RegionDTO regionDTO){
        return repo.save(regionDTO);
    }
    public boolean delete(int idRegion){
        return findById(idRegion)
                .map(regionDTO -> {
                    repo.delete(idRegion);
                    return true;})
                .orElse(false);
    }
}
