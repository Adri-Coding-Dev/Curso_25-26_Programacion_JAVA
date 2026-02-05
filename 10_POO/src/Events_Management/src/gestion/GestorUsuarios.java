package gestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import excepciones.PasswordDebilCaracteres;
import excepciones.PasswordDebilCompuestaException;
import excepciones.PasswordDebilException;
import excepciones.PasswordDebilLetras;
import excepciones.PasswordDebilLongitud;
import excepciones.PasswordDebilNumeros;

/**
 * Gestion de validar y crear usuarios
 */
public class GestorUsuarios {
    private HashMap<String, String> usuarios = new HashMap<>();

    public GestorUsuarios() {
        // Usuarios iniciales
        usuarios.put("admin", "Password@#%&%*");
        usuarios.put("alumno", "alumno");
        usuarios.put("test", "test");
    }

    /**
     * Metodo para verificar si un usuario esta dentro de nuestro HashMap de
     * usuarios
     * 
     * @param usuario  -> Nombre de Usuario
     * @param password -> Contraseña del Usuario
     * @return -> Si el Usuario esta dentro de la lista de Usuarios
     */
    public boolean login(String usuario, String password) {
        return usuarios.containsKey(usuario) && usuarios.get(usuario).equals(password);
    }

    /**
     * Metodo para verificar si un usuario es Admin o no
     * 
     * @param usuario -> Nombre de Usuario
     * @return -> Si el usuario es Admin (o test para hacer pruebas)
     */
    public boolean esAdmin(String usuario) {
        return "admin".equals(usuario) || "test".equals(usuario);
    }

    /**
     * Metodo para crear Usuarios
     * 
     * @param nombre   -> Nombre de usuario
     * @param password -> Contraseña del Usuario
     * @return -> Si se ha creado o no el Usuario
     * @throws PasswordDebilException -> Se lanza en caso de que la contraseña no
     *                                pase el REGEX
     */
    public boolean crearUsuario(String nombre, String password) throws PasswordDebilCompuestaException {
        // Validar contraseña
        if (validarPassword(password)) {
            usuarios.put(nombre, password);
            return true;
        } else {
            System.out.println("Error al crear al usuario...");
            return false;
        }
    }

    /**
     * Metodo para validar la contraseña
     * 
     * @param pass -> Contraseña del Usuario
     * @return -> Si la contraseña es valida o no
     * @throws PasswordDebilException -> Se lanza en caso de que la contraseña no
     *                                pase el REGEX
     */
    public boolean validarPassword(String pass) throws PasswordDebilCompuestaException {

        List<PasswordDebilException> excepciones = new ArrayList<>();

        if (!pass.matches("[A-Za-z!@#$%&*]{8,12}"))
            excepciones.add(new PasswordDebilLongitud());

        if (!pass.matches(".*[A-Za-z].*"))
            excepciones.add(new PasswordDebilLetras());

        if (!pass.matches(".*[!@#$%&*].*"))
            excepciones.add(new PasswordDebilCaracteres());

        if (pass.matches(".*[0-9].*"))
            excepciones.add(new PasswordDebilNumeros());

        if (!excepciones.isEmpty())
            throw new PasswordDebilCompuestaException(excepciones);

        return true;
    }

}
