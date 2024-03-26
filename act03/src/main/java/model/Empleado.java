package model;

import java.util.List;

public class Empleado {
    private int id;
    private String nombreUsuario;
    private String contrasena;
    private String nombreCompleto;
    private String telefonoContacto;
    private List<Incidencia> incidenciasOrigen;
    private List<Incidencia> incidenciasDestino;

    public Empleado() {
    }

    public Empleado(int id, String nombreUsuario, String contrasena, String nombreCompleto, String telefonoContacto) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.telefonoContacto = telefonoContacto;
    }

    public Empleado(int id, String nombreUsuario, String contrasena, String nombreCompleto, String telefonoContacto, List<Incidencia> incidenciasOrigen, List<Incidencia> incidenciasDestino) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.telefonoContacto = telefonoContacto;
        this.incidenciasOrigen = incidenciasOrigen;
        this.incidenciasDestino = incidenciasDestino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public List<Incidencia> getIncidenciasOrigen() {
        return incidenciasOrigen;
    }

    public void setIncidenciasOrigen(List<Incidencia> incidenciasOrigen) {
        this.incidenciasOrigen = incidenciasOrigen;
    }

    public List<Incidencia> getIncidenciasDestino() {
        return incidenciasDestino;
    }

    public void setIncidenciasDestino(List<Incidencia> incidenciasDestino) {
        this.incidenciasDestino = incidenciasDestino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empleado empleado = (Empleado) o;

        if (getId() != empleado.getId()) return false;
        if (!getNombreUsuario().equals(empleado.getNombreUsuario())) return false;
        if (!getContrasena().equals(empleado.getContrasena())) return false;
        if (!getNombreCompleto().equals(empleado.getNombreCompleto())) return false;
        return getTelefonoContacto().equals(empleado.getTelefonoContacto());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getNombreUsuario().hashCode();
        result = 31 * result + getContrasena().hashCode();
        result = 31 * result + getNombreCompleto().hashCode();
        result = 31 * result + getTelefonoContacto().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                '}';
    }
}
