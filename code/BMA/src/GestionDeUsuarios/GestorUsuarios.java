/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeUsuarios;
import ServiciosAlmacenamiento.BaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Francisco
 */
public class GestorUsuarios {
    public static boolean darAltaUsuario(BaseDatos accesoBD, String nombre, String primerApellido, String segundoApellido, String dni,
            String clave, boolean entrenador, int telMovil, int telFijo, String email, String numeroCuenta){
        boolean exito = true;
        try {
            if (AccesoBDUsuario.consultaUsuarioDNI(accesoBD, dni)) {
                exito = false;
            } else {
                Usuario usuario = Usuario.crearUsuario(nombre, primerApellido, segundoApellido,
                        dni, clave, entrenador, telMovil, telFijo, email, numeroCuenta);
                AccesoBDUsuario.insertarUsuarioBD(accesoBD, usuario);
            }
        } catch (SQLException ex) {
            exito = false;
        }
        return exito;
    }

    public static ResultSet consultarUsuario(BaseDatos accesoBD, String consulta){
        return AccesoBDUsuario.consultaUsuarioBD(accesoBD, consulta);
    }
    
    public static boolean tengoPermisosAdministrador(BaseDatos accesoBD, String usuario) {
        boolean tengoPermisos = true;
        String consulta = "SELECT entrenador FROM Usuario WHERE user='" + usuario + "'";
        ResultSet rs = AccesoBDUsuario.consultaUsuarioBD(accesoBD, consulta);
        try {
            rs.next();
            if (rs.getBoolean(1)) {
                tengoPermisos = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tengoPermisos;
    }

    public static void actualizaUsuario(BaseDatos accesoBD, String actualizacion){
        AccesoBDUsuario Usuario=new AccesoBDUsuario();
        try {
            Usuario.actualizaUsuarioBD(accesoBD, actualizacion);
        } catch (SQLException ex) {
            Logger.getLogger(GestorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public static void eliminaUsuario(BaseDatos accesoBD, String nombre, String primerApellido, String segundoApellido, 
            String dni, String clave, boolean entrenador, int telMovil, int telFijo,
            String email, String numeroCuenta ){
        
        AccesoBDUsuario usuarioBD=new AccesoBDUsuario();
        Usuario usuarioEliminado = Usuario.crearUsuario(nombre,
            primerApellido, segundoApellido, dni,
            clave, entrenador, telMovil, telFijo,
            email, numeroCuenta);
        usuarioBD.eliminaUsuarioBD(accesoBD, usuarioEliminado);
    }
    
    public static List<String> getEntrenadores(BaseDatos accesoBD, String s) throws SQLException{
        List<String> res = new ArrayList<String>();        
        res = AccesoBDUsuario.getListaEntrenadores(accesoBD, s);

        return res;
    }

    public static int getIdEnt(BaseDatos accesoBD, String entrenador) throws SQLException {
        int res = AccesoBDUsuario.getIdEnt(accesoBD, entrenador);

        return res;
    }

    public static String getEntrenador(BaseDatos accesoBD, String s) throws SQLException {
        return AccesoBDUsuario.getEntrenador(accesoBD, s);
    }
}
