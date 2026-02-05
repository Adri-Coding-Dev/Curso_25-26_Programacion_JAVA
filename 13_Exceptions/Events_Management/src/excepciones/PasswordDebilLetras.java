package excepciones;

public class PasswordDebilLetras extends PasswordDebilException {

    /**
     * Execpcion del tipo PasswordDebil en caso de que no se use ninguna letra
     */

    public PasswordDebilLetras() {
        super("- La contraseña debe contener al menos una letra");
    }
}
