package models;

/**
 * @author porfi
 */

public class Asignacion {
    private String identificacionNino;
    private String codigoRegalo;

    public Asignacion(){}

    public Asignacion(String identificacionNino, String codigoRegalo){
        this.identificacionNino = identificacionNino;
        this.codigoRegalo = codigoRegalo;
    }

    public String getIdentificacionNino(){
        return identificacionNino;
    }

    public void setIdentificacionNino(String identificacionNino){
        this.identificacionNino = identificacionNino;
    }

    public String getCodigoRegalo(){
        return codigoRegalo;
    }

    public void setCodigoRegalo(String codigoRegalo){
        this.codigoRegalo = codigoRegalo;
    }

    public String toString(){
        return "Asignacion {" + "identificacionNino=" + identificacionNino + ", codigoRegalo=" + codigoRegalo + "}";
    }
}