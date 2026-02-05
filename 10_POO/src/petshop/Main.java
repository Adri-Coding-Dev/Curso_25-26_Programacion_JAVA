package petshop;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorClientes gestorClientes = new GestorClientes();
        PetShop tienda = new PetShop(gestorClientes);
        if(!tienda.estaAbierta()) {
        	System.out.println("Lo sentimos, actualmente estamos cerrados, vuelva en un rato...");
        	return;
        }
        int opcion;
        do {
            System.out.println("\n===== 🐾 PET SHOP 🐾 =====");
            System.out.println("1. Vender animal");
            System.out.println("2. Mostrar totales");
            System.out.println("3. Buscar dueño por DNI");
            System.out.println("4. Buscar mascota por chip");
            System.out.println("5. Mostrar lista de clientes");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> venderAnimal(tienda, sc);
                case 2 -> tienda.mostrarTotales();
                case 3 -> {
                    System.out.print("Introduce DNI del dueño: ");
                    String dni = sc.nextLine();
                    tienda.buscarDuenioPorDni(dni);
                }
                case 4 -> {
                    System.out.print("Introduce número de chip: ");
                    int chip = sc.nextInt();
                    tienda.buscarPorChip(chip);
                }
                case 5 -> tienda.mostrarClientes();
                case 0 -> System.out.println("👋 Saliendo...");
                default -> System.out.println("❌ Opción inválida");
            }
        } while (opcion != 0);
    }

    private static void venderAnimal(PetShop tienda, Scanner sc) {
        System.out.println("¿Qué tipo de animal quieres vender?");
        System.out.println("1. Gato");
        System.out.println("2. Perro");
        System.out.println("3. Ave");
        System.out.println("4. Rata");
        int tipo = sc.nextInt(); sc.nextLine();

        System.out.print("Sexo (MACHO/HEMBRA): ");
        String sexo = sc.nextLine();
        System.out.print("Edad (en días): ");
        int edad = sc.nextInt(); sc.nextLine();
        System.out.print("EAN: ");
        String ean = sc.nextLine();

        Animal animal = null;

        switch (tipo) {
            case 1 -> {
                System.out.print("Color: ");
                String color = sc.nextLine();
                System.out.print("Raza: ");
                String raza = sc.nextLine();
                System.out.print("Tipo de pelo: ");
                String pelo = sc.nextLine();
                System.out.print("Chip (número entero): ");
                int chip = sc.nextInt(); sc.nextLine();
                System.out.print("Nombre de la mascota: ");
                String nombre = sc.nextLine();
                animal = new Gato(sexo, edad, ean, color, raza, pelo);
            }
            case 2 -> {
                System.out.print("Color: ");
                String color = sc.nextLine();
                System.out.print("Raza: ");
                String raza = sc.nextLine();
                System.out.print("Tipo de pelo: ");
                String pelo = sc.nextLine();
                System.out.print("¿Tiene pedigree? (true/false): ");
                boolean pedigree = sc.nextBoolean(); sc.nextLine();
                System.out.print("Chip (número entero): ");
                int chip = sc.nextInt(); sc.nextLine();
                System.out.print("Nombre de la mascota: ");
                String nombre = sc.nextLine();
                animal = new Perro(sexo, edad, ean, color, raza, pelo, pedigree);
            }
            case 3 -> {
                System.out.print("Color: ");
                String color = sc.nextLine();
                System.out.print("Tipo de ave: ");
                String tipoAve = sc.nextLine();
                animal = new Ave(sexo, edad, ean, color, tipoAve);
            }
            case 4 -> {
                System.out.print("Peso (gr): ");
                int peso = sc.nextInt();
                System.out.print("Tamaño (cm): ");
                int tam = sc.nextInt(); sc.nextLine();
                if (peso < 200 || tam < 5) {
                    System.out.println(" No se venden ratas menores de 200g o 5cm.");
                    return;
                }
                animal = new Rata(sexo, edad, ean, peso, tam);
            }
            default -> System.out.println(" Tipo inválido.");
        }

        if (animal == null) return;

        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt(); sc.nextLine();

        System.out.println("Datos del dueño:");
        System.out.print("Nombre: ");
        String nombreD = sc.nextLine();
        System.out.print("Apellido: ");
        String apellidoD = sc.nextLine();
        System.out.print("DNI: ");
        String dni = sc.nextLine();

        Duenio duenio = new Duenio(nombreD, apellidoD, dni);

        tienda.venderAnimal(animal, duenio, cantidad);
    }
}

