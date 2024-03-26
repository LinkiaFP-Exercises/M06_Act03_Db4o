package test;

import com.db4o.ObjectContainer;
import controller.EmpleadoController;
import controller.IncidenciaController;
import model.Empleado;
import model.Incidencia;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class TestData {
    private final EmpleadoController empleadoController;
    private final IncidenciaController incidenciaController;

    private final Empleado agonzalez = new Empleado("agonzalez", "password", "Ana Gonzalez", "123456789");
    private final Empleado jramirez;
    private final Empleado afernandez;
    private final Empleado smartinez;
    private final Empleado lsuarez;
    private final Incidencia incidencia01;
    private final Incidencia incidencia02;
    private final Incidencia incidencia03;
    private final Incidencia incidencia04;
    private final Incidencia incidencia05;
    private final Empleado empleadoTest = new Empleado("testuser", "testpass", "Test User", "000000000");
    private final Incidencia incidenciaTest = new Incidencia(convertToDate(2020, 1, 1, 0, 0, 1), agonzalez, empleadoTest, "Incidencia de prueba", "N");


    public TestData(ObjectContainer db) {
        empleadoController = new EmpleadoController(db);
        incidenciaController = new IncidenciaController(db);

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
            test.insertarEmpleados();
            test.insertarIncidencias();
//            test.readAll();
            test.testInsertarEmpleado();
            test.testFindOneEmpleado();
            test.testFindAllEmpleados();
            test.testUpdateEmpleado();
            test.testDeleteEmpleado();
            test.testInsertIncidencia();
            test.testFindIncidencia();
            test.testFindAllIncidencia();
            test.testUpdateIncidencia();
            test.testDeleteIncidencia();
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

    private Date convertToDate(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public void testInsertarEmpleado() {
        System.out.print("Test Insertar Empleado: ");
        try {
            empleadoController.insert(empleadoTest);
            if (empleadoController.findOne("testuser") != null) {
                System.out.println("OK.");
            } else {
                System.out.println("FAIL.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void testFindOneEmpleado() {
        System.out.print("Test FindOne Empleado: ");
        Empleado result = empleadoController.findOne(empleadoTest.getNombreUsuario());
        if (result != null && result.getNombreUsuario().equals(empleadoTest.getNombreUsuario())) {
            System.out.println("OK.");
        } else {
            System.out.println("FAIL.");
        }
    }

    public void testFindAllEmpleados() {
        System.out.print("Test FindAll Empleados: ");
        List<Empleado> results = empleadoController.findAll();
        if (results.size() == 6) {
            System.out.println("OK.");
        } else {
            System.out.println("FAIL.");
        }
    }

    public void testUpdateEmpleado() {
        System.out.print("Test Update Empleado: ");
        Empleado toUpdate = empleadoController.findOne(empleadoTest.getNombreUsuario());
        String originalTelefono = toUpdate.getTelefonoContacto();
        toUpdate.setTelefonoContacto("999999999");
        empleadoController.update(toUpdate);
        Empleado updated = empleadoController.findOne(empleadoTest.getNombreUsuario());
        if (updated.getTelefonoContacto().equals("999999999")) {
            System.out.println("OK.");
        } else {
            System.out.println("FAIL.");
            toUpdate.setTelefonoContacto(originalTelefono);
            empleadoController.update(toUpdate);
        }
    }

    public void testDeleteEmpleado() {
        System.out.print("Test Delete Empleado: ");
        empleadoController.delete(empleadoTest.getNombreUsuario());
        if (empleadoController.findOne(empleadoTest.getNombreUsuario()) == null) {
            System.out.println("OK.");
        } else {
            System.out.println("FAIL.");
        }
    }

    public void testInsertIncidencia() {
        System.out.print("Test Insert Incidencia: ");
        incidenciaController.insert(incidenciaTest);
        Incidencia found = incidenciaController.findById(incidenciaTest.getId());
        if (found != null && found.getDetalle().equals(incidenciaTest.getDetalle())) {
            System.out.println("OK.");
        } else {
            System.out.println("FAIL.");
        }
    }

    public void testFindIncidencia() {
        System.out.print("Test Find Incidencia: ");
        // Asumiendo que tienes un método para obtener el ID después de insertar
        Incidencia found = incidenciaController.findById(incidenciaTest.getId());
        if (found != null && found.getDetalle().equals(incidenciaTest.getDetalle())) {
            System.out.println("OK.");
        } else {
            System.out.println("FAIL.");
        }
    }

    public void testFindAllIncidencia() {
        System.out.print("Test FindAll Incidencias: ");
        List<Incidencia> results = incidenciaController.findAll();
        if (results.size() == 6) {
            System.out.println("OK.");
        } else {
            System.out.println("FAIL.");
        }
    }

    public void testUpdateIncidencia() {
        System.out.print("Test Update Incidencia: ");
        incidenciaTest.setDetalle("Detalle actualizado");
        incidenciaController.update(incidenciaTest);
        Incidencia updated = incidenciaController.findById(incidenciaTest.getId());
        if (updated.getDetalle().equals("Detalle actualizado")) {
            System.out.println("OK.");
        } else {
            System.out.println("FAIL.");
        }
    }

    public void testDeleteIncidencia() {
        System.out.print("Test Delete Incidencia: ");
        incidenciaController.delete(incidenciaTest.getId());
        if (incidenciaController.findById(incidenciaTest.getId()) == null) {
            System.out.println("OK.");
        } else {
            System.out.println("FAIL.");
        }
    }

}
