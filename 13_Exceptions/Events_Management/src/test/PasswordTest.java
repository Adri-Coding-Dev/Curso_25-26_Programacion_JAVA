package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.PasswordDebilCaracteres;
import excepciones.PasswordDebilCompuestaException;
import excepciones.PasswordDebilLetras;
import excepciones.PasswordDebilLongitud;
import excepciones.PasswordDebilNumeros;
import gestion.GestorUsuarios;

public class PasswordTest {

        GestorUsuarios gestor;

        @BeforeEach
        void crear_instancia() {
                gestor = new GestorUsuarios();
        }

        @Test
        void validarPassword_cuandoTieneNumeros_contieneExcepcionNumeros() {

                PasswordDebilCompuestaException ex = assertThrows(
                                PasswordDebilCompuestaException.class,
                                () -> gestor.validarPassword("123455678"));

                assertTrue(ex.getCausas().stream()
                                .anyMatch(e -> e instanceof PasswordDebilNumeros));
        }

        @Test
        void validarPassword_cuandoNoTieneLetras_contieneExcepcionLetras() {
                PasswordDebilCompuestaException ex = assertThrows(
                                PasswordDebilCompuestaException.class, () -> gestor.validarPassword("@#$%&$$%&&*"));
                assertTrue(ex.getCausas().stream()
                                .anyMatch(e -> e instanceof PasswordDebilLetras));
        }

        @Test
        void validarPassword_CuandoNoTieneCaracteres_contieneExcepcionCaracteres() {
                PasswordDebilCompuestaException ex = assertThrows(
                                PasswordDebilCompuestaException.class, () -> gestor.validarPassword("asjdgajshdgas"));
                assertTrue(ex.getCausas().stream()
                                .anyMatch(e -> e instanceof PasswordDebilCaracteres));
        }

        @Test
        void validarPassword_CuandoNoTieneLongitud_contieneExcepcionLongitud() {
                PasswordDebilCompuestaException ex = assertThrows(
                                PasswordDebilCompuestaException.class, () -> gestor.validarPassword("Hola$%"));
                assertTrue(ex.getCausas().stream()
                                .anyMatch(e -> e instanceof PasswordDebilLongitud));
        }

        @Test
        void validarPassword_CuandoTodoEsValido_noLanzaExcepcion() {
                assertDoesNotThrow(() -> gestor.validarPassword("Test@#$%"));
        }

}
