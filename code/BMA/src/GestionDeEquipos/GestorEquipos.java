/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeEquipos;

import InterfazUsuario.NuevoEquipo;
import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class GestorEquipos {

    public List<Equipo> ConsultaEquipo(BaseDatos accesoBD, String nombre, String sexo,
            String temporada, String categoria, String entrenador, String entrenador2) throws SQLException {

        List<Equipo> listaEquipos = new ArrayList();
        ResultSet equipos;
        Equipo eq;

        String n;
        String sex;
        String temp;
        String cat;
        String entrena;
        String entrena2;

        equipos = EquipoBD.BuscarEquipos(accesoBD, nombre, sexo, temporada, categoria, entrenador, entrenador2);

        while (equipos.next()) {
            n = equipos.getString("nombre");
            sex = equipos.getString("sexo");
            temp = equipos.getString("curso");
            cat = equipos.getString(categoria);
            entrena = equipos.getString(entrenador);
            entrena2 = equipos.getString(entrenador2);

            eq = new Equipo(n, sex, temp, cat, entrena, entrena2);

            listaEquipos.add(eq);
        }

        return listaEquipos;
    }

    public boolean EliminarEquipo(BaseDatos accesoBD, Equipo e) throws SQLException {

        boolean equipoEliminado;

        equipoEliminado = EquipoBD.EliminarEquipoBD(accesoBD, e);
        
        if(equipoEliminado==true)
            JOptionPane.showMessageDialog(new NuevoEquipo(), "Equipo eliminado", "Equipo", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(new NuevoEquipo(), "No se puede eliminar el equipo", "Error", JOptionPane.ERROR_MESSAGE);
            

        return equipoEliminado;
    }
    
    public void InsertarDatosEquipo(BaseDatos accesoBD, String nombre, String sexo,
            String temporada, String categoria, String entrenador, String entrenador2) throws SQLException{
                
        boolean validar = EquipoBD.ConsultarEquipo(accesoBD, nombre, sexo, temporada, categoria, entrenador, entrenador2);
        
        if(validar==true)
            JOptionPane.showMessageDialog(new NuevoEquipo(), "El equipo ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        else{
            
        }
        
    }
}
