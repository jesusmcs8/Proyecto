/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bma;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Francisco
 * @author Carlos
 */
public class BaseDatos {

    Connection conexion;
    ArrayList<Connection> Cooconexion = new ArrayList();
    Statement stmt;
    ResultSet retset;

    public BaseDatos(){        
    }
    
    public BaseDatos(String driver, String servidor, String usuario, String clave) {
        try {
            Class.forName(driver);
            //Busca el controlador y abre conexion
                //("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/mydb", "root", "baloncesto");
            DriverManager.getConnection(servidor, usuario, clave);

        } catch (ClassNotFoundException e) {
            System.err.print("No se han podido cargar los controladores");
        } catch (SQLException e) {
            System.err.print("No se ha podido acceder a la base de datos");
        }
    }

    public Connection abreConexion(String driver, String servidor, String usuario, String clave) {
        try {
            Class.forName(driver);
            //Busca el controlador y abre conexion
            return DriverManager.getConnection(servidor, usuario, clave);
        } catch (ClassNotFoundException e) {
            System.out.print("No se han podido cargar los controladores");
            return null;
        } catch (SQLException e) {
            System.out.print("No se ha podido acceder a la base de datos");
            return null;
        }
    }

    public void conectaBD() {
        conexion = abreConexion("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/mydb", "root", "baloncesto");
    }

    public ResultSet ejecutaConsulta(String consulta) {

        try {
            stmt = conexion.createStatement();
            retset = stmt.executeQuery(consulta);
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }


        return retset;
    }

    public void ejecutaActualizacion(String actualizacion) {
        try{
            stmt = conexion.createStatement();
            stmt.executeUpdate(actualizacion);
        }catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
