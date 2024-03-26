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
    private final List<Empleado> empleadoList;
    private final List<Incidencia> incidenciaList;
    private final Empleado empleadoTest;
    private final Incidencia incidenciaTest;

    public TestData(ObjectContainer db) {
        empleadoController = new EmpleadoController(db);
        incidenciaController = new IncidenciaController(db);

        empleadoTest = new Empleado("testuser", "testpass", "Test User", "000000000");
        Empleado jramirez = new Empleado("jramirez", "password", "Juan Ramirez", "123456780");
        Empleado afernandez = new Empleado("afernandez", "password", "Antonio Fernandez", "123456781");
        Empleado smartinez = new Empleado("smartinez", "password", "Sonia Martinez", "123456782");
        Empleado lsuarez = new Empleado("lsuarez", "password", "Luis Suarez", "123456783");
        Empleado agonzalez = new Empleado("agonzalez", "password", "Ana Gonzalez", "123456789");
        empleadoList = List.of(agonzalez, jramirez, afernandez, smartinez, lsuarez);

        incidenciaTest = new Incidencia(convertToDate(2020, 1, 1, 0, 0, 1), agonzalez, empleadoTest, "Incidencia de prueba", "N");
        Incidencia incidencia01 = new Incidencia(convertToDate(2019, 9, 21, 15, 27, 14), agonzalez, jramirez, "La impresora no tiene tóner.", "U");
        Incidencia incidencia02 = new Incidencia(convertToDate(2018, 3, 22, 10, 28, 37), jramirez, afernandez, "No se ha entregado la documentación del expediente EXP324.", "N");
        Incidencia incidencia03 = new Incidencia(convertToDate(2017, 2, 22, 16, 28, 45), smartinez, jramirez, "No quedan folios.", "N");
        Incidencia incidencia04 = new Incidencia(convertToDate(2019, 9, 23, 11, 3, 5), smartinez, lsuarez, "El ordenador de recepción no funciona.", "U");
        Incidencia incidencia05 = new Incidencia(convertToDate(2019, 10, 28, 13, 11, 29), jramirez, lsuarez, "Mi portátil no puede acceder a la wifi.", "N");
        incidenciaList = List.of(incidencia01, incidencia02, incidencia03, incidencia04, incidencia05);
    }

    private Date convertToDate(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void start(ObjectContainer db) {
        try {
            TestData test = new TestData(db);
            test.borrarDatosDePrueba();
            test.insertarEmpleados();
            test.insertarIncidencias();
            test.testInsertarEmpleado();
            test.testFindOneEmpleado();
            test.testFindAllEmpleados();
            test.testUpdateEmpleado();
            test.testDeleteEmpleado();
            test.testInsertIncidencia();
            test.testFindIncidencia();
            test.testFindAllIncidencia();
            test.testFindIncidenciasPorOrigen();
            test.testFindIncidenciasPorDestino();
            test.testUpdateIncidencia();
            test.testDeleteIncidencia();
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

    public void testFindIncidenciasPorOrigen() {
        System.out.print("Test Find Incidencias Por Origen: ");
        // Insertamos una incidencia de prueba si es necesario o utilizamos las existentes
        List<Incidencia> incidenciasPorOrigen = incidenciaController.findIncidenciasByOrigin(empleadoList.getFirst());
        boolean testPassed = incidenciasPorOrigen.stream()
                .anyMatch(inc -> inc.getEmpleadoOrigen().equals(empleadoList.getFirst()));
        if (testPassed && incidenciasPorOrigen.size() == 2) {
            System.out.println("OK.");
        } else {
            System.out.println("FAIL.");
        }
    }

    public void testFindIncidenciasPorDestino() {
        System.out.print("Test Find Incidencias Por Destino: ");
        // Asumiendo que 'lsuarez' es un destino en al menos una incidencia
        List<Incidencia> incidenciasPorDestino = incidenciaController.findIncidenciasByDestiny(empleadoList.get(1));
        boolean testPassed = incidenciasPorDestino.stream()
                .anyMatch(inc -> inc.getEmpleadoDestino().equals(empleadoList.get(1)));
        if (testPassed && incidenciasPorDestino.size() == 2) {
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
