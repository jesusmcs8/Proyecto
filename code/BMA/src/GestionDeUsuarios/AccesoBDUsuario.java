/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeUsuarios;
import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Francisco
 */
class AccesoBDUsuario {
    
    public static void insertarUsuarioBD(BaseDatos accesoBD, Usuario nuevoUsuario) {
        String insercion = "INSERT INTO usuario (nombre, primerApellido,"
                + "segundoApellido, DNI, clave, entrenador, numeroCuenta,"
                + "telMovil, telFijo, email) values ('"
                + nuevoUsuario.getNombre() + "', '" + nuevoUsuario.getPrimerApellido() + "', '" + nuevoUsuario.getSegundoApellido()
                + "','" + nuevoUsuario.getDni() + "', '" + nuevoUsuario.getClave() + "', " + nuevoUsuario.getEntrenador() + ", '"
                + nuevoUsuario.getNumeroCuenta() + "', " + nuevoUsuario.getTelMovil() + ", " + nuevoUsuario.getTelFijo() + ", '"
                + nuevoUsuario.getEmail() + "')";

        System.out.print("\n insert usuario--> " + insercion);
        try {
            accesoBD.ejecutaActualizacion(insercion);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBDUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean consultaUsuarioDNI(BaseDatos accesoBD, String dni) throws SQLException {
        boolean existe = true;
        ResultSet rs = accesoBD.ejecutaConsulta("SELECT dni FROM usuario WHERE dni='" + dni + "'");
        if (!rs.next()) {
            existe = false;
        }
        return existe;
    }
    
    public static ResultSet consultaUsuarioBD(BaseDatos accesoBD, String consulta) {
        return accesoBD.ejecutaConsulta(consulta);
    }

    public void actualizaUsuarioBD(BaseDatos accesoBD, String actualizacion) throws SQLException {
        accesoBD.ejecutaActualizacion(actualizacion);
    }

}
