package cl.pinolabs.backend.web.controller;

import cl.pinolabs.backend.model.domain.dto.ComunaDTO;
import cl.pinolabs.backend.model.domain.service.ComunaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/api/comunas")
public class ComunaController {
    private final ComunaService service;
    public ComunaController(ComunaService service) {
        this.service = service;
    }
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<ComunaDTO>> findAll(){
        return service.findAll()
                .map(comunas -> new ResponseEntity<>(comunas, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/reg/{idregion}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<ComunaDTO>> findByIdRegion(@PathVariable("idregion") int idregion){
        return service.findByIdRegion(idregion)
                .map(comunaDTO -> new ResponseEntity<>(comunaDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{idComuna}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<ComunaDTO> findById(@PathVariable("idComuna") int idComuna){
        return service.findById(idComuna)
                .map(comunaDTO -> new ResponseEntity<>(comunaDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{idComuna}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<ComunaDTO> update(@PathVariable(value = "idComuna") int idComuna,@Valid @RequestBody ComunaDTO comunaDTO) {
        ComunaDTO reg = service.findById(idComuna).get();
        reg.setId(idComuna);
        reg.setNombre(comunaDTO.getNombre());
        reg.setIdRegion(comunaDTO.getIdRegion());
        reg.setId(idComuna);
        final ComunaDTO update = service.save(reg);
        return ResponseEntity.ok(update);
    }
    @PostMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<ComunaDTO> save(@Valid @RequestBody ComunaDTO comunaDTO){
        return new ResponseEntity<>(service.save(comunaDTO), HttpStatus.OK);
    }
    //response entity String para el manejo de errores
    @DeleteMapping("/{idComuna}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable("idComuna") int idComuna){
        if (service.delete(idComuna)){
            return ResponseEntity.ok().body("Comuna Eliminada con exito!");
        } else {
            return ResponseEntity.badRequest().body("No se a podido eliminar la Comuna con id "+ idComuna +" =C");
        }
    }
}
