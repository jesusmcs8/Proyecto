package GestionDeGrupos;

import GestionDeAlumnos.Alumno;
import GestionDeInstalaciones.Instalacion;
import GestionDeUsuarios.Usuario;
import bma.Categoria;
import GestionDeTemporadas.Temporada;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class GestorGrupos {
    
    private List<Grupo> grupos;
    
    public void InsertarGrupo(Temporada t, List<Alumno> al, Categoria cat, Usuario ent, Instalacion inst){
        boolean validar = GruposBD.ConsultarGrupos(t, al, cat, ent, inst);
    }
    
    public List<Grupo> ConsultarGrupo(Temporada t, List<Alumno> a, Categoria c, Usuario ent, Instalacion inst){
        return new ArrayList<Grupo>();
    }
    
    public void ModificarGrupo(Grupo g){
        
    }
    
    public void EliminarGrupo(Grupo g){
        
    }
}
