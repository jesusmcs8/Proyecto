package GestionDeCategorias;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class GestorCategorias {
    
    public static String getCategoria(BaseDatos accesoBD, int idCat) throws SQLException {
        return CategoriaBD.getCategoria(accesoBD, idCat);
    }
}
