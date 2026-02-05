package petshop;
import java.time.*;
import java.util.*;

public class PetShop {

    private List<Animal> animalesVendidos;
    private GestorClientes gestorClientes;
    private int totalAnimalesVendidos;

    public PetShop(GestorClientes gestorClientes) {
        this.animalesVendidos = new ArrayList<>();
        this.gestorClientes = gestorClientes;
        this.totalAnimalesVendidos = 0;
    }

    // Comprueba si la tienda está abierta
    public boolean estaAbierta() {
        LocalTime ahora = LocalTime.now();
        DayOfWeek dia = LocalDate.now().getDayOfWeek();
        boolean esLaborable = dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY;
        boolean horarioManana = ahora.isAfter(LocalTime.of(8,0)) && ahora.isBefore(LocalTime.of(14,0));
        boolean horarioTarde = ahora.isAfter(LocalTime.of(18,0)) && ahora.isBefore(LocalTime.of(22,0));

        return esLaborable && (horarioManana || horarioTarde);
    }

    // Venta de un animal
    public void venderAnimal(Animal animal, Duenio duenio, int cantidad) {
        if (!estaAbierta()) {
            System.out.println(" La tienda está cerrada. Horario: L-V de 8:00 a 14:00 y de 18:00 a 22:00.");
            return;
        }

        if (animal instanceof Mascota && cantidad > 1) {
            System.out.println(" No se pueden vender varias mascotas iguales a la vez.");
            return;
        }

        // Si es mascota, registrar dueño
        if (animal instanceof Mascota) {
            ((Mascota) animal).setDuenio(duenio);
        }

        // Registrar cliente en el sistema y venta
        gestorClientes.agregarCliente(duenio);
        gestorClientes.registrarCompra(duenio.getDni());

        // Mostrar si es cliente habitual
        if (duenio.getNumCompras() >= 4) {
            System.out.println("\u001B[33m⭐ Cliente habitual: " + duenio.getNombre() + " " + duenio.getApellido() + "\u001B[0m");
        }

        // Registrar animales vendidos
        for (int i = 0; i < cantidad; i++) {
            animalesVendidos.add(animal);
        }

        totalAnimalesVendidos += cantidad;
        double total = animal.getPrecio() * cantidad;
        System.out.println(" Venta realizada: " + cantidad + " " + animal.getClass().getSimpleName() + "(s) por " + total + "€.");
    }

    // 📊 Mostrar totales
    public void mostrarTotales() {
        System.out.println("\n Estadísticas de ventas:");
        System.out.println("Total animales vendidos: " + totalAnimalesVendidos);
        System.out.println("Total gatos vendidos: " + contarTipo(Gato.class));
        System.out.println("Total perros vendidos: " + contarTipo(Perro.class));
        System.out.println("Total aves vendidas: " + contarTipo(Ave.class));
        System.out.println("Total ratas vendidas: " + contarTipo(Rata.class));
    }

    private long contarTipo(Class<?> tipo) {
        return animalesVendidos.stream().filter(tipo::isInstance).count();
    }

    // 🔍 Buscar mascota por chip
    public void buscarPorChip(int chip) {
        for (Animal a : animalesVendidos) {
            if (a instanceof Mascota) {
                Mascota m = (Mascota) a;
                if (m.getChip() == chip) {
                    System.out.println("🐶 Mascota encontrada: " + m.getNombre() + " - " + m.getClass().getSimpleName());
                    return;
                }
            }
        }
        System.out.println("⚠️ No se encontró ninguna mascota con chip " + chip);
    }

    // 🔍 Buscar dueño por DNI
    public void buscarDuenioPorDni(String dni) {
        Duenio d = gestorClientes.getClientePorDni(dni);
        if (d != null) {
            System.out.println("👤 Dueño encontrado: " + d.getNombre() + " " + d.getApellido() + " (" + d.getDni() + ")");
        } else {
            System.out.println("⚠️ No se encontró ningún dueño con ese DNI.");
        }
    }

    public void mostrarClientes() {
        System.out.println("\n📋 Lista de clientes registrados:");
        for (Duenio d : gestorClientes.getTodosLosClientes()) {
            if (d.getNumCompras() >= 4)
                System.out.println("\u001B[33m⭐ " + d.getNombre() + " " + d.getApellido() + " (" + d.getDni() + ")\u001B[0m");
            else
                System.out.println(d.getNombre() + " " + d.getApellido() + " (" + d.getDni() + ")");
        }
    }
}


