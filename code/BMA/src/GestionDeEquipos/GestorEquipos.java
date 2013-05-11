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

    public static List<Equipo> ConsultaEquipo(BaseDatos accesoBD, String nombre,
                            String temporada, String categoria, String entrenador, String entrenador2) throws SQLException {

        List<Equipo> listaEquipos = new ArrayList();
        Equipo eq;

        String n;
        String temp;
        String cat;
        String entrena;
        String entrena2;

        ResultSet res = EquipoBD.BuscarEquipos(accesoBD, nombre, temporada, categoria, entrenador, entrenador2);
        
        while (res.next()) {
            n = res.getString(1);
            cat = res.getString(2);
            temp = res.getString(3);
            entrena = res.getString(4);
            entrena2 = res.getString(5);
            eq = new Equipo(n, temp, cat, entrena, entrena2);

            listaEquipos.add(eq);
        }

        return listaEquipos;
    }

    public static List<Equipo> getListaEquipos(BaseDatos accesoBD) throws SQLException{
       
        List<Equipo> equipos = new ArrayList<Equipo>();
        
        equipos = EquipoBD.getListaEquipos(accesoBD);
        
        return equipos;
    }
    
    public static boolean EliminarEquipo(BaseDatos accesoBD, Equipo e) throws SQLException {

        boolean equipoEliminado;

        equipoEliminado = EquipoBD.EliminarEquipoBD(accesoBD, e);
        
/*        if(equipoEliminado==true)
            JOptionPane.showMessageDialog(new NuevoEquipo(), "Equipo eliminado", "Equipo", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(new NuevoEquipo(), "No se puede eliminar el equipo", "Error", JOptionPane.ERROR_MESSAGE);
*/            
        return equipoEliminado;
    }
    
    public static void InsertarDatosEquipo(BaseDatos accesoBD, String nombre,
            String temporada, String categoria, String entrenador, String entrenador2) throws SQLException{
                
        boolean validar = EquipoBD.ConsultarEquipo(accesoBD, nombre, temporada, categoria);
        
        if(validar==false)
            JOptionPane.showMessageDialog(new NuevoEquipo(), "El equipo ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        else{
            Equipo equipo;
            equipo = new Equipo(nombre, temporada, categoria, entrenador, entrenador2);
            
            EquipoBD.crearEquipoBD(accesoBD, equipo);
        }
        
    }
}
