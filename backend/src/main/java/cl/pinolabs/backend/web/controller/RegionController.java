package cl.pinolabs.backend.web.controller;

import cl.pinolabs.backend.model.domain.dto.RegionDTO;
import cl.pinolabs.backend.model.domain.service.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/api/regiones")
public class RegionController {
    private final RegionService service;
    public RegionController(RegionService service) {
        this.service = service;
    }
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<RegionDTO>> findAll(){
        return service.findAll()
                .map(regiones -> new ResponseEntity<>(regiones, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{idRegion}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<RegionDTO> findById(@PathVariable("idRegion") int idRegion){
        return service.findById(idRegion)
                .map(regionDTO -> new ResponseEntity<>(regionDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{idRegion}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<RegionDTO> update(@PathVariable(value = "idRegion") int idRegion,@Valid @RequestBody RegionDTO regionDTO) {
        RegionDTO reg = service.findById(idRegion).get();
        reg.setNombre(regionDTO.getNombre());
        reg.setId(idRegion);
        final RegionDTO update = service.save(reg);
        return ResponseEntity.ok(update);
    }
    @PostMapping
    @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<RegionDTO> save(@Valid @RequestBody RegionDTO regionDTO){
        return new ResponseEntity<>(service.save(regionDTO), HttpStatus.OK);
    }
    @DeleteMapping("/{idRegion}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable("idRegion") int idRegion){
        if (service.delete(idRegion)){
            return ResponseEntity.ok("Region Eliminada con Exito!");
        } else {
            return ResponseEntity.badRequest().body("no se a podido eliminar la region con id "+idRegion);
        }
    }

}
