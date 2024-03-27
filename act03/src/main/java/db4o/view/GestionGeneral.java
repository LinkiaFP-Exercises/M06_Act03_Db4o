package db4o.view;

import db4o.utilities.Util;
import db4o.view.empleados.*;
import db4o.view.incidencias.*;

import java.util.HashMap;
import java.util.Map;

import static db4o.utilities.Util.*;

/**
 * Clase principal que maneja la interacción con el usuario a través de la consola para la gestión de empleados e incidencias.
 * Presenta un menú de opciones que permite al usuario ejecutar diferentes operaciones relacionadas con empleados e incidencias,
 * como insertar, validar, modificar, eliminar empleados, gestionar incidencias y más.
 * <p>
 * Utiliza un mapa para asociar cada opción del menú con su acción correspondiente, facilitando la adición o modificación de opciones.
 * Cada opción del menú está vinculada a una clase específica que implementa la funcionalidad deseada.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see Util
 * @see InsertarEmpleado
 * @see ValidarEmpleado
 * @see ModificarPerfilEmpleado
 * @see CambiarContrasenaEmpleado
 * @see EliminarEmpleado
 * @see ListarTodosLosEmpleados
 * @see ObtenerIncidenciaPorId
 * @see ListarTodasLasIncidencias
 * @see CrearNuevaIncidencia
 * @see ObtenerIncidenciasPorOrigen
 */
public class GestionGeneral {
    private static final Map<Integer, Runnable> opcionesMenu = new HashMap<>();
    private static final Util util = new Util();

    /**
     * Inicia la interacción con el usuario presentando el menú de gestión general y procesando la selección de opciones.
     * La ejecución continúa hasta que el usuario elija salir.
     */
    public static void start() {
        GestionEmpleadosIncidencias();
        int opcion;
        do {
            mostrarMenu();
            opcion = util.pideEntero("Seleccione una opción: ");

            Runnable accion = opcionesMenu.get(opcion);
            if (accion != null && opcion >= 1 && opcion <= 11) {
                accion.run();
                pausaAntesDeContinuar();
            } else if (opcion == 0) {
                util.close();
                printlnGreen("Saliendo del programa...");
            } else {
                printLnRed("Opción no válida, intente nuevamente.");
            }
        } while (opcion != 0);
    }

    /**
     * Muestra el menú de opciones en la consola. Cada opción está numerada y describe la acción que realizará.
     */
    private static void mostrarMenu() {
        System.out.println("""
                    
                    --- Gestión de Empleados ---
                    1. Insertar empleado
                    2. Validar entrada de empleado
                    3. Modificar perfil de empleado
                    4. Cambiar contraseña de empleado
                    5. Eliminar empleado
                    6. Listar todos los empleados
                    7. Obtener incidencia por ID
                    8. Listar todas las incidencias
                    9. Crear una nueva incidencia
                    10. Incidencias por origen
                    11. Incidencias por destino
                    0. Salir
                    """);
    }

    /**
     * Configura las acciones asociadas a cada opción del menú.
     * Utiliza lambdas y referencias a métodos para vincular cada opción numérica con su acción correspondiente.
     */
    private static void GestionEmpleadosIncidencias() {
        opcionesMenu.put(1, InsertarEmpleado::run);
        opcionesMenu.put(2, ValidarEmpleado::run);
        opcionesMenu.put(3, ModificarPerfilEmpleado::run);
        opcionesMenu.put(4, CambiarContrasenaEmpleado::run);
        opcionesMenu.put(5, EliminarEmpleado::run);
        opcionesMenu.put(6, ListarTodosLosEmpleados::run);
        opcionesMenu.put(7, ObtenerIncidenciaPorId::run);
        opcionesMenu.put(8, ListarTodasLasIncidencias::run);
        opcionesMenu.put(9, CrearNuevaIncidencia::run);
        opcionesMenu.put(10, ObtenerIncidenciasPorOrigen::run);
    }
}
