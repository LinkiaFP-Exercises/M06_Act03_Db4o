package controller;

import com.db4o.ObjectContainer;
import model.Empleado;

import java.util.List;

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
    public void update(Empleado empleado) {
        try {
            Empleado found = (Empleado) db.queryByExample(new Empleado(empleado.getNombreUsuario(), null, null, null)).stream().findFirst().orElse(null);
            if (found != null) {
                found.setNombreUsuario(empleado.getNombreUsuario());
                found.setContrasena(empleado.getContrasena());
                found.setNombreCompleto(empleado.getNombreCompleto());
                found.setTelefonoContacto(empleado.getTelefonoContacto());
                db.store(found);
            }
        } catch (Exception e) {
            handleError(e, new Object(){}.getClass().getEnclosingMethod().getName());
        }
    }

    // Simplificar eliminación usando Streams
    public void delete(String nombreUsuario) {
        try {
            Empleado prototipo = new Empleado(nombreUsuario, null, null, null);
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


    private void handleError(Exception e, String methodName) {
        System.err.println("Exception in " + this.getClass().getSimpleName() + "." + methodName + "() : " + e.getMessage());
    }

}
