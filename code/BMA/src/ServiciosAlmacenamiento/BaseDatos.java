/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiciosAlmacenamiento;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Francisco
 * @author Carlos
 */
public class BaseDatos {

    private ArrayList<Connection> conexion = new ArrayList();
    private Statement stmt;
    private ResultSet retset;
    private String driver;
    private String servidor;
    private String usuario;
    private String clave;

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

    private boolean reconec() {
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

    private int comprobar() {
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
            //System.out.println(i);
            stmt = conexion.get(i).createStatement();
            retset = stmt.executeQuery(consulta);
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }

        return retset;
    }

    public int ejecutaActualizacion(String actualizacion) throws SQLException {
        int i = comprobar();
        stmt = conexion.get(i).createStatement();
        int res = stmt.executeUpdate(actualizacion);
        return res;
    }

    public boolean eliminar(String delete) {
        try {
            int i = comprobar();
            stmt = conexion.get(i).createStatement();
            int del = stmt.executeUpdate(delete);
            if (del >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            return false;
        }

    }

    

    
}
