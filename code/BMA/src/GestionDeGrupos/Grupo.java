package GestionDeGrupos;

import GestionDeAlumnos.Alumno;
import GestionDeInstalaciones.Instalacion;
import GestionDeTemporadas.Temporada;
import GestionDeUsuarios.Usuario;
import bma.Entrenador;
import bma.Horario;
import bma.Sexo;
import java.sql.Time;
import java.text.ParseException;
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
    
    private String sexo;
    //private Categoria categoria;
    private Temporada temporada;
    private List<Alumno> alumnos;
    private Horario horario;
    
    private Usuario entrenador;

    Grupo(String sex, String temp, String dia1, String dia2, String hora, String min, String entrenador) throws ParseException {
        this.sexo = sex;
        
        temporada = new Temporada(temp);
        horario = new Horario(dia1, dia2, hora, min);
        this.entrenador = new Usuario(entrenador);
    }

    public String getDia1() {
        return horario.getDia1();
    }
    
    String getDia2() {
        return horario.getDia2();
    }
    
    public String getHora1(){
        String s = horario.getHora1().toString();
        return s;
    }

    Time getHora1Time() {
        return horario.getHora1();
    }

    
    
    
}
