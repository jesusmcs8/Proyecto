/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bma;

/**
 *
 * @author Alex
 */
public class Usuario {
    private int idUsuario;
    private String dni;
    private String clave;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    
    public void crearUsuario(int idUsuarioArg, String nombreArg, String apellidosArg, String dniArg, String claveArg, boolean entrenaArg){
        idUsuario=idUsuarioArg;
        dni=new String();
        dni=dniArg;
        clave=new String();
        clave=claveArg;
        nombre=new String();
        nombre=nombreArg;
        apellidos=new String();
        apellidos=apellidosArg;
        entrenador=entrenaArg;
    }
}
