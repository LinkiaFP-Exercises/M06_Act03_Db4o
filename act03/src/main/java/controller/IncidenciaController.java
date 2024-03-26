package controller;

import com.db4o.ObjectContainer;
import model.Incidencia;

import java.util.List;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

public class IncidenciaController {

    private final ObjectContainer db;

    public IncidenciaController(ObjectContainer db) {
        this.db = db;
    }

    // Insertar una nueva incidencia
    public void insert(Incidencia incidencia) {
        try {
            db.store(incidencia);
        } catch (Exception e) {
            handleError(e, new Object(){}.getClass().getEnclosingMethod().getName());
        }
    }

    // Listar todas las incidencias
    public List<Incidencia> findAll() {
        try {
            return db.queryByExample(Incidencia.class);
        } catch (Exception e) {
            handleError(e, new Object(){}.getClass().getEnclosingMethod().getName());
            return null;
        }
    }

    // Eliminar una incidencia por su ID
    public void delete(String id) {
        try {
            Incidencia incidencia = findById(id);
            if (incidencia != null) {
                db.delete(incidencia);
            }
        } catch (Exception e) {
            handleError(e, new Object(){}.getClass().getEnclosingMethod().getName());
        }
    }

    // Limpiar todas las incidencias
    public void limpiarIncidencias() {
        try {
            findAll().forEach(db::delete);
        } catch (Exception e) {
            handleError(e, new Object(){}.getClass().getEnclosingMethod().getName());
        }
    }

    // Actualizar una incidencia existente
    public void update(Incidencia incidencia) {
        try {
            Incidencia existente = findById(incidencia.getId());
            if (existente != null) {
                existente.setEmpleadoOrigen(incidencia.getEmpleadoOrigen());
                existente.setEmpleadoDestino(incidencia.getEmpleadoDestino());
                existente.setDetalle(incidencia.getDetalle());
                existente.setTipo(incidencia.getTipo());
                db.store(existente);
            }
        } catch (Exception e) {
            handleError(e, new Object(){}.getClass().getEnclosingMethod().getName());
        }
    }

    // Buscar una incidencia por su ID
    private Incidencia findById(String id) {
        return (Incidencia) StreamSupport.stream(Spliterators.spliteratorUnknownSize(db.queryByExample(new Incidencia(id)).iterator(), 0), false)
                .findFirst().orElse(null);
    }

    private void handleError(Exception e, String methodName) {
        System.err.println("Exception in " + this.getClass().getSimpleName() + "." + methodName + "() : " + e.getMessage());
    }
}
