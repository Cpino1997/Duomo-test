package cl.pinolabs.backend.model.persistence.enitty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comunas")
public class Comuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotBlank @NotNull
    private String nombre;
    @Column(name = "id_region")
    private Integer idRegion;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_region", insertable = false, updatable = false)
    private Region region;

    /*Getters and Setters*/
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public Integer getIdRegion() {return idRegion;}
    public void setIdRegion(Integer idRegion) {this.idRegion = idRegion;}
    public Region getRegion() {return region;}
    public void setRegion(Region region) {this.region = region;}
}
