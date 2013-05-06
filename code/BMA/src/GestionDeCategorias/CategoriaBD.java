package GestionDeCategorias;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class CategoriaBD {
    static String getCategoria(BaseDatos accesoBD, int idCat) throws SQLException {
        String query = "SELECT tipo FROM Categoria WHERE "
                + "idCategoria='"+idCat+"'";
        
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        String cat = "";
        if(res.next())
            cat = res.getString(1);
        
        return cat;
    }

    static int getIdCategoria(BaseDatos accesoBD, String categoria) throws SQLException {
        String query = "SELECT idCategoria FROM Categoria WHERE "
                + "tipo='"+categoria+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        int idCat = 0;
        if(res.next())
            idCat = res.getInt(1);
        
        return idCat;
    }
}
