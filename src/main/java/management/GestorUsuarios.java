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

    public boolean registrarUsuario(String nombreUsuario, String contraseña){
        if (!Validaciones.esTextoValido(nombreUsuario) || !Validaciones.esTextoValido(contraseña)){
            return false;
        }

        if (existeUsuario(nombreUsuario)){
            return false;
        }

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contraseña);
        usuarios.add(nuevoUsuario);
        FileManager.guardarUsuarios(usuarios);
        return true;
    }

    public boolean iniciarSesion(String nombreUsuario, String contraseña){
        if (!Validaciones.esTextoValido(nombreUsuario) || !Validaciones.esTextoValido(contraseña)){
            return false;
        }
        for (Usuario usuario : usuarios){
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContraseña().equals(contraseña)){
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