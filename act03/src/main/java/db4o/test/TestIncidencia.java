package db4o.test;

import db4o.config.Db4oHelper;
import db4o.model.Incidencia;
import db4o.model.Empleado;
import db4o.controller.IncidenciaController;
import db4o.controller.EmpleadoController;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static db4o.utilities.Util.*;

/**
 * Clase para probar las funcionalidades del servicio de incidencias.
 * Realiza pruebas como listar todas las incidencias, crear incidencias, buscar incidencia por ID,
 * obtener incidencias por origen y destino. Estas pruebas ayudan a verificar el correcto funcionamiento
 * de las operaciones relacionadas con incidencias al inicio de la aplicación.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see IncidenciaController
 * @see Incidencia
 */
public class TestIncidencia {

    private static final Logger log = Logger.getLogger(TestIncidencia.class.getName());
    private static final IncidenciaController IncidenciaController = new IncidenciaController(Db4oHelper.getDb());
    private static final EmpleadoController EmpleadoController = new EmpleadoController(Db4oHelper.getDb());
    public static final String NOMBRE_USUARIO_ORIGEN = "agonzalez";
    public static final String NOMBRE_USUARIO_DESTINO = "jramirez";

    private static String id;

    public static void doAll() {
        testListarTodasLasIncidencias();
        testCrearIncidencia();
        testObtenerIncidenciaPorId();
        testObtenerIncidenciasPorOrigen();
        testObtenerIncidenciasPorDestino();
    }

    private static void testListarTodasLasIncidencias() {
        try {
            printYellow("LISTANDO TODAS LAS INCIDENCIAS: ");
            List<Incidencia> incidencias = IncidenciaController.findAll();
            if (incidencias != null && !incidencias.isEmpty()) {
                printSuccess();
                id = incidencias.getFirst().getId();
            }
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testCrearIncidencia() {
        try {
            // Asumiendo que ya tienes un método para buscar empleados por nombre de usuario
            Empleado empleadoOrigen = EmpleadoController.findOne(NOMBRE_USUARIO_ORIGEN);
            Empleado empleadoDestino = EmpleadoController.findOne(NOMBRE_USUARIO_DESTINO);

            if (empleadoOrigen == null || empleadoDestino == null) {
                printLnRed("Empleado de origen o destino no encontrado.");
                return;
            }

            Incidencia nuevaIncidencia = new Incidencia();
            // Configura la fecha y hora actual para la incidencia
            nuevaIncidencia.setFechaHora(new Date(System.currentTimeMillis()));
            nuevaIncidencia.setEmpleadoOrigen(empleadoOrigen);
            nuevaIncidencia.setEmpleadoDestino(empleadoDestino);
            nuevaIncidencia.setDetalle("Detalle de prueba para la incidencia");
            nuevaIncidencia.setTipo("N"); // N para normal, U para urgente

            printYellow("INSERTANDO INCIDENCIA: ");
            IncidenciaController.insert(nuevaIncidencia);
            printSuccess();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testObtenerIncidenciaPorId() {
        try {
            printYellow("BUSCANDO INCIDENCIA POR ID: ");
            Incidencia incidencia = IncidenciaController.findById(id);
            if (incidencia != null) {
                printSuccess();
            } else {
                printFail();
            }
        } catch (Exception e) { log.severe(e.getMessage()); }
    }


    private static void testObtenerIncidenciasPorOrigen() {
        try {
            printYellow("BUSCANDO INCIDENCIAS ORIGINADAS POR EMPLEADO: ");
            Empleado empleadoOrigen = EmpleadoController.findOne(NOMBRE_USUARIO_ORIGEN);
            List<Incidencia> incidencias = IncidenciaController.findIncidenciasByOrigin(empleadoOrigen);
            if (incidencias != null && !incidencias.isEmpty()) {
                printSuccess();
            } else {
                printFail();
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
            printFail();
        }
    }


    private static void testObtenerIncidenciasPorDestino() {
        try {
            printYellow("BUSCANDO INCIDENCIAS DESTINADAS AL EMPLEADO: ");
            Empleado empleadoDestino = EmpleadoController.findOne(NOMBRE_USUARIO_DESTINO);
            List<Incidencia> incidencias = IncidenciaController.findIncidenciasByDestiny(empleadoDestino);
            if (incidencias != null && !incidencias.isEmpty()) {
                printSuccess();
            } else {
                printFail();
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
            printFail();
        }
    }

    private static void printSuccess() {
        printlnGreen("SUCCESS!!!");
    }

    // Método para imprimir fallo
    private static void printFail() {
        printLnRed("FAILURE!!!");
    }
}
