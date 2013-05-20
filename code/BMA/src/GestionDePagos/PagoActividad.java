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

    /**
     * @return the idPagoActividad
     */
    public int getIdPagoActividad() {
        return idPagoActividad;
    }

    /**
     * @return the recibo
     */
    public String getRecibo() {
        return recibo;
    }

    /**
     * @return the actividad
     */
    public Actividad getActividad() {
        return actividad;
    }

    /**
     * @return the cuota
     */
    public CuotaPrecio getCuota() {
        return cuota;
    }

    /**
     * @return the temporada
     */
    public Temporada getTemporada() {
        return temporada;
    }

    public Object getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getCategoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getEntrenador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getEntrenador2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
