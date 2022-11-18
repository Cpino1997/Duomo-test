package cl.pinolabs.backend.model.persistence.enitty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 40,nullable = false)
    private String nombre;
    @Column(length = 40,nullable = false)
    private String apellido;
    @Column(length = 60,nullable = false, unique = true)
    private String correo;
    @Column(name = "fecha_nacimiento")
    private LocalDate nacimiento;
    @Column(length = 9,nullable = false, unique = true)
    private Integer telefono;
    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDateTime creacion;
    @UpdateTimestamp
    @Column(name = "ultimo_update")
    private LocalDateTime update;
    @Column(name = "id_comuna")
    private Integer idComuna;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comuna", insertable = false, updatable = false)
    private Comuna comuna;
    /* Getter and Setters*/
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}
    public LocalDate getNacimiento() {return nacimiento;}
    public void setNacimiento(LocalDate nacimiento) {this.nacimiento = nacimiento;}
    public Integer getTelefono() {return telefono;}
    public void setTelefono(Integer telefono) {this.telefono = telefono;}
    public LocalDateTime getCreacion() {return creacion;}
    public void setCreacion(LocalDateTime creacion) {this.creacion = creacion;}
    public LocalDateTime getUpdate() {return update;}
    public void setUpdate(LocalDateTime update) {this.update = update;}
    public Integer getIdComuna() {return idComuna;}
    public void setIdComuna(Integer idComuna) {this.idComuna = idComuna;}
    public Comuna getComuna() {return comuna;}
    public void setComuna(Comuna comuna) {this.comuna = comuna;}
}
