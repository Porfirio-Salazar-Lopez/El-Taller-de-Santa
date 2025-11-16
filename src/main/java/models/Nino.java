package models;

/**
 * @author porfi
 */

public class Nino {
    private String identificacion;
    private String nombreCompleto;
    private String edad;
    private String ciudad;
    private String direccionDetallada;

    public Nino(){}

    public Nino(String identificacion, String nombreCompleto, String edad, String ciudad, String direccionDetallada){
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.ciudad = ciudad;
        this.direccionDetallada = direccionDetallada;
    }

    public String getIdentificacion(){
        return identificacion;
    }

    public void setIdentificacion(String identificacion){
        this.identificacion = identificacion;
    }

    public String getNombreCompleto(){
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto){
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getEdad(){
        return edad;
    }

    public void setEdad(String edad){
        this.edad = edad;
    }
    

    public String getCiudad(){
        return ciudad;
    }

    public void setCiudad(String ciudad){
        this.ciudad = ciudad;
    }
    

    public String getDireccionDetallada(){
        return direccionDetallada;
    }

    public void setDireccionDetallada(String direccionDetallada){
        this.direccionDetallada = direccionDetallada;
    }
    
    public String toString(){
        return "Nino {" + "identificacion=" + identificacion + ", nombreCompleto=" + nombreCompleto + ", edad=" + edad + ", ciudad=" + ciudad + ", direccionDetallada=" + direccionDetallada + "}";
    }
}