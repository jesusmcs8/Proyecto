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
    
    public static boolean EliminarPagoActividad(BaseDatos accesoBD, PagoActividad pago){
        boolean valido = false;
        
        valido = PagoBD.eliminarPagoActividadBD(accesoBD, pago);
        
        return valido;
    }
    
    public static boolean EliminarPagoTemporada(BaseDatos accesoBD, PagoTemporada pago){
        boolean valido = false;
        
        valido = PagoBD.eliminarPagoTemporadaBD(accesoBD, pago);
        
        return valido;
    }
    
    public void InsertarPagoActividad(){
    }
    
    public void InsertarPagoTemporada(){
    }
    
    public void ModificarPagoActividad(){
    }
    
    public void ModificarPagoTemporada(){
    }
}
