/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeEquipos;

import GestionDeTemporadas.GestorTemporadas;
import GestionDeTemporadas.TemporadaBD;
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

    static ResultSet BuscarEquipos(BaseDatos accesoBD, String nombre, String temporada, String categoria, String entrenador, String entrenador2) throws SQLException {

        int idCategoria = 0;

        ResultSet res = accesoBD.ejecutaConsulta("SELECT idCategoria FROM Categoria WHERE tipo=" + categoria);

        idCategoria = res.getInt(1);
        System.out.println("ID Categoria:"+idCategoria);

        String consulta = "SELECT Equipo.nombre, Categoria.tipo, Usuario.nombre FROM Equipo, Rango WHERE Equipo.nombre='" + nombre + "'";
        String condicion = "WHERE";

        if (nombre != null || categoria != null || temporada != null || entrenador != null || entrenador2 != null) {
            if (nombre != null) {
                condicion += "Equipo.nombre='" + nombre + "' AND";
            }
            if (categoria != null) {
                consulta += "Categoria.idCategoria='" + idCategoria + "' AND";
            }
            if (temporada != "") {
                consulta += "Temporada.idTemporada='" + GestorTemporadas.getIdTemporada(accesoBD, temporada) + "' AND";
            }
            if (entrenador != null) {
                condicion += "Rango.Usuario_idUsuario='" + getIdUsuario(accesoBD, entrenador, "primero") + "' AND";
            }
            if (entrenador2 != null) {
                condicion += "Rango.Usuario_idUsuario='" + getIdUsuario(accesoBD, entrenador2, "segundo") + "' AND";
            }
        }
        //condicion.substring(0, condicion.length()-4);

        consulta += condicion;

        ResultSet listaEquipos = accesoBD.ejecutaConsulta(consulta);

        return listaEquipos;
    }

    static int getIdEq(BaseDatos accesoBD, String nombre) throws SQLException {

        int id = 0;
        String consulta;
        ResultSet res;

        consulta = "SELECT idEquipo FROM Equipo WHERE Equipo.nombre='" + nombre + "'";

        res = accesoBD.ejecutaConsulta(consulta);

        if (res.next()) {
            id = res.getInt(1);
        }
        return id;
    }

    static int getIdUsuario(BaseDatos accesoBD, String nombre, String tipo) throws SQLException {

        int id = 0;

        String consulta = "SELECT idUsuario FROM Usuario, Rango WHERE Usuario.nombre='" + nombre + "'";
        consulta = "AND Rango.tipo=" + tipo;

        ResultSet res = accesoBD.ejecutaConsulta(consulta);

        if (res.next()) {
            id = res.getInt(1);
        }

        return id;
    }

    static boolean EliminarEquipoBD(BaseDatos accesoBD, Equipo e) throws SQLException {

        boolean equipoEliminado = true;

        String consulta = "SELECT idEquipo FROM Equipo WHERE nombre='"+e.getNombre()+"'";
        //consulta += "AND Categoria_idCategoria='"++"'";
        consulta += "AND Temporada_idTemporada='"+GestionDeTemporadas.TemporadaBD.getIdTemporada(accesoBD, e.getTemporada().toString())+"'";

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

    static boolean ConsultarEquipo(BaseDatos accesoBD, String nombre, String temporada,
            String categoria, String entrenador, String entrenador2) throws SQLException {

        boolean validar = false;
        String consulta;

        consulta = "";

        ResultSet res = accesoBD.ejecutaConsulta(consulta);

        if (res.next()) {
            validar = true;
        } else {
            validar = false;
        }

        return validar;
    }

    static void crearEquipoBD(BaseDatos accesoBD, String nombre, String temporada,
            String categoria, String entrenador, String entrenador2) throws SQLException {

        String consulta = "";

        accesoBD.ejecutaActualizacion(consulta);

    }
}
