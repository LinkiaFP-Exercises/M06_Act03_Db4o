package db4o.utilities;

import db4o.model.Empleado;
import db4o.model.Incidencia;

import java.util.Scanner;

/**
 * Proporciona utilidades generales para la aplicación, incluyendo métodos para solicitar entrada del usuario,
 * cerrar el escáner y imprimir mensajes coloreados en la consola.
 * También incluye métodos específicos para imprimir la información de {@link Empleado} e {@link Incidencia}.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @see Empleado
 * @see Incidencia
 */
public class Util {

    private final Scanner scanner;

    public Util() {
        scanner = new Scanner(System.in);
    }

    /**
     * Solicita al usuario un entero, mostrando un enunciado y validando la entrada.
     *
     * @param enunciado El enunciado a mostrar al usuario.
     * @return El entero proporcionado por el usuario.
     */
    public int pideEntero(String enunciado) {
        while (true) {
            try {
                printYellow(enunciado);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("El valor entrado no es válido!");
            }
        }
    }

    /**
     * Solicita al usuario un texto, mostrando un enunciado y validando la entrada.
     *
     * @param enunciado El enunciado a mostrar al usuario.
     * @return El texto proporcionado por el usuario.
     */
    public String pideTexto(String enunciado) {
        while (true) {
            try {
                printYellow(enunciado);
                String texto = scanner.nextLine();
                if (texto == null || texto.isEmpty() || texto.isBlank())
                    throw new Exception("La entrada no puede ser vacia");
                return texto;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void close() {
        scanner.close();
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void printColored(String message, String colorCode) {
        System.out.print(colorCode + message + ANSI_RESET);
    }

    public static void printLnRed(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    public static void printlnGreen(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_RESET);
    }

    public static void printYellow(String message) {
        System.out.print(ANSI_YELLOW + message + ANSI_RESET);
    }

    public static void printLnYellow(String message) {
        System.out.println(ANSI_YELLOW + message + ANSI_RESET);
    }

    /**
     * Imprime la información de un {@link Empleado} formateada.
     *
     * @param subject El {@link Empleado} cuya información se imprimirá.
     * @return La cadena de texto formateada con la información del empleado.
     */
    public static String printEmpleado(Empleado subject) {
        return String.format("%-20s | %-30s | %-15s", subject.getNombreUsuario(), subject.getNombreCompleto(), subject.getTelefonoContacto());
    }

    /**
     * Imprime la información de una {@link Incidencia} formateada, incluyendo detalles específicos.
     *
     * @param subject La {@link Incidencia} cuya información se imprimirá.
     */
    public static void printIncidencia(Incidencia subject) {
        String origen = subject.getEmpleadoOrigen() != null ? subject.getEmpleadoOrigen().getNombreCompleto() : "Desconocido";
        String destino = subject.getEmpleadoDestino() != null ? subject.getEmpleadoDestino().getNombreCompleto() : "Desconocido";
        String fechaHora = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(subject.getFechaHora());

        String header = String.format("%-10s | %-20s | %-20s | %-5s | %s", subject.getId(), fechaHora, origen, destino, subject.getTipo());

        String detalle = "Detalle: " + subject.getDetalle();

        printLnYellow(header);
        printLnRed(detalle);
    }

    public static void pausaAntesDeContinuar() {
        printlnGreen("\nPresione Enter para continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            printLnRed(e.getMessage());
        }
    }
}
