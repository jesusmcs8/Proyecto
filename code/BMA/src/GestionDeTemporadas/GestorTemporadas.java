package GestionDeTemporadas;

import GestionDeAlumnos.Alumno;
import GestionDeGrupos.Grupo;
import InterfazUsuario.NuevaTemporada;
import InterfazUsuario.PantallaPrincipal;
import ServiciosAlmacenamiento.BaseDatos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class GestorTemporadas {

    //private List<Temporada> temporadas;
    
    public static List<String> getListaTemporadas(BaseDatos accesoBD) throws SQLException {
        List<String> res = new ArrayList<String>();
        res = TemporadaBD.getListaTemporadas(accesoBD);
        
        return res;
    }

    public static int modificarTemporada(BaseDatos accesoBD, String cursoAnterior) throws SQLException {
        int correcto = 0;
        String auxCursoAnt = cursoAnterior;
        
        cursoAnterior = cursoAnterior.substring(0, 4);
        String cursoNuevo = JOptionPane.showInputDialog(new PantallaPrincipal(), "Introduzca el nuevo año del curso", cursoAnterior);
        
        while(cursoNuevo.length() != 4){
            JOptionPane.showMessageDialog(new PantallaPrincipal(), "Numero de digitos incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            cursoNuevo = JOptionPane.showInputDialog(new PantallaPrincipal(), "Introduzca el nuevo año del curso", cursoAnterior);
        }
        
        int conf = JOptionPane.showConfirmDialog(new PantallaPrincipal(), "¿Desea modificar la temporada "+auxCursoAnt+"?");
        if(conf == JOptionPane.YES_OPTION){
            cursoAnterior = cursoAnterior +"/"+Integer.toString(Integer.parseInt(cursoAnterior)+1);
            cursoNuevo = cursoNuevo + "/"+Integer.toString(Integer.parseInt(cursoNuevo)+1);
            
            correcto = TemporadaBD.modificarTemporada(accesoBD, cursoAnterior, cursoNuevo);
        }
        
        return correcto;
    }

    public static boolean eliminarTemporada(BaseDatos accesoBD, Temporada t) {
        boolean correcto = false;
        System.out.println("hola");
        int conf = JOptionPane.showConfirmDialog(new PantallaPrincipal(), "¿Desea eliminar la temporada "+t.getCurso()+"?");
        if(conf == JOptionPane.YES_OPTION)
            correcto = TemporadaBD.eliminarTemporadaBD(accesoBD, t);
        
        return correcto;
    }

    
    
    public static int InsertarTemporada(int curso, BaseDatos accesoBD) throws SQLException {
        int correcto = 0;
        boolean validar = TemporadaBD.consultarTemporada(curso, accesoBD);
        
        if(validar == false)
            JOptionPane.showMessageDialog(new NuevaTemporada(), "La temporada ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        else{
            int conf = JOptionPane.showConfirmDialog(new NuevaTemporada(), "¿Desea crear la temporada?");
            if(conf == JOptionPane.YES_OPTION){
                int auxCurso = curso+1;
                String cursoComp = Integer.toString(curso)+"/"+Integer.toString(auxCurso);
                Temporada t = new Temporada(cursoComp);
                correcto = TemporadaBD.insertarTemporadaBD(accesoBD, t);
            }
        }
        return correcto;
    }
        
    public  List<Temporada> ConsultarTemporada(String curso){
        return new ArrayList<Temporada>();
    }
    
    public void EliminarTemporada(Temporada temp){
        
    }
    
}
