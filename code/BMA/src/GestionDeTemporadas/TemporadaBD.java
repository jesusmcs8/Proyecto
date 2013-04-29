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
    
}
