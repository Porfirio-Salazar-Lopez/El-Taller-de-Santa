package models;

/**
 * @author porfi
 */

public class Usuario {
    private String nombreUsuario;
    private String contrasena;

    public Usuario(){}

    public Usuario(String nombreUsuario, String contrasena){
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario(){
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getContrasena(){
        return contrasena;
    }

    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
    
    @Override
    public String toString(){
        return "Usuario {" + "nombreUsuario=" + nombreUsuario + '}';
    }
}