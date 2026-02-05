package postOffice;

import java.time.LocalDate;
import java.util.Scanner;

public class InterfazUsuario {
    private Scanner scanner;
    private GestionPaquetes gestionPaquetes;
    private GestionTickets gestionTickets;
    private Autenticacion autenticacion;

    public InterfazUsuario() {
        scanner = new Scanner(System.in);
        gestionPaquetes = new GestionPaquetes();
        gestionTickets = new GestionTickets();
        autenticacion = new Autenticacion();
    }

    public void iniciarSistema() {
        System.out.println("=== SISTEMA DE CORREOS ===");

        // Autenticación
        boolean autenticado = false;
        String usuarioActual = "";

        while (!autenticado) {
            System.out.print("Usuario: ");
            String usuario = scanner.nextLine();
            System.out.print("Contraseña: ");
            String contraseña = scanner.nextLine();

            if (autenticacion.autenticar(usuario, contraseña)) {
                autenticado = true;
                usuarioActual = usuario;
                System.out.println("¡Bienvenido, " + usuario + "!");
            } else {
                System.out.println("Usuario o contraseña incorrectos. Intente nuevamente.");
            }
        }

        // Mostrar menú según tipo de usuario
        if (autenticacion.esAdmin(usuarioActual)) {
            mostrarMenuAdmin();
        } else {
            mostrarMenuCliente();
        }

        scanner.close();
    }

