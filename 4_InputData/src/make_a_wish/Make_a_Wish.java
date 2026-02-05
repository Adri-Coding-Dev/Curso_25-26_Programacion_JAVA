package make_a_wish;
import java.util.*;
public class Make_a_Wish {
	/*
		  ------------------------------------------------------------------
		 | 																	|
		 | 						Ejercicio_1									|
		 | 																	|
		  ------------------------------------------------------------------
		 *																	*
		 * -> Realiza un programa que pida al usuario 1 numero				*
		 * 																	*
		 * -> Muestra si ese numero es el mismo que se ha generado			*
		 *	aleatoriamente													*
		 * 																	*
		 -------------------------------------------------------------------- */

	public static void main(String[] args) {
		//Pedimos al usuario un numero, se genera un numero aleatorio y si coincide, mostrar mensaje
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un numero(1/10):");
		int numUsuario=s.nextInt();
		
		//Generamos un numero aleatorio
		int max=10,min=1;
		int numero_Random=(int)Math.round(Math.random()*(max-min)+1);
		if(numero_Random==numUsuario) {
			System.out.println("Pide un Deseo");
		}else {
			System.out.println("Esta vez no has tenido suerte...");
		}
		s.close();
	}

}
