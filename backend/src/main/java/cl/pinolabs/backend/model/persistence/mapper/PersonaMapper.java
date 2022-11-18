package cl.pinolabs.backend.model.persistence.mapper;

import cl.pinolabs.backend.model.domain.dto.PersonaDTO;
import cl.pinolabs.backend.model.persistence.enitty.Persona;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ComunaMapper.class})
public interface PersonaMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "correo", target = "correo"),
            @Mapping(source = "nacimiento", target = "nacimiento"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "idComuna", target = "idComuna"),
            @Mapping(source = "comuna", target = "comunaDTO")
    })
    PersonaDTO toPersonaDTO(Persona persona);
    List<PersonaDTO> toPersonasDTO(List<Persona> personas);
    @InheritInverseConfiguration
    Persona toPersona(PersonaDTO personaDTO);
}
