/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeEquipos;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David
 */
public class EquipoBD {
    
    static ResultSet BuscarEquipos(BaseDatos accesoBD, String nombre, String sexo, String temporada, String categoria, String entrenador, String entrenador2){
        
        ResultSet listaEquipos;
        String consulta;
        
        consulta = "SELECT idEquipo FROM Equipo WHERE Equipo.nombre='"+nombre+"'";
        listaEquipos = accesoBD.ejecutaConsulta(consulta);
        
        return listaEquipos;
    }
    
    static int getIDEquipo(BaseDatos accesoBD, String nombre) throws SQLException{
        
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
}
