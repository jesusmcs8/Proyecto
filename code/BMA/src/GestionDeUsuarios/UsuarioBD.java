/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeUsuarios;
import java.sql.*;
import bma.BaseDatos;
/**
 *
 * @author Francisco
 */
public class UsuarioBD {
    
    public void insertarUsuarioBD(BaseDatos accesoBD, Usuario nuevoUsuario) {
        String insercion = "INSERT INTO usuario (nombre, primerApellido,"
                + "segundoApellido, DNI, clave, entrenador, numeroCuenta,"
                + "telMovil, telFijo, email) values ('"
                + nuevoUsuario.getNombre() + "', '" + nuevoUsuario.getPrimerApellido() + "', '" + nuevoUsuario.getSegundoApellido()
                + "','" + nuevoUsuario.getDni() + "', '" + nuevoUsuario.getClave() + "', " + nuevoUsuario.getEntrenador() + ", '"
                + nuevoUsuario.getNumeroCuenta() + "', " + nuevoUsuario.getTelMovil() + ", " + nuevoUsuario.getTelFijo() + ", '"
                + nuevoUsuario.getEmail() + "')";

        System.out.print("\n insert usuario--> " + insercion);
        accesoBD.ejecutaActualizacion(insercion);
    }

    public ResultSet consultaUsuarioBD(BaseDatos accesoBD, String consulta) {
        return accesoBD.ejecutaConsulta(consulta);
    }
    public void actualizaUsuarioBD(BaseDatos accesoBD, String actualizacion) {
        accesoBD.ejecutaActualizacion(actualizacion);
    }

}
