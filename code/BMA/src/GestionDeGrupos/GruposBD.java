package GestionDeGrupos;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Diego
 */
public class GruposBD {

    /*static boolean ConsultarGrupos(BaseDatos accesoBD, Temporada t, Categoria cat, Horario hor, Instalacion inst) throws SQLException {
        //String categoria = cat.getTipo();
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
        
    }*/

    static boolean ConsultarGrupos(BaseDatos accesoBD, String dia1, String dia2, String hora, String min) throws SQLException {
        boolean validar = false;
        
        String query = "SELECT * FROM Horario, Usuario "
                + "WHERE Horario.dia='"+dia1+"' "
                    + "AND Horario.horaInicio='"+hora+"' "
                    + "AND Horario.Grupo_Usuario_idUsuario='"+"Usuario.idUsuario'";
        
        ResultSet res = accesoBD.ejecutaConsulta(query);
        if(res.next())
            validar = false;
        else
            validar = true;
        
        return validar;
    }

    static void crearGruposBD(BaseDatos accesoBD, Grupo g, List<Integer> listaIDAl, int idEnt, int idCat, int idTemp) throws SQLException {
        System.out.println();
        String query1 = "INSERT INTO Grupo (n_alumnos, Categoria_idCategoria, "
                + "Usuario_idUsuario, Temporada_idTemporada) VALUES "
                + "('"+listaIDAl.size()+"','"+idCat+"','"+idEnt+"','"+idTemp+"')";
        
        //int res1 = accesoBD.ejecutaActualizacion(query1);
        
        String query2 = "SELECT idGrupo FROM Grupo WHERE "
                + "Categoria_idCategoria='"+idCat+"' "
                + "AND Usuario_idUsuario='"+idEnt+"' "
                + "AND Temporada_idTemporada='"+idTemp+"'";
        
        ResultSet res2 = accesoBD.ejecutaConsulta(query2);
        
        int idGrupo =0;
        if(res2.next())
            idGrupo = res2.getInt(1);
        
        String query3 = "";
        for(Integer it : listaIDAl){
            query3 = "INSERT INTO Alumnogrupo (Alumno_idAlumno, Grupo_idGrupo, "
                    + "Grupo_Categoria_idCategoria"
                + "Usuario_idUsuario, Temporada_idTemporada) VALUES "
                + "('"+listaIDAl.size()+"','"+idCat+"','"+idEnt+"','"+idTemp+"')";
        }
        
        
        System.out.println();
    }
    
}
