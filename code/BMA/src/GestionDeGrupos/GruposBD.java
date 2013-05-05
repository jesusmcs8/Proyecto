package GestionDeGrupos;

import GestionDeCategorias.GestorCategorias;
import GestionDeTemporadas.GestorTemporadas;
import GestionDeUsuarios.GestorUsuarios;
import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
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
        
        String query = "SELECT * FROM Horario, Usuario, Grupo "
                + "WHERE Horario.dia1='"+dia1+"' "
                    + "AND Horario.hora1='"+hora+"' "
                    + "AND Grupo.Usuario_idUsuario=Usuario.idUsuario";
        
        ResultSet res = accesoBD.ejecutaConsulta(query);
        if(res.next())
            validar = false;
        else
            validar = true;
        
        return validar;
    }

    static void crearGruposBD(BaseDatos accesoBD, Grupo g, List<Integer> listaIDAl, int idEnt, int idCat, int idTemp, int idInst) throws SQLException {
        
        
        String dia1, dia2;
        dia1 = g.getDia1();
        dia2 = g.getDia2();
        Time hora = g.getHora1Time();
        
        String query1 = "INSERT INTO Horario (Instalacion_IdInstalacion, "
                + "dia1, dia2, hora1, hora2) VALUES "
                + "('"+idInst+"','"+dia1+"','"+dia2+"','"+hora+"','"+hora+"')";
        int res1 = accesoBD.ejecutaActualizacion(query1);
        
        
        String query2 = "SELECT idHorario FROM Horario WHERE "
                + "Instalacion_idInstalacion='"+idInst+"'"
                + " AND dia1='"+dia1+"' AND dia2='"+dia2+"' AND hora1='"+hora+"'";
        ResultSet res2 = accesoBD.ejecutaConsulta(query2);
        int idHorario = 0;
        if(res2.next())
            idHorario = res2.getInt(1);
        
        String query3 = "INSERT INTO Grupo (n_alumnos, Categoria_idCategoria, "
                + "Usuario_idUsuario, Temporada_idTemporada, Horario_idHorario, "
                + "Horario_Instalacion_idInstalacion) VALUES "
                + "('"+listaIDAl.size()+"','"+idCat+"','"+idEnt+"','"+idTemp+"',"
                + "'"+idHorario+"',"+"'"+idInst+"')";
        int res3 = accesoBD.ejecutaActualizacion(query3);
        
        
        String query4 = "SELECT idGrupo FROM Grupo WHERE "
                + "Categoria_idCategoria='"+idCat+"' "
                + "AND Usuario_idUsuario='"+idEnt+"' "
                + "AND Temporada_idTemporada='"+idTemp+"' "
                + "AND Horario_idHorario='"+idHorario+"' "
                + "AND Horario_Instalacion_idInstalacion='"+idInst+"'";
        ResultSet res4 = accesoBD.ejecutaConsulta(query4);
        int idGrupo = 0;
        if(res4.next())
            idGrupo = res4.getInt(1);
        
        String query5 = "";
        int res5 = 0;
        for(Integer it : listaIDAl){
            query5 = "INSERT INTO Alumnogrupo (Alumno_idAlumno, Grupo_idGrupo, "
                    + "Grupo_Categoria_idCategoria, Grupo_Usuario_idUsuario, "
                    + "Grupo_Temporada_idTemporada) VALUES "
                + "('"+it+"','"+idGrupo+"','"+idCat+"','"+idEnt+"','"+idTemp+"')";
            res5 = accesoBD.ejecutaActualizacion(query5);
        }
        
    }

    static List<List<String>> getListaGrupos(BaseDatos accesoBD) throws SQLException {
        List<List<String>> grupos = new ArrayList<List<String>>();

        String query = "SELECT idGrupo, n_alumnos, Categoria_idCategoria, "
                + "Usuario_idUsuario, Temporada_idTemporada "
                + "FROM Grupo";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        List<String> aux;
        
        while(res.next()){
            aux = new ArrayList<String>();
            aux.add(res.getString(1)+","+res.getString(2)+","+res.getString(3)+","+res.getString(4)+","+res.getString(5));
            grupos.add(aux);
        }
        
        return grupos;
    }

    static List<List<String>> getListaGruposFiltro(BaseDatos accesoBD, String temporada, String categoria, String entrenador) throws SQLException {
        int idTemp, idCat = 0, idEnt;
        idTemp = GestorTemporadas.getIdTemporada(accesoBD, temporada);
        
        /*****************************************/
        /*********PROVISIONAL*********************/
        String auxQ = "SELECT idCategoria FROM Categoria WHERE tipo='"+categoria+"'";
        ResultSet auxR = accesoBD.ejecutaConsulta(auxQ);
        if(auxR.next())
            idCat = auxR.getInt(1);
        /*****************************************/
        
        idEnt = GestorUsuarios.getIdEnt(accesoBD, entrenador);
        
        List<List<String>> grupos = new ArrayList<List<String>>();

        String query = "SELECT idGrupo, n_alumnos, Categoria_idCategoria, "
                + "Usuario_idUsuario, Temporada_idTemporada "
                + "FROM Grupo WHERE Categoria_idCategoria='"+idCat+"' AND "
                + "Usuario_idUsuario='"+idEnt+"' AND "
                + "Temporada_idTemporada='"+idTemp+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        
        
        List<String> aux;
        
        while(res.next()){
            aux = new ArrayList<String>();
            aux.add(res.getString(1)+","+res.getString(2)+","+res.getString(3)+","+res.getString(4)+","+res.getString(5));
            grupos.add(aux);
        }
        
        return grupos;
    }

    static String getTemporada(BaseDatos accesoBD, String idGrupo) throws SQLException {
        
        String query = "SELECT Temporada_idTemporada FROM Grupo WHERE "
                + "idGrupo='"+idGrupo+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        int idTemp = 0;
        if(res.next())
            idTemp = res.getInt(1);
        
      
        
        String temp = GestorTemporadas.getTemporada(accesoBD, idTemp);
        return temp;
    }

    static String getCategoria(BaseDatos accesoBD, String idGrupo) throws SQLException {
        String query = "SELECT Categoria_idCategoria FROM Grupo WHERE "
                + "idGrupo='"+idGrupo+"'";
        
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        int idCat = 0;
        if(res.next())
            idCat = res.getInt(1);
        
        return GestorCategorias.getCategoria(accesoBD, idCat);
    }

    static String getHora1(BaseDatos accesoBD, String idGrupo) throws SQLException {
        String query = "SELECT Horario_idHorario FROM Grupo WHERE "
                + "idGrupo='"+idGrupo+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        int idHor = 0;
        if(res.next())
            idHor = res.getInt(1);
        
        String query2 = "SELECT hora1 FROM Horario WHERE "
                + "idHorario='"+idHor+"'";
        ResultSet res2 = accesoBD.ejecutaConsulta(query2);
        
        String hora1 = "";
        if(res2.next())
            hora1 = res2.getString(1);
        
        return hora1;
    }

    static String getHora2(BaseDatos accesoBD, String idGrupo) throws SQLException {
        String query = "SELECT Horario_idHorario FROM Grupo WHERE "
                + "idGrupo='"+idGrupo+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        int idHor = 0;
        if(res.next())
            idHor = res.getInt(1);
        
        String query2 = "SELECT hora2 FROM Horario WHERE "
                + "idHorario='"+idHor+"'";
        ResultSet res2 = accesoBD.ejecutaConsulta(query2);
        
        String hora2 = "";
        if(res2.next())
            hora2 = res2.getString(1);
        
        return hora2;
    }

    static String getDia1(BaseDatos accesoBD, String idGrupo) throws SQLException {
        String query = "SELECT Horario_idHorario FROM Grupo WHERE "
                + "idGrupo='"+idGrupo+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        int idHor = 0;
        if(res.next())
            idHor = res.getInt(1);

        String query2 = "SELECT dia1 FROM Horario WHERE "
                + "idHorario='"+idHor+"'";
        ResultSet res2 = accesoBD.ejecutaConsulta(query2);
        
        String dia1 = "";
        if(res2.next())
            dia1 = res2.getString(1);
        
        
        
        return dia1;
    }

    static String getDia2(BaseDatos accesoBD, String idGrupo) throws SQLException {
        String query = "SELECT Horario_idHorario FROM Grupo WHERE "
                + "idGrupo='"+idGrupo+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        int idHor = 0;
        if(res.next())
            idHor = res.getInt(1);
        
        String query2 = "SELECT dia2 FROM Horario WHERE "
                + "idHorario='"+idHor+"'";
        ResultSet res2 = accesoBD.ejecutaConsulta(query2);
        
        String dia2 = "";
        if(res2.next())
            dia2 = res2.getString(1);
        
        return dia2;
    }

    static String getInstalacion(BaseDatos accesoBD, String idGrupo) throws SQLException {
        String query = "SELECT Horario_idHorario FROM Grupo WHERE "
                + "idGrupo='"+idGrupo+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        int idHor = 0;
        if(res.next())
            idHor = res.getInt(1);
        
        String query2 = "SELECT Instalacion_idInstalacion FROM Horario WHERE "
                + "idHorario='"+idHor+"'";
        ResultSet res2 = accesoBD.ejecutaConsulta(query2);
        
        
        
        int idInst = 0;
        if(res2.next())
            idInst = res2.getInt(1);
        
        String query3 = "SELECT nombre FROM Instalacion WHERE "
                + "idInstalacion='"+idInst+"'";
        ResultSet res3 = accesoBD.ejecutaConsulta(query3);
        
      
        
        String inst = "";
        if(res3.next())
            inst = res3.getString(1);
        
     
        
        return inst;
    }

    static List<String> geteListaAlumnosIntroducidos(BaseDatos accesoBD, String idGrupo) throws SQLException {
        
        String query = "SELECT Alumno_idAlumno From AlumnoGrupo WHERE "
                + "Grupo_idGrupo='"+idGrupo+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        
        int idAl  = 0;
        String query2 = "";
        ResultSet res2 = null;
        
        List<String> als = new ArrayList<String>();
        
        while(res.next()){
            idAl = res.getInt(1);
            query2 = "SELECT nombre, primerApellido, segundoApellido FROM Alumno "
                + "WHERE idAlumno='"+idAl+"'";
            res2 = accesoBD.ejecutaConsulta(query2);
            res2.next();
            als.add(res2.getString(2)+" "+res2.getString(3)+" "+res2.getString(1));
        }
        
        return als;
    }

    static void eliminarAlumnoIntroducido(BaseDatos accesoBD, int idGrupo, int idAl) {
        String query = "DELETE FROM AlumnoGrupo WHERE "
                + "Alumno_idAlumno='"+idAl+"' AND Grupo_idGrupo='"+idGrupo+"'";
       
        boolean res = accesoBD.eliminar(query);
        
        if(res)
            System.out.println("se ha eliminado");
        else
            System.out.println("no se ha eliminado");
    }
    
}
