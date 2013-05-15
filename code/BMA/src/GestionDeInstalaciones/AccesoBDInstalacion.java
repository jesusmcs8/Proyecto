/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeInstalaciones;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus Manuel Contreras Siles
 */
public class AccesoBDInstalacion {

    public static void insertarInstalacionBD(BaseDatos accesoBD, Instalacion nuevaInstalacion) throws SQLException {

        String insercion = "INSERT INTO Instalacion (nombre, "
                + "capacidadEquipos, localizacion) VALUES (\"";

        insercion = insercion + nuevaInstalacion.getNombre() + "\","
                + nuevaInstalacion.getCapacidadEquipos() + ",\""
                + nuevaInstalacion.getLocalizacion() + "\");";

        System.out.print("\n inser " + insercion);
        accesoBD.ejecutaActualizacion(insercion);

    }

    static int getIdInstalacion(BaseDatos accesoBD, String inst) throws SQLException {
        String query = "SELECT idInstalacion FROM Instalacion WHERE "
                + "nombre='" + inst + "'";
        ResultSet res2 = accesoBD.ejecutaConsulta(query);
       
        int idInst = 0;
        if (res2.next()) {
            idInst = res2.getInt(1);
        }
        
        return idInst;
    }

    static List<String> getListaInstalaciones(BaseDatos accesoBD) throws SQLException {
        List<String> inst = new ArrayList<String>();
        String query = "SELECT nombre, localizacion FROM Instalacion";
        ResultSet res = accesoBD.ejecutaConsulta(query);

        while (res.next()) {
            inst.add(res.getString(1) + "," + res.getString(2));
        }

        return inst;
    }

    public ResultSet consultaInstalacionBD(BaseDatos accesoBD, String consulta) {
        ResultSet retset;
        retset = accesoBD.ejecutaConsulta(consulta);

        return retset;
    }

    public static boolean modificarDatosInstalacionBD(BaseDatos accesoBD, int idInstalacion,
            String nombre, String capacidadEquipos, String localizacion) {

        boolean exito = true;
        String actualizacion = "UPDATE Instalacion SET ";

        if (nombre != null) {
            actualizacion = actualizacion + "nombre = \"" + nombre + "\" , ";
        }
        if (capacidadEquipos != null) {
            actualizacion = actualizacion + "capacidadEquipos = " + Integer.parseInt(capacidadEquipos) + " , ";
        }
        if (localizacion != null) {
            actualizacion = actualizacion + "localizacion = \"" + localizacion + "\" , ";
        }

        actualizacion = actualizacion.substring(0, actualizacion.length() - 2);
        actualizacion = actualizacion + " WHERE idInstalacion= " + idInstalacion;

        try {
            accesoBD.ejecutaActualizacion(actualizacion);
            System.out.print("\nModificado act\n " + actualizacion);
        } catch (SQLException ex) {
            exito = false;
        }

        return exito;
    }

    public static void eliminarInstalacionBD(BaseDatos accesoBD, Instalacion nuevaInstalacion) {
        String selId = new String();

        selId = "SELECT i.idInstalacion FROM Instalacion i WHERE i.nombre= \""
                + nuevaInstalacion.getNombre();
        if (nuevaInstalacion.getCapacidadEquipos() > 0) {
            selId = selId + "\" AND i.capacidadEquipos= " + nuevaInstalacion.getCapacidadEquipos()
                    + " ";
        }
        if (nuevaInstalacion.getLocalizacion() != null) {
            selId = selId + " AND i.localizacion= \"" + nuevaInstalacion.getLocalizacion()
                    + "\" ";
        }

        System.out.println("Consulta eliminar " + selId);
        ResultSet retset;
        try {
            retset = accesoBD.ejecutaConsulta(selId);
            if (retset.next()) {
                nuevaInstalacion.setIdInstalacion(retset.getInt("idInstalacion"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }

        String delete = "DELETE FROM Instalacion WHERE idInstalacion = "
                + nuevaInstalacion.getIdInstalacion();
        
            boolean exito = accesoBD.eliminar(delete);
            if (!exito) {
                JOptionPane.showMessageDialog(null, "Ha habido un error en la base de datos",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Instalacion Eliminada",
                        "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            }
        
    }
}
