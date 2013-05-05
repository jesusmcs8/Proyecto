/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDePagos;

import java.sql.Date;

/**
 *
 * @author David
 */
public class CuotaPrecio {
    
    private Date fechaPago;
    private int idCuotaPrecio;
    private float importe;
    private boolean pagado;
    
    CuotaPrecio(String fecha, float importe, boolean pagado){
        //this.fechaPago = new Date();
        this.importe = importe;
        this.pagado = pagado;
    }
    CuotaPrecio(float importe){
        this.importe = importe;
    }
}
