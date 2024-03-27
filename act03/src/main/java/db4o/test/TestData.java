package db4o.test;

import com.db4o.ObjectContainer;
import db4o.config.Db4oHelper;
import db4o.controller.EmpleadoController;
import db4o.controller.IncidenciaController;
import db4o.model.Empleado;
import db4o.model.Incidencia;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class TestData {
    private final EmpleadoController empleadoController;
    private final IncidenciaController incidenciaController;
    private final List<Empleado> empleadoList;
    private final List<Incidencia> incidenciaList;

    public TestData(ObjectContainer db) {
        empleadoController = new EmpleadoController(db);
        incidenciaController = new IncidenciaController(db);

        empleadoList = List.of(
                new Empleado("jramirez", "password", "Juan Ramirez", "123456780"),
                new Empleado("afernandez", "password", "Antonio Fernandez", "123456781"),
                new Empleado("smartinez", "password", "Sonia Martinez", "123456782"),
                new Empleado("lsuarez", "password", "Luis Suarez", "123456783"),
                new Empleado("agonzalez", "password", "Ana Gonzalez", "123456789")
        );

        incidenciaList = List.of(
                new Incidencia(convertToDate(2019, 9, 21, 15, 27, 14), empleadoList.getLast(), empleadoList.getFirst(), "La impresora no tiene tóner.", "U"),
                new Incidencia(convertToDate(2018, 3, 22, 10, 28, 37), empleadoList.getFirst(), empleadoList.get(1), "No se ha entregado la documentación del expediente EXP324.", "N"),
                new Incidencia(convertToDate(2017, 2, 22, 16, 28, 45), empleadoList.get(2), empleadoList.getFirst(), "No quedan folios.", "N"),
                new Incidencia(convertToDate(2019, 9, 23, 11, 3, 5), empleadoList.get(2), empleadoList.get(3), "El ordenador de recepción no funciona.", "U"),
                new Incidencia(convertToDate(2019, 10, 28, 13, 11, 29), empleadoList.getFirst(), empleadoList.get(3), "Mi portátil no puede acceder a la wifi.", "N")
        );
    }

    private Date convertToDate(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void insert() {
        try {
            TestData test = new TestData(Db4oHelper.getDb());
            test.borrarDatosDePrueba();
            test.insertarEmpleados();
            test.insertarIncidencias();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void borrarDatosDePrueba() {
        empleadoController.limpiarEmpleados();
        incidenciaController.limpiarIncidencias();
    }

    private void insertarEmpleados() {
        empleadoList.forEach(empleadoController::insert);
    }

    private void insertarIncidencias() {
        incidenciaList.forEach(incidenciaController::insert);
    }

}
