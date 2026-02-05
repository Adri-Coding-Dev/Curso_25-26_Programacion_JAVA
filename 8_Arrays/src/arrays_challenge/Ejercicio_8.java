package arrays_challenge;

public class Ejercicio_8 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que inicialice dos arrays de 150 
		 *elementos enteros al azar entre 0 y 100. Debes crear 
		 *un tercer array, compuesto de la siguiente forma: en 
		 *los elementos de índice impar del tercer array debes 
		 *copiar los elementos de índice impar del primer array; 
		 *en los elementos de índice par del tercer array debes 
		 *copiar los elementos de índice par del segundo array. 
		 *Debes realizar el ejercicio mediante un método que admita 
		 *los arrays como argumentos y realice la acción pedida. 
		 *Cabecera del método 'public static int[] creaTercerArray 
		 *(int a1[], int a2[])'*/
		int min=0,max=100;
		int array1[]=new int [150];
		int array2[]=new int [150];
		for(int i=0;i<array1.length;i++) {
			array1[i]=(int)(Math.round(Math.random()*(max-min)+min));
			array2[i]=(int)(Math.round(Math.random()*(max-min)+min));
		}
		int array3[]=crearTercerArray(array1,array2);
		for(int i=0;i<array3.length;i++) {
			System.out.println(array3[i]);
		}
	}

	private static int[] crearTercerArray(int[] array1, int[] array2) {
		int array3[]=new int[array1.length];
		for(int i=0;i<array3.length;i++) {
			if(i%2!=0) {
				array3[i]=array1[i];
			}else {
				array3[i]=array2[i];
			}
		}
		
		return array3;
	}

}
