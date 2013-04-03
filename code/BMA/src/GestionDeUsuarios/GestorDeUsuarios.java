/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeUsuarios;
import bma.BaseDatos;
import java.util.Calendar;
import java.sql.*;
/**
 *
 * @author Francisco
 */
public class GestorDeUsuarios {
    public static Usuario crearUsuario(String nombre, String primerApellido, String segundoApellido, String dni,
            String clave, boolean entrenador, int telMovil, int telFijo, String email, String numeroCuenta){
        
        Usuario usuarioNuevo=new Usuario(nombre, primerApellido, segundoApellido, dni, clave, entrenador, telMovil, telFijo,
             email,  numeroCuenta);
        return usuarioNuevo;
    }

    public static void insertarUsuario(BaseDatos accesoBD, Usuario usuarioNuevo){
        UsuarioBD usuario=new UsuarioBD();
        
        usuario.insertarUsuarioBD( accesoBD, usuarioNuevo);
    }
    public static ResultSet consultarUsuario(BaseDatos accesoBD, String consulta){
        UsuarioBD usuario=new UsuarioBD();
        
        return usuario.consultaUsuarioBD(accesoBD, consulta);
    }

    public static void actualizaUsuario(BaseDatos accesoBD, String actualizacion){
        UsuarioBD Usuario=new UsuarioBD();
        
        Usuario.actualizaUsuarioBD(accesoBD, actualizacion);
    }    
}
