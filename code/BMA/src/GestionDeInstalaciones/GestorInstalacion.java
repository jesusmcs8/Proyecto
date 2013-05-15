/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeInstalaciones;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Jesus Manuel Contreras Siles
 */
public class GestorInstalacion {

    public static boolean darAltaInstalacion(BaseDatos accesoBD, String nombre,
            int capacidad, String localizacion) {

        boolean exito = true;
        Instalacion instalacion = new Instalacion(nombre, capacidad, localizacion);

        try {
            AccesoBDInstalacion.insertarInstalacionBD(accesoBD, instalacion);
        } catch (SQLException ex) {
            exito = false;
        }


        return exito;
    }

    public static ResultSet consultaInstalacion(BaseDatos accesoBD, String consulta) {
        AccesoBDInstalacion instalacion = new AccesoBDInstalacion();

        return instalacion.consultaInstalacionBD(accesoBD, consulta);
    }

    public static boolean modificaInstalacion(BaseDatos accesoBD, int idInstalacion, String nombre,
            String capacidad, String locacizacion) {

        return AccesoBDInstalacion.modificarDatosInstalacionBD(accesoBD, idInstalacion,
                nombre, capacidad, locacizacion);
    }
    
    public static void eliminaInstalacion(BaseDatos accesoBD, String nombre,
            int capacidad, String locacizacion){
        
        AccesoBDInstalacion instalacionBD = new AccesoBDInstalacion();
        
        Instalacion instalacionEliminada = Instalacion.crearInstalacion(nombre, capacidad, locacizacion);
        
        instalacionBD.eliminarInstalacionBD(accesoBD, instalacionEliminada);
        
    }

    public static int getIdInstalacion(BaseDatos accesoBD, String inst) throws SQLException {
        return AccesoBDInstalacion.getIdInstalacion(accesoBD, inst);
    }

    public static List<String> getListaInstalaciones(BaseDatos accesoBD) throws SQLException {
        return AccesoBDInstalacion.getListaInstalaciones(accesoBD);
    }
}