package GestionDeAlumnos;

import bma.BaseDatos;
import java.util.Calendar;
import java.sql.*;
/**
 * Clase que representa al alumno y sus datos personales, además de los nombres
 * del padre y la madre.
 *
 * @author Alex Moreno
 * @version 1.0
 */
public class GestorDeAlumnos {

    public static Alumno crearAlumno(String nombre, String primerApellido, String segundoApellido, java.util.Date fechaNac,
                    String cuentaCorriente, String domicilio, String localidad, int codPostal, String provincia, String colegio,
                    String nombrePadre, String nombreMadre, int telFijo, int telMovil, String email, String observaciones, String tallaAlumno){
        
        Alumno alumnoNuevo=new Alumno(nombre, primerApellido, segundoApellido, fechaNac, cuentaCorriente,  domicilio, localidad, codPostal, provincia,
                 colegio, nombrePadre, nombreMadre, telFijo, telMovil, email, observaciones, tallaAlumno);
        return alumnoNuevo;
    }

    public static void insertarAlumno(BaseDatos accesoBD, Alumno alumnoNuevo){
        AlumnoBD alumno=new AlumnoBD();
        
        alumno.insertarAlumnoBD(accesoBD, alumnoNuevo.getNombre(), alumnoNuevo.getPrimerApellido(), alumnoNuevo.getSegundoApellido(), alumnoNuevo.getFechaNacimiento(), alumnoNuevo.getColegio(), 
            alumnoNuevo.getEmail(), alumnoNuevo.getLocalidad(), alumnoNuevo.getProvincia(), alumnoNuevo.getCodPostal(), alumnoNuevo.getDomicilio(), 
            alumnoNuevo.getNombrePadre(), alumnoNuevo.getNombreMadre(), alumnoNuevo.getCuentaCorriente(), alumnoNuevo.getTallaAlumno(), 
            alumnoNuevo.getTelFijo(), alumnoNuevo.getTelMovil());
    }
    public static ResultSet consultarAlumno(BaseDatos accesoBD, String consulta){
        AlumnoBD alumno=new AlumnoBD();
        
        return alumno.consultaAlumnoBD(accesoBD, consulta);
    }
    public static void actualizaAlumno(BaseDatos accesoBD, String actualizacion){
        AlumnoBD alumno=new AlumnoBD();
        
        alumno.actualizaAlumnoBD(accesoBD, actualizacion);
    }
}
