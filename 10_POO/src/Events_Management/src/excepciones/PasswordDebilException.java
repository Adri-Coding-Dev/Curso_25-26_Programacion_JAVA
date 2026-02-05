package excepciones;

/**
 * Excepcion lanzada cuando una contraseña no cumple los requisitos
 */

public abstract class PasswordDebilException extends Exception {

    public PasswordDebilException(String mensaje) {
        super(mensaje);
    }
}
