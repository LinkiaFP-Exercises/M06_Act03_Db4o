package model;

import java.util.Date;

public class Incidencia {
    private int id;
    private Date fechaHora;
    private Empleado empleadoOrigen;
    private Empleado empleadoDestino;
    private String detalle;
    private String tipo;

    public Incidencia() {
    }

    public Incidencia(int id, Date fechaHora, Empleado empleadoOrigen, Empleado empleadoDestino, String detalle, String tipo) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.empleadoOrigen = empleadoOrigen;
        this.empleadoDestino = empleadoDestino;
        this.detalle = detalle;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Empleado getEmpleadoOrigen() {
        return empleadoOrigen;
    }

    public void setEmpleadoOrigen(Empleado empleadoOrigen) {
        this.empleadoOrigen = empleadoOrigen;
    }

    public Empleado getEmpleadoDestino() {
        return empleadoDestino;
    }

    public void setEmpleadoDestino(Empleado empleadoDestino) {
        this.empleadoDestino = empleadoDestino;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Incidencia that = (Incidencia) o;

        if (getId() != that.getId()) return false;
        if (!getFechaHora().equals(that.getFechaHora())) return false;
        if (!getEmpleadoOrigen().equals(that.getEmpleadoOrigen())) return false;
        if (!getEmpleadoDestino().equals(that.getEmpleadoDestino())) return false;
        if (!getDetalle().equals(that.getDetalle())) return false;
        return getTipo().equals(that.getTipo());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getFechaHora().hashCode();
        result = 31 * result + getEmpleadoOrigen().hashCode();
        result = 31 * result + getEmpleadoDestino().hashCode();
        result = 31 * result + getDetalle().hashCode();
        result = 31 * result + getTipo().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Incidencia{" +
                "id=" + id +
                ", fechaHora=" + fechaHora +
                ", empleadoOrigen=" + empleadoOrigen +
                ", empleadoDestino=" + empleadoDestino +
                ", detalle='" + detalle + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
    
}
