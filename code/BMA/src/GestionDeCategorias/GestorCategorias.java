package GestionDeCategorias;

import InterfazUsuario.ModificarCategoria;
import InterfazUsuario.NuevaCategoria;
import InterfazUsuario.PantallaPrincipal;
import ServiciosAlmacenamiento.BaseDatos;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class GestorCategorias {
    
    public static String getCategoria(BaseDatos accesoBD, int idCat) throws SQLException {
        return CategoriaBD.getCategoria(accesoBD, idCat);
    }

    public static int getIdCategoria(BaseDatos accesoBD, String categoria) throws SQLException, SQLException, SQLException, SQLException, SQLException {
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

    public static void ModificarCategoria(BaseDatos accesoBD, Categoria cNuevo, Categoria cViejo) throws SQLException {
        boolean existe = false;
        existe = existeCategoria(accesoBD, cNuevo);
        
        int aceptadaModificacion = 0;
                
        if(!existe){
            aceptadaModificacion = Categoria.Modificar(accesoBD, cNuevo, cViejo);
            if(aceptadaModificacion > 0)
                JOptionPane.showMessageDialog(new ModificarCategoria(), "Categoria modificada", "Exito", JOptionPane.NO_OPTION);
        }
        else{
            JOptionPane.showMessageDialog(new ModificarCategoria(), "La categoria ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
       
    }

    private static boolean existeCategoria(BaseDatos accesoBD, Categoria c) throws SQLException {
        return CategoriaBD.existeCategoria(accesoBD, c);
    }

    public static void EliminarCategorias(BaseDatos accesoBD, Categoria c) {
        boolean categoriaEliminada = false;
        
        categoriaEliminada = CategoriaBD.EliminarCategoria(accesoBD, c);
        
        if(categoriaEliminada)
            JOptionPane.showMessageDialog(new PantallaPrincipal(), "Categoria eliminada", "Exito", JOptionPane.NO_OPTION);
        else
            JOptionPane.showMessageDialog(new PantallaPrincipal(), "Error al eliminar", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static List<String> getTipoCategorias(BaseDatos accesoBD) throws SQLException {
        return CategoriaBD.getTipoCategorias(accesoBD);
    }

    }

    

