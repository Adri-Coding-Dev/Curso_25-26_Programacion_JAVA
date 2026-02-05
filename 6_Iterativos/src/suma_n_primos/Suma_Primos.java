package suma_n_primos;
import java.util.*;
public class Suma_Primos {

	public static void main(String[] args) {
		/*--------------------------------------------------
		 | 			      EJERICICO_MANDADO					|
		 |--------------------------------------------------|
		 | ->OBJETIVO: Suma de los numeros Primos 			|
		 | encontrados en un rango de limites dados por el  |
		 | usuario											|
		 |__________________________________________________|*/
		
		//Usamos el Metodo Scanner para pedirle al Usuario los limites de numeros
		Scanner s=new Scanner(System.in);
		//Pedimos los valores y los recogemos en sus variables
		int minimo=0,maximo=0;
		do{
			System.out.println("Introduce un limite inferior");
			minimo=s.nextInt();
			System.out.println("Introduce un limite superior");
			maximo=s.nextInt();

			//Verificamos que los limites sean correctos
			if(minimo>maximo) {
				System.out.println("No puedo realizar semejante operacion");
			}
		}while(minimo>=maximo);	
		//variable que acumula el valor de los numeros primos
		int acumulador=0;
		
		//Recorremos todos los numeros desde el minimo hasta el maximo
		for(int i=minimo;i<=maximo;i++) {
			boolean esPrimo_Comprobacion=verificarPrimo(i);
			if(esPrimo_Comprobacion) {
				acumulador+=i;
			}
		}
		System.out.println("Suma total de los primos: "+acumulador);
		s.close();
	}

	private static boolean verificarPrimo(int i) {
		/*hacemos que el contador empiece en dos porque queremos incrementar el numero
		  hasta igualarlo al numero del usuario (nos saltamos la verificacion de si mismo
		  y la verificacion con 1 -> en Vez de 0, es 2)*/ 
		int contador=2;
		/*Suponemos de primeras que el numero (minimo) no es un Primo (hay mas numeros
		  No primos que numeros primos, o eso dicen*/
		boolean esPrimo=false;
		for(int j=i-1;j>=0;j--) {
			if(j==0) {
				//Numero 1 -> Es primo
				esPrimo=true;
				break;
			}
			if(contador==i) {
				esPrimo=true; //Terminado de comprobar
			}
			//Numero 2 ->
			if(j==1) {
				esPrimo=true;
				break;
			}
			if(i%j==0) {
				esPrimo=false;
				break;
			}else {
				contador++; //Avanzamos al siguiente numero
			}
		}
		if(esPrimo) {
			System.out.println("el Numero "+i+" es Primo");
			return true;
		}else {
			return false;
		}
		
	}
}
