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
                condicion += "PagoActividades.Actividades.idActividades='"+PagoBD.getIdPagoActividad(accesoBD, actividad, temporada)+"' AND ";
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
    
    static int getIdPagoActividad(BaseDatos accesoBD, String nombre, String temporada) throws SQLException{
        int id=0;

        String consulta = "SELECT idActividades FROM Actividades WHERE Actividad.nombre='"+nombre+"' "
                + "AND Temporada_idTemporada='"+GestorTemporadas.getIdTemporada(accesoBD, temporada)+"'";
        
        ResultSet res = accesoBD.ejecutaConsulta(consulta);
        
        if(res.next())
            id = res.getInt(1);
        
        return id;
        
    }
    
    static List<String> getListaAlumnosPagosActividad(BaseDatos accesoBD, int idCuota) throws SQLException {
        
        String query = "SELECT Alumno_idAlumno FROM PagoActividades WHERE "
                + "Grupo_idGrupo='"+idCuota+"'";
        
        ResultSet res = accesoBD.ejecutaConsulta(query);
             
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
            
    static boolean eliminarPagoActividadBD(BaseDatos accesoBD, PagoActividad pago){
        boolean valido = false;
        
        String consulta="";
        
        valido = accesoBD.eliminar(consulta);
        
        return valido;
    }
    
    static boolean eliminarPagoTemporadaBD(BaseDatos accesoBD, PagoTemporada pago){
        boolean valido = false;
        
        String consulta="";
        
        valido = accesoBD.eliminar(consulta);
        
        return valido;
    }
    
    static boolean IntroducirPagoActividadBD(BaseDatos accesoBD, CuotaPrecio cuota){
        boolean valido = false;
        
        return valido;
    }
}
