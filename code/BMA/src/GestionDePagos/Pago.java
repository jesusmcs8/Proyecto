/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDePagos;

/**
 *
 * @author David
 */
public class Pago {
    private int idPago;
    private CuotaPrecio importe;
    private PagoActividad actividad;
    private PagoTemporada temporada;
    private String fecha;
    
    Pago(String fecha, float importe, int  pagado){
        this.fecha = fecha;
    }
}
