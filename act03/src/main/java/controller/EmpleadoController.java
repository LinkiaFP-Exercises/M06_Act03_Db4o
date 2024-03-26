package controller;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import model.Empleado;
import config.Db4oHelper;
import java.util.List;

public class EmpleadoController {

    public void insertarEmpleado(Empleado empleado) {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            db.store(empleado);
        } finally {
            Db4oHelper.closeDB();
        }
    }

    public List<Empleado> listarEmpleados() {
        ObjectContainer db = Db4oHelper.openDB();
        try {
            Query query = db.query();
            query.constrain(Empleado.class);
            return query.execute();
        } finally {
            Db4oHelper.closeDB();
        }
    }

    // Aquí puedes agregar métodos adicionales como actualizar, eliminar, etc.
}
