/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionActividades;

import GestionActividades.AccesoBDActividad;
import ServiciosAlmacenamiento.BaseDatos;
import java.sql.*;
import java.util.Date;
/**
 *
 * @author Jesus Manuel Contreras Siles
 */
public class GestorActividad {
    
    public static boolean darAltaActividad (BaseDatos accesoBD,String descripcion, int nAlumnos, float precioSocio, float precioNoSocio, int idTemporada, Date fechaInicio, Date fechaFin, String nombre){
       
        boolean exito = true;
        Actividad actividad = Actividad.creaActividad(descripcion, nAlumnos, precioSocio, precioNoSocio, idTemporada, fechaInicio, fechaFin, nombre);
        
         try {
            AccesoBDActividad.insertarActividadBD(accesoBD, actividad);
        } catch (SQLException ex) {
            exito = false;
        }


        return exito;
    }
    
    public static boolean InsertarAlumnoActividad (BaseDatos accesoBD, int idAlumno, int idTemporada, int idActividad){
        boolean exito = true;
        
        AccesoBDActividad.InsertarAlumnoActividadBD(accesoBD, idAlumno, idTemporada, idActividad);
        
        return exito;
    }
    
    public static ResultSet consultaActividad(BaseDatos accesoBD, String consulta){
        AccesoBDActividad actividad = new AccesoBDActividad();

        return actividad.consultaActividadBD(accesoBD, consulta);
    }
    
    public static boolean modificaActividad(BaseDatos accesoBD, int idActividad, String descripcion, String nAlumnos,
            String precioSocio, String precioNoSocio, String idTemporada, String fechaInicio, 
            String fechaFin, String nombre){
        
        return AccesoBDActividad.modificarDatosActividadBD(accesoBD, idActividad, descripcion, nAlumnos, precioSocio, 
                precioNoSocio, idTemporada, fechaInicio, fechaFin, nombre);
    }
    
    public static void eliminaActividad(BaseDatos accesoBD, String descripcion, int nAlumnos, float precioSocio, float precioNoSocio, int idTemporada, 
            Date fechaInicio, Date fechaFin, String nombre){
        
        AccesoBDActividad actividadBD = new AccesoBDActividad();
        
        Actividad actividadEliminada = Actividad.creaActividad(descripcion, nAlumnos, precioSocio, precioNoSocio, idTemporada, fechaInicio, fechaFin, nombre);
        
        actividadBD.eliminarActividadBD(accesoBD, actividadEliminada);
        
    }
    
}
