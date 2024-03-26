package controller;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import config.Db4oHelper;
import model.Empleado;

import java.util.List;
import java.util.Objects;

public class EmpleadoController {

    private final ObjectContainer db;

    public EmpleadoController(ObjectContainer db) {
        this.db = db;
    }

    // Insertar un nuevo empleado
    public void insert(Empleado empleado) {
        try {
            Objects.requireNonNull(db).store(empleado);
        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorPlace = this.getClass().getSimpleName() + "." + methodName + "()";
            String error = "Exception in " + errorPlace + " : " + e.getMessage();
            System.err.println(error);
        }
    }

    // Listar todos los empleados
    public List<Empleado> findAll() {
        try {
            return Objects.requireNonNull(db).queryByExample(Empleado.class);
        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorPlace = this.getClass().getSimpleName() + "." + methodName + "()";
            String error = "Exception in " + errorPlace + " : " + e.getMessage();
            System.err.println(error);
            return null;
        }
    }

    // Listar uno de los empleados
    public Empleado findOne(String nombreUsuario) {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            return findByNombreUsuario(nombreUsuario, Objects.requireNonNull(db));
        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorPlace = this.getClass().getSimpleName() + "." + methodName + "()";
            String error = "Exception in " + errorPlace + " : " + e.getMessage();
            System.err.println(error);
            return null;
        }
    }

    // Actualizar un empleado existente
    public void update(Empleado empleado) {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            Empleado empleadoExistente = findByNombreUsuario(empleado.getNombreUsuario(), Objects.requireNonNull(db));
            if (empleadoExistente != null) {
                empleadoExistente.setNombreUsuario(empleado.getNombreUsuario());
                empleadoExistente.setContrasena(empleado.getContrasena());
                empleadoExistente.setNombreCompleto(empleado.getNombreCompleto());
                empleadoExistente.setTelefonoContacto(empleado.getTelefonoContacto());
                db.store(empleadoExistente);
            }
        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorPlace = this.getClass().getSimpleName() + "." + methodName + "()";
            String error = "Exception in " + errorPlace + " : " + e.getMessage();
            System.err.println(error);
        }
    }

    // Eliminar un empleado por su ID
    public void delete(String nombreUsuario) {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            Empleado empleado = findByNombreUsuario(nombreUsuario, Objects.requireNonNull(db));
            if (empleado != null) {
                db.delete(empleado);
            }
        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorPlace = this.getClass().getSimpleName() + "." + methodName + "()";
            String error = "Exception in " + errorPlace + " : " + e.getMessage();
            System.err.println(error);
        }
    }

    public void limpiarEmpleados() {
        try {
            findAll().forEach(db::delete);
        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorPlace = this.getClass().getSimpleName() + "." + methodName + "()";
            String error = "Exception in " + errorPlace + " : " + e.getMessage();
            System.err.println(error);
        }
    }

    // Buscar un empleado por su ID
    private Empleado findByNombreUsuario(String nombreUsuario, ObjectContainer db) {
        Query query = db.query();
        query.constrain(Empleado.class);
        query.descend("nombreUsuario").constrain(nombreUsuario);
        List<Empleado> resultado = query.execute();
        if (!resultado.isEmpty()) {
            return resultado.getFirst();
        }
        return null;
    }

}
