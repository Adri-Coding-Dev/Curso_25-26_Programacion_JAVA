package excepciones;

public class PasswordDebilLongitud extends PasswordDebilException {

    /**
     * Excepcion del tipo PasswordDebil en caso de que la longitud no sea la
     * acertada
     */

    public PasswordDebilLongitud() {
        super("- La longitud debe estar entre 8 y 12 caracteres");
    }
}
