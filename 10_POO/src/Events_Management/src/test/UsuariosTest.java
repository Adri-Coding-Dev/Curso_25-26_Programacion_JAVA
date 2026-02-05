package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestion.GestorUsuarios;

public class UsuariosTest {

    GestorUsuarios gestor;

    @BeforeEach
    void crear_instancia() {
        gestor = new GestorUsuarios();
    }

    @Test
    void comprobarLogin_cuandoNigunCampoEsValido_devuelveFalse() {
        assertFalse(gestor.login("Adrian", "Hola"));
    }

    @Test
    void comprobarLogin_cuandoNombreEsValido_devuelveFalse() {
        assertFalse(gestor.login("Admin", "Hola"));
    }

    @Test
    void comprobarLogin_cuandoPasswordEsValido_devuelveFalse() {
        assertFalse(gestor.login("Adri", "test"));
    }

    @Test
    void comprobarLogin_cuandoTodoEsValido_devuelveTrue() {
        assertTrue(gestor.login("test", "test"));
    }

    // ==================================IS_ADMIN_TEST==============================================\\
    @Test
    void comprobarAdmin_cuandoNoEsAdminEsTest_devuelveTrue() {
        assertTrue(gestor.esAdmin("test"));
    }

    @Test
    void comprobarAdmin_cuandoEsAdmin_devuelveTrue() {
        assertTrue(gestor.esAdmin("Admin"));
    }

    @Test
    void comprobarAdmin_cuandoNoEsAdmin_devuelveFalse() {
        assertFalse(gestor.esAdmin("alumno"));
    }
}
