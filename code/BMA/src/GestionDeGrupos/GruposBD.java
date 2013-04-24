package GestionDeGrupos;

import GestionDeAlumnos.Alumno;
import GestionDeInstalaciones.Instalacion;
import GestionDeUsuarios.Usuario;
import bma.Categoria;
import GestionDeTemporadas.Temporada;
import ServiciosAlmacenamiento.BaseDatos;
import bma.Horario;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author Diego
 */
public class GruposBD {

    static boolean ConsultarGrupos(BaseDatos accesoBD, Temporada t, Categoria cat, Horario hor, Instalacion inst) {
        String categoria = cat.getTipo();
        String temp = t.getCurso();
        String dia1 = hor.getDia1();
        String dia2 = hor.getDia2();
        String hora1 = hor.getHora1().toString();
        String horaT2 = hor.getHora2().toString();
        
        String consulta = "SELECT * FROM ";
        ResultSet res = accesoBD.ejecutaConsulta(consulta);
        
        if(res.next())
            return false;
        else
            return true;
        
    }
    
}
