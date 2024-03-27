package db4o.view.incidencias;

import db4o.config.Db4oHelper;
import db4o.model.Incidencia;
import db4o.controller.IncidenciaController;
import db4o.utilities.Util;

import static db4o.utilities.Util.*;

/**
 * Permite a los usuarios buscar y mostrar los detalles de una incidencia específica usando su ID.
 * Esta clase proporciona una funcionalidad crítica para el seguimiento de incidencias, permitiendo
 * a los usuarios acceder a infdb4oación detallada sobre incidencias particulares para su revisión
 * o gestión adicional.
 * <p>
 * La capacidad de recuperar incidencias por ID es esencial para la administración efectiva de incidencias,
 * facilitando la identificación y el acceso rápido a la infdb4oación relevante de incidencias registradas.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see Incidencia
 * @see IncidenciaController
 * @see Util
 */
public class ObtenerIncidenciaPorId {

    private static final Util util = new Util();
    private static final IncidenciaController IncidenciaController = new IncidenciaController(Db4oHelper.getDb());

    /**
     * Ejecuta la búsqueda de una incidencia por su ID y muestra sus detalles si se encuentra.
     * Interactúa con el usuario para obtener el ID de la incidencia y utiliza {@link IncidenciaController}
     * para buscar la incidencia en la base de datos.
     */
    public static void run() {
        try {
            printlnGreen("--- OBTENER INCIDENCIA POR ID ---");
            String idIncidencia = util.pideTexto("Introduce el ID de la incidencia: ");
            Incidencia incidencia = IncidenciaController.findById(idIncidencia);

            if (incidencia != null) {
                printlnGreen("Incidencia encontrada: ");
                printIncidencia(incidencia);
            } else {
                printLnRed("Incidencia no encontrada.");
            }
        } catch (Exception e) {
            printLnRed("Error al obtener la incidencia: " + e.getMessage());
        }
    }

}
