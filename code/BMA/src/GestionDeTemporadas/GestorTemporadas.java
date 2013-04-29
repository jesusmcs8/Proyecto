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

    public static List<String> getListaTemporadas(BaseDatos accesoBD) throws SQLException {
        List<String> res = new ArrayList<String>();
        res = TemporadaBD.getListaTemporadas(accesoBD);
        
        return res;
    }

    private List<Temporada> temporadas;
    
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
