package cl.pinolabs.backend.web.security.jwt.advice;


import java.util.Date;

public class ErrorMessage {
    private int codigo;
    private Date fecha;
    private String mensaje;
    private String descripcion;

    public ErrorMessage(int codigo, Date fecha, String mensaje, String descripcion) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}