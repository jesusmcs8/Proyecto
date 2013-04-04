package GestionDeAlumnos;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.*;
/**
 * Clase que representa al alumno y sus datos personales, además de los nombres
 * del padre y la madre.
 *
 * @author Alex Moreno
 * @version 1.0
 */
public class GestorDeAlumnos {

    public static boolean darAltaAlumno(BaseDatos accesoBD, String nombre, String primerApellido, String segundoApellido, java.util.Date fechaNac,
                    String cuentaCorriente, String domicilio, String localidad, int codPostal, String provincia, String colegio,
                    String nombrePadre, String nombreMadre, int telFijo, int telMovil, String email, String observaciones, String tallaAlumno){
        Alumno alumno = Alumno.crearAlumno(nombre, primerApellido, segundoApellido, fechaNac,
                cuentaCorriente, domicilio, localidad, codPostal, provincia, colegio,
                nombrePadre, nombreMadre, telFijo, telMovil, email, observaciones, tallaAlumno);
        boolean exito = true;
        try {
            AlumnoBD.insertarAlumnoBD(accesoBD, alumno);
        } catch (SQLException ex) {
            exito = false;
        }
        return exito;
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
