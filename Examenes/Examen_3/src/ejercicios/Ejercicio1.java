package ejercicios;

import java.util.Scanner;

public class Ejercicio1 {
    public static void solveEjercicio() {
        System.out.println("=".repeat(60));
        System.out.println("Ejercicio 1");
        System.out.println("=".repeat(60));
        int[] carrera = new int[10];
        int min = 3, max = 10;
        // REllenar el array
        for (int i = 0; i < carrera.length; i++) {
            carrera[i] = (int) (Math.round(Math.random() * (max - min) + min));
            System.out.println(carrera[i]);
        }
        Scanner s = new Scanner(System.in);
        int opcion = 1;
        do {
            mostrarOpciones();
            opcion = s.nextInt();
            switch (opcion) {
                case 0:
                    mostrarCocheRapido(carrera);
                    mostrarArray(carrera);
                    System.out.println("Saliendo...");
                    break;
                case 1:
                    mostrarCocheRapido(carrera);
                    mostrarCocheLento(carrera);
                    mostrarMedia(carrera);
                    mostrarArray(carrera);
                    break;
                case 2:
                    System.out.println("1º Posicion a cambiar");
                    int pos1 = s.nextInt();
                    System.out.println("2º Posicion a cambiar");
                    int pos2 = s.nextInt();
                    intercambiarPosiciones(pos1, pos2, carrera);
                    mostrarArray(carrera);
                    break;
                case 3:
                    System.out.println("1º Posicion a cambiar");
                    int ind1 = s.nextInt();
                    System.out.println("2º Posicion a cambiar");
                    int ind2 = s.nextInt();
                    invertirTramo(ind1, ind2, carrera);
                    mostrarArray(carrera);
                    break;
                case 4:
                    System.out.println("Posicion a cambiar");
                    int potenciador = s.nextInt();
                    potenciarCoche(potenciador, carrera);
                    mostrarArray(carrera);
                default:
                    break;
            }
        } while (opcion != 0);
        s.close();
    }

    private static void potenciarCoche(int potenciador, int[] carrera) {
        carrera[potenciador] *= 2;
    }

    private static void invertirTramo(int ind1, int ind2, int[] carrera) {
        while (ind1 < ind2) {
            int aux = carrera[ind1];
            carrera[ind1] = carrera[ind2];
            carrera[ind2] = aux;

            ind1++;
            ind2--;
        }
    }

    private static int[] intercambiarPosiciones(int pos1, int pos2, int[] carrera) {
        int aux = carrera[pos1];
        carrera[pos1] = carrera[pos2];
        carrera[pos2] = aux;
        return carrera;
    }

    private static double mostrarMedia(int[] carrera) {
        double suma = 0;
        for (int i = 0; i < carrera.length; i++) {
            suma += carrera[i];
        }
        double media = suma / carrera.length;
        return media;
    }

    private static int mostrarCocheLento(int[] carrera) {
        int index = 0;
        int vel = Integer.MIN_VALUE;
        for (int i = 0; i < carrera.length; i++) {
            if (carrera[i] > vel) {
                index = i;
                vel = carrera[i];
            }
        }
        System.out.println("Coche mas Lento(Posicion): " + index);
        System.out.println("Velocidad mas lenta: " + vel);
        return index;
    }

    private static void mostrarArray(int[] carrera) {
        for (int i = 0; i < carrera.length; i++) {
            System.out.print(carrera[i] + ",");
        }
        System.out.println();
    }

    private static int mostrarCocheRapido(int[] carrera) {
        int index = 0;
        int vel = Integer.MAX_VALUE;
        for (int i = 0; i < carrera.length; i++) {
            if (carrera[i] < vel) {
                index = i;
                vel = carrera[i];
            }
        }
        System.out.println("Coche mas Rápido(Posicion): " + index);
        System.out.println("Velocidad mas alta: " + vel);
        return index;
    }

    private static void mostrarOpciones() {
        System.out.println("0.- Salir");
        System.out.println(
                "1.- Mostrar qué coche tiene la velocidad máxima, la mínima y la velocidad media de todos los coches actuales");
        System.out.println("2.- Intercambiar dos posiciones");
        System.out.println("3.- Invertir tramo del array");
        System.out.println("4.- Potenciador de un elemento");
    }
}