    private void mostrarMenuAdmin() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Añadir paquete");
            System.out.println("2. Eliminar paquete");
            System.out.println("3. Atender siguiente cliente");
            System.out.println("4. Ver todos los paquetes");
            System.out.println("5. Ver cola de tickets");
            System.out.println("6. Agregar nuevo usuario");
            System.out.println("7.- Cambiar de Usuario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    añadirPaquete();
                    break;
                case 2:
                    eliminarPaquete();
                    break;
                case 3:
                    atenderCliente();
                    break;
                case 4:
                    verPaquetes();
                    break;
                case 5:
                    verColaTickets();
                    break;
                case 6:
                    agregarUsuario();
                    break;
                case 7:
                    iniciarSistema();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuCliente() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ CLIENTE ===");
            System.out.println("1. Enviar paquete");
            System.out.println("2. Recoger paquete");
            System.out.println("3. Ver mi posición en la cola");
            System.out.println("4.- Cambiar de usuario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    enviarPaquete();
                    break;
                case 2:
                    recogerPaquete();
                    break;
                case 3:
                    verPosicionCola();
                    break;
                case 4:
                    iniciarSistema();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void añadirPaquete() {
        System.out.println("\n=== AÑADIR PAQUETE ===");

        System.out.print("ID del paquete: ");
        long id = Long.parseLong(scanner.nextLine());

        if (gestionPaquetes.existePaquete(id)) {
            System.out.println("Error: Ya existe un paquete con este ID.");
            return;
        }

        System.out.print("Peso (kg): ");
        double peso = Double.parseDouble(scanner.nextLine());
        System.out.print("Altura (cm): ");
        double altura = Double.parseDouble(scanner.nextLine());
        System.out.print("Ancho (cm): ");
        double ancho = Double.parseDouble(scanner.nextLine());

        System.out.print("Remitente: ");
        String remitente = scanner.nextLine();
        System.out.print("Destinatario: ");
        String destinatario = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        LocalDate fechaEnvio = LocalDate.now();
        LocalDate fechaRecogida = fechaEnvio.plusDays(7);

        Paquete nuevoPaquete = new Paquete(id, peso, altura, ancho, remitente,
                destinatario, direccion, fechaEnvio, fechaRecogida);

        if (gestionPaquetes.agregarPaquete(nuevoPaquete)) {
            System.out.println("Paquete añadido correctamente.");
        } else {
            System.out.println("Error al añadir el paquete.");
        }
    }

    private void eliminarPaquete() {
        System.out.println("\n=== ELIMINAR PAQUETE ===");
        System.out.print("ID del paquete a eliminar: ");
        long id = Long.parseLong(scanner.nextLine());

        if (gestionPaquetes.eliminarPaquete(id)) {
            System.out.println("Paquete eliminado correctamente.");
        } else {
            System.out.println("No se encontró un paquete con ese ID.");
        }
    }

    private void atenderCliente() {
        System.out.println("\n=== ATENDER CLIENTE ===");

        if (!gestionTickets.hayClientesEnEspera()) {
            System.out.println("No hay clientes en espera.");
            return;
        }

        Ticket ticket = gestionTickets.atenderSiguienteCliente();
        System.out.println("Atendiendo: " + ticket);

        if (ticket.getTipoAccion().equals("ENVIAR")) {
            Paquete paquete = ticket.getPaqueteEnvio();
            gestionPaquetes.agregarPaquete(paquete);
            System.out.println("Paquete registrado con ID: " + paquete.getIdPackage());
        } else if (ticket.getTipoAccion().equals("RECOGER")) {
            gestionPaquetes.eliminarPaquete(ticket.getIdPackage());
            System.out.println("Paquete " + ticket.getIdPackage() + " entregado.");
        }

        Ticket siguiente = gestionTickets.verSiguienteCliente();
        if (siguiente != null) {
            System.out.println("Siguiente en cola: " + siguiente);
        } else {
            System.out.println("No hay más clientes en espera.");
        }
    }

    private void enviarPaquete() {
        System.out.println("\n=== ENVIAR PAQUETE ===");

        System.out.print("Peso (kg): ");
        double peso = Double.parseDouble(scanner.nextLine());
        System.out.print("Altura (cm): ");
        double altura = Double.parseDouble(scanner.nextLine());
        System.out.print("Ancho (cm): ");
        double ancho = Double.parseDouble(scanner.nextLine());

        System.out.print("Remitente: ");
        String remitente = scanner.nextLine();
        System.out.print("Destinatario: ");
        String destinatario = scanner.nextLine();
        System.out.print("Dirección de destino: ");
        String direccion = scanner.nextLine();

        long idNuevo = (long) (Math.random() * 1000000);
        LocalDate fechaEnvio = LocalDate.now();
        LocalDate fechaRecogida = fechaEnvio.plusDays(7);

        Paquete nuevoPaquete = new Paquete(idNuevo, peso, altura, ancho, remitente,
                destinatario, direccion, fechaEnvio, fechaRecogida);

        Ticket ticket = gestionTickets.crearTicketEnvio(nuevoPaquete);

        System.out.println("\n=== TICKET GENERADO ===");
        System.out.println(ticket);
        System.out.println("Su ID de paquete será: " + idNuevo);
        System.out.println("Posición en cola: " + gestionTickets.getTamanioCola());
        System.out.println("Conserve este número para cualquier consulta.");
    }

    private void recogerPaquete() {
        System.out.println("\n=== RECOGER PAQUETE ===");
        System.out.print("ID del paquete a recoger: ");
        long idPaquete = Long.parseLong(scanner.nextLine());

        if (!gestionPaquetes.existePaquete(idPaquete)) {
            System.out.println("Error: No existe un paquete con ese ID.");
            return;
        }

        Ticket ticket = gestionTickets.crearTicketRecogida(idPaquete);

        System.out.println("\n=== TICKET GENERADO ===");
        System.out.println(ticket);
        System.out.println("Posición en cola: " + gestionTickets.getTamanioCola());
        System.out.println("Conserve este número para cualquier consulta.");
    }

    private void verPaquetes() {
        System.out.println("\n=== LISTA DE PAQUETES ===");
        var listaPaquetes = gestionPaquetes.listarPaquetes();

        if (listaPaquetes.isEmpty()) {
            System.out.println("No hay paquetes registrados.");
        } else {
            for (Paquete p : listaPaquetes) {
                System.out.println(p);
            }
            System.out.println("Total: " + listaPaquetes.size() + " paquetes.");
        }
    }

    private void verColaTickets() {
        System.out.println("\n=== COLA DE TICKETS ===");
        var cola = gestionTickets.getColaTickets();

        if (cola.isEmpty()) {
            System.out.println("No hay tickets en espera.");
        } else {
            int posicion = 1;
            for (Ticket t : cola) {
                System.out.println(posicion++ + ". " + t);
            }
        }
    }

    private void verPosicionCola() {
        System.out.println("\n=== POSICIÓN EN COLA ===");
        System.out.println("Actualmente hay " + gestionTickets.getTamanioCola() +
                " personas en espera.");
        System.out.println("Su posición exacta depende del número de ticket asignado.");
        System.out.println("Consulte con el administrador para más detalles.");
    }

    private void agregarUsuario() {
        System.out.println("\n=== AGREGAR USUARIO ===");
        System.out.print("Nombre de usuario: ");
        String usuario = scanner.nextLine();

        if (autenticacion.existeUsuario(usuario)) {
            System.out.println("El usuario ya existe.");
            return;
        }

        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();
        System.out.print("¿Es administrador? (s/n): ");
        String respuesta = scanner.nextLine();

        autenticacion.agregarUsuario(usuario, contraseña);
        System.out.println("Usuario " + usuario + " agregado correctamente.");
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Nota: Para que sea administrador, edite el código fuente.");
        }
    }
}