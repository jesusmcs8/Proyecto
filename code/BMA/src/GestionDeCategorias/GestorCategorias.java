package GestionDeCategorias;

import InterfazUsuario.NuevaCategoria;
import ServiciosAlmacenamiento.BaseDatos;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class GestorCategorias {
    
    public static String getCategoria(BaseDatos accesoBD, int idCat) throws SQLException {
        return CategoriaBD.getCategoria(accesoBD, idCat);
    }

    public static int getIdCategoria(BaseDatos accesoBD, String categoria) throws SQLException {
        return CategoriaBD.getIdCategoria(accesoBD, categoria);
    }

    public static void InsertarDatosCategorias(BaseDatos accesoBD, String t, String desc) throws SQLException {
        boolean validar = CategoriaBD.ConsultarCategoria(accesoBD, t);
        int resAct = 0;
        
        if(!validar){
            JOptionPane.showMessageDialog(new NuevaCategoria(), "La categoria ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        else{
            int continuar = JOptionPane.showConfirmDialog(new NuevaCategoria(), "¿Desea crear la categoria", "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);
            if(continuar == JOptionPane.YES_OPTION){
                Categoria c = new Categoria(t, desc);
                resAct = CategoriaBD.crearCategoria(accesoBD, c);
                
                if(resAct > 0)
                    JOptionPane.showMessageDialog(new NuevaCategoria(), "Categoria creada con exito", "Exito", JOptionPane.NO_OPTION);  
                
            }     
        }
    }


    public static List<List<String>> getListaCategorias(BaseDatos accesoBD) throws SQLException {
        return CategoriaBD.getListaCategorias(accesoBD);
    }

    public static void ModificarCategoria(BaseDatos accesoBD, Categoria c) {
        int aceptadaModificacion = Categoria.Modificar(accesoBD, c);
    }

    
}
