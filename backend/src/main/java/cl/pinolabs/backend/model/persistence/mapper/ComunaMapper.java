package cl.pinolabs.backend.model.persistence.mapper;


import cl.pinolabs.backend.model.domain.dto.ComunaDTO;
import cl.pinolabs.backend.model.persistence.enitty.Comuna;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = {RegionMapper.class})
public interface ComunaMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "idRegion", target = "idRegion"),
            @Mapping(source = "region", target = "regionDTO")
    })
    ComunaDTO toComunaDTO(Comuna comuna);
    List<ComunaDTO> toComunasDTO(List<Comuna> comunas);
    @InheritInverseConfiguration
    Comuna toComuna(ComunaDTO comunaDTO);
}
