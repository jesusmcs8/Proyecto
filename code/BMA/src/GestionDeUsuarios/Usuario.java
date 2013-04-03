package GestionDeUsuarios;

import bma.BaseDatos;
import java.sql.*;

/**
 * Clase que indica los usuarios del sistema como entrenadores y
 * administradores.
 *
 * @author Alex Moreno
 * @version 1.0
 */
public class Usuario {

    private int idUsuario;
    private String dni;
    private String clave;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String numeroCuenta;
    private int telMovil;
    private int telFijo;
    private String email;
    private boolean entrenador;

    public void crearUsuario(String nombre,
            String primerApellido, String segundoApellido, String dni,
            String clave, boolean entrenador, int telMovil, int telFijo,
            String email, String numeroCuenta) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.dni = dni;
        this.clave = clave;
        this.entrenador = entrenador;
        this.numeroCuenta = numeroCuenta;
        this.telMovil = telMovil;
        this.telFijo = telFijo;
        this.email = email;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getDni() {
        return dni;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public int getTelMovil() {
        return telMovil;
    }

    public int getTelFijo() {
        return telFijo;
    }

    public String getEmail() {
        return email;
    }

    public boolean getEntrenador() {
        return entrenador;
    }

    public void insertarUsuarioBD(BaseDatos accesoBD) {
        String insercion = "INSERT INTO usuario (nombre, primerApellido,"
                + "segundoApellido, DNI, clave, entrenador, numeroCuenta,"
                + "telMovil, telFijo, email) values ('"
                + nombre + "', '" + primerApellido + "', '" + segundoApellido
                + "','" + dni + "', '" + clave + "', " + entrenador + ", '"
                + numeroCuenta + "', " + telMovil + ", " + telFijo + ", '"
                + email + "')";

        System.out.print("\n insert usuario--> " + insercion);
        accesoBD.ejecutaActualizacion(insercion);
    }

    public ResultSet consultaUsuario(BaseDatos accesoBD, String consulta) {
        return accesoBD.ejecutaConsulta(consulta);
    }

    public void actualizaUsuario(BaseDatos accesoBD, String actualizacion) {
        accesoBD.ejecutaActualizacion(actualizacion);
    }

    public boolean tengoPermisosAdministrador() {
        if (this.entrenador) {
            return false;
        } else {
            return true;
        }
    }
}
