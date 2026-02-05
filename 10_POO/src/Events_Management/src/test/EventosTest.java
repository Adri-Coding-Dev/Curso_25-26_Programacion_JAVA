package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eventos.EventoOnline;
import excepciones.EventoNoEncontradoException;
import gestion.GestorEventos;

public class EventosTest {

    GestorEventos gestor;

    @BeforeEach
    void crear_instancia() {
        gestor = new GestorEventos();
    }

    @Test
    void borrarPorNombreTest_cuandoNoHayEvento_causaExcepcion() throws EventoNoEncontradoException {
        assertThrows(EventoNoEncontradoException.class, () -> gestor.borrarPorNombre("Clase"));
    }

    @Test
    void borrarPorNombreTest_cuandoHayEvento_NocausaExcepcion() throws EventoNoEncontradoException {
        gestor.agregarEvento(new EventoOnline("Clase", LocalDateTime.now(), "Moodle"));
        assertTrue(gestor.borrarPorNombre("Clase"));
    }
}
