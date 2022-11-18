package cl.pinolabs.backend.model.domain.repository;

import cl.pinolabs.backend.model.domain.dto.ComunaDTO;

import java.util.List;
import java.util.Optional;

public interface ComunaDTORepository {
    Optional<List<ComunaDTO>> findAll();
    Optional<ComunaDTO> findById(int idComuna);
    Optional<List<ComunaDTO>> findByIdRegion(Integer idRegion);
    ComunaDTO save(ComunaDTO comunaDTO);
    void delete(int idComuna);
}
