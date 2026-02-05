package excepciones;

public class PasswordDebilCaracteres extends PasswordDebilException {

    /**
     * Execpcion del tipo PasswordDebil en caso de que no contenga caracteres
     * especiales
     */

    public PasswordDebilCaracteres() {
        super("- La contraseña debe contener como minimo un caracter especial");
    }

}
