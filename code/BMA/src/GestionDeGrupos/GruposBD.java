package GestionDeGrupos;

import GestionDeAlumnos.GestorAlumnos;
import GestionDeCategorias.GestorCategorias;
import GestionDeTemporadas.GestorTemporadas;
import GestionDeUsuarios.GestorUsuarios;
import InterfazUsuario.PantallaPrincipal;
import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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

    static boolean ConsultarGrupos(BaseDatos accesoBD, String dia1, String dia2, String hora, String min, String cat, String instalacion) throws SQLException {
        /*boolean validar = false;
        
        String query = "SELECT * FROM Horario, Usuario, Grupo "
                + "WHERE Horario.dia1='"+dia1+"' "
                    + "AND Horario.hora1='"+hora+"' "
                    + "AND Grupo.Usuario_idUsuario=Usuario.idUsuario";
        
        ResultSet res = accesoBD.ejecutaConsulta(query);
        if(res.next())
            validar = false;
        else
            validar = true;
        
        return validar;*/
        
        boolean validar = false;
        
        String query = "SELECT idCategoria FROM Categoria WHERE tipo='"+cat+"'";
       
        ResultSet res = accesoBD.ejecutaConsulta(query);
        int idCat = 0;
        while(res.next())
            idCat = res.getInt(1);
        
        System.out.println();
        
        query = "SELECT count(*) from mydb.categoria, mydb.Grupo, mydb.horario, mydb.instalacion where "
                + "horario.Instalacion_idInstalacion=instalacion.idInstalacion AND "
                + "horario.hora1='18:00:00' AND grupo.categoria_idCategoria=categoria.idCategoria AND "
                + "grupo.Categoria_idCategoria='1' ";
        
        res = accesoBD.ejecutaConsulta(query);
        System.out.println(query);
        
        int nCons = 0, nCons2 = 0;
        while(res.next())
            nCons = res.getInt(1);
        
        nCons = nCons*1;
        
        query = "select count(*) from mydb.categoria, mydb.Grupo, mydb.horario, mydb.instalacion where "
                + "horario.Instalacion_idInstalacion=instalacion.idInstalacion AND "
                + "horario.hora1='18:00:00' AND grupo.categoria_idCategoria=categoria.idCategoria AND "
                + "grupo.Categoria_idCategoria<>'1' ";
        res = accesoBD.ejecutaConsulta(query);
        System.out.println(query);
        
        while(res.next())
            nCons2 = res.getInt(1);
        nCons2 = nCons2*2;
        
        
        
        instalacion = instalacion.substring(0, instalacion.indexOf(",", 0));
        
        query = "SELECT idInstalacion FROM instalacion WHERE nombre='"+instalacion+"'";
        res = accesoBD.ejecutaConsulta(query);
        int idInst = 0;
        while(res.next())
            idInst = res.getInt(1);
        
        query = "SELECT capacidadEquipos FROM instalacion WHERE idInstalacion='"+idInst+"'";
        res = accesoBD.ejecutaConsulta(query);
        
        int capacidad = 0;
        while(res.next())
            capacidad = res.getInt(1);
        
        int resta = capacidad - (nCons + nCons2);
        
        System.out.println(capacidad);
        System.out.println(nCons);
        System.out.println(nCons2);
        
        if(resta == 0){
            JOptionPane.showMessageDialog(new PantallaPrincipal(), "La instalacion esta llena", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(idCat != 1 && resta == 1)
            JOptionPane.showMessageDialog(new PantallaPrincipal(), "No hay capacidad", "Error", JOptionPane.ERROR_MESSAGE);
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
        int idTemp = 0, idCat = 0, idEnt = 0;
        
        if(temporada != "-Ninguno-")
            idTemp = GestorTemporadas.getIdTemporada(accesoBD, temporada);
        
        
        if(categoria != "-Ninguno-")
            idCat = GestorCategorias.getIdCategoria(accesoBD, categoria);
        
          
        if(entrenador != "-Ninguno-")
            idEnt = GestorUsuarios.getIdEnt(accesoBD, entrenador);
        
        List<List<String>> grupos = new ArrayList<List<String>>();
        String query;
        /*String query = "SELECT idGrupo, n_alumnos, Categoria_idCategoria, "
                + "Usuario_idUsuario, Temporada_idTemporada "
                + "FROM Grupo WHERE Categoria_idCategoria='"+idCat+"' AND "
                + "Usuario_idUsuario='"+idEnt+"' AND "
                + "Temporada_idTemporada='"+idTemp+"'";*/
        
        if(idEnt != 0 || idCat != 0 || idTemp != 0)
            query = "SELECT idGrupo, n_alumnos, Categoria_idCategoria, "
                    + "Usuario_idUsuario, Temporada_idTemporada "
                    + "FROM Grupo WHERE";
        else
            query = "SELECT idGrupo, n_alumnos, Categoria_idCategoria, "
                    + "Usuario_idUsuario, Temporada_idTemporada "
                    + "FROM Grupo ";
        
        if(idEnt != 0)
            query = query.concat(" Usuario_idUsuario='"+idEnt+"' AND");
        
        
        if(idCat != 0)
            query = query.concat(" Categoria_idCategoria='"+idCat+"' AND");
        
        if(idTemp != 0)
            query = query.concat(" Temporada_idTemporada='"+idTemp+"' AND");
        
        
        if(idEnt != 0 || idCat != 0 || idTemp != 0)
            query = query.substring(0, query.length()-4);
        
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
        
        String query3 = "SELECT nombre, localizacion FROM Instalacion WHERE "
                + "idInstalacion='"+idInst+"'";
        ResultSet res3 = accesoBD.ejecutaConsulta(query3);
        
      
        
        String inst = "";
        if(res3.next())
            inst = (res3.getString(1)+","+res3.getString(2));
        
     
        
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

    static String getEntrenador(BaseDatos accesoBD, String idGrupo) throws SQLException {
        String query = "SELECT Usuario_idUsuario FROM Grupo WHERE "
                + "idGrupo='"+idGrupo+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        int idEnt = 0;
        if(res.next())
            idEnt = res.getInt(1);
        
        String ent = GestorUsuarios.getEntrenador(accesoBD, idEnt);
        
        return ent;
    }

    static void ModificarGruposBD(Grupo g) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static void ModificarGruposBD(BaseDatos accesoBD, Grupo gNuevo, Grupo gViejo, String idGrupo, List<String> listaAlumnos) throws SQLException {
        //String query = "UPDATE Temporada SET curso='"+cursoNuevo+"' WHERE curso='"+cursoAnterior+"'";
        boolean horMod = false, dia1Mod = false, dia2Mod = false;
        String query1 = "", query2 = "";
        int res1 = 0, idTemp=0, idEnt=0, idCat = 0, idDia1 = 0;
        ResultSet res2;
        
        idCat = GestorCategorias.getIdCategoria(accesoBD, gViejo.getCategoria());
        
        if(gNuevo.getTemporada() != gViejo.getTemporada()){
            idTemp = GestorTemporadas.getIdTemporada(accesoBD, gNuevo.getTemporada());
            query1 = "UPDATE Grupo SET Temporada_idTemporada='"+idTemp+"' "
                    + "WHERE idGrupo='"+idGrupo+"'";
            res1 = accesoBD.ejecutaActualizacion(query1);
            query1 = "UPDATE AlumnoGrupo SET Grupo_Temporada_idTemporada='"+idTemp+"' "
                    + "WHERE Grupo_idGrupo='"+idGrupo+"'";
            res1 = accesoBD.ejecutaActualizacion(query1);
        }
        
        if(gNuevo.getEntrenador() != gViejo.getEntrenador()){
            idEnt = GestorUsuarios.getIdEnt(accesoBD, gNuevo.getEntrenador());
            query1 = "UPDATE Grupo SET Usuario_idUsuario='"+idEnt+"' "
                    + "WHERE idGrupo='"+idGrupo+"'";
            res1 = accesoBD.ejecutaActualizacion(query1);
            query1 = "UPDATE AlumnoGrupo SET Grupo_Usuario_idUsuario='"+idEnt+"' "
                    + "WHERE Grupo_idGrupo='"+idGrupo+"'";
            res1 = accesoBD.ejecutaActualizacion(query1);
        }
        
        
        
        if(gNuevo.getDia1() != gViejo.getDia1()){
            query1 = "SELECT Horario_idHorario FROM Grupo WHERE "
                    + "idGrupo='"+idGrupo+"'";
            res2 = accesoBD.ejecutaConsulta(query1);
            int idHor = 0;
            if(res2.next())
                idHor = res2.getInt(1);
            
            query1 = "UPDATE Horario SET dia1='"+gNuevo.getDia1()+"' "
                    + "WHERE idHorario='"+idHor+"'";
            res1 = accesoBD.ejecutaActualizacion(query1);
            
            dia1Mod = true;
        }
        
        if(gNuevo.getDia2() != gViejo.getDia2()){
            query1 = "SELECT Horario_idHorario FROM Grupo WHERE "
                    + "idGrupo='"+idGrupo+"'";
            res2 = accesoBD.ejecutaConsulta(query1);
            int idHor = 0;
            if(res2.next())
                idHor = res2.getInt(1);
            
            query1 = "UPDATE Horario SET dia2='"+gNuevo.getDia2()+"' "
                    + "WHERE idHorario='"+idHor+"'";
            res1 = accesoBD.ejecutaActualizacion(query1);
            
            dia2Mod = true;
        }
        
        if(gNuevo.getHora1() != gViejo.getHora1()){
            query1 = "SELECT Horario_idHorario FROM Grupo WHERE "
                    + "idGrupo='"+idGrupo+"'";
            res2 = accesoBD.ejecutaConsulta(query1);
            int idHor = 0;
            if(res2.next())
                idHor = res2.getInt(1);
            
            query1 = "UPDATE Horario SET hora1='"+gNuevo.getHora1()+"' "
                    + "WHERE idHorario='"+idHor+"'";
            res1 = accesoBD.ejecutaActualizacion(query1);
            
            horMod = true;
        }
            
        if(gNuevo.getInstalacion() != gViejo.getInstalacion()){
            String auxInstN, auxInstV;
            
            auxInstN = gNuevo.getInstalacion();
            auxInstN = auxInstN.substring(0, auxInstN.indexOf(","));
            
            auxInstV = gViejo.getInstalacion();
            auxInstV = auxInstV.substring(0, auxInstV.indexOf(","));
            
            query1 = "SELECT idInstalacion FROM Instalacion "
                    + "WHERE nombre='"+auxInstV+"'";
            res2 = accesoBD.ejecutaConsulta(query1);
            
            int idInstV = 0;
            if(res2.next())
                idInstV = res2.getInt(1);
            
            query1 = "SELECT idInstalacion FROM Instalacion "
                    + "WHERE nombre='"+auxInstN+"'";
            res2 = accesoBD.ejecutaConsulta(query1);
            
            int idInstN = 0;
            if(res2.next())
                idInstN = res2.getInt(1);
            
            query1 = "SELECT Horario_idHorario FROM Grupo "
                    + "WHERE idGrupo='"+idGrupo+"'";
            res2 = accesoBD.ejecutaConsulta(query1);
            
            int idHor = 0;
            if(res2.next())
                idHor = res2.getInt(1);

            query1 = "UPDATE Horario SET Instalacion_idInstalacion='"+idInstN+"' "
                   + "WHERE idHorario='"+idHor+"' AND Instalacion_idInstalacion='"+idInstV+"'";

            
            res1 = accesoBD.ejecutaActualizacion(query1);

            query1 = "UPDATE Grupo SET Horario_Instalacion_idInstalacion='"+idInstN+"' "
                    + "WHERE idGrupo='"+idGrupo+"' AND "
                    + "Horario_Instalacion_idInstalacion='"+idInstV+"' "
                    + "AND Horario_idHorario='"+idHor+"'";
            res1 = accesoBD.ejecutaActualizacion(query1);   
        }
        
        if(listaAlumnos.size() > 0){
            List<Integer> idAls = GestorAlumnos.getIdAl(accesoBD, listaAlumnos);
            
            String auxQ = "SELECT Categoria_idCategoria FROM Grupo WHERE "
                    + "idGrupo='"+idGrupo+"'";
            ResultSet auxR = accesoBD.ejecutaConsulta(auxQ);
            if(auxR.next())
                idCat = auxR.getInt(1);
            
            auxQ = "SELECT Usuario_idUsuario FROM Grupo WHERE "
                    + "idGrupo='"+idGrupo+"'";
            auxR = accesoBD.ejecutaConsulta(auxQ);
            if(auxR.next())
                idEnt = auxR.getInt(1);
            
            auxQ = "SELECT Temporada_idTemporada FROM Grupo WHERE "
                    + "idGrupo='"+idGrupo+"'";
            auxR = accesoBD.ejecutaConsulta(auxQ);
            if(auxR.next())
                idTemp = auxR.getInt(1);
            
            String query = "";
            int res = 0;
            for(Integer it : idAls){
                query = "INSERT INTO Alumnogrupo (Alumno_idAlumno, Grupo_idGrupo, "
                        + "Grupo_Categoria_idCategoria, Grupo_Usuario_idUsuario, "
                        + "Grupo_Temporada_idTemporada) VALUES "
                        + "('"+it+"','"+idGrupo+"','"+idCat+"','"+idEnt+"','"+idTemp+"')";
                res = accesoBD.ejecutaActualizacion(query);
            }
        }
    }

    static int getIdTemporada(BaseDatos accesoBD, int idGrup) throws SQLException {
        String query = "SELECT Temporada_idTemporada FROM Grupo WHERE "
                + "idGrupo='"+idGrup+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        int idTemp = 0;
        if(res.next())
            idTemp = res.getInt(1);
        
        return idTemp;
    }

    static boolean EliminarGrupo(BaseDatos accesoBD, Grupo g) throws SQLException {
        boolean GrupoEliminado = false;
        
        String query1 = "SELECT Horario_idHorario FROM Grupo WHERE "
                + "idGrupo='"+g.getIdGrupo()+"'";
        ResultSet res1 = accesoBD.ejecutaConsulta(query1);
        
        int idHor = 0;
        if(res1.next())
            idHor = res1.getInt(1);
        
        
        
        String query3 = "DELETE FROM AlumnoGrupo WHERE "
                + "Grupo_idGrupo='"+g.getIdGrupo()+"'";
        boolean eliminaAlumGrup = accesoBD.eliminar(query3);
        
        
        
        String query4 = "DELETE FROM Grupo WHERE "
                + "idGrupo='"+g.getIdGrupo()+"'";
        boolean eliminaGrupo = accesoBD.eliminar(query4);
        
        String query2 = "DELETE FROM Horario WHERE idHorario='"+idHor+"'";
        boolean eliminaHor = accesoBD.eliminar(query2);
        
        if(eliminaHor == true && eliminaGrupo == true && eliminaAlumGrup == true)
            GrupoEliminado = true;
        else
            GrupoEliminado = false;
        
        return GrupoEliminado;
    }
    
}
