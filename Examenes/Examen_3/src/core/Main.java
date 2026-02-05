package core;

import ejercicios.*;

public class Main {
    public static void main(String[] args) {
        // Ejercicio 1
        // Ejercicio1.solveEjercicio();

        // Ejercicio 2
        // Ejercicio2.solveEjercicio();

        // Ejercicio3
        Libro lib1 = new Libro("Quijote", "Cervantes", 900);

        Libro lib2 = new Libro("1984", "Orwell", 300);

        lib1.mostrarInfo();
        lib1.prestar();

        lib2.prestar();

        lib1.prestar();

        lib1.devolver();

        LibroInfantil libInf1 = new LibroInfantil("El principito", "Saint-Exupéry", 120, 10);

        libInf1.mostrarInfo();
    }

}
