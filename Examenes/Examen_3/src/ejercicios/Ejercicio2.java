package ejercicios;

import java.util.Scanner;

public class Ejercicio2 {
    public static void solveEjercicio() {
        int[][] matrix = new int[5][5];
        int min = 1, max = 20;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = (int) (Math.round(Math.random() * (max - min) + min));
                System.out.print("|" + matrix[i][j] + "|");
            }
            System.out.println();
        }

        Scanner s = new Scanner(System.in);
        System.out.println("Introduce una fila (int)");
        int fila = s.nextInt();
        System.out.println("Introduce una columna (int)");
        int columna = s.nextInt();

        int contadorNumerosFila = contarNumerosFila(fila, matrix);
        int contadorNumerosColumna = contarNumerosColumna(columna, matrix);

        int sumaTotal = (contadorNumerosColumna + contadorNumerosFila) - matrix[fila][columna];

        System.out.println("Suma Total de los numeros: " + sumaTotal);

        s.close();
    }

    private static int contarNumerosColumna(int columna, int[][] matrix) {
        int result = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            result += matrix[columna][i];
        }
        return result;
    }

    private static int contarNumerosFila(int fila, int[][] matrix) {
        int result = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            result += matrix[i][fila];
        }
        return result;
    }
}
