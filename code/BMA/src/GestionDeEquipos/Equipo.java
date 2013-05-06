/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeEquipos;

import GestionDeTemporadas.Temporada;
import GestionDeUsuarios.Usuario;
import bma.Categoria;

/**
 *
 * @author David
 */
public class Equipo {
    private int idEquipo;
    private String nombre;
    
    private Temporada temporada;
    private Categoria categoria;
    private Usuario entrenador;
    private Usuario entrenador2;
    
    public Equipo(String nombre, String temporada, String categoria, String entrenador, String entrenador2){
        this.nombre = nombre;
        this.temporada = new Temporada(temporada);
        this.categoria = new Categoria(categoria);
        this.entrenador = new Usuario(entrenador);
        this.entrenador2 = new Usuario(entrenador2);
    }

    /**
     * @return the idEquipo
     */
    public int getIdEquipo() {
        return idEquipo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the temporada
     */
    public Temporada getTemporada() {
        return temporada;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @return the entrenador
     */
    public Usuario getEntrenador() {
        return entrenador;
    }

    /**
     * @return the entrenador2
     */
    public Usuario getEntrenador2() {
        return entrenador2;
    }
    
}
