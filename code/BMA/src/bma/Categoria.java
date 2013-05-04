package bma;

/**
 * Clase que representa las categorías a las que puede pertenecer un alumno.
 *
 * @author Alex Moreno
 * @version 1.0
 */
public class Categoria {

    private int idCategoria;
    private String tipo;
    private String descripcion;
    private int edadMaxima;
    
    public Categoria(String categoria){
        this.tipo = categoria;
    }
}
