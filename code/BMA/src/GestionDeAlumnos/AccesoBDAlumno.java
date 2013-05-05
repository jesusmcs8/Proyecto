/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeAlumnos;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco
 * @author Carlos
 * @author Alexander Moreno Borrego
 */
class AccesoBDAlumno {

    public static void insertarAlumnoBD(BaseDatos accesoBD, Alumno alumnoNuevo) throws SQLException {
        String dateString = String.format("%1$tY-%1$tm-%1$td", alumnoNuevo.getFechaNacimiento());

        String inserccion = "INSERT INTO alumno (nombre, primerapellido, segundoapellido, fechanacimiento, colegio, email, localidad, provincia, codigoPostal, domicilio, "
                + "nombrePadre, nombreMadre, numeroCuenta, talla, telFijo, telMovil) VALUES ('";
        inserccion = inserccion + alumnoNuevo.getNombre() + "', '" + alumnoNuevo.getPrimerApellido() + "', '" + alumnoNuevo.getSegundoApellido() + "','"
                + dateString + "', '" + alumnoNuevo.getColegio() + "', '" + alumnoNuevo.getEmail() + "', '" + alumnoNuevo.getLocalidad() + "', '" + alumnoNuevo.getProvincia() + "', "
                + alumnoNuevo.getCodPostal() + ", '" + alumnoNuevo.getDomicilio() + "', '" + alumnoNuevo.getNombrePadre() + "', '" + alumnoNuevo.getNombreMadre()
                + "', '" + alumnoNuevo.getCuentaCorriente() + "', '" + alumnoNuevo.getTallaAlumno() + "', " + alumnoNuevo.getTelFijo() + ", " + alumnoNuevo.getTelMovil() + ")";

        System.out.print("\n inser " + inserccion);
        accesoBD.ejecutaActualizacion(inserccion);
    }

    static List<String> getListaAlumnos(BaseDatos accesoBD, String s) throws SQLException {
        List<String> als = new ArrayList<String>();
        String query = "";
        if(!"".equals(s)){
            
            query = "SELECT nombre, primerApellido, segundoApellido FROM Alumno "
                +"WHERE primerApellido LIKE '%"+s+"%'";
        }
        else{
            
            query = "SELECT nombre, primerApellido, segundoApellido FROM Alumno";
        }
        ResultSet res = accesoBD.ejecutaConsulta(query);
        
        while(res.next())
            als.add(res.getString(2)+" "+res.getString(3)+" "+res.getString(1));
        
        return als;
    }

    static List<Integer> getIdAl(BaseDatos accesoBD, List<String> listaAlumnos) throws SQLException {
        List<Integer> listaIDAl = new ArrayList<Integer>();
        
        String aux, apellido1="", apellido2="";
        
        for(String s : listaAlumnos){
            aux = s;
            
            apellido1 = aux.substring(0, aux.indexOf(" "));
            apellido2 = aux.substring(aux.indexOf(" ")+1, aux.indexOf(" ", aux.indexOf(" ")+1));
            
            String query = "SELECT idAlumno FROM Alumno WHERE primerApellido='"+apellido1+"'"
                + "AND segundoApellido='"+apellido2+"'";
            ResultSet res = accesoBD.ejecutaConsulta(query);
            
            if(res.next())
                listaIDAl.add(res.getInt(1));
        }
       
        return listaIDAl;
    }

    static int getIdAl(BaseDatos accesoBD, String s) throws SQLException {
        int idAl = 0;
        String aux, apellido1="", apellido2="";

        aux = s;
        
        apellido1 = aux.substring(0, aux.indexOf(" "));
        apellido2 = aux.substring(aux.indexOf(" ")+1, aux.indexOf(" ", aux.indexOf(" ")+1));
            
        String query = "SELECT idAlumno FROM Alumno WHERE primerApellido='"+apellido1+"'"
                + " AND segundoApellido='"+apellido2+"'";
        ResultSet res = accesoBD.ejecutaConsulta(query);
          
        System.out.println(","+apellido1+","+apellido2+",");
        
        if(res.next())
            idAl = res.getInt(1);
        //System.out.println();
        System.out.println(idAl);
        return idAl;
    }

    public ResultSet consultaAlumnoBD(BaseDatos accesoBD, String consulta) {
        ResultSet retset;
        retset = accesoBD.ejecutaConsulta(consulta);

        return retset;
    }

