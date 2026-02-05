package arrays_challenge;
public class Ejercicio_7 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que inicialice un array de 150 
		 *elementos enteros al azar entre -100 y 100. Debes 
		 *conseguir que todos los números pares del array 
		 *cambien de signo, los positivos deben pasar a negativos 
		 *y viceversa. Debes realizar el ejercicio mediante un 
		 *método que admita el array como argumento y realice 
		 *la acción pedida. Cabecera del método 'public static 
		 *void cambiaSignoEnValores(int array[])'*/
		int min=-100,max=100;
		int array[]=new int[150];
		for(int i=0;i<array.length;i++) {
			array[i]=(int)(Math.round(Math.random()*(max-min)+min));
			System.out.println(array[i]);
		}
		cambiarSignosEnValores(array);
	}

	private static void cambiarSignosEnValores(int[] array) {
		for(int i=0;i<array.length;i++) {
			System.out.println((array[i]*(-1)));
		}
		
	}

}
