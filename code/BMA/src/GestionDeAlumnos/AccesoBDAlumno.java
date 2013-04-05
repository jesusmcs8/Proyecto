/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDeAlumnos;

import ServiciosAlmacenamiento.BaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        if (alumnoNuevo.getIdAlumno() == -1) {

            String selId = "SELECT a.idAlumno FROM alumno a WHERE" + "a.nombre=" + alumnoNuevo.getNombre() + ", a.primerApellido="
                    + alumnoNuevo.getPrimerApellido() + ", a.segundoApellido=" + alumnoNuevo.getSegundoApellido() + ", a.nombrePadre="
                    + alumnoNuevo.getNombrePadre() + ", a.nombreMadre=" + alumnoNuevo.getNombreMadre() + ", a.numeroCuenta="
                    + alumnoNuevo.getCuentaCorriente() + "," + "a.telMovil" + alumnoNuevo.getTelMovil()
                    + ", a.telFijo" + alumnoNuevo.getTelFijo() + ", a.email" + alumnoNuevo.getEmail();


            ResultSet retset;
            try {
                retset = accesoBD.ejecutaConsulta(selId);
                alumnoNuevo.setIdAlumno(retset.getInt("idAlumno"));
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
            String delete = "DELETE FROM alumno WHERE idAlumno = " + alumnoNuevo.getIdAlumno();
            try {
                accesoBD.ejecutaActualizacion(delete);
            } catch (SQLException ex) {
                Logger.getLogger(AccesoBDAlumno.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String delete = "DELETE FROM alumno WHERE idAlumno = " + alumnoNuevo.getIdAlumno();
            try {
                accesoBD.ejecutaActualizacion(delete);
            } catch (SQLException ex) {
                Logger.getLogger(AccesoBDAlumno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
