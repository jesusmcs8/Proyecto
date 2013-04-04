/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeUsuarios;
import ServiciosAlmacenamiento.BaseDatos;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Francisco
 */
public class GestorDeUsuarios {
    public static boolean darAltaUsuario(BaseDatos accesoBD, String nombre, String primerApellido, String segundoApellido, String dni,
            String clave, boolean entrenador, int telMovil, int telFijo, String email, String numeroCuenta){
        boolean exito = true;
        try {
            if (UsuarioBD.consultaUsuarioDNI(accesoBD, dni)) {
                exito = false;
            } else {
                Usuario usuario = Usuario.crearUsuario(nombre, primerApellido, segundoApellido,
                        dni, clave, entrenador, telMovil, telFijo, email, numeroCuenta);
                UsuarioBD.insertarUsuarioBD(accesoBD, usuario);
            }
        } catch (SQLException ex) {
            exito = false;
        }
        return exito;
    }

    public static ResultSet consultarUsuario(BaseDatos accesoBD, String consulta){
        return UsuarioBD.consultaUsuarioBD(accesoBD, consulta);
    }
    
    public static boolean tengoPermisosAdministrador(BaseDatos accesoBD, String usuario) {
        boolean tengoPermisos = true;
        String consulta = "SELECT entrenador FROM Usuario WHERE user='" + usuario + "'";
        ResultSet rs = UsuarioBD.consultaUsuarioBD(accesoBD, consulta);
        try {
            rs.next();
            if (rs.getBoolean(1)) {
                tengoPermisos = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorDeUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tengoPermisos;
    }

    public static void actualizaUsuario(BaseDatos accesoBD, String actualizacion){
        UsuarioBD Usuario=new UsuarioBD();
        try {
            Usuario.actualizaUsuarioBD(accesoBD, actualizacion);
        } catch (SQLException ex) {
            Logger.getLogger(GestorDeUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
