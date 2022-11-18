package cl.pinolabs.backend.model.persistence.mapper;

import cl.pinolabs.backend.model.domain.dto.RegionDTO;
import cl.pinolabs.backend.model.persistence.enitty.Region;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "nombre")
    })
    RegionDTO toRegionDTO(Region region);
    List<RegionDTO> toRegionesDTO(List<Region> regiones);
    @InheritInverseConfiguration
    Region toRegion(RegionDTO regionDTO);
}
