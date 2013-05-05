/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDePagos;

import java.text.ParseException;

/**
 *
 * @author David
 */
public class PagoActividad {
    
    private int idPagoActividad;
    private String recibo;
    
    private CuotaPrecio cuota;
    
    public PagoActividad(String recibo, String fecha, String importe, String pagado) throws ParseException{
        this.recibo = recibo;
        cuota = new CuotaPrecio(fecha, importe, pagado);
    }
}
