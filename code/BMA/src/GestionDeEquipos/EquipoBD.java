/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeEquipos;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class EquipoBD {
    
    static ResultSet BuscarEquipos(BaseDatos accesoBD, String nombre, String sexo, String temporada, String categoria, String entrenador, String entrenador2){
        
        ResultSet listaEquipos;
        
        String consulta;
        
        
        //Arreglar consulta
        consulta = "SELECT nombre, Temporada.curso, categoria, entrenador FROM Equipo WHERE Equipo.nombre='"+nombre+"'";
        listaEquipos = accesoBD.ejecutaConsulta(consulta);
        
        return listaEquipos;
    }
    
    static int getIdEq(BaseDatos accesoBD, String nombre) throws SQLException{
        
        int id=0;
        String consulta;
        ResultSet res;
        
        consulta = "SELECT idEquipo FROM Equipo WHERE Equipo.nombre='"+nombre+"'";
        
        res = accesoBD.ejecutaConsulta(consulta);
        
        if(res.next()){
            id = res.getInt(1);
        }
        return id;
    }
        
    static boolean EliminarEquipoBD(BaseDatos accesoBD, Equipo e) throws SQLException{
        
        boolean equipoEliminado = true;
        String consulta;
        
        //Arreglar consulta
        consulta = "";
        
        int idEquipo = getIdEq(accesoBD, e.getNombre());
        
        String delete1 = "delete from alumnoequipo where alumnoequipo.equipo_idequipo= " + idEquipo;
        String delete2 = "delete from temporadaequipo where temporadaequipo.equipo_idequipo= " + idEquipo;
        String delete3 = "delete from rango where rango.equipo_idequipo= " + idEquipo;
                
        try {
            accesoBD.ejecutaActualizacion(delete1);
            accesoBD.ejecutaActualizacion(delete2);
            accesoBD.ejecutaActualizacion(delete3);
        } catch (SQLException ex) {
            equipoEliminado = false;
        }
        return equipoEliminado;
    }
    
    static boolean ConsultarEquipo(BaseDatos accesoBD, String nombre, String sexo,
            String temporada, String categoria, String entrenador, String entrenador2) throws SQLException{
        
        boolean validar = false;
        String consulta;
        
        consulta = "";
        
        ResultSet res = accesoBD.ejecutaConsulta(consulta);
        
        if(res.next())
            validar = true;
        else
            validar = false;
        
        return validar; 
    }
}
