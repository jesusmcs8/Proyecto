/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeInstalaciones;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.*;

/**
 *
 * @author Jesus Manuel Contreras Siles
 */
public class GestorInstalacion {

    public static boolean darAltaInstalacion(BaseDatos accesoBD, String nombre,
            int capacidad, String localizacion) {

        boolean exito = true;
        Instalacion instalacion = Instalacion.crearInstalacion(nombre, capacidad, localizacion);

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
}