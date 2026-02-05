package rookie_exercises;

public class Ejercicio_3 {

	public static void main(String[] args) {
		/*- Crea un ejercicio que imprima en pantalla 
		 *todas las tablas de multiplicar desde el 1 hasta 
		 *el 15. Debes utilizar únicamente bucles de tipo "do...while
		 * */
		int limite=0;
		do {
			System.out.println("Tabla del "+limite);
			for(int i=0;i<=10;i++) {
				System.out.println(limite+"*"+i+": "+(limite*i));
			}
			limite++;
		}while(limite<=15);
	}
}
