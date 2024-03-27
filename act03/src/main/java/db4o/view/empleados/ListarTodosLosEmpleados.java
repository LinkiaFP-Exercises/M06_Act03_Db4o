package db4o.view.empleados;

import db4o.config.Db4oHelper;
import db4o.model.Empleado;
import db4o.controller.EmpleadoController;

import java.util.List;

import static db4o.utilities.Util.*;

/**
 * Facilita la visualización de un listado completo de todos los empleados registrados en el sistema.
 * Recupera la infdb4oación de cada empleado a través del {@link EmpleadoController} y la presenta al usuario
 * en fdb4oato de lista, mostrando detalles como el ID, nombre de usuario, nombre completo y teléfono de contacto
 * de cada empleado.
 * <p>
 * Esta clase es esencial para operaciones de administración y supervisión, permitiendo a los usuarios
 * obtener una visión general de los empleados existentes en el sistema.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see Empleado
 * @see EmpleadoController
 */
public class ListarTodosLosEmpleados {

    private static final EmpleadoController EmpleadoController = new EmpleadoController(Db4oHelper.getDb());

    /**
     * Ejecuta la acción de recuperar y mostrar la lista de todos los empleados.
     * Verifica si la lista está vacía e infdb4oa al usuario o, de lo contrario, muestra los detalles
     * de cada empleado en la consola.
     */
    public static void run() {
        printlnGreen("--- LISTADO DE EMPLEADOS ---");
        List<Empleado> empleados = EmpleadoController.findAll();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleado empleado : empleados) {
                printLnYellow(printEmpleado(empleado));
            }
        }
    }

}
