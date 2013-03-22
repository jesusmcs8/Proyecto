package bma;

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
    private boolean entrenador;

    public void crearUsuario(int idUsuario, String nombre, String primerApellido, String segundoApellido, String dni, String clave, boolean entrenador) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.dni = dni;
        this.clave = clave;
        this.entrenador=entrenador;
    }
}
