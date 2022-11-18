package cl.pinolabs.backend.model.persistence.repository;

import cl.pinolabs.backend.model.domain.dto.ComunaDTO;
import cl.pinolabs.backend.model.domain.repository.ComunaDTORepository;
import cl.pinolabs.backend.model.persistence.crud.ComunaCrud;
import cl.pinolabs.backend.model.persistence.enitty.Comuna;
import cl.pinolabs.backend.model.persistence.mapper.ComunaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ComunaRepository implements ComunaDTORepository {
    private final ComunaCrud crud;
    private final ComunaMapper mapper;
    public ComunaRepository(ComunaCrud crud, ComunaMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }
    @Override
    public Optional<List<ComunaDTO>> findAll() {
        return Optional.of(mapper.toComunasDTO((List<Comuna>) crud.findAll()));
    }
    @Override
    public Optional<ComunaDTO> findById(int idComuna) {
        return crud.findById(idComuna)
                .map(mapper::toComunaDTO);
    }
    @Override
    public Optional<List<ComunaDTO>> findByIdRegion(Integer idRegion) {
        return crud.findByIdRegion(idRegion)
                .map(mapper::toComunasDTO);
    }

    @Override
    public ComunaDTO save(ComunaDTO comunaDTO) {
        return mapper.toComunaDTO(crud.save(mapper.toComuna(comunaDTO)));    }
    @Override
    public void delete(int idComuna) {
        crud.deleteById(idComuna);
    }
}