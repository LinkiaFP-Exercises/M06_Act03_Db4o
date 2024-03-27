package db4o.controller;

import com.db4o.ObjectContainer;
import db4o.model.Empleado;

import java.util.List;

import static db4o.utilities.Util.notNullOrBlank;

public class EmpleadoController {

    private final ObjectContainer db;

    public EmpleadoController(ObjectContainer db) {
        this.db = db;
    }

    // Insertar un nuevo empleado con expresiones lambda
    public void insert(Empleado empleado) {
        try {
            db.store(empleado);
        } catch (Exception e) {
            handleError(e, new Object(){}.getClass().getEnclosingMethod().getName());
        }
    }

    // Uso de Streams para buscar un empleado por nombreUsuario
    public Empleado findOne(String nombreUsuario) {
        return (Empleado) db.queryByExample(new Empleado(nombreUsuario)).stream().findFirst().orElse(null);
    }

    public List<Empleado> findAll() {
        try {
            return db.queryByExample(Empleado.class);
        } catch (Exception e) {
            handleError(e, new Object(){}.getClass().getEnclosingMethod().getName());
            return null;
        }
    }

    // Actualización de empleados usando lambdas para error handling
    public boolean update(Empleado empleado) {
        try {
            Empleado empleadoActual = (Empleado) db.queryByExample(new Empleado(empleado.getNombreUsuario())).stream().findFirst().orElse(null);
            boolean updated = false;
            if (empleadoActual != null) {
                if (notNullOrBlank(empleado.getNombreUsuario())) {
                    empleadoActual.setNombreUsuario(empleado.getNombreUsuario());
                    updated = true;
                }
                if (notNullOrBlank(empleado.getNombreCompleto())) {
                    empleadoActual.setNombreCompleto(empleado.getNombreCompleto());
                    updated = true;
                }
                if (notNullOrBlank(empleado.getTelefonoContacto())) {
                    empleadoActual.setTelefonoContacto(empleado.getTelefonoContacto());
                    updated = true;
                }
                if (updated)
                    db.store(empleadoActual);
            }
            return updated;
        } catch (Exception e) {
            handleError(e, new Object(){}.getClass().getEnclosingMethod().getName());
            return false;
        }
    }

    public boolean updatePassword(Empleado empleado) {
        try {
            db.store(empleado);
            return true;
        } catch (Exception e) {
            handleError(e, new Object(){}.getClass().getEnclosingMethod().getName());
            return false;
        }
    }

    // Simplificar eliminación usando Streams
    public void delete(String nombreUsuario) {
        try {
            Empleado prototipo = new Empleado(nombreUsuario);
            db.queryByExample(prototipo).forEach(db::delete);
        } catch (Exception e) {
            handleError(e, new Object(){}.getClass().getEnclosingMethod().getName());
        }
    }

    // Ejemplo de cómo limpiar empleados usando Streams
    public void limpiarEmpleados() {
        try { db.queryByExample(Empleado.class).forEach(db::delete); }
        catch (Exception e) {
            handleError(e, new Object(){}.getClass().getEnclosingMethod().getName());
        }
    }

    public boolean validarEmpleado(String nombreUsuario, String contrasena) {
        return buscarEmpleadoPorCredenciales(nombreUsuario, contrasena) != null;
    }

    public Empleado buscarEmpleadoPorCredenciales(String usuario, String contrasena) {
        List<Empleado> resultados = db.queryByExample(new Empleado(usuario, contrasena));
        if (resultados.isEmpty())
            return null;
        return resultados.getFirst();
    }

    private void handleError(Exception e, String methodName) {
        System.err.println("Exception in " + this.getClass().getSimpleName() + "." + methodName + "() : " + e.getMessage());
    }
}