    public static boolean modificarDatosAlumno(BaseDatos accesoBD, String idAlumno, String nombre, String primerApellido, String segundoApellido, String fechaNac,
            String cuentaCorriente, String domicilio, String localidad, String codPostal, String provincia, String colegio,
            String nombrePadre, String nombreMadre, String telFijo, String telMovil, String email, String observaciones, String tallaAlumno) {
        boolean exito = true;
        String actualizacion = "UPDATE alumno SET ";
        if (nombre != null) {
            actualizacion = actualizacion + "nombre='" + nombre + "', ";
        }
        if (primerApellido != null) {
            actualizacion = actualizacion + "primerApellido='" + primerApellido + "', ";
        }
        if (segundoApellido != null) {
            actualizacion = actualizacion + "segundoApellido='" + segundoApellido + "', ";
        }
        if (fechaNac != null) {
            actualizacion = actualizacion + "fechaNacimiento='" + segundoApellido + "', ";
        }
        if (cuentaCorriente != null) {
            actualizacion = actualizacion + "numeroCuenta='" + cuentaCorriente + "', ";
        }
        if (domicilio != null) {
            actualizacion = actualizacion + "domicilio='" + domicilio + "', ";
        }
        if (localidad != null) {
            actualizacion = actualizacion + "localidad='" + localidad + "', ";
        }
        if (codPostal != null) {
            actualizacion = actualizacion + "codigoPostal=" + codPostal + ", ";
        }
        if (provincia != null) {
            actualizacion = actualizacion + "provincia='" + provincia + "', ";
        }
        if (colegio != null) {
            actualizacion = actualizacion + "colegio='" + colegio + "', ";
        }
        if (nombrePadre != null) {
            actualizacion = actualizacion + "nombrePadre='" + nombrePadre + "', ";
        }
        if (nombreMadre != null) {
            actualizacion = actualizacion + "nombreMadre='" + nombreMadre + "', ";
        }
        if (telFijo != null) {
            actualizacion = actualizacion + "telFijo=" + telFijo + ", ";
        }
        if (telMovil != null) {
            actualizacion = actualizacion + "telMovil=" + telMovil + ", ";
        }
        if (email != null) {
            actualizacion = actualizacion + "email='" + email + "', ";
        }
        if (observaciones != null) {
            actualizacion = actualizacion + "observaciones='" + observaciones + "', ";
        }
        if (tallaAlumno != null) {
            actualizacion = actualizacion + "talla='" + tallaAlumno + "', ";
        }
        actualizacion = actualizacion.substring(0, actualizacion.length() - 2);
        actualizacion = actualizacion + " WHERE idAlumno=" + idAlumno;
        try {
            accesoBD.ejecutaActualizacion(actualizacion);
            System.out.print("\nModificado act\n " + actualizacion);
        } catch (SQLException ex) {
            exito = false;
        }
        return exito;
    }

    public void eliminaAlumnoBD(BaseDatos accesoBD, Alumno alumnoNuevo) {

        String selId=new String();
        
        selId="SELECT a.idAlumno FROM alumno a WHERE a.nombre='" + alumnoNuevo.getNombre() + "' AND a.primerApellido='"
                + alumnoNuevo.getPrimerApellido() + "' AND a.segundoApellido='" + alumnoNuevo.getSegundoApellido() + "' AND a.nombrePadre='"
                + alumnoNuevo.getNombrePadre() + "' AND a.nombreMadre='" + alumnoNuevo.getNombreMadre()+"' ";
        if(alumnoNuevo.getCuentaCorriente()!=null){
            selId=selId+" AND a.numeroCuenta='"+ alumnoNuevo.getCuentaCorriente()+ "' ";
        }
        if(alumnoNuevo.getTelMovil()!=0){
            selId=selId+" AND a.telMovil="+ alumnoNuevo.getTelMovil()+ " ";
        }
        if(alumnoNuevo.getTelFijo()!=0){
            selId=selId+" AND a.telFijo="+ alumnoNuevo.getTelFijo()+ " ";
        }
        if(alumnoNuevo.getEmail()!=null){
            selId=selId+" AND a.email='"+alumnoNuevo.getEmail()+ "' ";
        }        

        ResultSet retset;
        try {
            retset = accesoBD.ejecutaConsulta(selId);
            if(retset.next()){
                alumnoNuevo.setIdAlumno(retset.getInt("idAlumno"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }

        String delete1 = "delete from alumnoGrupo where alumnoGrupo.alumno_idalumno= " + alumnoNuevo.getIdAlumno();
        String delete2 = "delete from alumnoEquipo where alumnoEquipo.alumno_idalumno= " + alumnoNuevo.getIdAlumno();
        String delete3 = "delete from alumnoTemporada where alumnoTemporada.alumno_idalumno= " + alumnoNuevo.getIdAlumno();
        String delete4 = "delete from pagoActividades where pagoActividades.alumno_idalumno= " + alumnoNuevo.getIdAlumno();
        String delete5 = "DELETE FROM alumno WHERE idAlumno = " + alumnoNuevo.getIdAlumno();

        try {
            accesoBD.ejecutaActualizacion(delete1);
            accesoBD.ejecutaActualizacion(delete2);
            accesoBD.ejecutaActualizacion(delete3);
            accesoBD.ejecutaActualizacion(delete4);
            accesoBD.ejecutaActualizacion(delete5);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBDAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
