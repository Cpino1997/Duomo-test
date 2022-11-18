package cl.pinolabs.backend.web.controller;

import cl.pinolabs.backend.model.domain.dto.PersonaDTO;
import cl.pinolabs.backend.model.domain.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/api/personas")
public class PersonaController {
    private final PersonaService service;
    public PersonaController(PersonaService service) {
        this.service = service;
    }
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<PersonaDTO>> findAll() {
        return service.findAll()
                .map(personas -> new ResponseEntity<>(personas, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/{idPersona}")
    public ResponseEntity<PersonaDTO> findById(@PathVariable("idPersona") int idPersona){
        return service.findById(idPersona)
                .map(personaDTO -> new ResponseEntity<>(personaDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{idPersona}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<PersonaDTO> update(@PathVariable(value = "idPersona") int idPersona,@Valid @RequestBody PersonaDTO personaDTO) {
        PersonaDTO reg = service.findById(idPersona).get();
        reg.setId(idPersona);
        reg.setNombre(personaDTO.getNombre());
        reg.setApellido(personaDTO.getApellido());
        reg.setCorreo(personaDTO.getCorreo());
        reg.setNacimiento(personaDTO.getNacimiento());
        reg.setTelefono(personaDTO.getTelefono());
        reg.setIdComuna(personaDTO.getIdComuna());
        reg.setComunaDTO(personaDTO.getComunaDTO());
        final PersonaDTO update = service.save(reg);
        return ResponseEntity.ok(update);
    }
    @GetMapping("/com/{idComuna}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<PersonaDTO>> findByIdComuna(@PathVariable("idComuna") int idComuna){
        return service.findByIdComuna(idComuna)
                .map(personaDTO -> new ResponseEntity<>(personaDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<PersonaDTO> save(@Valid @RequestBody PersonaDTO personaDTO){
        return new ResponseEntity<>(service.save(personaDTO), HttpStatus.OK);
    }
    @DeleteMapping("/{idPersona}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable("idPersona") int idPersona){
        if (service.delete(idPersona)){
            return ResponseEntity.ok().body("Se a eliminado con exito la persona con id "+idPersona);
        } else {
            return ResponseEntity.badRequest().body("no se a podido eliminar la Persona con id "+idPersona);
        }


    }
}
