package models;

/**
 * @author porfi
 */

public class Regalo {
    private String codigo;
    private String nombre;
    private String descripcion;
    private String marca;
    private int cantidadDisponible;

    public Regalo(){}

    public Regalo(String codigo, String nombre, String descripcion, String marca, int cantidadDisponible){
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.cantidadDisponible = cantidadDisponible;
    }
    
    public String getCodigo(){
        return codigo;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    
    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    
    public String getMarca(){
        return marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public int getCantidadDisponible(){
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible){
        this.cantidadDisponible = cantidadDisponible;
    }

    public void descontarUnidad(){
        if (cantidadDisponible > 0){
            cantidadDisponible--;
        } else {
            System.out.println("No hay suficientes unidades disponibles del regalo " + nombre);
        }
    }

    public void reabastecer(int cantidad){
        if (cantidad > 0){
            cantidadDisponible += cantidad;
        } else {
            System.out.println("La cantidad a reabastecer debe ser mayor a 0");
        }
    }
    
    public String toString(){
        return "Regalo {" + "codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", marca=" + marca + ", cantidadDisponible=" + cantidadDisponible + "}";
    }
}