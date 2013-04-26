package GestionDeGrupos;

import GestionDeAlumnos.Alumno;
import GestionDeInstalaciones.Instalacion;
import GestionDeUsuarios.Usuario;
import bma.Categoria;
import GestionDeTemporadas.Temporada;
import ServiciosAlmacenamiento.BaseDatos;
import bma.Horario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class GestorGrupos {
    
    private List<Grupo> grupos;
    
    public void InsertarGrupo(BaseDatos accesoBD, Temporada temp, List<Alumno> als, Categoria cat, Horario hor, Usuario ent, Instalacion inst) throws SQLException{
        boolean validar = GruposBD.ConsultarGrupos(accesoBD, temp, cat, hor, inst);
    }
    
    public List<Grupo> ConsultarGrupo(Temporada t, List<Alumno> a, Categoria c, Usuario ent, Instalacion inst){
        return new ArrayList<Grupo>();
    }
    
    public void ModificarGrupo(Grupo g){
        
    }
    
    public void EliminarGrupo(Grupo g){
        
    }
}
