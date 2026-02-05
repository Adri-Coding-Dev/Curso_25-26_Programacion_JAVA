package rookie_exercises;
import java.util.*;
public class Ejercicio_10 {

	public static void main(String[] args) {
		/*Escribe un programa que imprima las primeras n filas del triángulo de Pascal*/
		Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el número de filas del triángulo de Pascal: ");
        int n = sc.nextInt();
        int filaMax = n - 1;
        int numGrande = 1;
        for (int i = 1; i <= filaMax / 2; i++) {
            numGrande = numGrande * (filaMax - i + 1) / i;
        }
        int temp = numGrande;
        int cifras = 0;
        while (temp > 0) {
            temp /= 10;
            cifras++;
        }
        if (cifras == 0) cifras = 1;
        int ancho = cifras + 1;
        for (int fila = 0; fila < n; fila++) {
            int num = 1;
            for (int s = 0; s < (n - fila) * (ancho / 2); s++) {
                System.out.print(" ");
            }
            for (int pos = 0; pos <= fila; pos++) {
                imprimirAlineado(num, ancho);
                num = num * (fila - pos) / (pos + 1);
            }
            System.out.println();
        }
        sc.close();
    }
    public static void imprimirAlineado(int num, int ancho) {
        int temp = num;
        int digitos = 0;
        if (num == 0) digitos = 1;
        else {
            while (temp > 0) {
                temp /= 10;
                digitos++;
            }
        }
        for (int i = 0; i < ancho - digitos; i++) {
            System.out.print(" ");
        }
        System.out.print(num);
	}
}
