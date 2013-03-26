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

    ArrayList<Connection> conexion = new ArrayList();
    Statement stmt;
    ResultSet retset;
    String driver;
    String servidor;
    String usuario;
    String clave;

    public BaseDatos() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            driver = "com.mysql.jdbc.Driver";
            servidor = "jdbc:mysql://localhost:3306/mydb";
            usuario = "root";
            clave = "baloncesto";
            for (int i = 0; i < 10; i++) {
                this.conexion.add(DriverManager.getConnection(servidor, usuario, clave));
                //this.conexion.add(DriverManager.getConnection(servidor, usuario, clave));
            }

        } catch (ClassNotFoundException e) {
            System.err.print("No se han podido cargar los controladores");
        } catch (SQLException e) {
            System.err.print("No se ha podido acceder a la base de datos");
        }
    }

    public boolean reconec() {
        try {
            Class.forName(driver);
            for (int i = 0; i < 10; i++) {
                if (conexion.get(i).isClosed()) {
                    conexion.set(i, DriverManager.getConnection(servidor, usuario, clave));
                }

            }
        } catch (ClassNotFoundException e) {
            System.err.print("No se han podido cargar los controladores");
            return false;
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            return false;
        }

        return true;
    }

    public BaseDatos(String driv, String serv, String user, String pass) {
        try {
            Class.forName(driv);
            driver = driv;
            servidor = serv;
            usuario = user;
            clave = pass;
            for (int i = 0; i < 10; i++) {
                this.conexion.add(DriverManager.getConnection(servidor, usuario, clave));
            }

            //Busca el controlador y abre conexion
            //("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/mydb", "root", "baloncesto");
            //DriverManager.getConnection(servidor, usuario, clave);

        } catch (ClassNotFoundException e) {
            System.err.print("No se han podido cargar los controladores");
        } catch (SQLException e) {
            System.err.print("No se ha podido acceder a la base de datos");
        }
    }

  
    public int comprobar() {
        int i = 0;
        try {
            while (conexion.get(i).isClosed()) {
                i++;
                if (i > 10) {
                    reconec();
                    i = 0;
                }
            }
            if (i != 0) {
                reconec();
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }

        return i;
    }

    public ResultSet ejecutaConsulta(String consulta) {

        try {
            int i = comprobar();
            System.out.println(i);
            stmt = conexion.get(i).createStatement();
            retset = stmt.executeQuery(consulta);
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }


        return retset;
    }

    public void ejecutaActualizacion(String actualizacion) {
        try {
            int i = 0;
            while (!conexion.get(i).isValid(i)) {
                i++;
            }
            stmt = conexion.get(i).createStatement();
            stmt.executeUpdate(actualizacion);
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
