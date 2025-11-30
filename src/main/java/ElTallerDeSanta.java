import management.GestorAsignaciones;
import management.GestorNinos;
import management.GestorRegalos;
import management.GestorUsuarios;
import models.Asignacion;
import models.Nino;
import models.Regalo;
import reports.GeneradorReportes;
import utilities.Validaciones;
import java.util.Scanner;

/**
 * @author porfi
 */

public class ElTallerDeSanta {
    private static Scanner scanner = new Scanner(System.in);
    private static GestorUsuarios gestorUsuarios;
    private static GestorRegalos gestorRegalos;
    private static GestorNinos gestorNinos;
    private static GestorAsignaciones gestorAsignaciones;
    private static GeneradorReportes generadorReportes;
    private static boolean sesionActiva = false;

    public static void main(String[] args) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("          BIENVENIDO AL TALLER DE SANTA");
        System.out.println("-----------------------------------------------------------\n");

        // Cargar todos los datos guardados al iniciar el programa
        gestorUsuarios = new GestorUsuarios();
        gestorRegalos = new GestorRegalos();
        gestorNinos = new GestorNinos();
        gestorAsignaciones = new GestorAsignaciones();
        generadorReportes = new GeneradorReportes(gestorRegalos, gestorNinos, gestorAsignaciones);

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    // Solo permite entrar si ya inició sesión
                    if (sesionActiva) {
                        menuGestion();
                    } else {
                        System.out.println("\nDebe iniciar sesion primero.\n");
                    }
                    break;
                case 4:
                    System.out.println("\n¡Gracias por usar El Taller de Santa! ¡Feliz Navidad! 🎄\n");
                    break;
                default:
                    System.out.println("\n Opcion no valida. Por favor, seleccione una opcion del menu.\n");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("                        MENU PRINCIPAL");
        System.out.println("-----------------------------------------------------------");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar Sesion");
        System.out.println("3. Gestion del Sistema" + (sesionActiva ? " " : ""));
        System.out.println("4. Salir");
        System.out.println("-----------------------------------------------------------");
    }

    private static void registrarUsuario() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                    REGISTRO DE USUARIO");
        System.out.println("-----------------------------------------------------------\n");

        String nombreUsuario = leerTexto("Ingrese nombre de usuario: ");
        String contrasena = leerTexto("Ingrese contrasena: ");

        if (gestorUsuarios.registrarUsuario(nombreUsuario, contrasena)) {
            System.out.println("\n Usuario registrado exitosamente.\n");
        } else {
            System.out.println("\n Error: El usuario ya existe o los datos son invalidos.\n");
        }
    }

    private static void iniciarSesion() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                      INICIO DE SESION");
        System.out.println("-----------------------------------------------------------\n");

        String nombreUsuario = leerTexto("Ingrese nombre de usuario: ");
        String contrasena = leerTexto("Ingrese contrasena: ");

        // Si las credenciales son correctas, activa la sesión
        if (gestorUsuarios.iniciarSesion(nombreUsuario, contrasena)) {
            sesionActiva = true;
            System.out.println("\n Sesion iniciada correctamente.\n");
        } else {
            System.out.println("\n Error: Credenciales incorrectas.\n");
        }
    }

    private static void menuGestion() {
        int opcion;
        do {
            mostrarMenuGestion();
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    menuGestionRegalos();
                    break;
                case 2:
                    menuGestionNinos();
                    break;
                case 3:
                    menuAsignaciones();
                    break;
                case 4:
                    menuReportes();
                    break;
                case 5:
                    // Cierra la sesión y vuelve al menú principal
                    sesionActiva = false;
                    System.out.println("\n Sesion cerrada.\n");
                    break;
                default:
                    System.out.println("\n Opcion no valida.\n");
            }
        } while (opcion != 5);
    }

    private static void mostrarMenuGestion() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                    GESTION DEL SISTEMA");
        System.out.println("-----------------------------------------------------------");
        System.out.println("1. Gestion de Regalos");
        System.out.println("2. Gestion de Ninos");
        System.out.println("3. Asignacion de Regalos");
        System.out.println("4. Reportes");
        System.out.println("5. Cerrar Sesion");
        System.out.println("-----------------------------------------------------------");
    }

    private static void menuGestionRegalos() {
        int opcion;
        do {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("                  GESTION DE REGALOS");
            System.out.println("-----------------------------------------------------------");
            System.out.println("1. Registrar Regalo");
            System.out.println("2. Modificar Regalo");
            System.out.println("3. Eliminar Regalo");
            System.out.println("4. Reabastecer Inventario");
            System.out.println("5. Consultar Regalo por Codigo");
            System.out.println("6. Volver");
            System.out.println("-----------------------------------------------------------");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarRegalo();
                    break;
                case 2:
                    modificarRegalo();
                    break;
                case 3:
                    eliminarRegalo();
                    break;
                case 4:
                    reabastecerInventario();
                    break;
                case 5:
                    consultarRegalo();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("\n Opcion no valida.\n");
            }
        } while (opcion != 6);
    }

    private static void registrarRegalo() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                  REGISTRAR REGALO");
        System.out.println("-----------------------------------------------------------\n");

        String codigo = leerTexto("Codigo del regalo: ");
        String nombre = leerTexto("Nombre del regalo: ");
        String descripcion = leerTexto("Descripcion: ");
        String marca = leerTexto("Marca: ");
        int cantidad = leerEntero("Cantidad disponible: ");

        if (gestorRegalos.registrarRegalo(codigo, nombre, descripcion, marca, cantidad)) {
            System.out.println("\n Regalo registrado exitosamente.\n");
        } else {
            System.out.println("\n Error: El codigo ya existe o los datos son invalidos.\n");
        }
    }

    private static void modificarRegalo() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                  MODIFICAR REGALO");
        System.out.println("-----------------------------------------------------------\n");

        String codigo = leerTexto("Codigo del regalo a modificar: ");
        String nombre = leerTexto("Nuevo nombre: ");
        String descripcion = leerTexto("Nueva descripcion: ");
        String marca = leerTexto("Nueva marca: ");
        int cantidad = leerEntero("Nueva cantidad disponible: ");

        if (gestorRegalos.modificarRegalo(codigo, nombre, descripcion, marca, cantidad)) {
            System.out.println("\n Regalo modificado exitosamente.\n");
        } else {
            System.out.println("\n Error: Regalo no encontrado o datos invalidos.\n");
        }
    }

    private static void eliminarRegalo() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                  ELIMINAR REGALO");
        System.out.println("-----------------------------------------------------------\n");

        String codigo = leerTexto("Codigo del regalo a eliminar: ");

        if (gestorRegalos.eliminarRegalo(codigo, gestorAsignaciones)) {
            System.out.println("\n Regalo eliminado exitosamente.\n");
        } else {
            System.out.println("\n Error: Regalo no encontrado o tiene ninos asignados.\n");
        }
    }

    private static void reabastecerInventario() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("              REABASTECER INVENTARIO");
        System.out.println("-----------------------------------------------------------\n");

        String codigo = leerTexto("Codigo del regalo: ");
        int cantidad = leerEntero("Cantidad a agregar: ");

        if (gestorRegalos.reabastecerInventario(codigo, cantidad)) {
            System.out.println("\n Inventario reabastecido exitosamente.\n");
        } else {
            System.out.println("\n Error: Regalo no encontrado o cantidad invalida.\n");
        }
    }

    private static void consultarRegalo() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("              CONSULTAR REGALO");
        System.out.println("-----------------------------------------------------------\n");

        String codigo = leerTexto("Codigo del regalo: ");
        Regalo regalo = gestorRegalos.buscarRegaloPorCodigo(codigo);

        if (regalo != null) {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("Codigo: " + regalo.getCodigo());
            System.out.println("Nombre: " + regalo.getNombre());
            System.out.println("Descripcion: " + regalo.getDescripcion());
            System.out.println("Marca: " + regalo.getMarca());
            System.out.println("Cantidad Disponible: " + regalo.getCantidadDisponible());
            System.out.println("-----------------------------------------------------------\n");
        } else {
            System.out.println("\n Regalo no encontrado.\n");
        }
    }

    private static void menuGestionNinos() {
        int opcion;
        do {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("                  GESTION DE NINOS");
            System.out.println("-----------------------------------------------------------");
            System.out.println("1. Registrar Nino");
            System.out.println("2. Modificar Nino");
            System.out.println("3. Eliminar Nino");
            System.out.println("4. Consultar Nino por Identificacion");
            System.out.println("5. Volver");
            System.out.println("-----------------------------------------------------------");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarNino();
                    break;
                case 2:
                    modificarNino();
                    break;
                case 3:
                    eliminarNino();
                    break;
                case 4:
                    consultarNino();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\n Opcion no valida.\n");
            }
        } while (opcion != 5);
    }

    private static void registrarNino() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                  REGISTRAR NINO");
        System.out.println("-----------------------------------------------------------\n");

        String identificacion = leerTexto("Identificacion: ");
        String nombreCompleto = leerTexto("Nombre completo: ");
        int edad = leerEntero("Edad: ");
        String ciudad = leerTexto("Ciudad: ");
        String direccion = leerTexto("Direccion detallada: ");

        if (gestorNinos.registrarNino(identificacion, nombreCompleto, edad, ciudad, direccion)) {
            System.out.println("\n Nino registrado exitosamente.\n");
        } else {
            System.out.println("\n Error: La identificacion ya existe o los datos son invalidos.\n");
        }
    }

    private static void modificarNino() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                  MODIFICAR NINO");
        System.out.println("-----------------------------------------------------------\n");

        String identificacion = leerTexto("Identificacion del nino a modificar: ");
        String nombreCompleto = leerTexto("Nuevo nombre completo: ");
        int edad = leerEntero("Nueva edad: ");
        String ciudad = leerTexto("Nueva ciudad: ");
        String direccion = leerTexto("Nueva direccion detallada: ");

        if (gestorNinos.modificarNino(identificacion, nombreCompleto, edad, ciudad, direccion)) {
            System.out.println("\n Nino modificado exitosamente.\n");
        } else {
            System.out.println("\n Error: Nino no encontrado o datos invalidos.\n");
        }
    }

    private static void eliminarNino() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                  ELIMINAR NINO");
        System.out.println("-----------------------------------------------------------\n");

        String identificacion = leerTexto("Identificacion del nino a eliminar: ");

        if (gestorNinos.eliminarNino(identificacion, gestorAsignaciones)) {
            System.out.println("\n Nino eliminado exitosamente.\n");
        } else {
            System.out.println("\n Error: Nino no encontrado o tiene un regalo asignado.\n");
        }
    }

    private static void consultarNino() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("              CONSULTAR NINO");
        System.out.println("-----------------------------------------------------------\n");

        String identificacion = leerTexto("Identificacion del nino: ");
        Nino nino = gestorNinos.buscarNinoPorIdentificacion(identificacion);

        if (nino != null) {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("Identificacion: " + nino.getIdentificacion());
            System.out.println("Nombre Completo: " + nino.getNombreCompleto());
            System.out.println("Edad: " + nino.getEdad());
            System.out.println("Ciudad: " + nino.getCiudad());
            System.out.println("Direccion: " + nino.getDireccionDetallada());
            System.out.println("-----------------------------------------------------------\n");
        } else {
            System.out.println("\n Nino no encontrado.\n");
        }
    }

    private static void menuAsignaciones() {
        int opcion;
        do {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("              ASIGNACION DE REGALOS");
            System.out.println("-----------------------------------------------------------");
            System.out.println("1. Asignar Regalo a Nino");
            System.out.println("2. Buscar Asignacion por Identificacion del Nino");
            System.out.println("3. Volver");
            System.out.println("-----------------------------------------------------------");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    asignarRegalo();
                    break;
                case 2:
                    buscarAsignacion();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("\n Opcion no valida.\n");
            }
        } while (opcion != 3);
    }

    private static void asignarRegalo() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("              ASIGNAR REGALO A NINO");
        System.out.println("-----------------------------------------------------------\n");

        String identificacion = leerTexto("Identificacion del nino: ");
        String codigoRegalo = leerTexto("Codigo del regalo: ");

        // Asigna el regalo y descuenta automáticamente del inventario
        if (gestorAsignaciones.asignarRegalo(identificacion, codigoRegalo, gestorRegalos, gestorNinos)) {
            System.out.println("\n Regalo asignado exitosamente.\n");
        } else {
            System.out.println("\n Error: No se pudo asignar el regalo. Verifique que:");
            System.out.println("   - El nino y el regalo existan");
            System.out.println("   - El nino no tenga ya un regalo asignado");
            System.out.println("   - El regalo tenga disponibilidad\n");
        }
    }

    private static void buscarAsignacion() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("          BUSCAR ASIGNACION POR IDENTIFICACION");
        System.out.println("-----------------------------------------------------------\n");

        String identificacion = leerTexto("Identificacion del nino: ");
        Asignacion asignacion = gestorAsignaciones.buscarAsignacionPorNino(identificacion);

        // Si encuentra la asignación, muestra los datos del niño y su regalo
        if (asignacion != null) {
            Nino nino = gestorNinos.buscarNinoPorIdentificacion(identificacion);
            Regalo regalo = gestorRegalos.buscarRegaloPorCodigo(asignacion.getCodigoRegalo());

            System.out.println("\n-----------------------------------------------------------");
            System.out.println("NINO:");
            System.out.println("  Identificacion: " + nino.getIdentificacion());
            System.out.println("  Nombre: " + nino.getNombreCompleto());
            System.out.println("\nREGALO ASIGNADO:");
            System.out.println("  Codigo: " + regalo.getCodigo());
            System.out.println("  Nombre: " + regalo.getNombre());
            System.out.println("  Marca: " + regalo.getMarca());
            System.out.println("  Descripcion: " + regalo.getDescripcion());
            System.out.println("-----------------------------------------------------------\n");
        } else {
            System.out.println("\n El nino no posee asignaciones.\n");
        }
    }

    private static void menuReportes() {
        int opcion;
        do {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("                        REPORTES");
            System.out.println("-----------------------------------------------------------");
            System.out.println("1. Inventario Actual de Regalos");
            System.out.println("2. Listado Completo de Ninos Registrados");
            System.out.println("3. Detalle de Regalos Asignados a Cada Nino");
            System.out.println("4. Ninos Registrados Sin Regalo Asignado");
            System.out.println("5. Regalos por Marca (guardar en archivo)");
            System.out.println("6. Volver");
            System.out.println("-----------------------------------------------------------");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    System.out.println(generadorReportes.generarReporteInventario());
                    break;
                case 2:
                    System.out.println(generadorReportes.generarReporteNinos());
                    break;
                case 3:
                    System.out.println(generadorReportes.generarReporteAsignaciones());
                    break;
                case 4:
                    System.out.println(generadorReportes.generarReporteNinosSinRegalo());
                    break;
                case 5:
                    String marca = leerTexto("Ingrese la marca: ");
                    System.out.println(generadorReportes.generarReportePorMarca(marca));
                    break;
                case 6:
                    break;
                default:
                    System.out.println("\n Opcion no valida.\n");
            }
        } while (opcion != 6);
    }

    // Lee texto de la consola y quita espacios al inicio y final
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        // Pide un número hasta que el usuario ingrese uno válido
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (Validaciones.esNumeroEnteroValido(entrada)) {
                return Integer.parseInt(entrada);
            } else {
                System.out.println(" Por favor, ingrese un numero valido.");
            }
        }
    }
}