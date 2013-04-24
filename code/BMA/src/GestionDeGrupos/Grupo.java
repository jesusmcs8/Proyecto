package GestionDeGrupos;

import GestionDeAlumnos.Alumno;
import GestionDeInstalaciones.Instalacion;
import GestionDeTemporadas.Temporada;
import GestionDeUsuarios.Usuario;
import bma.Sexo;
import java.util.List;

/**
 * Clase que nos agrupa a los alumnos en un grupo de entrenamiento.
 *
 * @author Alex Moreno
 * @version 1.0
 */
public class Grupo {

    private int idGrupo;
    //private Calendar[] horarios;
    
    private Sexo sexo;
    private Temporada temp;
    private List<Alumno> alumnos;// = new ArrayList<Alumno>(); 
    private Categoria categoria;
    private Usuario entrenador;
    private Instalacion instalacion;
}
