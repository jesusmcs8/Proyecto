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
class AlumnoBD {

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

    public void actualizaAlumnoBD(BaseDatos accesoBD, String actualizacion) {
        try {
            accesoBD.ejecutaActualizacion(actualizacion);
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                Logger.getLogger(AlumnoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String delete = "DELETE FROM alumno WHERE idAlumno = " + alumnoNuevo.getIdAlumno();
            try {
                accesoBD.ejecutaActualizacion(delete);
            } catch (SQLException ex) {
                Logger.getLogger(AlumnoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
