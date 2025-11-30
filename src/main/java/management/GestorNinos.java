package management;

import models.Nino;
import utilities.FileManager;
import utilities.Validaciones;
import java.util.ArrayList;
import java.util.List;

/**
 * @author porfi
 */

public class GestorNinos {
    private List<Nino> ninos;

    public GestorNinos() {
        // Carga los niños guardados al iniciar
        this.ninos = FileManager.cargarNinos();
    }

    public boolean registrarNino(String identificacion, String nombreCompleto, int edad, String ciudad, String direccionDetallada) {
        if (!Validaciones.esTextoValido(identificacion) || !Validaciones.esTextoValido(nombreCompleto) || 
            !Validaciones.esTextoValido(ciudad) || !Validaciones.esTextoValido(direccionDetallada)) {
            return false;
        }

        if (!Validaciones.esEdadValida(edad)) {
            return false;
        }

        if (buscarNinoPorIdentificacion(identificacion) != null) {
            return false;
        }

        Nino nuevoNino = new Nino(identificacion, nombreCompleto, String.valueOf(edad), ciudad, direccionDetallada);
        ninos.add(nuevoNino);
        FileManager.guardarNinos(ninos);
        return true;
    }

    public boolean modificarNino(String identificacion, String nombreCompleto, int edad, String ciudad, String direccionDetallada) {
        Nino nino = buscarNinoPorIdentificacion(identificacion);
        if (nino == null) {
            return false;
        }

        if (!Validaciones.esTextoValido(nombreCompleto) || !Validaciones.esTextoValido(ciudad) || 
            !Validaciones.esTextoValido(direccionDetallada)) {
            return false;
        }

        if (!Validaciones.esEdadValida(edad)) {
            return false;
        }

        nino.setNombreCompleto(nombreCompleto);
        nino.setEdad(String.valueOf(edad));
        nino.setCiudad(ciudad);
        nino.setDireccionDetallada(direccionDetallada);
        FileManager.guardarNinos(ninos);
        return true;
    }

    public boolean eliminarNino(String identificacion, GestorAsignaciones gestorAsignaciones) {
        Nino nino = buscarNinoPorIdentificacion(identificacion);
        if (nino == null) {
            return false;
        }

        // No se puede eliminar si ya tiene un regalo asignado
        if (gestorAsignaciones.tieneNinoAsignacion(identificacion)) {
            return false;
        }

        ninos.remove(nino);
        FileManager.guardarNinos(ninos);
        return true;
    }

    public Nino buscarNinoPorIdentificacion(String identificacion) {
        for (Nino nino : ninos) {
            if (nino.getIdentificacion().equals(identificacion)) {
                return nino;
            }
        }
        return null;
    }

    public List<Nino> obtenerTodosLosNinos() {
        return new ArrayList<>(ninos);
    }
}