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
    
    public PagoActividad(String fecha, String importe, String pagado) throws ParseException{
        cuota = new CuotaPrecio(fecha, importe, pagado);
    }
}
