package cl.pinolabs.backend.model.persistence.repository;

import cl.pinolabs.backend.model.domain.dto.RegionDTO;
import cl.pinolabs.backend.model.domain.repository.RegionDTORepository;
import cl.pinolabs.backend.model.persistence.crud.RegionCrud;
import cl.pinolabs.backend.model.persistence.enitty.Region;
import cl.pinolabs.backend.model.persistence.mapper.RegionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegionRepository implements RegionDTORepository{
    private final RegionCrud crud;
    private final RegionMapper mapper;

    public RegionRepository(RegionCrud crud, RegionMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }


    @Override
    public Optional<List<RegionDTO>> findAll() {
        return Optional.of(mapper.toRegionesDTO((List<Region>) crud.findAll()));    }

    @Override
    public Optional<RegionDTO> findById(int idRegion) {
        return crud.findById(idRegion)
                .map(mapper::toRegionDTO);
    }

    @Override
    public RegionDTO save(RegionDTO regionDTO) {
        return mapper.toRegionDTO(crud.save(mapper.toRegion(regionDTO)));
    }

    @Override
    public void delete(int idRegion) {
        crud.deleteById(idRegion);
    }
}
