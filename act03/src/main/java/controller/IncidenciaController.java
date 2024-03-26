package controller;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import model.Incidencia;
import config.Db4oHelper;
import java.util.List;

public class IncidenciaController {

    public void insertarIncidencia(Incidencia incidencia) {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            db.store(incidencia);
        } finally {
            Db4oHelper.closeDB();
        }
    }

    public List<Incidencia> listarIncidencias() {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            Query query = db.query();
            query.constrain(Incidencia.class);
            return query.execute();
        } finally {
            Db4oHelper.closeDB();
        }
    }

    // Métodos adicionales como actualizar, eliminar, etc., pueden ser agregados aquí
}
