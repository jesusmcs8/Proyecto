package GestionDeInstalaciones;

import GestionDeGrupos.Grupo;
import java.util.List;

/**
 *
 * @author Diego
 */
public class Instalacion {
    private int capacidadEquipos;
    private int idInstalacion;
    private String localizacion;
    private String nombre;
    
    //private List<Actividad> actividades;
    
    Instalacion(String nombre, int capacidadEquipos,
            String localizacion){
        

        this.nombre = nombre;
        this.capacidadEquipos = capacidadEquipos;
        this.localizacion = localizacion;
    }
    
    public static Instalacion crearInstalacion (String nombre,
    int capacidadEquipos, String localizacion){
        
        return new Instalacion(nombre, capacidadEquipos, localizacion);
    }

    public Instalacion(String inst) {
        this.nombre = inst;
    }

    public void setIdInst(int idInst) {
        this.idInstalacion = idInst;
    }
    
    public int getIdInstalacion(){
        return this.idInstalacion;
    }
    
    public void setIdInstalacion(int id){
        this.idInstalacion = id;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public int getCapacidadEquipos(){
        return this.capacidadEquipos;
    }
    
    public void setCapacidadEquipos(int capacidad){
        this.capacidadEquipos = capacidad;
    }
    
    public String getLocalizacion(){
        return this.localizacion;
    }
    
    public void setLocalizacion(String direccion){
        this.localizacion = direccion;
    }
}
