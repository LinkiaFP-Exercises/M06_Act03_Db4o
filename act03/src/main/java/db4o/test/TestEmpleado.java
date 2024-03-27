package db4o.test;

import db4o.config.Db4oHelper;
import db4o.model.Empleado;
import db4o.controller.EmpleadoController;

import java.util.List;
import java.util.logging.Logger;

import static db4o.utilities.Util.*;

/**
 * Clase para probar las funcionalidades del servicio de empleados.
 * Incluye pruebas para listar todos los empleados, crear, buscar por usuario, buscar por ID,
 * validar usuario y contraseña, actualizar perfil, cambiar contraseña y eliminar empleados.
 * Estas pruebas se ejecutan al iniciar la aplicación para asegurar que el entorno y las
 * funcionalidades básicas están trabajando como se espera.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see EmpleadoController
 * @see Empleado
 */
public class TestEmpleado {

    private static final Logger log = Logger.getLogger(TestEmpleado.class.getName());
    private static final EmpleadoController EmpleadoController = new EmpleadoController(Db4oHelper.getDb());
    private static final String USUARIO_TEST = "usuarioTest";
    private static final String CONTRASENA_TEST = "contrasenaTest";
    public static final String NOMBRE_COMPLETO = "Nombre Completo";
    public static final String TELEFONO = "123456789";
    private static Empleado empleadoMock;

    public static void doAll() {

        testListAll();
        testCreate();
        testReadByUser();
        testValidarUserPass();
        testUpdate();
        testUpdatePassByUser();
        testDelete();
        testListAll();

    }

    private static void testListAll() {
        try {
            printYellow("LISTANDO TODOS EMPLEADOS: ");
            List<Empleado> empleados = EmpleadoController.findAll();
            if (empleados != null)
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }


    private static void testCreate() {
        try {
            empleadoMock = new Empleado(USUARIO_TEST, CONTRASENA_TEST, NOMBRE_COMPLETO, TELEFONO);
            printYellow("INSERTANDO EMPLEADO: ");
            EmpleadoController.insert(empleadoMock);
            printSuccess();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testReadByUser() {
        try {
            printYellow("BUSCANDO EMPLEADO POR NOMBRE DE USUARIO: ");
            Empleado empleado = EmpleadoController.findOne(USUARIO_TEST);
            if (empleado != null)
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testValidarUserPass() {
        try {
            printYellow("VALIDANDO EMPLEADO POR USUARIO Y CONTRASEÑA: ");
            if (EmpleadoController.validarEmpleado(USUARIO_TEST, CONTRASENA_TEST))
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testUpdate() {
        try {
            printYellow("ACTUALIZAR EMPLEADO: ");
            empleadoMock.setContrasena("X");
            empleadoMock.setNombreCompleto("X");
            empleadoMock.setTelefonoContacto("X");
            boolean updated = EmpleadoController.update(empleadoMock);
            Empleado empleado = EmpleadoController.findOne(USUARIO_TEST);
            empleadoMock.setContrasena(CONTRASENA_TEST);
            if (updated && empleadoMock.equals(empleado))
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }


    private static void testUpdatePassByUser() {
        try {
            printYellow("ACTUALIZAR CONTRASEÑA EMPLEADO POR USER: ");
            empleadoMock.setContrasena("X");
            EmpleadoController.updatePassword(empleadoMock);
            Empleado empleado = EmpleadoController.findOne(USUARIO_TEST);
            empleadoMock.setContrasena(CONTRASENA_TEST);
            if (empleadoMock.equals(empleado))
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void testDelete() {
        try {
            printYellow("BORRANDO EMPLEADO: ");
            EmpleadoController.delete(USUARIO_TEST);
            Empleado empleado = EmpleadoController.findOne(USUARIO_TEST);
            if (empleado == null)
                printSuccess();
            else
                printFail();
        } catch (Exception e) { log.severe(e.getMessage()); }
    }

    private static void printFail() { printLnRed("FAILURE!!!"); }
    private static void printSuccess() { printlnGreen("SUCCESS!!!"); }

}
