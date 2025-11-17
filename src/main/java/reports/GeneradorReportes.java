package reports;

import management.GestorAsignaciones;
import management.GestorNinos;
import management.GestorRegalos;
import models.Asignacion;
import models.Nino;
import models.Regalo;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author porfi
 */

public class GeneradorReportes {
    private GestorRegalos gestorRegalos;
    private GestorNinos gestorNinos;
    private GestorAsignaciones gestorAsignaciones;

    public GeneradorReportes(GestorRegalos gestorRegalos, GestorNinos gestorNinos, GestorAsignaciones gestorAsignaciones) {
        this.gestorRegalos = gestorRegalos;
        this.gestorNinos = gestorNinos;
        this.gestorAsignaciones = gestorAsignaciones;
    }

    public String generarReporteInventario() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("-----------------------------------------------------------\n");
        reporte.append("           REPORTE 1: INVENTARIO ACTUAL DE REGALOS\n");
        reporte.append("-----------------------------------------------------------\n\n");

        List<Regalo> regalos = gestorRegalos.obtenerTodosLosRegalos();
        
        if (regalos.isEmpty()) {
            reporte.append("No hay regalos registrados en el inventario.\n");
        } else {
            reporte.append(String.format("%-10s %-25s %-15s %-10s\n", 
                "CODIGO", "NOMBRE", "MARCA", "CANTIDAD"));
            reporte.append("-----------------------------------------------------------\n");
            
            for (Regalo regalo : regalos) {
                reporte.append(String.format("%-10s %-25s %-15s %-10d\n",
                    regalo.getCodigo(),
                    regalo.getNombre(),
                    regalo.getMarca(),
                    regalo.getCantidadDisponible()));
            }
        }
        
        reporte.append("\n-----------------------------------------------------------\n");
        return reporte.toString();
    }

    public String generarReporteNinos() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("-----------------------------------------------------------\n");
        reporte.append("        REPORTE 2: LISTADO COMPLETO DE NINOS REGISTRADOS\n");
        reporte.append("-----------------------------------------------------------\n\n");

        List<Nino> ninos = gestorNinos.obtenerTodosLosNinos();
        
        if (ninos.isEmpty()) {
            reporte.append("No hay ninos registrados en el sistema.\n");
        } else {
            reporte.append(String.format("%-15s %-30s %-5s %-20s %-30s\n",
                "IDENTIFICACION", "NOMBRE COMPLETO", "EDAD", "CIUDAD", "DIRECCION"));
            reporte.append("-----------------------------------------------------------\n");
            
            for (Nino nino : ninos) {
                reporte.append(String.format("%-15s %-30s %-5d %-20s %-30s\n",
                    nino.getIdentificacion(),
                    nino.getNombreCompleto(),
                    nino.getEdad(),
                    nino.getCiudad(),
                    nino.getDireccionDetallada()));
            }
        }
        
        reporte.append("\n-----------------------------------------------------------\n");
        return reporte.toString();
    }

    public String generarReporteAsignaciones() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("-----------------------------------------------------------\n");
        reporte.append("     REPORTE 3: DETALLE DE REGALOS ASIGNADOS A CADA NINO\n");
        reporte.append("-----------------------------------------------------------\n\n");

        List<Asignacion> asignaciones = gestorAsignaciones.obtenerTodasLasAsignaciones();
        
        if (asignaciones.isEmpty()) {
            reporte.append("No hay asignaciones registradas.\n");
        } else {
            reporte.append(String.format("%-15s %-30s %-10s %-25s %-15s\n",
                "IDENTIFICACION", "NOMBRE COMPLETO", "CODIGO", "NOMBRE REGALO", "MARCA"));
            reporte.append("-----------------------------------------------------------\n");
            
            for (Asignacion asignacion : asignaciones) {
                Nino nino = gestorNinos.buscarNinoPorIdentificacion(asignacion.getIdentificacionNino());
                Regalo regalo = gestorRegalos.buscarRegaloPorCodigo(asignacion.getCodigoRegalo());
                
                if (nino != null && regalo != null) {
                    reporte.append(String.format("%-15s %-30s %-10s %-25s %-15s\n",
                        nino.getIdentificacion(),
                        nino.getNombreCompleto(),
                        regalo.getCodigo(),
                        regalo.getNombre(),
                        regalo.getMarca()));
                }
            }
        }
        
        reporte.append("\n-----------------------------------------------------------\n");
        return reporte.toString();
    }

    public String generarReporteNinosSinRegalo() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("-----------------------------------------------------------\n");
        reporte.append("    REPORTE 4: NINOS REGISTRADOS SIN REGALO ASIGNADO\n");
        reporte.append("-----------------------------------------------------------\n\n");

        List<Nino> todosLosNinos = gestorNinos.obtenerTodosLosNinos();
        List<String> ninosConAsignacion = gestorAsignaciones.obtenerNinosConAsignaciones();
        
        List<Nino> ninosSinRegalo = new java.util.ArrayList<>();
        for (Nino nino : todosLosNinos) {
            if (!ninosConAsignacion.contains(nino.getIdentificacion())) {
                ninosSinRegalo.add(nino);
            }
        }
        
        if (ninosSinRegalo.isEmpty()) {
            reporte.append("Todos los ninos registrados tienen un regalo asignado.\n");
        } else {
            reporte.append(String.format("%-15s %-30s %-5s %-20s\n",
                "IDENTIFICACION", "NOMBRE COMPLETO", "EDAD", "CIUDAD"));
            reporte.append("-----------------------------------------------------------\n");
            
            for (Nino nino : ninosSinRegalo) {
                reporte.append(String.format("%-15s %-30s %-5d %-20s\n",
                    nino.getIdentificacion(),
                    nino.getNombreCompleto(),
                    nino.getEdad(),
                    nino.getCiudad()));
            }
        }
        
        reporte.append("\n-----------------------------------------------------------\n");
        return reporte.toString();
    }

    public String generarReportePorMarca(String marca) {
        StringBuilder reporte = new StringBuilder();
        reporte.append("-----------------------------------------------------------\n");
        reporte.append("        REPORTE 5: REGALOS POR MARCA - ").append(marca.toUpperCase()).append("\n");
        reporte.append("-----------------------------------------------------------\n\n");

        List<Regalo> regalosPorMarca = gestorRegalos.obtenerRegalosPorMarca(marca);
        
        if (regalosPorMarca.isEmpty()) {
            reporte.append("No se encontraron regalos de la marca: ").append(marca).append("\n");
        } else {
            reporte.append(String.format("%-10s %-25s %-40s %-10s\n",
                "CODIGO", "NOMBRE", "DESCRIPCION", "CANTIDAD"));
            reporte.append("-----------------------------------------------------------\n");
            
            for (Regalo regalo : regalosPorMarca) {
                reporte.append(String.format("%-10s %-25s %-40s %-10d\n",
                    regalo.getCodigo(),
                    regalo.getNombre(),
                    regalo.getDescripcion(),
                    regalo.getCantidadDisponible()));
            }
        }
        
        reporte.append("\n-----------------------------------------------------------\n");
        
        guardarReporteEnArchivo(reporte.toString(), "reporte_marca_" + marca + ".txt");
        
        return reporte.toString();
    }

    private void guardarReporteEnArchivo(String contenido, String nombreArchivo) {
        try (FileWriter writer = new FileWriter("data/" + nombreArchivo)) {
            writer.write(contenido);
            System.out.println("\n Reporte guardado en: data/" + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el reporte: " + e.getMessage());
        }
    }
}