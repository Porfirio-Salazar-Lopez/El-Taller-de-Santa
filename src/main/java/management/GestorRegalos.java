package management;

import models.Regalo;
import utilities.FileManager;
import utilities.Validaciones;
import java.util.ArrayList;
import java.util.List;

/**
 * @author porfi
 */

public class GestorRegalos {
    private List<Regalo> regalos;

    public GestorRegalos() {
        this.regalos = FileManager.cargarRegalos();
    }

    public boolean registrarRegalo(String codigo, String nombre, String descripcion, String marca, int cantidadDisponible) {
        if (!Validaciones.esTextoValido(codigo) || !Validaciones.esTextoValido(nombre) || 
            !Validaciones.esTextoValido(descripcion) || !Validaciones.esTextoValido(marca)) {
            return false;
        }

        if (!Validaciones.esNumeroNoNegativo(cantidadDisponible)) {
            return false;
        }

        if (buscarRegaloPorCodigo(codigo) != null) {
            return false;
        }

        Regalo nuevoRegalo = new Regalo(codigo, nombre, descripcion, marca, cantidadDisponible);
        regalos.add(nuevoRegalo);
        FileManager.guardarRegalos(regalos);
        return true;
    }

    public boolean modificarRegalo(String codigo, String nombre, String descripcion, String marca, int cantidadDisponible) {
        Regalo regalo = buscarRegaloPorCodigo(codigo);
        if (regalo == null) {
            return false;
        }

        if (!Validaciones.esTextoValido(nombre) || !Validaciones.esTextoValido(descripcion) || 
            !Validaciones.esTextoValido(marca)) {
            return false;
        }

        if (!Validaciones.esNumeroNoNegativo(cantidadDisponible)) {
            return false;
        }

        regalo.setNombre(nombre);
        regalo.setDescripcion(descripcion);
        regalo.setMarca(marca);
        regalo.setCantidadDisponible(cantidadDisponible);
        FileManager.guardarRegalos(regalos);
        return true;
    }

    public boolean eliminarRegalo(String codigo, GestorAsignaciones gestorAsignaciones) {
        Regalo regalo = buscarRegaloPorCodigo(codigo);
        if (regalo == null) {
            return false;
        }

        if (gestorAsignaciones.tieneRegaloAsignaciones(codigo)) {
            return false;
        }

        regalos.remove(regalo);
        FileManager.guardarRegalos(regalos);
        return true;
    }

    public boolean reabastecerInventario(String codigo, int cantidad) {
        Regalo regalo = buscarRegaloPorCodigo(codigo);
        if (regalo == null) {
            return false;
        }

        if (!Validaciones.esNumeroPositivo(cantidad)) {
            return false;
        }

        regalo.reabastecer(cantidad);
        FileManager.guardarRegalos(regalos);
        return true;
    }

    public Regalo buscarRegaloPorCodigo(String codigo) {
        for (Regalo regalo : regalos) {
            if (regalo.getCodigo().equals(codigo)) {
                return regalo;
            }
        }
        return null;
    }

    public List<Regalo> obtenerTodosLosRegalos() {
        return new ArrayList<>(regalos);
    }

    public List<Regalo> obtenerRegalosPorMarca(String marca) {
        List<Regalo> regalosPorMarca = new ArrayList<>();
        for (Regalo regalo : regalos) {
            if (regalo.getMarca().equalsIgnoreCase(marca)) {
                regalosPorMarca.add(regalo);
            }
        }
        return regalosPorMarca;
    }

    public boolean descontarUnidad(String codigo) {
        Regalo regalo = buscarRegaloPorCodigo(codigo);
        if (regalo == null || regalo.getCantidadDisponible() <= 0) {
            return false;
        }
        regalo.descontarUnidad();
        FileManager.guardarRegalos(regalos);
        return true;
    }
}