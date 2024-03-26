package test;

import model.Empleado;
import model.Incidencia;
import controller.EmpleadoController;
import controller.IncidenciaController;

import java.text.SimpleDateFormat;

public class TestDataInserter {

    private static final EmpleadoController empleadoController = new EmpleadoController();
    private static final IncidenciaController incidenciaController = new IncidenciaController();

    public static void start() {
        empleadoController.insert(new Empleado("agonzalez", "password", "Ana Gonzalez", "123456789"));
        empleadoController.insert(new Empleado("jramirez", "password", "Juan Ramirez", "123456780"));
        empleadoController.insert(new Empleado("afernandez", "password", "Antonio Fernandez", "123456781"));
        empleadoController.insert(new Empleado("smartinez", "password", "Sonia Martinez", "123456782"));
        empleadoController.insert(new Empleado("lsuarez", "password", "Luis Suarez", "123456783"));
    }

    private void insertarIncidencias() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Empleado agonzalez = empleadoController.findOne("agonzalez");
        Empleado jramirez = empleadoController.findOne("jramirez");
        Empleado afernandez = empleadoController.findOne("afernandez");
        Empleado smartinez = empleadoController.findOne("smartinez");
        Empleado lsuarez = empleadoController.findOne("lsuarez");
        incidenciaController.insert(new Incidencia(sdf.parse("2019-09-21 15:27:14"), agonzalez, jramirez, "La impresora no tiene tóner.", "U"));
        incidenciaController.insert(new Incidencia(sdf.parse("2019-09-22 10:28:37"), jramirez, afernandez, "No se ha entregado la documentación del expediente EXP324.", "N"));
        incidenciaController.insert(new Incidencia(sdf.parse("2019-09-22 16:28:45"), smartinez, jramirez, "No quedan folios.", "N"));
        incidenciaController.insert(new Incidencia(sdf.parse("2019-09-23 11:03:05"), smartinez, lsuarez, "El ordenador de recepción no funciona.", "U"));
        incidenciaController.insert(new Incidencia(sdf.parse("2019-09-28 13:11:29"), jramirez, lsuarez, "Mi portátil no puede acceder a la wifi.", "N"));
    }
}
