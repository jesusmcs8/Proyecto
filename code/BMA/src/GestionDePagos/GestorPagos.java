/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDePagos;

import GestionDeInstalaciones.Instalacion;
import ServiciosAlmacenamiento.BaseDatos;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class GestorPagos {
    
    public static List<PagoActividad> ConsultarPagoActividad(BaseDatos accesoBD, String alumno, String actividad, String temporada, String fecha, String pagado) throws SQLException, ParseException{
        List<PagoActividad> pagos = new ArrayList();
        
        pagos = PagoBD.consultaPagoActividadBD(accesoBD, alumno, actividad, temporada, fecha, pagado);
        
        return pagos;
    }
    
    public static List<PagoTemporada> ConsultarPagoTemporada(BaseDatos accesoBD, String alumno, String fecha, String temporada, String pagado) throws SQLException, ParseException{
        List<PagoTemporada> pagos = new ArrayList();
        
        pagos = PagoBD.consultaPagoTemporadaBD(accesoBD, alumno, fecha, temporada, pagado);
        
        return pagos;
    }
    
    public static boolean EliminarPagoActividad(BaseDatos accesoBD, String alumno, PagoActividad pago) throws SQLException{
        boolean valido = false;
        
        valido = PagoBD.eliminarPagoActividadBD(accesoBD, alumno, pago);
        
        return valido;
    }
    
    public static boolean EliminarPagoTemporada(BaseDatos accesoBD, PagoTemporada pago){
        boolean valido = false;
        
        valido = PagoBD.eliminarPagoTemporadaBD(accesoBD, pago);
        
        return valido;
    }
    
    public static boolean InsertarPagoActividad(BaseDatos accesoBD, String alumno, String actividad, String temporada, String fecha,
                                             String importe, String pagado) throws SQLException{
        boolean valido;
        
        valido = PagoBD.IntroducirPagoActividadBD(accesoBD, alumno, actividad, temporada, fecha, importe, pagado);
       
        return valido;
    }
    
    public static boolean InsertarPagoTemporada(BaseDatos accesoBD, String alumno, String temporada, String fecha,
                                             String importe, String pagado) throws SQLException{
        boolean valido;
        
        valido = PagoBD.IntroducirPagoTemporadaBD(accesoBD, alumno, temporada, fecha, importe, pagado);
       
        return valido;
    }
    
    public void ModificarPagoActividad(){
    }
    
    public void ModificarPagoTemporada(){
    }
}
