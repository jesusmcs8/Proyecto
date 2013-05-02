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

    private List<Grupo> grupos;
    
    public static void insertarDatosGrupo(BaseDatos accesoBD, List<String> listaAlumnos, 
            String temporada, String categoria, String sexo, String dia1, 
            String dia2, String hora, String min, String entrenador) throws ParseException, SQLException {
        
        
        
        boolean validar = GruposBD.ConsultarGrupos(accesoBD, dia1, dia2, hora, min);
        if(validar == false)
            JOptionPane.showMessageDialog(new NuevoGrupo(), "El grupo ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        else{
            List<Integer> listaIDAl = new ArrayList<Integer>();
            listaIDAl = GestorAlumnos.getIdAl(accesoBD, listaAlumnos);
            
            int idEnt = GestorUsuarios.getIdEnt(accesoBD, entrenador);
            
            /***************************************************************/
            /**CODIGO PROVISIONAL HASTA TENER IMPLEMENTACION DE CATEGORIA **/
            String query = "SELECT idCategoria FROM Categoria WHERE tipo='"+categoria+"'";
            ResultSet res = accesoBD.ejecutaConsulta(query);
            int idCat=0;
            if(res.next())
                idCat = res.getInt(1);
            /***************************************************************/
            /***************************************************************/
            int idTemp = GestorTemporadas.getIdTemporada(accesoBD, temporada);
            
            Grupo g = new Grupo(sexo, temporada, dia1, dia2, hora, min, entrenador);
            
            GruposBD.crearGruposBD(accesoBD, g, listaIDAl, idEnt, idCat, idTemp);
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
