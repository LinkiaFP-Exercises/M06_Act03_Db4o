package db4o.view.incidencias;

import db4o.config.Db4oHelper;
import db4o.controller.IncidenciaController;
import db4o.model.Empleado;
import db4o.model.Incidencia;
import db4o.utilities.Util;

import java.util.List;

import static db4o.utilities.Util.*;
import static db4o.view.empleados.ModificarPerfilEmpleado.buscarEmpleado;

/**
 * Permite a los usuarios buscar y visualizar las incidencias reportadas por un empleado específico,
 * usando su ID como criterio de búsqueda. Este proceso es importante para el seguimiento y análisis
 * de las incidencias originadas por un mismo empleado, permitiendo identificar patrones o áreas de
 * mejora dentro de la organización.
 * <p>
 * Es una herramienta clave para la gestión de incidencias, promoviendo una mayor transparencia y
 * responsabilidad entre los empleados respecto a las incidencias reportadas.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see Empleado
 * @see Incidencia
 * @see IncidenciaController
 */
public class ObtenerIncidenciasPorOrigen {

    private static final IncidenciaController IncidenciaController = new IncidenciaController(Db4oHelper.getDb());

    /**
     * Realiza la búsqueda y muestra las incidencias reportadas por un empleado origen.
     * Interactúa con el usuario para obtener el ID del empleado origen y utiliza {@link IncidenciaController}
     * para recuperar y mostrar las incidencias asociadas. Proporciona detalles relevantes de cada
     * incidencia encontrada para facilitar su revisión y gestión.
     */
    public static void run() {
        try {
            printlnGreen("--- OBTENER INCIDENCIAS POR ORIGEN ---");
            Empleado empleadoOrigen = buscarEmpleado();
            if (empleadoOrigen == null) {
                printLnRed("Empleado de origen no encontrado.");
                return;
            }

            List<Incidencia> incidenciasPorOrigen = IncidenciaController.findIncidenciasByOrigin(empleadoOrigen);
            if (incidenciasPorOrigen.isEmpty()) {
                printLnRed("No hay incidencias registradas para el empleado de origen: " + empleadoOrigen.getNombreUsuario());
            } else {
                printLnYellow("Incidencias registradas por el empleado de origen: " + empleadoOrigen.getNombreUsuario());
                incidenciasPorOrigen.forEach(Util::printIncidencia);
            }
        } catch (Exception e) {
            printLnRed("Error al obtener las incidencias por origen: " + e.getMessage());
        }
    }

}
