package ejercicios;

public class Ejercicio_1 {

	public static void main(String[] args) {
		/*Máxima suma de dos números. Inicializa un array de 10 elementos con 
		 *valores enteros al azar entre 0 y 20. A continuación realiza todas 
		 *las sumas posibles entre dos elementos e imprime en la consola el 
		 *array, la suma máxima y los dos elementos que has sumado.*/
		int max=20,min=0;
		int array[]=new int [10];
		for(int i=0;i<array.length;i++) {
			array[i]=(int)(Math.round(Math.random()*(max-min)+min));
			System.out.println(array[i]);
		}
		int suma=0,num1=0,num2=0,sumaMax=0,numMax_1=0,numMax_2=0;
		
		for(int i=0;i<array.length;i++) {
			for(int j=i+1;j<array.length;j++) {
				num1=array[i];
				num2=array[j];
				suma=num1+num2;
				if(suma>sumaMax) {
					sumaMax=suma;
					numMax_1=num1;
					numMax_2=num2;
				}
			}
		}
		System.out.println("Suma Maxima: "+sumaMax);
		System.out.println("Numero 1 de la suma: "+numMax_1);
		System.out.println("Numero 2 de la suma: "+numMax_2);
	}

}
