package model;

import java.util.List;

public class Empleado {
    private String nombreUsuario;
    private String contrasena;
    private String nombreCompleto;
    private String telefonoContacto;
    private List<Incidencia> incidenciasOrigen;
    private List<Incidencia> incidenciasDestino;

    public Empleado() {
    }

    public Empleado(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Empleado(String nombreUsuario, String contrasena, String nombreCompleto, String telefonoContacto) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.telefonoContacto = telefonoContacto;
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

        if (!getNombreUsuario().equals(empleado.getNombreUsuario())) return false;
        if (!getContrasena().equals(empleado.getContrasena())) return false;
        if (!getNombreCompleto().equals(empleado.getNombreCompleto())) return false;
        return getTelefonoContacto().equals(empleado.getTelefonoContacto());
    }

    @Override
    public int hashCode() {
        int result = getNombreUsuario().hashCode();
        result = 31 * result + getContrasena().hashCode();
        result = 31 * result + getNombreCompleto().hashCode();
        result = 31 * result + getTelefonoContacto().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                '}';
    }
}
