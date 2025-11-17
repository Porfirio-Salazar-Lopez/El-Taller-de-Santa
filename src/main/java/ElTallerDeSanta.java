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

        gestorUsuarios = new GestorUsuarios();
        gestorRegalos = new GestorRegalos();
        gestorNinos = new GestorNinos();
        gestorAsignaciones = new GestorAsignaciones();
        generadorReportes = new GeneradorReportes(gestorRegalos, gestorNinos, gestorAsignaciones);

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    if (sesionActiva) {
                        menuGestion();
                    } else {
                        System.out.println("\nDebe iniciar sesión primero.\n");
                    }
                    break;
                case 4:
                    System.out.println("\n¡Gracias por usar El Taller de Santa! ¡Feliz Navidad! 🎄\n");
                    break;
                default:
                    System.out.println("\n Opción no válida. Por favor, seleccione una opción del menú.\n");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("                        MENÚ PRINCIPAL");
        System.out.println("-----------------------------------------------------------");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar Sesión");
        System.out.println("3. Gestión del Sistema" + (sesionActiva ? " " : ""));
        System.out.println("4. Salir");
        System.out.println("-----------------------------------------------------------");
    }

    private static void registrarUsuario() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                    REGISTRO DE USUARIO");
        System.out.println("-----------------------------------------------------------\n");

        String nombreUsuario = leerTexto("Ingrese nombre de usuario: ");
        String contraseña = leerTexto("Ingrese contraseña: ");

        if (gestorUsuarios.registrarUsuario(nombreUsuario, contraseña)) {
            System.out.println("\n Usuario registrado exitosamente.\n");
        } else {
            System.out.println("\n Error: El usuario ya existe o los datos son inválidos.\n");
        }
    }

    private static void iniciarSesion() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                      INICIO DE SESIÓN");
        System.out.println("-----------------------------------------------------------\n");

        String nombreUsuario = leerTexto("Ingrese nombre de usuario: ");
        String contraseña = leerTexto("Ingrese contraseña: ");

        if (gestorUsuarios.iniciarSesion(nombreUsuario, contraseña)) {
            sesionActiva = true;
            System.out.println("\n Sesión iniciada correctamente.\n");
        } else {
            System.out.println("\n Error: Credenciales incorrectas.\n");
        }
    }

    private static void menuGestion() {
        int opcion;
        do {
            mostrarMenuGestion();
            opcion = leerEntero("Seleccione una opción: ");

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
                    sesionActiva = false;
                    System.out.println("\n Sesión cerrada.\n");
                    break;
                default:
                    System.out.println("\n Opción no válida.\n");
            }
        } while (opcion != 5);
    }

    private static void mostrarMenuGestion() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                    GESTIÓN DEL SISTEMA");
        System.out.println("-----------------------------------------------------------");
        System.out.println("1. Gestión de Regalos");
        System.out.println("2. Gestión de Niños");
        System.out.println("3. Asignación de Regalos");
        System.out.println("4. Reportes");
        System.out.println("5. Cerrar Sesión");
        System.out.println("-----------------------------------------------------------");
    }

    private static void menuGestionRegalos() {
        int opcion;
        do {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("                  GESTIÓN DE REGALOS");
            System.out.println("-----------------------------------------------------------");
            System.out.println("1. Registrar Regalo");
            System.out.println("2. Modificar Regalo");
            System.out.println("3. Eliminar Regalo");
            System.out.println("4. Reabastecer Inventario");
            System.out.println("5. Consultar Regalo por Código");
            System.out.println("6. Volver");
            System.out.println("-----------------------------------------------------------");

            opcion = leerEntero("Seleccione una opción: ");

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
                    System.out.println("\n Opción no válida.\n");
            }
        } while (opcion != 6);
    }

    private static void registrarRegalo() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                  REGISTRAR REGALO");
        System.out.println("-----------------------------------------------------------\n");

        String codigo = leerTexto("Código del regalo: ");
        String nombre = leerTexto("Nombre del regalo: ");
        String descripcion = leerTexto("Descripción: ");
        String marca = leerTexto("Marca: ");
        int cantidad = leerEntero("Cantidad disponible: ");

        if (gestorRegalos.registrarRegalo(codigo, nombre, descripcion, marca, cantidad)) {
            System.out.println("\n Regalo registrado exitosamente.\n");
        } else {
            System.out.println("\n Error: El código ya existe o los datos son inválidos.\n");
        }
    }

    private static void modificarRegalo() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                  MODIFICAR REGALO");
        System.out.println("-----------------------------------------------------------\n");

        String codigo = leerTexto("Código del regalo a modificar: ");
        String nombre = leerTexto("Nuevo nombre: ");
        String descripcion = leerTexto("Nueva descripción: ");
        String marca = leerTexto("Nueva marca: ");
        int cantidad = leerEntero("Nueva cantidad disponible: ");

        if (gestorRegalos.modificarRegalo(codigo, nombre, descripcion, marca, cantidad)) {
            System.out.println("\n Regalo modificado exitosamente.\n");
        } else {
            System.out.println("\n Error: Regalo no encontrado o datos inválidos.\n");
        }
    }

    private static void eliminarRegalo() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                  ELIMINAR REGALO");
        System.out.println("-----------------------------------------------------------\n");

        String codigo = leerTexto("Código del regalo a eliminar: ");

        if (gestorRegalos.eliminarRegalo(codigo, gestorAsignaciones)) {
            System.out.println("\n Regalo eliminado exitosamente.\n");
        } else {
            System.out.println("\n Error: Regalo no encontrado o tiene niños asignados.\n");
        }
    }

    private static void reabastecerInventario() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("              REABASTECER INVENTARIO");
        System.out.println("-----------------------------------------------------------\n");

        String codigo = leerTexto("Código del regalo: ");
        int cantidad = leerEntero("Cantidad a agregar: ");

        if (gestorRegalos.reabastecerInventario(codigo, cantidad)) {
            System.out.println("\n Inventario reabastecido exitosamente.\n");
        } else {
            System.out.println("\n Error: Regalo no encontrado o cantidad inválida.\n");
        }
    }

    private static void consultarRegalo() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("              CONSULTAR REGALO");
        System.out.println("-----------------------------------------------------------\n");

        String codigo = leerTexto("Código del regalo: ");
        Regalo regalo = gestorRegalos.buscarRegaloPorCodigo(codigo);

        if (regalo != null) {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("Código: " + regalo.getCodigo());
            System.out.println("Nombre: " + regalo.getNombre());
            System.out.println("Descripción: " + regalo.getDescripcion());
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
            System.out.println("                  GESTIÓN DE NIÑOS");
            System.out.println("-----------------------------------------------------------");
            System.out.println("1. Registrar Niño");
            System.out.println("2. Modificar Niño");
            System.out.println("3. Eliminar Niño");
            System.out.println("4. Consultar Niño por Identificación");
            System.out.println("5. Volver");
            System.out.println("-----------------------------------------------------------");

            opcion = leerEntero("Seleccione una opción: ");

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
                    System.out.println("\n Opción no válida.\n");
            }
        } while (opcion != 5);
    }

    private static void registrarNino() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                  REGISTRAR NIÑO");
        System.out.println("-----------------------------------------------------------\n");

        String identificacion = leerTexto("Identificación: ");
        String nombreCompleto = leerTexto("Nombre completo: ");
        int edad = leerEntero("Edad: ");
        String ciudad = leerTexto("Ciudad: ");
        String direccion = leerTexto("Dirección detallada: ");

        if (gestorNinos.registrarNino(identificacion, nombreCompleto, edad, ciudad, direccion)) {
            System.out.println("\n Niño registrado exitosamente.\n");
        } else {
            System.out.println("\n Error: La identificación ya existe o los datos son inválidos.\n");
        }
    }

    private static void modificarNino() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                  MODIFICAR NIÑO");
        System.out.println("-----------------------------------------------------------\n");

        String identificacion = leerTexto("Identificación del niño a modificar: ");
        String nombreCompleto = leerTexto("Nuevo nombre completo: ");
        int edad = leerEntero("Nueva edad: ");
        String ciudad = leerTexto("Nueva ciudad: ");
        String direccion = leerTexto("Nueva dirección detallada: ");

        if (gestorNinos.modificarNino(identificacion, nombreCompleto, edad, ciudad, direccion)) {
            System.out.println("\n Niño modificado exitosamente.\n");
        } else {
            System.out.println("\n Error: Niño no encontrado o datos inválidos.\n");
        }
    }

    private static void eliminarNino() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("                  ELIMINAR NIÑO");
        System.out.println("-----------------------------------------------------------\n");

        String identificacion = leerTexto("Identificación del niño a eliminar: ");

        if (gestorNinos.eliminarNino(identificacion, gestorAsignaciones)) {
            System.out.println("\n Niño eliminado exitosamente.\n");
        } else {
            System.out.println("\n Error: Niño no encontrado o tiene un regalo asignado.\n");
        }
    }

    private static void consultarNino() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("              CONSULTAR NIÑO");
        System.out.println("-----------------------------------------------------------\n");

        String identificacion = leerTexto("Identificación del niño: ");
        Nino nino = gestorNinos.buscarNinoPorIdentificacion(identificacion);

        if (nino != null) {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("Identificación: " + nino.getIdentificacion());
            System.out.println("Nombre Completo: " + nino.getNombreCompleto());
            System.out.println("Edad: " + nino.getEdad());
            System.out.println("Ciudad: " + nino.getCiudad());
            System.out.println("Dirección: " + nino.getDireccionDetallada());
            System.out.println("-----------------------------------------------------------\n");
        } else {
            System.out.println("\n Niño no encontrado.\n");
        }
    }

    private static void menuAsignaciones() {
        int opcion;
        do {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("              ASIGNACIÓN DE REGALOS");
            System.out.println("-----------------------------------------------------------");
            System.out.println("1. Asignar Regalo a Niño");
            System.out.println("2. Buscar Asignación por Identificación del Niño");
            System.out.println("3. Volver");
            System.out.println("-----------------------------------------------------------");

            opcion = leerEntero("Seleccione una opción: ");

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
                    System.out.println("\n Opción no válida.\n");
            }
        } while (opcion != 3);
    }

    private static void asignarRegalo() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("              ASIGNAR REGALO A NIÑO");
        System.out.println("-----------------------------------------------------------\n");

        String identificacion = leerTexto("Identificación del niño: ");
        String codigoRegalo = leerTexto("Código del regalo: ");

        if (gestorAsignaciones.asignarRegalo(identificacion, codigoRegalo, gestorRegalos)) {
            System.out.println("\n Regalo asignado exitosamente.\n");
        } else {
            System.out.println("\n Error: No se pudo asignar el regalo. Verifique que:");
            System.out.println("   - El niño y el regalo existan");
            System.out.println("   - El niño no tenga ya un regalo asignado");
            System.out.println("   - El regalo tenga disponibilidad\n");
        }
    }

    private static void buscarAsignacion() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("          BUSCAR ASIGNACIÓN POR IDENTIFICACIÓN");
        System.out.println("-----------------------------------------------------------\n");

        String identificacion = leerTexto("Identificación del niño: ");
        Asignacion asignacion = gestorAsignaciones.buscarAsignacionPorNino(identificacion);

        if (asignacion != null) {
            Nino nino = gestorNinos.buscarNinoPorIdentificacion(identificacion);
            Regalo regalo = gestorRegalos.buscarRegaloPorCodigo(asignacion.getCodigoRegalo());

            System.out.println("\n-----------------------------------------------------------");
            System.out.println("NIÑO:");
            System.out.println("  Identificación: " + nino.getIdentificacion());
            System.out.println("  Nombre: " + nino.getNombreCompleto());
            System.out.println("\nREGALO ASIGNADO:");
            System.out.println("  Código: " + regalo.getCodigo());
            System.out.println("  Nombre: " + regalo.getNombre());
            System.out.println("  Marca: " + regalo.getMarca());
            System.out.println("  Descripción: " + regalo.getDescripcion());
            System.out.println("-----------------------------------------------------------\n");
        } else {
            System.out.println("\n El niño no posee asignaciones.\n");
        }
    }

    private static void menuReportes() {
        int opcion;
        do {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("                        REPORTES");
            System.out.println("-----------------------------------------------------------");
            System.out.println("1. Inventario Actual de Regalos");
            System.out.println("2. Listado Completo de Niños Registrados");
            System.out.println("3. Detalle de Regalos Asignados a Cada Niño");
            System.out.println("4. Niños Registrados Sin Regalo Asignado");
            System.out.println("5. Regalos por Marca (guardar en archivo)");
            System.out.println("6. Volver");
            System.out.println("-----------------------------------------------------------");

            opcion = leerEntero("Seleccione una opción: ");

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
                    System.out.println("\n Opción no válida.\n");
            }
        } while (opcion != 6);
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (Validaciones.esNumeroEnteroValido(entrada)) {
                return Integer.parseInt(entrada);
            } else {
                System.out.println(" Por favor, ingrese un número válido.");
            }
        }
    }
}