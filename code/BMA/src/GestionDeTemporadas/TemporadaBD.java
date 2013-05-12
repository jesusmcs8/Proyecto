package GestionDeTemporadas;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class TemporadaBD {

    static boolean consultarTemporada(int curso, BaseDatos accesoBD) throws SQLException {
        boolean validar = false;
        
        int auxCurso = curso+1;
        String temp = Integer.toString(curso)+"/"+Integer.toString(auxCurso);
        
        String query = "SELECT * FROM Temporada WHERE curso='"+temp+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        if(res.next())
            validar = false;
        else
            validar = true;
        
        return validar;
    }

    static int insertarTemporadaBD(BaseDatos accesoBD, Temporada t) throws SQLException {
        String query = "INSERT INTO Temporada (curso) VALUES ('"+t.getCurso()+"')";
        int correcto = accesoBD.ejecutaActualizacion(query);
        return correcto;
    }

    static List<String> getListaTemporadas(BaseDatos accesoBD) throws SQLException {
        String query = "SELECT curso FROM Temporada";
        ResultSet resCons = accesoBD.ejecutaConsulta(query);
        
        List<String> res = new ArrayList<String>();
        
        while(resCons.next()){
            res.add(resCons.getString(1));
        }
        
        return res;
    }

    static int modificarTemporada(BaseDatos accesoBD, String cursoAnterior, String cursoNuevo) throws SQLException {
        int correcto = 0;
        String query = "UPDATE Temporada SET curso='"+cursoNuevo+"' WHERE curso='"+cursoAnterior+"'";
        correcto = accesoBD.ejecutaActualizacion(query);
        
        return correcto;
    }

    static boolean eliminarTemporadaBD(BaseDatos accesoBD, Temporada t) {
        boolean correcto = false;
        String query = "DELETE FROM Temporada WHERE curso='"+t.getCurso()+"'";
        correcto = accesoBD.eliminar(query);
        
        return correcto;
    }

    static int getIdTemporada(BaseDatos accesoBD, String temporada) throws SQLException {
        int idTemp = 0;
        
        String query = "SELECT idTemporada FROM Temporada WHERE curso='"+temporada+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        if(res.next())
            idTemp = res.getInt(1);

        return idTemp;
    }

    static String getTemporada(BaseDatos accesoBD, String s) throws SQLException {
        String query = "SELECT curso FROM Temporada WHERE idTemporada='"+s+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        String temp = "";
        
        if(res.next())
            temp = res.getString(1);
        
        return temp;
    }

    static String getTemporada(BaseDatos accesoBD, int idTemp) throws SQLException {
        String query = "SELECT curso FROM Temporada WHERE idTemporada='"+idTemp+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        String temp = "";
        
        if(res.next())
            temp = res.getString(1);
        
        return temp;
    }
    
}
