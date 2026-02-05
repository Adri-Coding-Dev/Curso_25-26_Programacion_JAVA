package excepciones;

import java.util.List;

public class PasswordDebilCompuestaException extends Exception {

    private final List<PasswordDebilException> causas;

    public PasswordDebilCompuestaException(List<PasswordDebilException> causas) {
        super("La contraseña no cumple los requisitos de seguridad");
        this.causas = causas;
    }

    public List<PasswordDebilException> getCausas() {
        return causas;
    }
}
