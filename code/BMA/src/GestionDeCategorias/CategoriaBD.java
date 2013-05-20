package GestionDeCategorias;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    static boolean ConsultarCategoria(BaseDatos accesoBD, String t) throws SQLException {
             
        String query = "SELECT * FROM Categoria WHERE "
                + "tipo='"+t+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        if(res.next())
            return false;
        else
            return true;
    }

    static int crearCategoria(BaseDatos accesoBD, Categoria c) throws SQLException {
        String query = "INSERT INTO Categoria (tipo, descripcion) VALUES ('"+c.getNombreCategoria()+"',"
                + "'"+c.getDescripcion()+"')";
        int correcto = accesoBD.ejecutaActualizacion(query);
        return correcto;
    }

    static List<List<String>> getListaCategorias(BaseDatos accesoBD) throws SQLException {
        List<List<String>> listaCats = new ArrayList<List<String>>();
        
        String query = "SELECT tipo,descripcion FROM Categoria";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        List<String> aux;
        
        while(res.next()){
            aux = new ArrayList<String>();
            aux.add(res.getString(1)+" "+res.getString(2));
            listaCats.add(aux);
        }
        
        return listaCats;
    }

    static int ModificarCategoria(BaseDatos accesoBD, Categoria cNuevo, Categoria cViejo) throws SQLException {
        int correcto = 0;
        
        String query = "SELECT idCategoria FROM Categoria WHERE "
                + "tipo='"+cViejo.getNombreCategoria()+"' AND "
                + "descripcion='"+cViejo.getDescripcion()+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        int idCat = 0;
        while(res.next())
            idCat = res.getInt(1);
        
        query = "UPDATE Categoria SET tipo='"+cNuevo.getNombreCategoria()+"', "
                + "descripcion='"+cNuevo.getDescripcion()+"' WHERE "
                + "idCategoria='"+idCat+"'";
        
        correcto = accesoBD.ejecutaActualizacion(query);
        
        return correcto;
    }

    static boolean existeCategoria(BaseDatos accesoBD, Categoria c) throws SQLException {
        boolean existe = false;
        String query = "SELECT * FROM Categoria WHERE "
                + "tipo='"+c.getNombreCategoria()+"' AND "
                + "descripcion='"+c.getDescripcion()+"'";
        
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        if(res.next())
            existe = true;
        else
            existe = false;
        
        System.out.println();
        System.out.println("existe vale:"+existe);
        
        return existe;
    }

    static boolean EliminarCategoria(BaseDatos accesoBD, Categoria c) {
        String query = "DELETE FROM Categoria WHERE "
                + "tipo='"+c.getNombreCategoria()+"' AND "
                + "descripcion='"+c.getDescripcion()+"'";
        System.out.println(query);
        boolean res = accesoBD.eliminar(query);
        
        
        return res;
    }

    static List<String> getTipoCategorias(BaseDatos accesoBD) throws SQLException {
        List<String> listaCats = new ArrayList<String>();
        
        String query = "SELECT tipo FROM Categoria";
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        while (res.next()) {
            listaCats.add(res.getString(1));
        }
        
        return listaCats;
    }
}
