package db4o.view.incidencias;

import db4o.config.Db4oHelper;
import db4o.model.Empleado;
import db4o.model.Incidencia;
import db4o.controller.IncidenciaController;
import db4o.utilities.Util;

import java.util.List;

import static db4o.utilities.Util.*;
import static db4o.view.empleados.ModificarPerfilEmpleado.buscarEmpleado;

/**
 * Permite la recuperación y visualización de todas las incidencias dirigidas a un empleado específico.
 * Esta clase facilita la identificación y el seguimiento de incidencias que han sido reportadas con
 * un empleado como destino, proporcionando una herramienta útil para la revisión de las acciones
 * o medidas necesarias en respuesta a dichas incidencias.
 * <p>
 * La funcionalidad es esencial para mantener una comunicación efectiva y la gestión de incidencias
 * dentro de una organización, asegurando que los empleados y los gerentes estén infdb4oados sobre las
 * incidencias relevantes.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see Empleado
 * @see Incidencia
 * @see IncidenciaController
 */
public class ObtenerIncidenciasPorDestino {

    private static final IncidenciaController IncidenciaController = new IncidenciaController(Db4oHelper.getDb());

    /**
     * Ejecuta la búsqueda de incidencias destinadas a un empleado específico y muestra sus detalles.
     * Solicita al usuario el ID del empleado destino y utiliza {@link IncidenciaController} para buscar
     * y listar las incidencias. Muestra un resumen de cada incidencia encontrada.
     */
    public static void run() {
        try {
            printlnGreen("--- OBTENER INCIDENCIAS POR DESTINO ---");
            Empleado empleadoDestino = buscarEmpleado();
            if (empleadoDestino == null) {
                printLnRed("Empleado destino no encontrado.");
                return;
            }

            List<Incidencia> incidenciasPorDestino = IncidenciaController.findIncidenciasByDestiny(empleadoDestino);
            if (incidenciasPorDestino.isEmpty()) {
                printLnRed("No hay incidencias registradas para el empleado destino: " + empleadoDestino.getNombreUsuario());
            } else {
                printLnYellow("Incidencias dirigidas al empleado: " + empleadoDestino.getNombreUsuario());
                incidenciasPorDestino.forEach(Util::printIncidencia);
            }
        } catch (Exception e) {
            printLnRed("Error al obtener las incidencias por destino: " + e.getMessage());
        }
    }

}
