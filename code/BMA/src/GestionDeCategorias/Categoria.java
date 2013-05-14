package GestionDeCategorias;

import ServiciosAlmacenamiento.BaseDatos;

/**
 * Clase que representa las categorías a las que puede pertenecer un alumno.
 *
 * @author Alex Moreno
 * @version 1.0
 */
public class Categoria {

    static int Modificar(BaseDatos accesoBD, Categoria c) {
        return CategoriaBD.ModificarCategoria(accesoBD, c);
    }

    private int idCategoria;
    private String tipo;
    private String descripcion;
    
    public Categoria(String categoria){
        this.tipo = categoria;
    }
    
    public Categoria(String t, String desc){
        this.tipo = t;
        this.descripcion = desc;
    }

    public String getNombreCategoria() {
        return this.tipo;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }

    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }

    public void setTipo(String t) {
        this.tipo = t;
    }
   
    public void setIdCat(int idCat) {
        this.idCategoria = idCat;
    }

}
