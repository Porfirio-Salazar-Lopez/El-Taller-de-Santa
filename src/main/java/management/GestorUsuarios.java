package management;

import models.Usuario;
import utilities.FileManager;
import utilities.Validaciones;
import java.util.ArrayList;
import java.util.List;

/**
 * @author porfi
 */

public class GestorUsuarios {
    private List<Usuario> usuarios;

    public GestorUsuarios(){
        this.usuarios = FileManager.cargarUsuarios();
    }

    public boolean registrarUsuario(String nombreUsuario, String contrasena){
        if (!Validaciones.esTextoValido(nombreUsuario) || !Validaciones.esTextoValido(contrasena)){
            return false;
        }

        if (existeUsuario(nombreUsuario)){
            return false;
        }

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena);
        usuarios.add(nuevoUsuario);
        FileManager.guardarUsuarios(usuarios);
        return true;
    }

    public boolean iniciarSesion(String nombreUsuario, String contrasena){
        if (!Validaciones.esTextoValido(nombreUsuario) || !Validaciones.esTextoValido(contrasena)){
            return false;
        }
        for (Usuario usuario : usuarios){
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)){
                return true;
            }
        }
        return false;
    }

    private boolean existeUsuario(String nombreUsuario){
        for (Usuario usuario : usuarios){
            if (usuario.getNombreUsuario().equals(nombreUsuario)){
                return true;
            }
        }
        return false;
    }
    public List<Usuario> getUsuarios(){
        return new ArrayList<>(usuarios);
    }
}