package cl.pinolabs.backend.model.domain.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PersonaDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nacimiento;
    private Integer telefono;
    private Integer idComuna;
    private ComunaDTO comunaDTO;
    /* getters and setters */

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Integer idComuna) {
        this.idComuna = idComuna;
    }

    public ComunaDTO getComunaDTO() {
        return comunaDTO;
    }

    public void setComunaDTO(ComunaDTO comunaDTO) {
        this.comunaDTO = comunaDTO;
    }
}
