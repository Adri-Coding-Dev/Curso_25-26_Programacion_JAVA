package suma_n_primos;
import java.util.*;
public class Suma_Primos_Recursivo {

	public static void main(String[] args) {
		/*_________________________________________________
		 |_________________METODO RECURSIVO________________|
		 |                                                 |
		 | --> Conseguir la suma de los numeros primos     |
		 | 	   definidos entre dos valores                 |
		 |_________________________________________________|
		 * */
		//Usamos Scanner para pedir los limites con los que vamos a trabajar
		Scanner s=new Scanner(System.in);
		int minimo=0,maximo=0,sumaConcurrente=0;
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
		verificarPrimoRecursivo(maximo,(maximo-1),sumaConcurrente);
		s.close();
	}
	/***
	 * METODO PARA VERIFICAR SI UN NUMERO ES PRIMO DE FORMA RECURSIVA
	 * @param numero -> Es el numero maximo que ha introducido el usuario,
	 * El primero con el que vamos a tratar
	 * @param anterior -> Es el numero anterior al maximo, con el que vamos a 
	 * realizar la primera division
	 */
	private static void verificarPrimoRecursivo(int numero,int anterior,int sumaConcurrente) {
		// En principio, consideramos que el numero no es Primo
		boolean primo=false;
		//Creamos una variable que va incrementando a medida que descartamos divisores.
		//Empieza en 2 porque nos saltamos el mismo y el 1
		int contador=2;
		int decremento=anterior;
		for(int i=anterior;i>=1;i--) {
			//Si el numero "maximo" es 1 o el anterior es 1 (Maximo=2) es Primo
			if(numero==1 || decremento==1) {
				primo=true;
				break;
			}
			//Si el numero de descartes es igual al numero objetivo, es primo 
			//(No hay divisores cuyo resto sea 0)
			if(contador==numero) {
				primo=true;
				break;
			}
			//Si el numero da de resto 0 ente la variable que va decrementando (pasando
			//por todos los divisores), el numero NO es primo
			if(numero%decremento==0) {
				primo=false;
				break;
			}else {
				contador++; //-> El contador suma una mas (mas cerca de ser el numero objetivo)
				decremento--; // -> Mas cerca de que sea 1 y se convierta el objetivo en primo
			}
		}
		if(primo) {
			System.out.println("El numero "+numero+" es Primo");
			sumaConcurrente+=numero;
			if(numero==2) { //-> Ultimo numero a verificar (fin de la suma)
				System.out.println("Suma Final: "+sumaConcurrente);
			}
		}else {
		}
		if(anterior!=0) {
			//Reducimos una unidad para seguir verificando los numeros
			verificarPrimoRecursivo((numero-1),(anterior-1),sumaConcurrente);
		}
	}
}
