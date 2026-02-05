package ejercicios;

import java.util.*;

public class Ejercicio_2 {

	public static void main(String[] args) {
		/*
		 * Suma de submatriz rectangular. Inicializa una matriz de 5x5
		 * elementos con valores al azar entre 0 y 100. Muéstrala en la
		 * consola. Ahora pide al usuario las coordenadas de las esquinas
		 * superior izquierda e inferior derecha de un “rectángulo” (submatriz).
		 * Calcula la suma de todos los elementos dentro de la submatriz
		 */
		int matriz[][] = new int[5][5];
		int max = 100, min = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = (int) (Math.round(Math.random() * (max - min) + min));
				System.out.print("|" + matriz[i][j] + "|");
			}
			System.out.println();
		}

		System.out.println("Introduce una coordenada arriba a la izquierda:");
		Scanner s = new Scanner(System.in);
		int x1 = s.nextInt();
		int y1 = s.nextInt();

		System.out.println("Introduce una coordenada abajo a la derecha:");
		int x2 = s.nextInt();
		int y2 = s.nextInt();

		int suma = 0;
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				suma += matriz[i][j];
			}
		}
		System.out.println("Suma total: " + suma);

		s.close();
	}

}
