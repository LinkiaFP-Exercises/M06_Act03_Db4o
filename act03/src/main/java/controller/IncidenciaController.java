package controller;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import model.Incidencia;

import java.util.List;
import java.util.Objects;

public class IncidenciaController {

    private final ObjectContainer db;

    public IncidenciaController(ObjectContainer db) {
        this.db = db;
    }

    public void insert(Incidencia incidencia) {
        try {
            Objects.requireNonNull(db).store(incidencia);
        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorPlace = this.getClass().getSimpleName() + "." + methodName + "()";
            String error = "Exception in " + errorPlace + " : " + e.getMessage();
            System.err.println(error);
        }
    }

    public List<Incidencia> findAll() {
        try {
            return Objects.requireNonNull(db).queryByExample(Incidencia.class);
        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorPlace = this.getClass().getSimpleName() + "." + methodName + "()";
            String error = "Exception in " + errorPlace + " : " + e.getMessage();
            System.err.println(error);
            return null;
        }
    }

    public Incidencia findOne(String id) {
        try {
            return findById(id, Objects.requireNonNull(db));
        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorPlace = this.getClass().getSimpleName() + "." + methodName + "()";
            String error = "Exception in " + errorPlace + " : " + e.getMessage();
            System.err.println(error);
            return null;
        }
    }

    public List<Incidencia> findByIdOrigin(String nombreUsuario) {
        try {
            Query query = Objects.requireNonNull(db).query();
            query.constrain(Incidencia.class);
            query.descend("empleadoOrigen").descend("nombreUsuario").constrain(nombreUsuario);
            return query.execute();
        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorPlace = this.getClass().getSimpleName() + "." + methodName + "()";
            String error = "Exception in " + errorPlace + " : " + e.getMessage();
            System.err.println(error);
            return null;
        }
    }

    public List<Incidencia> findByIdDestiny(String nombreUsuario) {
        try {
            Query query = Objects.requireNonNull(db).query();
            query.constrain(Incidencia.class);
            query.descend("empleadoDestino").descend("nombreUsuario").constrain(nombreUsuario);
            return query.execute();
        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorPlace = this.getClass().getSimpleName() + "." + methodName + "()";
            String error = "Exception in " + errorPlace + " : " + e.getMessage();
            System.err.println(error);
            return null;
        }
    }


    public void update(Incidencia incidencia) {
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
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorPlace = this.getClass().getSimpleName() + "." + methodName + "()";
            String error = "Exception in " + errorPlace + " : " + e.getMessage();
            System.err.println(error);
        }
    }

    public void limpiarIncidencias() {
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

    public void delete(String id) {
        try {
            Incidencia incidencia = findById(id, Objects.requireNonNull(db));
            if (incidencia != null) {
                db.delete(incidencia);
            }
        } catch (Exception e) {
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String errorPlace = this.getClass().getSimpleName() + "." + methodName + "()";
            String error = "Exception in " + errorPlace + " : " + e.getMessage();
            System.err.println(error);
        }
    }

    private Incidencia findById(String id, ObjectContainer db) {
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
