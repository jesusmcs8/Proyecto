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
    public void eliminaUsuarioBD(BaseDatos accesoBD, Usuario usuarioNuevo) {

        String selId=new String();
        
        selId="SELECT u.idUsuario, u.entrenador FROM usuario u WHERE u.nombre='" + usuarioNuevo.getNombre() + "' AND u.primerApellido='"
                + usuarioNuevo.getPrimerApellido() + "' AND u.segundoApellido='" + usuarioNuevo.getSegundoApellido() + "' AND u.dni='"
                + usuarioNuevo.getDni() + "'";
        if(usuarioNuevo.getNumeroCuenta()!=null){
            selId=selId+" AND u.numeroCuenta='"+ usuarioNuevo.getNumeroCuenta()+ "' ";
        }
        if(usuarioNuevo.getTelMovil()!=0){
            selId=selId+" AND u.telMovil="+ usuarioNuevo.getTelMovil()+ " ";
        }
        if(usuarioNuevo.getTelFijo()!=0){
            selId=selId+" AND u.telFijo="+ usuarioNuevo.getTelFijo()+ " ";
        }
        if(usuarioNuevo.getEmail()!=null){
            selId=selId+" AND u.email='"+usuarioNuevo.getEmail()+ "' ";
        }  
        if(usuarioNuevo.getClave()!=null){
            selId=selId+" AND u.clave='"+ usuarioNuevo.getClave()+ "' ";
        }
        
        ResultSet retset;
        try {            
            retset = accesoBD.ejecutaConsulta(selId);
            if(retset.next()){
                usuarioNuevo.setIdUsuario(retset.getInt("idUsuario"));
                if(retset.getBoolean("entrenador")==false){
                    String consulta= "SELECT * FROM usuario WHERE usuario.entrenador=false";
                    ResultSet retset1 = accesoBD.ejecutaConsulta(consulta);
                    int i=0;
                    while(retset1.next()){
                        i++;
                    }
                    //en el caso de que solo halla un administrador, este no podra ser eliminado
                    if(i==1){
                        return;
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }

        String delete1 = "delete from alumnogrupo where alumnogrupo.grupo_usuario_idusuario= " + usuarioNuevo.getIdUsuario();
        String delete2 = "delete from horario where horario.grupo_usuario_idusuario= " + usuarioNuevo.getIdUsuario();
        String delete3 = "delete from rango where rango.usuario_idusuario= " + usuarioNuevo.getIdUsuario();
        String delete4 = "delete from grupo where grupo.usuario_idusuario= " + usuarioNuevo.getIdUsuario();
        String delete5 = "delete from usuario where usuario.idusuario= " + usuarioNuevo.getIdUsuario();

        try {
            accesoBD.ejecutaActualizacion(delete1);
            accesoBD.ejecutaActualizacion(delete2);
            accesoBD.ejecutaActualizacion(delete3);
            accesoBD.ejecutaActualizacion(delete4);
            accesoBD.ejecutaActualizacion(delete5);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBDUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
