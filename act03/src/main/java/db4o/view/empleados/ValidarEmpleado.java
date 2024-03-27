package db4o.view.empleados;

import db4o.config.Db4oHelper;
import db4o.controller.EmpleadoController;
import db4o.utilities.Util;

import static db4o.utilities.Util.printLnRed;
import static db4o.utilities.Util.printlnGreen;

/**
 * Permite la validación de las credenciales de un empleado.
 * Esta clase es responsable de solicitar al usuario el nombre de usuario y la contraseña,
 * y luego utilizar el {@link EmpleadoController} para verificar si coinciden con un empleado existente
 * en la base de datos.
 * <p>
 * Es una operación esencial de seguridad que asegura que solo los usuarios autenticados puedan acceder
 * a funciones restringidas dentro de la aplicación.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see EmpleadoController
 * @see Util
 */
public class ValidarEmpleado {

    private static final Util util = new Util();
    private static final EmpleadoController EmpleadoController = new EmpleadoController(Db4oHelper.getDb());

    /**
     * Ejecuta la validación del empleado mediante sus credenciales.
     * Interactúa con el usuario para recoger su nombre de usuario y contraseña,
     * y verifica si estas credenciales son válidas.
     */
    public static void run() {
        try {
            printlnGreen("--- VALIDAR ENTRADA DE EMPLEADO ---");
            String nombreUsuario = util.pideTexto("Introduce el nombre de usuario: ");
            String contrasena = util.pideTexto("Introduce la contraseña: ");

            boolean isValid = EmpleadoController.validarEmpleado(nombreUsuario, contrasena);

            if (isValid) {
                printlnGreen("Empleado validado correctamente.");
            } else {
                printLnRed("Credenciales incorrectos.");
            }
        } catch (Exception e) {
            printLnRed("Error validando el empleado: " + e.getMessage());
        }
    }

}

