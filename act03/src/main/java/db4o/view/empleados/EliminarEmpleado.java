package db4o.view.empleados;

import db4o.config.Db4oHelper;
import db4o.model.Empleado;
import db4o.controller.EmpleadoController;
import db4o.utilities.Util;

import static db4o.utilities.Util.printLnRed;
import static db4o.utilities.Util.printlnGreen;
import static db4o.view.empleados.ModificarPerfilEmpleado.buscarEmpleado;

/**
 * Facilita la interfaz para eliminar un empleado del sistema.
 * Interactúa con el usuario a través de la consola para obtener el ID del empleado a eliminar,
 * verifica que el empleado exista y procede con su eliminación.
 * <p>
 * Este proceso implica una operación crítica y debe asegurarse de que el empleado a eliminar es el correcto
 * para evitar la eliminación accidental de datos.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see Empleado
 * @see EmpleadoController
 * @see Util
 */
public class EliminarEmpleado {

    private static final Util util = new Util();
    private static final EmpleadoController EmpleadoController = new EmpleadoController(Db4oHelper.getDb());

    /**
     * Ejecuta el flujo para eliminar un empleado. Solicita al usuario el nombre de usuario del empleado a eliminar,
     * busca al empleado para confirmar su existencia y, si se encuentra, lo elimina del sistema.
     */
    public static void run() {
        try {
            printlnGreen("--- ELIMINAR EMPLEADO ---");

            Empleado empleado = buscarEmpleado();

            if (empleado == null) {
                printLnRed("Empleado no encontrado.");
                return;
            }

            EmpleadoController.delete(empleado.getNombreUsuario());
            printlnGreen("Empleado eliminado correctamente.");

        } catch (Exception e) {
            printLnRed("Error eliminando el empleado: " + e.getMessage());
        }
    }

}
