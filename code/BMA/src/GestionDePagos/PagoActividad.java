/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDePagos;

import GestionActividades.Actividad;
import GestionDeTemporadas.Temporada;
import java.text.ParseException;

/**
 *
 * @author David
 */
public class PagoActividad {
    
    private int idPagoActividad;
    private String recibo;
    
    private Actividad actividad;
    private CuotaPrecio cuota;
    private Temporada temporada;
    
    public PagoActividad(String actividad, String temporada, String fecha, String importe, String pagado) throws ParseException{
        this.actividad = new Actividad(actividad);
        this.temporada = new Temporada(temporada);
        cuota = new CuotaPrecio(fecha, importe, pagado);
    }
}
