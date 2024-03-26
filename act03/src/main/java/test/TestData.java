package test;

import com.db4o.ObjectContainer;
import controller.EmpleadoController;
import controller.IncidenciaController;
import model.Empleado;
import model.Incidencia;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TestData {
    private final EmpleadoController empleadoController;
    private final IncidenciaController incidenciaController;

    private final Empleado agonzalez;
    private final Empleado jramirez;
    private final Empleado afernandez;
    private final Empleado smartinez;
    private final Empleado lsuarez;
    private final Incidencia incidencia01;
    private final Incidencia incidencia02;
    private final Incidencia incidencia03;
    private final Incidencia incidencia04;
    private final Incidencia incidencia05;

    public TestData(ObjectContainer db) {
        empleadoController = new EmpleadoController(db);
        incidenciaController = new IncidenciaController(db);
        agonzalez = new Empleado("agonzalez", "password", "Ana Gonzalez", "123456789");
        jramirez = new Empleado("jramirez", "password", "Juan Ramirez", "123456780");
        afernandez = new Empleado("afernandez", "password", "Antonio Fernandez", "123456781");
        smartinez = new Empleado("smartinez", "password", "Sonia Martinez", "123456782");
        lsuarez = new Empleado("lsuarez", "password", "Luis Suarez", "123456783");

        incidencia01 = new Incidencia(convertToDate(2019, 9, 21, 15, 27, 14), agonzalez, jramirez, "La impresora no tiene tóner.", "U");
        incidencia02 = new Incidencia(convertToDate(2018, 3, 22, 10, 28, 37), jramirez, afernandez, "No se ha entregado la documentación del expediente EXP324.", "N");
        incidencia03 = new Incidencia(convertToDate(2017, 2, 22, 16, 28, 45), smartinez, jramirez, "No quedan folios.", "N");
        incidencia04 = new Incidencia(convertToDate(2019, 9, 23, 11, 3, 5), smartinez, lsuarez, "El ordenador de recepción no funciona.", "U");
        incidencia05 = new Incidencia(convertToDate(2019, 10, 28, 13, 11, 29), jramirez, lsuarez, "Mi portátil no puede acceder a la wifi.", "N");
    }

    public static void start(ObjectContainer db) {
        try {
            TestData test = new TestData(db);
            test.borrarDatosDePrueba();
            Thread.sleep(1000);
            test.insertarEmpleados();
            test.insertarIncidencias();
            test.readAll();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void readAll() {
        empleadoController.findAll().forEach(System.out::println);
        incidenciaController.findAll().forEach(System.out::println);
    }
    public void borrarDatosDePrueba() {
        empleadoController.limpiarEmpleados();
        incidenciaController.limpiarIncidencias();
    }

    private void insertarEmpleados() {
        empleadoController.insert(agonzalez);
        empleadoController.insert(jramirez);
        empleadoController.insert(afernandez);
        empleadoController.insert(smartinez);
        empleadoController.insert(lsuarez);
    }

    private void insertarIncidencias() {
        incidenciaController.insert(incidencia01);
        incidenciaController.insert(incidencia02);
        incidenciaController.insert(incidencia03);
        incidenciaController.insert(incidencia04);
        incidenciaController.insert(incidencia05);
    }

    // Utilidad para convertir LocalDateTime a Date
    private Date convertToDate(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
