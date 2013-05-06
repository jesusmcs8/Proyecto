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

    public Instalacion(String inst) {
        this.nombre = inst;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setIdInst(int idInst) {
        this.idInstalacion = idInst;
    }
}
