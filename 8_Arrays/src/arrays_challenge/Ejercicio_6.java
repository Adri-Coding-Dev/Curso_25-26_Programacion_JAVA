package arrays_challenge;
public class Ejercicio_6 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que inicialice un array de 150 
		 *elementos enteros al azar entre 0 y 100. Debes mostrar 
		 *al usuario la suma de los elementos cuyo índice sea par.*/
		int min=0,max=100;
		int array[]=new int[150];
		int sumaPares=0;
		for(int i=0;i<array.length;i++) {
			array[i]=(int)(Math.round(Math.random()*(max-min)+min));
			if(i%2==0) {
				sumaPares+=array[i];
			}
		}
		System.out.println("Suma de los numeros pares: "+sumaPares);
	}

}
