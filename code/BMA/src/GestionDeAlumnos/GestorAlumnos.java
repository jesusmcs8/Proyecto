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
public class GestorAlumnos {

    public static boolean darAltaAlumno(BaseDatos accesoBD, String nombre, String primerApellido, String segundoApellido, java.util.Date fechaNac,
                    String cuentaCorriente, String domicilio, String localidad, int codPostal, String provincia, String colegio,
                    String nombrePadre, String nombreMadre, int telFijo, int telMovil, String email, String observaciones, String tallaAlumno){
        Alumno alumno = Alumno.crearAlumno(nombre, primerApellido, segundoApellido, fechaNac,
                cuentaCorriente, domicilio, localidad, codPostal, provincia, colegio,
                nombrePadre, nombreMadre, telFijo, telMovil, email, observaciones, tallaAlumno);
        boolean exito = true;
        try {
            AccesoBDAlumno.insertarAlumnoBD(accesoBD, alumno);
        } catch (SQLException ex) {
            exito = false;
        }
        return exito;
    }
    public static ResultSet consultarAlumno(BaseDatos accesoBD, String consulta){
        AccesoBDAlumno alumno=new AccesoBDAlumno();
        
        return alumno.consultaAlumnoBD(accesoBD, consulta);
    }
    
    public static boolean modificarDatos(BaseDatos accesoBD, String idAlumno, String nombre, String primerApellido, String segundoApellido, String fechaNac,
            String cuentaCorriente, String domicilio, String localidad, String codPostal, String provincia, String colegio,
            String nombrePadre, String nombreMadre, String telFijo, String telMovil, String email, String observaciones, String tallaAlumno) {
        return AccesoBDAlumno.modificarDatosAlumno(accesoBD, idAlumno, nombre, primerApellido, segundoApellido, fechaNac,
            cuentaCorriente, domicilio, localidad, codPostal, provincia, colegio,
            nombrePadre, nombreMadre, telFijo, telMovil, email, observaciones, tallaAlumno);
    }
    public static void eliminaAlumno(BaseDatos accesoBD, String nombre, String primerApellido, String segundoApellido, java.util.Date fechaNac,
            String cuentaCorriente, String domicilio, String localidad, int codPostal, String provincia, String colegio,
            String nombrePadre, String nombreMadre, int telFijo, int telMovil, String email, String observaciones, String tallaAlumno){
        
        AccesoBDAlumno alumnoBD=new AccesoBDAlumno();
        Alumno alumnoEliminado = Alumno.crearAlumno(nombre, primerApellido, segundoApellido, fechaNac,
                cuentaCorriente, domicilio, localidad, codPostal, provincia, colegio,
                nombrePadre, nombreMadre, telFijo, telMovil, email, observaciones, tallaAlumno);
        alumnoBD.eliminaAlumnoBD(accesoBD, alumnoEliminado);
    }
}
