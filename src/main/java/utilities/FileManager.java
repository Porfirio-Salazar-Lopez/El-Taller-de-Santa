package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Usuario;
import models.Regalo;
import models.Nino;
import models.Asignacion;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author porfi
 */

public class FileManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String DIRECTORIO_DATOS = "data";
    private static final String ARCHIVO_USUARIOS = DIRECTORIO_DATOS + "/usuarios.json";
    private static final String ARCHIVO_REGALOS = DIRECTORIO_DATOS + "/regalos.json";
    private static final String ARCHIVO_NINOS = DIRECTORIO_DATOS + "/ninos.json";
    private static final String ARCHIVO_ASIGNACIONES = DIRECTORIO_DATOS + "/asignaciones.json";

    private static void asegurarDirectorio() {
        File directorio = new File(DIRECTORIO_DATOS);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }

    public static void guardarUsuarios(List<Usuario> usuarios) {
        asegurarDirectorio();
        try (FileWriter writer = new FileWriter(ARCHIVO_USUARIOS)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public static List<Usuario> cargarUsuarios() {
        asegurarDirectorio();
        List<Usuario> usuarios = new ArrayList<>();
        File archivo = new File(ARCHIVO_USUARIOS);
        if (archivo.exists()) {
            try (FileReader reader = new FileReader(archivo)) {
                Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();
                usuarios = gson.fromJson(reader, tipoLista);
                if (usuarios == null) {
                    usuarios = new ArrayList<>();
                }
            } catch (IOException e) {
                System.err.println("Error al cargar usuarios: " + e.getMessage());
                usuarios = new ArrayList<>();
            }
        }
        return usuarios;
    }

    public static void guardarRegalos(List<Regalo> regalos) {
        asegurarDirectorio();
        try (FileWriter writer = new FileWriter(ARCHIVO_REGALOS)) {
            gson.toJson(regalos, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar regalos: " + e.getMessage());
        }
    }

    public static List<Regalo> cargarRegalos() {
        asegurarDirectorio();
        List<Regalo> regalos = new ArrayList<>();
        File archivo = new File(ARCHIVO_REGALOS);
        if (archivo.exists()) {
            try (FileReader reader = new FileReader(archivo)) {
                Type tipoLista = new TypeToken<List<Regalo>>() {}.getType();
                regalos = gson.fromJson(reader, tipoLista);
                if (regalos == null) {
                    regalos = new ArrayList<>();
                }
            } catch (IOException e) {
                System.err.println("Error al cargar regalos: " + e.getMessage());
                regalos = new ArrayList<>();
            }
        }
        return regalos;
    }

    public static void guardarNinos(List<Nino> ninos) {
        asegurarDirectorio();
        try (FileWriter writer = new FileWriter(ARCHIVO_NINOS)) {
            gson.toJson(ninos, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar ninos: " + e.getMessage());
        }
    }

    public static List<Nino> cargarNinos() {
        asegurarDirectorio();
        List<Nino> ninos = new ArrayList<>();
        File archivo = new File(ARCHIVO_NINOS);
        if (archivo.exists()) {
            try (FileReader reader = new FileReader(archivo)) {
                Type tipoLista = new TypeToken<List<Nino>>() {}.getType();
                ninos = gson.fromJson(reader, tipoLista);
                if (ninos == null) {
                    ninos = new ArrayList<>();
                }
            } catch (IOException e) {
                System.err.println("Error al cargar ninos: " + e.getMessage());
                ninos = new ArrayList<>();
            }
        }
        return ninos;
    }

    public static void guardarAsignaciones(List<Asignacion> asignaciones) {
        asegurarDirectorio();
        try (FileWriter writer = new FileWriter(ARCHIVO_ASIGNACIONES)) {
            gson.toJson(asignaciones, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar asignaciones: " + e.getMessage());
        }
    }

    public static List<Asignacion> cargarAsignaciones() {
        asegurarDirectorio();
        List<Asignacion> asignaciones = new ArrayList<>();
        File archivo = new File(ARCHIVO_ASIGNACIONES);
        if (archivo.exists()) {
            try (FileReader reader = new FileReader(archivo)) {
                Type tipoLista = new TypeToken<List<Asignacion>>() {}.getType();
                asignaciones = gson.fromJson(reader, tipoLista);
                if (asignaciones == null) {
                    asignaciones = new ArrayList<>();
                }
            } catch (IOException e) {
                System.err.println("Error al cargar asignaciones: " + e.getMessage());
                asignaciones = new ArrayList<>();
            }
        }
        return asignaciones;
    }
}