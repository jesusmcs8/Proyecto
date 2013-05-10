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
    
    public List<PagoActividad> ConsultarPagoActividad(BaseDatos accesoBD, String fecha, String importe, String pagado) throws SQLException, ParseException{
        List<PagoActividad> pagos = new ArrayList();
        
        pagos = PagoBD.consultaPagoActividadBD(accesoBD, fecha, importe, pagado);
        
        return pagos;
    }
    
    public List<PagoTemporada> ConsultarPagoTemporada(BaseDatos accesoBD, String fecha, String importe, String pagado) throws SQLException, ParseException{
        List<PagoTemporada> pagos = new ArrayList();
        
        pagos = PagoBD.consultaPagoTemporadaBD(accesoBD, fecha, importe, pagado);
        
        return pagos;
    }
    
    public boolean EliminarPagoActividad(BaseDatos accesoBD, PagoActividad pago){
        boolean valido = false;
        
        valido = PagoBD.eliminarPagoActividadBD(accesoBD, pago);
        
        return valido;
    }
    
    public boolean EliminarPagoTemporada(BaseDatos accesoBD, PagoTemporada pago){
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
