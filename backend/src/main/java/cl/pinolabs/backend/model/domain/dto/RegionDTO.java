package cl.pinolabs.backend.model.domain.dto;

import javax.validation.constraints.*;

public class RegionDTO {
    private Integer id;
    @NotNull(message = "El Nombre es requerido! ")
    @NotBlank(message = "El nombre no puede estar vacio =c")
    @Size(min = 4, max = 40, message = "Error el nombre debe tener entre 4 a 40 caracteres")
    private String nombre;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
