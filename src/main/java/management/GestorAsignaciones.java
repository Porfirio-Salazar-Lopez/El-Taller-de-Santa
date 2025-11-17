package management;

import models.Asignacion;
import utilities.FileManager;
import utilities.Validaciones;
import java.util.ArrayList;
import java.util.List;

/**
 * @author porfi
 */

public class GestorAsignaciones {
    private List<Asignacion> asignaciones;

    public GestorAsignaciones() {
        this.asignaciones = FileManager.cargarAsignaciones();
    }

    public boolean asignarRegalo(String identificacionNino, String codigoRegalo, GestorRegalos gestorRegalos) {
        if (!Validaciones.esTextoValido(identificacionNino) || !Validaciones.esTextoValido(codigoRegalo)) {
            return false;
        }

        if (tieneNinoAsignacion(identificacionNino)) {
            return false;
        }

        if (!gestorRegalos.descontarUnidad(codigoRegalo)) {
            return false;
        }

        Asignacion nuevaAsignacion = new Asignacion(identificacionNino, codigoRegalo);
        asignaciones.add(nuevaAsignacion);
        FileManager.guardarAsignaciones(asignaciones);
        return true;
    }

    public Asignacion buscarAsignacionPorNino(String identificacionNino) {
        for (Asignacion asignacion : asignaciones) {
            if (asignacion.getIdentificacionNino().equals(identificacionNino)) {
                return asignacion;
            }
        }
        return null;
    }

    public boolean tieneNinoAsignacion(String identificacionNino) {
        return buscarAsignacionPorNino(identificacionNino) != null;
    }

    public boolean tieneRegaloAsignaciones(String codigoRegalo) {
        for (Asignacion asignacion : asignaciones) {
            if (asignacion.getCodigoRegalo().equals(codigoRegalo)) {
                return true;
            }
        }
        return false;
    }

    public List<Asignacion> obtenerTodasLasAsignaciones() {
        return new ArrayList<>(asignaciones);
    }

    public List<String> obtenerNinosConAsignaciones() {
        List<String> ninosConAsignacion = new ArrayList<>();
        for (Asignacion asignacion : asignaciones) {
            ninosConAsignacion.add(asignacion.getIdentificacionNino());
        }
        return ninosConAsignacion;
    }
}