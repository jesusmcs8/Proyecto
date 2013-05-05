/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDePagos;

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
    
    static List<PagoActividad> consultaPagoActividadBD(BaseDatos accesoBD, String recibo, String fecha, String importe, String pagado) throws SQLException, ParseException{
        List<PagoActividad> pagos = new ArrayList();
        ResultSet res;
        
        String consulta="SELECT recibo, ";
        
        res = accesoBD.ejecutaConsulta(consulta);
        
        String reci;
        String date;
        float cantidad;
        boolean pago;
        
        while(res.next()){
            reci = res.getString(recibo);
            date = res.getString(fecha);
            cantidad = res.getFloat(importe);
            pago = res.getBoolean(pagado);
            
            PagoActividad pa = new PagoActividad(reci, date, Float.toString(cantidad), Boolean.toString(pago));
            
            pagos.add(pa);
        }
        
        return pagos;
    }
    
    static int getIdPagoActividad(BaseDatos accesoBD, String nombre) throws SQLException{
        int id=0;
        ResultSet res;
        String consulta = "";
        
        res = accesoBD.ejecutaConsulta(consulta);
        
        if(res.next())
            id = res.getInt(1);
        
        return id;
        
    }
    
    static List<PagoTemporada> consultaPagoTemporadaBD(BaseDatos accesoBD, String fecha, String importe, String pagado) throws SQLException, ParseException{
        List<PagoTemporada> pagos = new ArrayList();
        ResultSet res;
        
        String consulta="";
        
        res = accesoBD.ejecutaConsulta(consulta);
        
        String date;
        float cantidad;
        boolean pago;
        
        while(res.next()){
            date = res.getString(fecha);
            cantidad = res.getFloat(importe);
            pago = res.getBoolean(pagado);
            
            PagoTemporada pt = new PagoTemporada(date, Float.toString(cantidad), Boolean.toString(pago));
            
            pagos.add(pt);
        }
        
        return pagos;
    }
    
    static int getIdTemporada(BaseDatos accesoBD, String temporada) throws SQLException{
        int id=0;
        ResultSet res;
        String consulta = "SELECT idTemporada FROM Temporada WHERE curso ="+temporada;
        
        res = accesoBD.ejecutaConsulta(consulta);
        
        if(res.next())
            id = res.getInt(1);
        
        return id;
        
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
