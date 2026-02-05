package arrays_challenge;
public class Ejercicio_5 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que inicialice un array de 150 elementos 
		 *enteros al azar entre 0 y 100. Debes obtener la suma de 
		 *los números pares y los impares en variables separadas*/
		int min=0,max=100;
		int array[]=new int[150];
		int arrayPares[]=new int[array.length];
		int arrayImpares[]=new int[array.length];
		for(int i=0;i<array.length;i++) {
			array[i]=(int)(Math.round(Math.random()*(max-min)+min));
			System.out.println(array[i]);
			if(array[i]%2==0) {
				arrayPares[i]=array[i];
			}else {
				arrayImpares[i]=array[i];
			}
		}
		System.out.println("Numeros Pares");
		for(int i=0;i<arrayPares.length;i++) {
			if(arrayPares[i]!=0) {
				System.out.println(arrayPares[i]);	
			}
		}
		System.out.println("Numeros Impares");
		for(int i=0;i<arrayImpares.length;i++) {
			if(arrayImpares[i]!=0) {
				System.out.println(arrayImpares[i]);
			}
		}
	}

}
