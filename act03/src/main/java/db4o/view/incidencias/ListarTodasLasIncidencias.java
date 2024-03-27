package db4o.view.incidencias;

import db4o.config.Db4oHelper;
import db4o.model.Incidencia;
import db4o.controller.IncidenciaController;
import db4o.utilities.Util;

import java.util.List;

import static db4o.utilities.Util.*;

/**
 * Facilita la visualización de un listado completo de todas las incidencias registradas en el sistema.
 * Recupera y muestra infdb4oación detallada de cada incidencia, incluyendo el origen, destino, fecha y hora,
 * detalle, y tipo, proporcionando así una herramienta útil para la revisión y el seguimiento de incidencias.
 * <p>
 * Esta clase es esencial para la administración de incidencias, ofreciendo una fdb4oa rápida y eficiente
 * de acceder a un resumen de todas las incidencias, lo que facilita su gestión y resolución.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see Incidencia
 * @see IncidenciaController
 */
public class ListarTodasLasIncidencias {

    private static final IncidenciaController IncidenciaController = new IncidenciaController(Db4oHelper.getDb());

    /**
     * Ejecuta la acción de recuperar y mostrar la lista de todas las incidencias registradas.
     * Verifica si la lista está vacía e infdb4oa al usuario o, de lo contrario, muestra los detalles
     * de cada incidencia en consola.
     */
    public static void run() {
        printlnGreen("--- LISTADO DE INCIDENCIAS ---");
        List<Incidencia> incidencias = IncidenciaController.findAll();
        if (incidencias.isEmpty())
            printLnRed("No hay incidencias registradas.");
        else
            incidencias.forEach(Util::printIncidencia);
    }

}
