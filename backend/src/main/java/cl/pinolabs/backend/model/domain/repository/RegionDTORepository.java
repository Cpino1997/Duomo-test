package cl.pinolabs.backend.model.domain.repository;

import cl.pinolabs.backend.model.domain.dto.RegionDTO;

import java.util.List;
import java.util.Optional;

public interface RegionDTORepository {
    Optional<List<RegionDTO>> findAll();
    Optional<RegionDTO> findById(int idRegion);
    RegionDTO save(RegionDTO regionDTO);
    void delete(int idRegion);
}
