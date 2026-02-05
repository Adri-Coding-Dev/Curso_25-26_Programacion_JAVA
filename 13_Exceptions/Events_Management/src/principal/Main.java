package principal;

import java.time.LocalDateTime;
import java.util.Scanner;
import gestion.*;
import eventos.*;
import excepciones.*;
import utils.Utils;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        GestorUsuarios usuarioService = new GestorUsuarios();
        GestorEventos eventoService = new GestorEventos();

        String usuario = "";
        boolean logueado = false;
        boolean activo = true;
        int intentos = 0;

        while (activo) {
            logueado = false;
            // Login
            while (!logueado && intentos < 3) {
                System.out.println("=".repeat(40));
                System.out.print("Usuario: ");
                usuario = s.next();
                System.out.print("Contraseña: ");
                String pass = s.next();
                System.out.println("=".repeat(40));

                if (usuarioService.login(usuario, pass)) {
                    logueado = true;
                } else {
                    intentos++;
                    System.out.println("Usuario o contraseña incorrectos (" + intentos + "/3)");
                    System.out.println("=".repeat(40));
                }
            }
            if (!logueado) {
                System.out.println("Superado número máximo de intentos. Saliendo...");
                System.out.println("=".repeat(40));
                return;
            }
            intentos = 0;
            while (logueado) {
                int opcion = mostrarOpciones(s);

                try {
                    switch (opcion) {
                        case 0:
                            activo = false;
                            break;
                        case 1:
                            eventoService.listarTodos();
                            break;
                        case 2:
                            eventoService.listarFuturos();
                            break;
                        case 3:
                            crearEventoPresencial(s, eventoService);
                            break;
                        case 4:
                            crearEventoOnline(s, eventoService);
                            break;
                        case 5:
                            borrarEvento(s, eventoService);
                            break;
                        case 6:
                            if (usuarioService.esAdmin(usuario)) {
                                crearUsuario(s, usuarioService);
                            } else {
                                System.out.println("Acceso denegado: solo admin puede crear usuarios.");
                            }
                            break;
                        case 7:
                            logueado = false;
                            break;
                        default:
                            System.out.println("Opción no válida");
                    }
                } catch (Exception e) {
                }
            }
        }
        System.out.println("Programa finalizado.");
        s.close();
    }

    /**
     * Metodo para mostrar todas las opciones
     * 
     * @param s -> Scanner abierto para poder retornar la opcion
     * @return -> la opcion del usuario
     */

    private static int mostrarOpciones(Scanner s) {
        System.out.println("=".repeat(18) + "Menú" + "=".repeat(18));
        System.out.println("0. Salir del Programa");
        System.out.println("1. Listar todos los eventos");
        System.out.println("2. Listar eventos futuros");
        System.out.println("3. Añadir evento presencial");
        System.out.println("4. Añadir evento online");
        System.out.println("5. Borrar evento");
        System.out.println("6. Crear usuario (solo admin)");
        System.out.println("7. Salir del menu");
        System.out.println("=".repeat(18) + "Menú" + "=".repeat(18));
        System.out.print("Opción: ");
        return s.nextInt();
    }

    /**
     * Metodo para crear eventos presenciales
     * 
     * @param s             -> Scanner abierto para que podamos guardar datos del
     *                      usuario
     * @param eventoService -> Gestor de eventos para usar el metodo crear evento
     * @throws FechaNoValidaException -> Se lanza en caso de que la fecha no tenga
     *                                un formato correcto
     */
    private static void crearEventoPresencial(Scanner s, GestorEventos eventoService) throws FechaNoValidaException {
        System.out.print("Nombre: ");
        String nombre = s.next();
        System.out.print("Aula: ");
        String aula = s.next();
        System.out.print("Fecha del evento: ");
        LocalDateTime fecha = Utils.parsearFecha(s);

        Evento e = new EventoPresencial(nombre, fecha, aula);
        eventoService.agregarEvento(e);
        System.out.println("Evento presencial creado correctamente!");
    }

    /**
     * Metodo para crear eventos Onlines
     * 
     * @param s             -> Scanner abierto para que podamos guardar datos del
     *                      usuario
     * @param eventoService -> Gestor de eventos para usar el metodo crear evento
     * @throws FechaNoValidaException -> Se lanza en caso de que la fecha no tenga
     *                                un formato correcto
     */
    private static void crearEventoOnline(Scanner s, GestorEventos eventoService) throws FechaNoValidaException {
        System.out.print("Nombre: ");
        String nombre = s.next();
        System.out.print("Plataforma: ");
        String plataforma = s.next();
        System.out.print("Fecha del evento: ");
        LocalDateTime fecha = Utils.parsearFecha(s);

        Evento e = new EventoOnline(nombre, fecha, plataforma);
        eventoService.agregarEvento(e);
        System.out.println("Evento online creado correctamente!");
    }

    /**
     * Metodo para borrar eventos por nombre
     * 
     * @param s             -> Scanner abierto para guardar el nombre del evento
     * @param eventoService -> Gesto de eventos para usar el metodo borrarPorNombre
     * @throws EventoNoEncontradoException -> Se lanza en caso de que no existan
     *                                     eventos
     */
    private static void borrarEvento(Scanner s, GestorEventos eventoService) throws EventoNoEncontradoException {
        System.out.print("Nombre del evento a borrar: ");
        String nombre = s.nextLine();
        eventoService.borrarPorNombre(nombre);
        System.out.println("Evento eliminado!!");
    }

    /**
     * Metodo para crear usuarios (solo Admin)
     * 
     * @param s              -> Scanner abierto para guardar datos del usuario
     * @param usuarioService -> Gestor de Usuarios para usar el metodo crearUsuario
     * @throws PasswordDebilException -> Se lanza en caso de que la password no
     *                                cumpla el REGEX
     */
    private static void crearUsuario(Scanner s, GestorUsuarios usuarioService) throws PasswordDebilCompuestaException {
        System.out.print("Nombre del nuevo usuario: ");
        String nombre = s.next();
        System.out.print("Contraseña: ");
        String password = s.next();
        if (usuarioService.crearUsuario(nombre, password))
            System.out.println("Usuario creado correctamente!");
    }
}
