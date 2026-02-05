package excepciones;

public class PasswordDebilNumeros extends PasswordDebilException {

    /**
     * Excepcion del tipo PasswordDebil en caso de que se use algun numero
     */

    public PasswordDebilNumeros() {
        super("- La contraseña NO debe contener ningun numero");
    }

}
