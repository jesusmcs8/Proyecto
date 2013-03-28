package bma;

import java.util.Calendar;
import java.sql.*;
/**
 * Clase que representa al alumno y sus datos personales, además de los nombres
 * del padre y la madre.
 *
 * @author Alex Moreno
 * @version 1.0
 */
public class Alumno {

    private int idAlumno;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private java.util.Date fechaNacimiento;
    private String cuentaCorriente;
    private String domicilio;
    private String localidad;
    private int codPostal;
    private String provincia;
    private String colegio;
    private String nombrePadre;
    private String nombreMadre;
    private int telFijo;
    private int telMovil;
    private String email;
    private String observaciones;
    private String tallaAlumno;
    Statement stmt;
    
    public void crearAlumno(String nombre, String primerApellido, String segundoApellido, java.util.Date fechaNac,
                    String cuentaCorriente, String domicilio, String localidad, int codPostal, String provincia, String colegio,
                    String nombrePadre, String nombreMadre, int telFijo, int telMovil, String email, String observaciones, String tallaAlumno){
        this.nombre=nombre;
        this.primerApellido=primerApellido;
        this.segundoApellido=segundoApellido;
        this.fechaNacimiento=fechaNac;
        this.cuentaCorriente=cuentaCorriente;
        this.domicilio=domicilio;
        this.localidad=localidad;
        this.codPostal=codPostal;
        this.provincia=provincia;
        this.colegio=colegio;
        this.nombrePadre=nombrePadre;
        this.nombreMadre=nombreMadre;
        this.email=email;
        this.telFijo=telFijo;
        this.telMovil=telMovil;
        this.tallaAlumno=tallaAlumno;
        
    }
    
    public void insertarAlumnoBD(BaseDatos accesoBD){
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
    public ResultSet consultaAlumno(BaseDatos accesoBD, String consulta){
        ResultSet retset;
        retset=accesoBD.ejecutaConsulta(consulta);
        
        return retset;
    }
    public void actualizaAlumno(BaseDatos accesoBD, String actualizacion){
        accesoBD.ejecutaActualizacion(actualizacion);
    }
}
