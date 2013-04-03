/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeAlumnos;

/**
 *
 * @author Francisco
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
    
    Alumno(String nombre, String primerApellido, String segundoApellido, java.util.Date fechaNac,
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
}
