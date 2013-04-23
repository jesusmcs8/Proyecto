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
    
    private List<Grupo> grupos;
    private List<Actividad> actividades;
}
