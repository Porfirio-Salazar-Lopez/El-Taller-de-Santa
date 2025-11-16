package models;

/**
 * @author porfi
 */

public class Usuario {
    private String nombreUsuario;
    private String contraseña;

    public Usuario(){}

    public Usuario(String nombreUsuario, String contraseña){
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public String getNombreUsuario(){
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getContraseña(){
        return contraseña;
    }

    public void setContraseña(String contraseña){
        this.contraseña = contraseña;
    }
    
    @Override
    public String toString(){
        return "Usuario {" + "nombreUsuario=" + nombreUsuario + '}';
    }
}