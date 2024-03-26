package controller;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import config.Db4oHelper;
import model.Empleado;
import utilities.Util;

import java.util.List;
import java.util.Objects;

public class EmpleadoController {

    // Insertar un nuevo empleado
    public void insert(Empleado empleado) {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            Objects.requireNonNull(db).store(empleado);
        } catch (Exception e) {
            System.err.println(Util.errorDescription(e, this));
        } finally {
            Db4oHelper.closeDB();
        }
    }

    // Listar todos los empleados
    public List<Empleado> findAll() {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            Query query = Objects.requireNonNull(db).query();
            query.constrain(Empleado.class);
            return query.execute();
        } catch (Exception e) {
            System.err.println(Util.errorDescription(e, this));
            return null;
        } finally {
            Db4oHelper.closeDB();
        }
    }

    // Listar uno de los empleados
    public Empleado findOne(String nombreUsuario) {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            return findByNombreUsuario(nombreUsuario, Objects.requireNonNull(db));
        } catch (Exception e) {
            System.err.println(Util.errorDescription(e, this));
            return null;
        } finally {
            Db4oHelper.closeDB();
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
            System.err.println(Util.errorDescription(e, this));
        } finally {
            Db4oHelper.closeDB();
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
            System.err.println(Util.errorDescription(e, this));
        } finally {
            Db4oHelper.closeDB();
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
