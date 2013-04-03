/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeAlumnos;
import java.sql.*;
import bma.BaseDatos;
/**
 *
 * @author Francisco
 */
public class AlumnoBD {
        
    public void insertarAlumnoBD(BaseDatos accesoBD, String nombre, String primerApellido, String segundoApellido, java.util.Date fechaNacimiento, String colegio, 
            String email, String localidad, String provincia, int codPostal, String domicilio, String nombrePadre, String nombreMadre, 
            String cuentaCorriente, String tallaAlumno, int telFijo, int telMovil){
        String inserccion=new String();
        String dateString=new String();
        dateString = String.format("%1$tY-%1$tm-%1$td", fechaNacimiento);
        
        inserccion="INSERT INTO alumno (nombre, primerapellido, segundoapellido, fechanacimiento, colegio, email, localidad, provincia, codigoPostal, domicilio, "
                + "nombrePadre, nombreMadre, numeroCuenta, talla, telFijo, telMovil) VALUES ('";
        inserccion=inserccion+nombre+"', '"+primerApellido+"', '"+segundoApellido+"','"+
                dateString+"', '"+colegio+"', '"+email+"', '"+localidad+"', '"+provincia+"', "+
                codPostal+", '"+domicilio+"', '"+nombrePadre+"', '"+nombreMadre+
                "', '"+cuentaCorriente+"', '"+tallaAlumno+"', "+telFijo+", "+telMovil+")";

        System.out.print("\n inser "+inserccion);
        accesoBD.ejecutaActualizacion(inserccion);
    }
    public void insertarAlumnoBD(BaseDatos accesoBD, Alumno alumnoNuevo){
        String inserccion=new String();
        String dateString=new String();
        dateString = String.format("%1$tY-%1$tm-%1$td", alumnoNuevo.getFechaNacimiento());
        
        inserccion="INSERT INTO alumno (nombre, primerapellido, segundoapellido, fechanacimiento, colegio, email, localidad, provincia, codigoPostal, domicilio, "
                + "nombrePadre, nombreMadre, numeroCuenta, talla, telFijo, telMovil) VALUES ('";
        inserccion=inserccion+alumnoNuevo.getNombre()+"', '"+alumnoNuevo.getPrimerApellido()+"', '"+alumnoNuevo.getSegundoApellido()+"','"+
                dateString+"', '"+alumnoNuevo.getColegio()+"', '"+alumnoNuevo.getEmail()+"', '"+alumnoNuevo.getLocalidad()+"', '"+alumnoNuevo.getProvincia()+"', "+
                alumnoNuevo.getCodPostal()+", '"+alumnoNuevo.getDomicilio()+"', '"+alumnoNuevo.getNombrePadre()+"', '"+alumnoNuevo.getNombreMadre()+
                "', '"+alumnoNuevo.getCuentaCorriente()+"', '"+alumnoNuevo.getTallaAlumno()+"', "+alumnoNuevo.getTelFijo()+", "+alumnoNuevo.getTelMovil()+")";

        System.out.print("\n inser "+inserccion);
        accesoBD.ejecutaActualizacion(inserccion);
    }
    public ResultSet consultaAlumno(BaseDatos accesoBD, String consulta){
        ResultSet retset;
        retset=accesoBD.ejecutaConsulta(consulta);
        
        return retset;
    }
    public void actualizaAlumno(BaseDatos accesoBD, String actualizacion){
        accesoBD.ejecutaActualizacion(actualizacion);
    }
}
