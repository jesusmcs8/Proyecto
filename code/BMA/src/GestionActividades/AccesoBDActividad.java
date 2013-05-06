/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionActividades;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class AccesoBDActividad {

    public static void insertarActividadBD(BaseDatos accesoBD, Actividad nuevaActividad) throws SQLException {
        String fechaInicioString = String.format("%1$tY-%1$tm-%1$td",nuevaActividad.getFechaInicio());
        String fechaFinString = String.format("%1$tY-%1$tm-%1$td", nuevaActividad.getFechaFin());
        String insercion = "INSERT INTO actividades ("
                + "nAlumnos, descripcion, precioSocio, precioNoSocio, Temporada_idTemporada, fechaInicio"
                + ", fechaFin, nombre) VALUES (";

        insercion = insercion + nuevaActividad.getNAlumnos() + ",\"" + nuevaActividad.getDescripcion() + "\","
                + nuevaActividad.getPrecioSocio() + "," + nuevaActividad.getPrecioNoSocio() + ","
                + nuevaActividad.getIdTemporada() + ",\"" + fechaInicioString + "\",\""
                + fechaFinString + "\",\"" + nuevaActividad.getNombre() + "\");";

        System.out.print("\n inser " + insercion);
        accesoBD.ejecutaActualizacion(insercion);
        
    }

    public ResultSet consultaActividadBD(BaseDatos accesoBD, String consulta) {
        ResultSet retset;
        retset = accesoBD.ejecutaConsulta(consulta);

        return retset;
    }

    public static boolean modificarDatosActividadBD(BaseDatos accesoBD, int idActividad, String descripcion, String nAlumnos, String precioSocio, String precioNoSocio,
            String idTemporada, String fechaInicio, String fechaFin, String nombre) {

        boolean exito = true;

        String actualizacion = "UPDATE Actividad SET ";

        if (descripcion != null) {
            actualizacion = actualizacion + "descripcion = \"" + descripcion + "\" , ";
        }
        if (nAlumnos != null) {
            actualizacion = actualizacion + "nAlumnos = \"" + nAlumnos + "\" , ";
        }
        if (precioSocio != null) {
            actualizacion = actualizacion + "precioSocio = \"" + precioSocio + "\" , ";
        }
        if (precioNoSocio != null) {
            actualizacion = actualizacion + "precioNoSocio = \"" + precioNoSocio + "\" , ";
        }
        if (idTemporada != null) {
            actualizacion = actualizacion + "idTemporada = \"" + idTemporada + "\" , ";
        }
        if (fechaInicio != null) {
            actualizacion = actualizacion + "fechaInicio = \"" + fechaInicio + "\" , ";
        }
        if (fechaFin != null) {
            actualizacion = actualizacion + "fechaFin = \"" + fechaFin + "\" , ";
        }
        if (nombre != null) {
            actualizacion = actualizacion + "nombre = \"" + nombre + "\" , ";
        }

        actualizacion = actualizacion.substring(0, actualizacion.length() - 2);
        actualizacion = actualizacion + " WHERE idActividad= " + idActividad;

        try {
            accesoBD.ejecutaActualizacion(actualizacion);
            System.out.print("\nModificado act\n " + actualizacion);
        } catch (SQLException ex) {
            exito = false;
        }


        return exito;
    }

    public static void eliminarActividadBD(BaseDatos accesoBD, Actividad nuevaActividad) {
        String selId = new String();

        selId = "SELECT a.idActividad FROM Actividad a WHERE a.nAlumnos = "
                + nuevaActividad.getNAlumnos();
        if (nuevaActividad.getDescripcion() != null) {
            selId = selId + " AND a.descripcion = \" " + nuevaActividad.getDescripcion() + "\" ";
        }
        if (nuevaActividad.getPrecioSocio() > 0) {
            selId = selId + "AND a.precioSocio = " + nuevaActividad.getPrecioSocio();
        }
        if (nuevaActividad.getPrecioNoSocio() > 0) {
            selId = selId + " AND a.precioNoSocio = " + nuevaActividad.getPrecioNoSocio();
        }
        if (nuevaActividad.getIdTemporada() != 0) {
            selId = selId + " AND a.idTemporada = " + nuevaActividad.getIdTemporada();
        }
        if (nuevaActividad.getFechaInicio() != null) {
            selId = selId + "AND a.fechaInicio = \"" + nuevaActividad.getFechaInicio() + "\" ";
        }
        if (nuevaActividad.getFechaFin() != null) {
            selId = selId + "AND a.fechaFin = \"" + nuevaActividad.getFechaFin() + "\" ";
        }
        if (nuevaActividad.getNombre() != null) {
            selId = selId + "AND a.nombre = \"" + nuevaActividad.getNombre() + "\" ";
        }

        System.out.println("Consulta eliminar " + selId);
        ResultSet retset;
        try {
            retset = accesoBD.ejecutaConsulta(selId);
            if (retset.next()) {
                nuevaActividad.setIdActividad(retset.getInt("idActividad"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }

        String delete = "DELETE FROM Actividad WHERE idActividad = "
                + nuevaActividad.getIdActividad();

        boolean exito = accesoBD.eliminar(delete);
        if (!exito) {
            JOptionPane.showMessageDialog(null, "Ha habido un error en la base de datos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Actividad Eliminada",
                    "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static boolean InsertarAlumnoActividadBD (BaseDatos accesoBD, int idAlumno, int idTemporada, int idActividad){
        boolean exito = false;
        
        try {
            ResultSet rts, retset;
            exito = true;
            
            String insercion = "INSERT INTO pagoActividades (Alumno_idAlumno, Actividades_idActividades,"
                    + "Actividades_Temporada_idTemporada, Couta_idCuota) SET (";
            String cuota = "SELECT Cuota_idCuota FROM pagotemporada WHERE AlumnoTemporada_Alumno_idAlumno = " + idAlumno + 
                    " AND AlumnoTemporada_Temporada_idTemporada = " + idTemporada;
            
            rts = accesoBD.ejecutaConsulta(cuota);
            int idcuota = rts.getInt("Cuota_idCuota");
            insercion = insercion + idAlumno + ", " + idActividad + ", "+ idcuota + ")";
            
            retset = accesoBD.ejecutaConsulta(insercion);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBDActividad.class.getName()).log(Level.SEVERE, null, ex);
            exito = false;
        }
        return exito;
    }
}
