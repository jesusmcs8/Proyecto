/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDePagos;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author David
 */
public class CuotaPrecio {

    private Date fechaPago;
    private int idCuotaPrecio;
    private float importe;
    private boolean pagado;

    public CuotaPrecio(String fecha, String importe, String pagado) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaPago = (Date) formato.parse(fecha);
        this.importe = Float.parseFloat(importe);
        this.pagado = Boolean.parseBoolean(pagado);
    }
    
    public int getIdCuotaPrecio(){
        return this.idCuotaPrecio;
    }
}
