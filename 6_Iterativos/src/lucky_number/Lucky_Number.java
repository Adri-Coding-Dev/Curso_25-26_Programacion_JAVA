package lucky_number;
import java.util.*;

public class Lucky_Number {

	public static void main(String[] args) {
		/*Dado un numero de varias cifras, comentar si el numero es un numero de la suerte
		 * (cantidad de numeros de la suerte > cantidad de numero de no suerte)
		 * 
		 * Numeros de la suerte -> 3,7,8,9
		 * 
		 * Numeros de la no suerte -> 0,1,2,4,5,6
		 * */
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un numero");
		Long numUsuario=s.nextLong();
		System.out.println("==============Metodo 1==============");
		luckyNumber_Metodo1(numUsuario);
		System.out.println("==============Metodo 2==============");
		luckyNumber_Metodo2(numUsuario);
	}

	private static void luckyNumber_Metodo2(Long numUsuario) {
		//Solo con numeros enteros
		long copia=numUsuario;
		long resto=0;
		long recreacion=0;
		int iteracion=0;
		int lucky_conter=0,notLucky_conter=0;
		boolean salir=false;
		while(!salir) {
			if(recreacion==numUsuario) {
				salir=true;
				break;
			}else {
				resto=copia%10;
				copia/=10;
				recreacion+=(int)(Math.pow(10, iteracion)*resto);
				int cambioResto=(int)resto;
				switch(cambioResto) {
				case 3,7,8,9:
					lucky_conter++;
				break;
				case 0,1,2,4,5,6:
					notLucky_conter++;
				break;
				}
				iteracion++;
			}
		}
		if(lucky_conter>notLucky_conter) {
			System.out.println("Tu numero es un numero de la suerte");
		}else if(lucky_conter<notLucky_conter){
			System.out.println("Tu numero NO es un numero de la suerte");
		}else {
			System.out.println("No se si tu numero es de la suerte o no...");
		}
	}

	private static void luckyNumber_Metodo1(Long numUsuario) {
		//Cambiando a String
		int lucky_conter=0,notLucky_conter=0;
		String cadena=numUsuario.toString();
		for(int i=0;i<cadena.length();i++) {
			char caracter=cadena.charAt(i);
			switch(caracter) {
			case '3','7','8','9':
				lucky_conter++;
			break;
			default:
				notLucky_conter++;
			break;
			}
		}
		if(lucky_conter>notLucky_conter) {
			System.out.println("Tu numero es un numero de la suerte");
		}else if(lucky_conter<notLucky_conter){
			System.out.println("Tu numero NO es un numero de la suerte");
		}else {
			System.out.println("No se si tu numero es de la suerte o no...");
		}
		
	}

}
