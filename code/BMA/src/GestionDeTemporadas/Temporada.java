package GestionDeTemporadas;

import GestionDeAlumnos.Alumno;
import GestionDeGrupos.Grupo;
import bma.Actividad;
import bma.Equipo;
import java.util.List;

/**
 * Clase que representa la temporada.
 *
 * @author Alex Moreno
 * @version 1.0
 */
public class Temporada {

    
    
    private int idTemporada;
    private String curso;
    
    private List<Equipo> equipos;
    private List<Actividad> actividades;
    private List<Grupo> grupos;
    
    private List<Alumno> alumnos;
    
    public Temporada(String c){
        curso = c;
    }

    public String getCurso() {
        return curso;
    }
    
    
}
