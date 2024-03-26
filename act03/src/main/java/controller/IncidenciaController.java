package controller;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import model.Incidencia;
import config.Db4oHelper;
import utilities.Util;

import java.util.List;
import java.util.Objects;

public class IncidenciaController {

    public void insert(Incidencia incidencia) {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            Objects.requireNonNull(db).store(incidencia);
        } catch (Exception e) {
            System.err.println(Util.errorDescription(e, this));
        } finally {
            Db4oHelper.closeDB();
        }
    }

    public List<Incidencia> findAll() {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            Query query = Objects.requireNonNull(db).query();
            query.constrain(Incidencia.class);
            return query.execute();
        } catch (Exception e) {
            System.err.println(Util.errorDescription(e, this));
            return null;
        } finally {
            Db4oHelper.closeDB();
        }
    }

    public List<Incidencia> findByIdOrigin(int idEmpleadoOrigen) {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            Query query = Objects.requireNonNull(db).query();
            query.constrain(Incidencia.class);
            query.descend("empleadoOrigen").descend("id").constrain(idEmpleadoOrigen);
            return query.execute();
        } catch (Exception e) {
            System.err.println(Util.errorDescription(e, this));
            return null;
        } finally {
            Db4oHelper.closeDB();
        }
    }

    public List<Incidencia> findByIdDestiny(int idEmpleadoDestino) {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            Query query = Objects.requireNonNull(db).query();
            query.constrain(Incidencia.class);
            query.descend("empleadoDestino").descend("id").constrain(idEmpleadoDestino);
            return query.execute();
        } catch (Exception e) {
            System.err.println(Util.errorDescription(e, this));
            return null;
        } finally {
            Db4oHelper.closeDB();
        }
    }


    public void update(Incidencia incidencia) {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            Incidencia incidenciaExistente = findById(incidencia.getId(), Objects.requireNonNull(db));
            if (incidenciaExistente != null) {
                incidenciaExistente.setEmpleadoOrigen(incidencia.getEmpleadoOrigen());
                incidenciaExistente.setEmpleadoDestino(incidencia.getEmpleadoDestino());
                incidenciaExistente.setDetalle(incidencia.getDetalle());
                incidenciaExistente.setTipo(incidencia.getTipo());
                db.store(incidenciaExistente);
            }
        } catch (Exception e) {
            System.err.println(Util.errorDescription(e, this));
        } finally {
            Db4oHelper.closeDB();
        }
    }

    public void delete(int id) {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            Incidencia incidencia = findById(id, Objects.requireNonNull(db));
            if (incidencia != null) {
                db.delete(incidencia);
            }
        } catch (Exception e) {
            System.err.println(Util.errorDescription(e, this));
        } finally {
            Db4oHelper.closeDB();
        }
    }

    private Incidencia findById(int id, ObjectContainer db) {
        Query query = db.query();
        query.constrain(Incidencia.class);
        query.descend("id").constrain(id);
        List<Incidencia> resultado = query.execute();
        if (!resultado.isEmpty()) {
            return resultado.getFirst();
        }
        return null;
    }
}
