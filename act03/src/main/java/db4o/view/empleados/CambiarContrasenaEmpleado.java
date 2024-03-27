package db4o.view.empleados;

import db4o.config.Db4oHelper;
import db4o.model.Empleado;
import db4o.controller.EmpleadoController;
import db4o.utilities.Util;

import static db4o.utilities.Util.printLnRed;
import static db4o.utilities.Util.printlnGreen;
import static db4o.view.empleados.ModificarPerfilEmpleado.buscarEmpleado;

/**
 * Maneja el proceso de cambio de contraseña para los empleados.
 * Interactúa con el usuario para realizar el cambio de contraseña de un empleado específico,
 * validando la contraseña antigua antes de establecer una nueva.
 * <p>
 * Este proceso incluye la búsqueda del empleado por ID o nombre de usuario,
 * la verificación de la contraseña actual, y la actualización a la nueva contraseña en la base de datos.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see Empleado
 * @see EmpleadoController
 * @see Util
 */
public class CambiarContrasenaEmpleado {

    private static final Util util = new Util();
    private static final EmpleadoController EmpleadoController = new EmpleadoController(Db4oHelper.getDb());

    /**
     * Ejecuta el flujo para cambiar la contraseña de un empleado.
     * Solicita al usuario la identificación del empleado y las contraseñas (antigua y nueva),
     * y realiza la actualización si la infdb4oación es válida.
     */
    public static void run() {
        try {
            printlnGreen("--- CAMBIAR CONTRASEÑA DE EMPLEADO ---");

            Empleado empleado = buscarEmpleado();

            if (empleado == null) {
                printLnRed("Empleado no encontrado.");
                return;
            }

            String antiguaContrasena = util.pideTexto("Introduce la contraseña antigua: ");
            String nuevaContrasena = util.pideTexto("Introduce la nueva contraseña: ");

            if (!empleado.getContrasena().equals(antiguaContrasena)) {
                printLnRed("La contraseña antigua no coincide.");
                return;
            }

            empleado.setContrasena(nuevaContrasena);
            boolean exito = EmpleadoController.updatePassword(empleado);

            if (exito) {
                printlnGreen("Contraseña cambiada correctamente.");
            } else {
                printLnRed("Error al cambiar la contraseña.");
            }
        } catch (Exception e) {
            printLnRed("Error al cambiar la contraseña: " + e.getMessage());
        }
    }
}
