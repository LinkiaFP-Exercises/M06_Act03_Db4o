package db4o.view.empleados;

import db4o.config.Db4oHelper;
import db4o.model.Empleado;
import db4o.controller.EmpleadoController;
import db4o.utilities.Util;

import static db4o.utilities.Util.printLnRed;
import static db4o.utilities.Util.printlnGreen;

/**
 * Permite la inserción de nuevos empleados en el sistema.
 * Solicita al usuario los datos necesarios para registrar un nuevo empleado, como nombre de usuario,
 * contraseña, nombre completo y teléfono de contacto, y procede con la inserción a través del {@link EmpleadoController}.
 * <p>
 * Esta clase es un ejemplo de cómo se implementan operaciones de creación de registros dentro de la aplicación,
 * asegurando que la información requerida sea recopilada de manera eficiente y segura antes de realizar la inserción.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see Empleado
 * @see EmpleadoController
 * @see Util
 */
public class InsertarEmpleado {

    private static final Util util = new Util();
    private static final EmpleadoController EmpleadoController = new EmpleadoController(Db4oHelper.getDb());

    /**
     * Ejecuta el proceso de recopilación de datos del nuevo empleado y su inserción en la base de datos.
     * Interactúa con el usuario para obtener la información necesaria y utiliza {@link EmpleadoController}
     * para llevar a cabo la operación de inserción.
     */
    public static void run() {
        try {
            printlnGreen("--- INSERTAR NUEVO EMPLEADO ---");
            String nombreUsuario = util.pideTexto("Introduce el nombre de usuario: ");
            String contrasena = util.pideTexto("Introduce la contraseña: ");
            String nombreCompleto = util.pideTexto("Introduce el nombre completo: ");
            String telefonoContacto = util.pideTexto("Introduce el teléfono de contacto: ");

            Empleado nuevoEmpleado = new Empleado(nombreUsuario, contrasena, nombreCompleto, telefonoContacto);

            EmpleadoController.insert(nuevoEmpleado);
            printlnGreen("Empleado insertado correctamente.");
        } catch (Exception e) {
            printLnRed("Error insertando el empleado: " + e.getMessage());
        }
    }
}

