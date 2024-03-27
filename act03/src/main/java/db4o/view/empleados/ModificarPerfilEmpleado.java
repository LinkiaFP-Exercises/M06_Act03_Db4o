package db4o.view.empleados;

import db4o.config.Db4oHelper;
import db4o.model.Empleado;
import db4o.controller.EmpleadoController;
import db4o.utilities.Util;

import static db4o.utilities.Util.printLnRed;
import static db4o.utilities.Util.printlnGreen;

/**
 * Maneja la funcionalidad para modificar la información del perfil de un empleado existente.
 * Permite al usuario actualizar el nombre de usuario, nombre completo y teléfono de contacto del empleado.
 * Antes de realizar cualquier modificación, se busca al empleado para asegurar que existe en el sistema.
 * <p>
 * Si se proporcionan nuevos valores para los campos, estos se actualizarán; de lo contrario, se mantendrán los actuales.
 * Es una herramienta crucial para mantener actualizada la información del personal en la base de datos.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see Empleado
 * @see EmpleadoController
 * @see Util
 */
public class ModificarPerfilEmpleado {

    private static final Util util = new Util();
    private static final EmpleadoController EmpleadoController = new EmpleadoController(Db4oHelper.getDb());

    /**
     * Ejecuta el proceso de actualización del perfil del empleado. Solicita al usuario la nueva información
     * y, si es válida, actualiza el registro del empleado correspondiente.
     */
    public static void run() {
        try {
            printlnGreen("--- MODIFICAR PERFIL DE EMPLEADO ---");

            Empleado empleado = buscarEmpleado();

            String nuevoNombreCompleto = util.pideTexto("Introduce el nuevo nombre completo (actual: " + empleado.getNombreCompleto() + "): ");
            String nuevoTelefonoContacto = util.pideTexto("Introduce el nuevo teléfono de contacto (actual: " + empleado.getTelefonoContacto() + "): ");

            empleado.setNombreCompleto(nuevoNombreCompleto.isEmpty() ? empleado.getNombreCompleto() : nuevoNombreCompleto);
            empleado.setTelefonoContacto(nuevoTelefonoContacto.isEmpty() ? empleado.getTelefonoContacto() : nuevoTelefonoContacto);

            boolean exito = EmpleadoController.update(empleado);

            if (exito) {
                printlnGreen("Perfil del empleado actualizado correctamente.");
            } else {
                printLnRed("Error actualizando el perfil del empleado.");
            }
        } catch (Exception e) {
            printLnRed("Error modificando el perfil del empleado: " + e.getMessage());
        }
    }


    /**
     * Busca un empleado por nombre de usuario, según la elección del usuario.
     *
     * @return El {@link Empleado} del empleado encontrado o null si no se encuentra.
     */
    public static Empleado buscarEmpleado() {
        String nombreUsuario = util.pideTexto("Introduce el nombre de usuario del empleado: ");
        Empleado empleado = EmpleadoController.findOne(nombreUsuario);

        if (empleado == null) {
            printLnRed("Empleado no encontrado.");
        }

        return empleado;
    }

}
