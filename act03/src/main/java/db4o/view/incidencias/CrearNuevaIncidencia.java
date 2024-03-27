package db4o.view.incidencias;

import db4o.config.Db4oHelper;
import db4o.controller.EmpleadoController;
import db4o.controller.IncidenciaController;
import db4o.model.Empleado;
import db4o.model.Incidencia;
import db4o.utilities.Util;

import java.util.Date;

import static db4o.utilities.Util.printLnRed;
import static db4o.utilities.Util.printlnGreen;

/**
 * Facilita la creación de una nueva incidencia en el sistema.
 * Solicita al usuario infdb4oación clave sobre la incidencia, como los empleados involucrados (origen y destino),
 * el detalle de la incidencia, y el tipo. Esta infdb4oación se utiliza para crear un registro de incidencia
 * que se añade a la base de datos a través del {@link IncidenciaController}.
 * <p>
 * Este proceso asegura que se recolecten todos los datos necesarios para una adecuada gestión y seguimiento
 * de las incidencias dentro de la organización.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see Empleado
 * @see Incidencia
 * @see EmpleadoController
 * @see IncidenciaController
 * @see Util
 */
public class CrearNuevaIncidencia {

    private static final Util util = new Util();
    private static final EmpleadoController EmpleadoController = new EmpleadoController(Db4oHelper.getDb());
    private static final IncidenciaController IncidenciaController = new IncidenciaController(Db4oHelper.getDb());

    /**
     * Ejecuta la lógica para recoger datos del usuario y registrar una nueva incidencia en el sistema.
     * Valida la existencia de los empleados involucrados y recopila la infdb4oación detallada de la incidencia
     * antes de proceder con su creación.
     */
    public static void run() {
        try {
            printlnGreen("--- CREAR NUEVA INCIDENCIA ---");
            String nombreUsuarioOrigen = util.pideTexto("Introduce el nombre de usuario del empleado origen: ");
            Empleado empleadoOrigen = EmpleadoController.findOne(nombreUsuarioOrigen);
            if (empleadoOrigen == null) {
                printLnRed("Empleado de origen no encontrado.");
                return;
            }

            String nombreUsuarioDestino = util.pideTexto("Introduce el nombre de usuario del empleado destino: ");
            Empleado empleadoDestino = EmpleadoController.findOne(nombreUsuarioDestino);
            if (empleadoDestino == null) {
                printLnRed("Empleado de destino no encontrado.");
                return;
            }

            String detalle = util.pideTexto("Introduce el detalle de la incidencia: ");
            String tipo;
            do {
                tipo = util.pideTexto("Introduce el tipo de la incidencia (N/U): ")
                        .substring(0,1).toUpperCase();
            } while (!tipo.matches("^[NU]$"));

            Incidencia nuevaIncidencia = new Incidencia();
            nuevaIncidencia.setEmpleadoOrigen(empleadoOrigen);
            nuevaIncidencia.setEmpleadoDestino(empleadoDestino);
            nuevaIncidencia.setDetalle(detalle);
            nuevaIncidencia.setTipo(tipo);
            nuevaIncidencia.setFechaHora(new Date(System.currentTimeMillis()));

            IncidenciaController.insert(nuevaIncidencia);
            printlnGreen("Incidencia creada correctamente.");
        } catch (Exception e) {
            printLnRed("Error al crear la incidencia: " + e.getMessage());
        }
    }

}
