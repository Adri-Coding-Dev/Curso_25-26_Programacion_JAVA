package ejercicios;
import java.util.*;
public class Ejercicio_2 {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un numero");
		int num=s.nextInt();
		boolean palindromo=esPalindromoNumero(num);
		System.out.println(palindromo);

	}

	private static boolean esPalindromoNumero(int num) {
		//Cantidad de Digitos
		int copia=num;//num = 1123 -> 1000+100+20+3 = 1123
		int recreacion=0,iteracion=0;
		while(recreacion!=num) {
			int resto=copia%10;
			recreacion+=(int)(Math.pow(10, iteracion)*resto);
			copia/=10;
			iteracion++;
		}
		int inverso=0;
		for(int i=iteracion;i>=0;i--) {
			int resto=recreacion%10;
			inverso+=(int)(Math.pow(10,iteracion-1)*resto);//1123 ->3000+200+10+1 =3211
			recreacion/=10;
			iteracion--;
		}
		if(num==inverso) {
			return true;
		}else {
			return false;
		}
		
	}

}
