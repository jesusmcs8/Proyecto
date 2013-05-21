/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeEquipos;

import GestionDeCategorias.GestorCategorias;
import GestionDeTemporadas.GestorTemporadas;
import GestionDeTemporadas.TemporadaBD;
import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class EquipoBD {

    //Filtrar entrenador correctamente
    static ResultSet BuscarEquipos(BaseDatos accesoBD, String nombre, String temporada, String categoria, String entrenador) throws SQLException {

        int idCategoria = GestorCategorias.getIdCategoria(accesoBD, categoria);
    
        String select = "SELECT DISTINCT Equipo.nombre, Categoria.tipo, Temporada.curso";
        if(!"".equals(entrenador))
            select += ", Usuario.nombre ";
        
        String from = "FROM Equipo, Categoria, Temporada, Rango, Usuario ";
        
        String condicion = "WHERE ";

        if (!"".equals(nombre) || !"-Categoria-".equals(categoria) || !"-Temporada-".equals(temporada) || !"".equals(entrenador)) {
            
            if (!"".equals(nombre)) {
                condicion += "Equipo.nombre='" + nombre + "' AND ";
            }       
            if (!"-Categoria-".equals(categoria)) {
                condicion += "Categoria.idCategoria='" + idCategoria + "' AND ";
            }           
            if (!"-Temporada-".equals(temporada)) {
                condicion += "Temporada.idTemporada='" + GestorTemporadas.getIdTemporada(accesoBD, temporada) + "' AND ";
            }            
            if (!"".equals(entrenador)) {
                condicion += "Rango.Usuario_idUsuario='" + getIdUsuario(accesoBD, entrenador, "primero") + "' AND ";
                condicion += "Usuario.idUsuario=Rango.Usuario_idUsuario AND ";   
            }    
            
        }
        condicion = condicion.substring(0, condicion.length()-5);
        select = select + from + condicion;

        System.out.println("\nLa consulta es: " + select);
        ResultSet listaEquipos = accesoBD.ejecutaConsulta(select);
 
        return listaEquipos;
    }
    
    //Correcta
    static int getIdEq(BaseDatos accesoBD, String nombre, String categoria) throws SQLException {

        int idEquipo = 0;
        
        int idCategoria = GestorCategorias.getIdCategoria(accesoBD, categoria);
        
        String consulta = "SELECT idEquipo FROM Equipo WHERE Equipo.nombre='" + nombre + "' AND Categoria_idCategoria='" + idCategoria + "'";

        ResultSet res = accesoBD.ejecutaConsulta(consulta);

        if (res.next()) {
            idEquipo = res.getInt(1);
        }
        return idEquipo;
    }

    //Correcta
    static int getIdUsuario(BaseDatos accesoBD, String nombre, String tipo) throws SQLException {

        int id = 0;

        String consulta = "SELECT idUsuario FROM Usuario, Rango WHERE Usuario.nombre='" + nombre + "'";
        consulta += " AND Rango.tipo='" + tipo + "'";

        ResultSet res = accesoBD.ejecutaConsulta(consulta);

        if (res.next()) {
            id = res.getInt(1);
        }

        return id;
    }
    
    //Filtrar entrenador
    static List<Equipo> getListaEquipos(BaseDatos accesoBD) throws SQLException{
        
        List<Equipo> equipos = new ArrayList();
        
        String consulta = "SELECT Equipo.nombre, Categoria.tipo, Temporada.curso, Usuario.nombre "
                        + "FROM Equipo, Categoria, Temporada, Usuario, Rango "
                        + "WHERE Equipo.Categoria_idCategoria = Categoria.idCategoria AND "
                        + "Equipo.Temporada_idTemporada = Temporada.idTemporada AND "
                        + "Usuario.idUsuario = Rango.Usuario_idUsuario AND "
                        + "Rango.Equipo_idEquipo = Equipo.idEquipo AND "
                        + "Rango.tipo = 'primero'";
        
        ResultSet res = accesoBD.ejecutaConsulta(consulta);
        
        String n;
        String temp;
        String cat;
        String entrena;
        String entrena2="";
        Equipo eq;
        
        while(res.next()){
            n = res.getString(1);
            cat = res.getString(2);
            temp = res.getString(3);
            entrena = res.getString(4);
            eq = new Equipo(n, temp, cat, entrena, entrena2);

            equipos.add(eq);
        }
            
        System.out.println("\nLa consulta es: " + consulta);
        
        return equipos;
    }

    //Probar con cascade
    static boolean EliminarEquipoBD(BaseDatos accesoBD, Equipo e) throws SQLException {

        boolean equipoEliminado = true;

        int idCategoria = GestorCategorias.getIdCategoria(accesoBD, e.getCategoria().getNombreCategoria());
        int idEquipo = getIdEq(accesoBD, e.getNombre(), e.getCategoria().getNombreCategoria());
        
        //String delete1 = "DELETE FROM Rango WHERE Equipo_idEquipo='" + idEquipo + "' AND Equipo_Categoria_idCategoria='"+idCategoria+"'";
        //String delete2 = "DELETE FROM TemporadaEquipo where temporadaequipo.Equipo_idEquipo= " + idEquipo;
        String delete3 = "DELETE FROM Equipo WHERE Equipo.idEquipo= " + idEquipo;

        //equipoEliminado = accesoBD.eliminar(delete1);
        //equipoEliminado = accesoBD.eliminar(delete2);
        //equipoEliminado = accesoBD.eliminar(delete3);

        //equipoEliminado = false;
        //System.out.println("\nDELETE 1: " + delete1);
        //System.out.println("\nDELETE 2: " + delete2);
        System.out.println("\nDELETE 3: " + delete3);
        
        return equipoEliminado;
    }

    //Correcta
    static boolean ConsultarEquipo(BaseDatos accesoBD, String nombre, String temporada,
            String categoria) throws SQLException {

        boolean validar;

        int idCategoria = GestorCategorias.getIdCategoria(accesoBD, categoria);
        
        String consulta = "SELECT Equipo.nombre, Categoria.tipo, Temporada.curso "
                        + "FROM Equipo, Categoria, Temporada "
                        + "WHERE Equipo.nombre='"+nombre+"' "
                        + "AND Categoria.idCategoria='" + idCategoria + "' "
                        + "AND Temporada.idTemporada='" + GestorTemporadas.getIdTemporada(accesoBD, temporada) + "'";
                
        ResultSet res = accesoBD.ejecutaConsulta(consulta);

        System.out.println("\nLa consulta es: " + consulta);
        
        if (res.next()) {
            validar = false;
        } else {
            validar = true;
        }

        return validar;
    }

    //Correcta
    static void crearEquipoBD(BaseDatos accesoBD, Equipo equipo) throws SQLException {
        
        int idTemporada = GestorTemporadas.getIdTemporada(accesoBD, equipo.getTemporada().getCurso());
        int idCategoria = GestorCategorias.getIdCategoria(accesoBD, equipo.getCategoria().getNombreCategoria());        
        int idEntrenador = getIdUsuario(accesoBD, equipo.getEntrenador().getNombre(), "primero");
        int idEntrenador2 = getIdUsuario(accesoBD, equipo.getEntrenador2().getNombre(), "segundo");
        
        //Insertar en Equipo
        String consulta = "INSERT INTO Equipo (Fundacion_idFundacion, Categoria_idCategoria, Temporada_idTemporada, nombre)"
                        + "VALUES (1, '"+idCategoria+"', '"+idTemporada+"', '"+equipo.getNombre()+"')";

        int res1;
        res1 = accesoBD.ejecutaActualizacion(consulta);
        
        int idEquipo = getIdEq(accesoBD, equipo.getNombre(), equipo.getCategoria().getNombreCategoria());
        
        if(!"".equals(equipo.getEntrenador().getNombre())){
        //Insertar el Primer Entrenador en Rango
            String consulta2 = "INSERT INTO Rango (Usuario_idUsuario, Equipo_idEquipo, Equipo_Fundacion_idFundacion, Equipo_Categoria_idCategoria, Equipo_Temporada_idTEmporada, tipo)"
                + "VALUES ('"+idEntrenador+"', '"+ idEquipo +"', 1, '"+idCategoria+"', '"+idTemporada+"', 'primero')";
        
            int res2;
            res2 = accesoBD.ejecutaActualizacion(consulta2);
        }
        
        if(!"".equals(equipo.getEntrenador2().getNombre())){
            //Insertar el Segundo Entrenador en Rango
            String consulta3 = "INSERT INTO Rango (Usuario_idUsuario, Equipo_idEquipo, Equipo_Fundacion_idFundacion, Equipo_Categoria_idCategoria, Equipo_Temporada_idTEmporada, tipo)"
                    + "VALUES ('"+idEntrenador2+"', '"+ idEquipo +"', 1, '"+idCategoria+"', '"+idTemporada+"', 'segundo')";

            int res3;
            res3 = accesoBD.ejecutaActualizacion(consulta3);
        }
        
        String consulta4 = "INSERT INTO TemporadaEquipo (Temporada_idTemporada, Equipo_idEquipo, Equipo_Fundacion_idFundacion, Equipo_Categoria_idCategoria, Equipo_Temporada_idTEmporada)"
                         + "VALUES ('"+idTemporada+"', '"+ idEquipo +"', 1, '"+idCategoria+"', '"+idTemporada+"')";
        
        int res4;
        res4 = accesoBD.ejecutaActualizacion(consulta4);
    }

}
