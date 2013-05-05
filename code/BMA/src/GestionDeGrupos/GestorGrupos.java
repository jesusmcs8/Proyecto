package GestionDeGrupos;

import GestionDeAlumnos.Alumno;
import GestionDeAlumnos.GestorAlumnos;
import GestionDeInstalaciones.Instalacion;
import GestionDeTemporadas.GestorTemporadas;
import GestionDeUsuarios.Usuario;
import bma.Categoria;
import GestionDeTemporadas.Temporada;
import GestionDeUsuarios.GestorUsuarios;
import InterfazUsuario.NuevoGrupo;
import ServiciosAlmacenamiento.BaseDatos;
import bma.Horario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class GestorGrupos {

    public static List<List<String>> getListaGrupos(BaseDatos accesoBD) throws SQLException {
        List<List<String>> grupos = new ArrayList<List<String>>();
        grupos = GruposBD.getListaGrupos(accesoBD);
        
        return grupos;
    }

    public static List<List<String>> getListaGruposFiltro(BaseDatos accesoBD, String temporada, String categoria, String entrenador) throws SQLException {
        List<List<String>> grupos = new ArrayList<List<String>>();
        grupos = GruposBD.getListaGruposFiltro(accesoBD, temporada, categoria, entrenador);
        
        return grupos;
    }

    public static String getTemporada(BaseDatos accesoBD, String idGrupo) throws SQLException {
        return GruposBD.getTemporada(accesoBD, idGrupo);
    }

    public static String getCategoria(BaseDatos accesoBD, String idGrupo) throws SQLException {
        return GruposBD.getCategoria(accesoBD, idGrupo);
    }

    public static String getHora1(BaseDatos accesoBD, String idGrupo) throws SQLException {
        return GruposBD.getHora1(accesoBD, idGrupo);
    }

    public static String getHora2(BaseDatos accesoBD, String idGrupo) throws SQLException {
        return GruposBD.getHora2(accesoBD, idGrupo);
    }

    public static String getDia1(BaseDatos accesoBD, String idGrupo) throws SQLException {
        return GruposBD.getDia1(accesoBD, idGrupo);
    }

    public static String getDia2(BaseDatos accesoBD, String idGrupo) throws SQLException {
        return GruposBD.getDia2(accesoBD, idGrupo);
    }

    public static String getInstalacion(BaseDatos accesoBD, String idGrupo) throws SQLException {
        return GruposBD.getInstalacion(accesoBD, idGrupo);
    }

    public static List<String> getListaAlumnosIntroducidos(BaseDatos accesoBD, String idGrupo) throws SQLException {
        return GruposBD.geteListaAlumnosIntroducidos(accesoBD, idGrupo);
    }

    public static void eliminarAlumnoIntroducido(BaseDatos accesoBD, int idGrupo, int idAl) {
        GruposBD.eliminarAlumnoIntroducido(accesoBD, idGrupo, idAl);
    }

    private List<Grupo> grupos;
    
    public static void insertarDatosGrupo(BaseDatos accesoBD, List<String> listaAlumnos, 
            String temporada, String categoria, String sexo, String dia1, 
            String dia2, String hora, String min, String entrenador, String instalacion) throws ParseException, SQLException {
        
        
        
        boolean validar = GruposBD.ConsultarGrupos(accesoBD, dia1, dia2, hora, min);
        if(validar == false)
            JOptionPane.showMessageDialog(new NuevoGrupo(), "El grupo ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        else{
            List<Integer> listaIDAl = new ArrayList<Integer>();
            listaIDAl = GestorAlumnos.getIdAl(accesoBD, listaAlumnos);
            
            int idEnt = GestorUsuarios.getIdEnt(accesoBD, entrenador);
            
            /***************************************************************/
            /**CODIGO PROVISIONAL HASTA TENER IMPLEMENTACION DE CATEGORIA **/
            String query1 = "SELECT idCategoria FROM Categoria WHERE tipo='"+categoria+"'";
            ResultSet res1 = accesoBD.ejecutaConsulta(query1);
            int idCat = 0;
            if(res1.next())
                idCat = res1.getInt(1);
            /***************************************************************/
            /***************************************************************/
            
            int idTemp = GestorTemporadas.getIdTemporada(accesoBD, temporada);
            
            /***************************************************************/
            /**CODIGO PROVISIONAL HASTA TENER IMPLEMENTACION DE INSTALACIONES **/
            String auxString = instalacion.substring(0, instalacion.indexOf(","));
            String query2 = "SELECT idInstalacion FROM Instalacion WHERE nombre='"+auxString+"'";
            ResultSet res2 = accesoBD.ejecutaConsulta(query2);
            int idInst = 0;
            if(res2.next())
                idInst = res2.getInt(1);
            /***************************************************************/
            /***************************************************************/
            Grupo g = new Grupo(sexo, temporada, dia1, dia2, hora, min, entrenador);
            
            GruposBD.crearGruposBD(accesoBD, g, listaIDAl, idEnt, idCat, idTemp, idInst);
        }
       
    }
    
    /*public void InsertarGrupo(BaseDatos accesoBD, Temporada temp, List<Alumno> als, Categoria cat, Horario hor, Usuario ent, Instalacion inst) throws SQLException{
        boolean validar = GruposBD.ConsultarGrupos(accesoBD, temp, cat, hor, inst);
    }*/
    
    public List<Grupo> ConsultarGrupo(Temporada t, List<Alumno> a, Categoria c, Usuario ent, Instalacion inst){
        return new ArrayList<Grupo>();
    }
    
    public void ModificarGrupo(Grupo g){
        
    }
    
    public void EliminarGrupo(Grupo g){
        
    }
}
