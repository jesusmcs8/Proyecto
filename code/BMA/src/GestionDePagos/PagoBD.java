/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDePagos;

import GestionDeAlumnos.GestorAlumnos;
import GestionDeTemporadas.GestorTemporadas;
import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class PagoBD {
    
    static List<PagoActividad> consultaPagoActividadBD(BaseDatos accesoBD, String alumno, String actividad, String temporada, String fecha, String pagado) throws SQLException, ParseException{
        List<PagoActividad> pagos = new ArrayList();
        
        String consulta = "SELECT Alumno.nombre, Actividad.nombre, Cuota.fecha, Cuota.importe, Cuota.pagado "
                        + "FROM Cuota, PagoActividades, Alumno, PagoActividad ";
        String condicion = "WHERE ";
        
        if(!"".equals(alumno) || !"".equals(actividad) || !"".equals(temporada) || !"".equals(fecha) || !"".equals(pagado)){
            if(!"".equals(alumno)){
                condicion += "Alumno.idAlumno='"+GestorAlumnos.getIdAl(accesoBD, alumno)+"' AND ";
            }
            if(!"".equals(actividad)){
                condicion += "PagoActividades.Actividades.idActividades='"+PagoBD.getIdActividad(accesoBD, actividad, temporada)+"' AND ";
            }
            if(!"".equals(temporada)){
                condicion += "Actividades_Temporada_idTemporada='"+GestorTemporadas.getIdTemporada(accesoBD, temporada)+"' AND ";
            }
            if(!"".equals(fecha)){
                condicion += "Cuota.fecha='"+fecha+"' AND ";
            }
            if(!"".equals(pagado)){
                condicion += "Cuota.pagado='"+pagado+"' AND ";
            }
        }
        
        condicion = condicion.substring(0, condicion.length()-5);
        consulta += condicion;
        
        ResultSet res = accesoBD.ejecutaConsulta(consulta);
        
        String alum;
        String activ;
        String date;
        float cantidad;
        boolean pago;
        
        while(res.next()){
            alum = res.getString(1);
            activ = res.getString(2);
            date = res.getString(3);
            cantidad = res.getFloat(4);
            pago = res.getBoolean(5);
            
            PagoActividad pa = new PagoActividad(activ, temporada, date, Float.toString(cantidad), Boolean.toString(pago));
            
            pagos.add(pa);
        }
        
        return pagos;
    }
    
    static List<PagoTemporada> consultaPagoTemporadaBD(BaseDatos accesoBD, String alumno, String fecha, String temporada, String pagado) throws SQLException, ParseException{
        List<PagoTemporada> pagos = new ArrayList();
        ResultSet res;
        
        String consulta="";
        
        res = accesoBD.ejecutaConsulta(consulta);
        
        String date;
        float cantidad=0;
        boolean pago;
        
        while(res.next()){
            date = res.getString(fecha);
            pago = res.getBoolean(pagado);
            
            CuotaPrecio cuota = new CuotaPrecio(date, Float.toString(cantidad), Boolean.toString(pago));
            PagoTemporada pt = new PagoTemporada(cuota);
            
            pagos.add(pt);
        }
        
        return pagos;
    }
    
    static int getIdActividad(BaseDatos accesoBD, String actividad, String temporada) throws SQLException{
        int id=0;

        String consulta = "SELECT idActividades FROM Actividades WHERE Actividad.nombre='"+actividad+"' "
                + "AND Temporada_idTemporada='"+GestorTemporadas.getIdTemporada(accesoBD, temporada)+"'";
        
        ResultSet res = accesoBD.ejecutaConsulta(consulta);
        
        if(res.next())
            id = res.getInt(1);
        
        return id;
        
    }
    
    static List<PagoActividad> getListaPagoActividad(){
        List<PagoActividad> pagos = new ArrayList<PagoActividad>();
        
        //String consulta = "SELECT fecha, pagado, importe FROM PagoActividades, Cuota WHERE";
        return pagos;
    }
    
    static List<String> getListaAlumnosPagoActividad(BaseDatos accesoBD, String actividad, String temporada, String fecha, String pagado) throws SQLException {
        
        String consulta = "SELECT Alumno_idAlumno FROM PagoActividades, Cuota ";
        String condicion = "WHERE Actividades_Temporada_idTemporada='"+GestorTemporadas.getIdTemporada(accesoBD, temporada)+"' AND ";
        
        if(!"".equals(actividad) || !"".equals(temporada) || !"".equals(fecha) || !"".equals(pagado)){
            if(!"".equals(actividad)){
                condicion += "Actividades_idActividades='"+getIdActividad(accesoBD, actividad, temporada)+"' AND ";
            }
            if(!"".equals(fecha)){
                condicion += "Cuota.fecha='"+fecha+"' AND ";
            }
        }
        ResultSet res = accesoBD.ejecutaConsulta(consulta);
             
        int idAl  = 0;
        String query2 = "";
        ResultSet res2 = null;
        
        List<String> alumnos = new ArrayList<String>();
        
        while(res.next()){
            idAl = res.getInt(1);
            query2 = "SELECT nombre, primerApellido, segundoApellido FROM Alumno "
                + "WHERE idAlumno='"+idAl+"'";
            res2 = accesoBD.ejecutaConsulta(query2);
            res2.next();
            alumnos.add(res2.getString(2)+" "+res2.getString(3)+" "+res2.getString(1));
        }
        
        return alumnos;
    }
           
    static int getIdCuotaActividad(BaseDatos accesoBD, String alumno, PagoActividad pago) throws SQLException{
        int idCuota = 0;
        int idAlumno = GestorAlumnos.getIdAl(accesoBD, alumno);
        
        String consulta = "SELECT Cuota_idCuota FROM PagoActividad WHERE Alumno_idAlumno='"+idAlumno+"' AND "
                        + "Actividades_idActividades='"+getIdActividad(accesoBD, pago.getActividad().getNombre(), pago.getTemporada().getCurso())+"' AND "
                        + "Actividades_Temporada_idTemporada='"+pago.getTemporada().getCurso()+"'";
        
        ResultSet res = accesoBD.ejecutaConsulta(consulta);
        
        if(res.next())
            idCuota = res.getInt(1);
        
        return idCuota;
    }
    
    static boolean eliminarPagoActividadBD(BaseDatos accesoBD, String alumno, PagoActividad pago) throws SQLException{
        boolean valido = false;
        boolean delete1 = false;
        boolean delete2 = false;
        
        int idAlumno = GestorAlumnos.getIdAl(accesoBD, alumno);
        
        int idActividad = getIdActividad(accesoBD, pago.getActividad().getNombre(), pago.getTemporada().getCurso());
        int idCuota = getIdCuotaActividad(accesoBD, alumno, pago);
        
        String consulta = "DELETE FROM PagoActividad WHERE Actividades_idActividades='"+idActividad+"' AND "
                        + "Actividades_Temporada_idTemporada='"+GestorTemporadas.getIdTemporada(accesoBD, pago.getTemporada().getCurso())+"' AND "
                        + "Alumno_idAlumno='"+idAlumno+"' AND "
                        + "Cuota_idCuota='"+idCuota+"'";
        
        delete1 = accesoBD.eliminar(consulta);
        
        String consulta2 = "DELETE FROM Cuota WHERE idCuota='"+idCuota+"'";
        
        delete2 = accesoBD.eliminar(consulta2);
        
        if(delete1 == false || delete2 == false)
            valido = false;
        else
            valido = true;
        
        return valido;
    }
    
    static boolean eliminarPagoTemporadaBD(BaseDatos accesoBD, PagoTemporada pago){
        boolean valido = false;
        
        String consulta="";
        
        valido = accesoBD.eliminar(consulta);
        
        return valido;
    }
    
    public static boolean IntroducirPagoActividadBD(BaseDatos accesoBD, String alumno, String actividad, String temporada, String fecha,
                                             String importe, String pagado) throws SQLException{
       
        boolean valido=false;
        
        int idAlumno = GestorAlumnos.getIdAl(accesoBD, alumno);
        int idActividad = getIdActividad(accesoBD, actividad, temporada);
        int idTemporada = GestorTemporadas.getIdTemporada(accesoBD, temporada);
        int idCuota = 0; 
                
        String consulta = "INSERT INTO Cuota (importe, fecha, pagado) VALUES ('"+importe+"', '"+fecha+"', '"+pagado+"')";
        
        int res1;
        res1 = accesoBD.ejecutaActualizacion(consulta);
        
        if(res1!=0){
            String consultaCuota = "SELECT MAX(idCuota) FROM Cuota";
            ResultSet res = accesoBD.ejecutaConsulta(consultaCuota);
        
            if(res.next())
                idCuota = res.getInt(1);
            
            String consulta2 = "INSERT INTO PagoActividad (Alumno_idAlumno, Actividades_idActividades, Actividades_Temporada_idTemporada, Cuota_idCuota) "
                         + "VALUES ('"+idAlumno+"', '"+idActividad+"', '"+idTemporada+"', '"+idCuota+"')";
        
            int res2;
            res2 = accesoBD.ejecutaActualizacion(consulta2);
            
            if(res2!=0){
                valido = true;
            }
        }    
        else
            valido = false;

        return valido;
    }
    
    static boolean IntroducirPagoTemporadaBD(BaseDatos accesoBD, String alumno, String temporada, String fecha,
                                             String importe, String pagado) throws SQLException{
       
        boolean valido=false;
        
        int idAlumno = GestorAlumnos.getIdAl(accesoBD, alumno);
        int idTemporada = GestorTemporadas.getIdTemporada(accesoBD, temporada);
        int idGrupo = 0;
        int idCategoria = 0;
        int idCuota = 0; 
                
        String consulta = "INSERT INTO Cuota (importe, fecha, pagado) VALUES ('"+importe+"', '"+fecha+"', '"+pagado+"')";
        
        int res1;
        res1 = accesoBD.ejecutaActualizacion(consulta);
        
        if(res1!=0){
            String consultaCuota = "SELECT MAX(idCuota) FROM Cuota";
            ResultSet res = accesoBD.ejecutaConsulta(consultaCuota);
        
            if(res.next())
                idCuota = res.getInt(1);
            
            String consulta2 = "INSERT INTO PagoTemporada (Cuota_idCuota, AlumnoTemporada_Alumno_idAlumno, AlumnoTemporada_Alumno_Grupo_idGrupo,"
                             + "AlumnoTemporada_Alumno_Grupo_Categoria_idCategoria, AlumnoTemporada_Temporada_idTemporada) "
                             + "VALUES ('"+idCuota+"', '"+idAlumno+"', '"+idGrupo+"', '"+idCategoria+"', '"+idTemporada+"')";
        
            int res2;
            res2 = accesoBD.ejecutaActualizacion(consulta2);
            
            if(res2!=0){
                valido = true;
            }
        }    
        else
            valido = false;

        return valido;
    }
}
