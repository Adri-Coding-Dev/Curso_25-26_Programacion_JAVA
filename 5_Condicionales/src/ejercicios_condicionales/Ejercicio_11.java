package ejercicios_condicionales;
import java.util.*;
public class Ejercicio_11 {

	public static void main(String[] args) {
		/*Realiza un programa que resuelva lo siguientes puntos:
			1. Cálculo de la hipotenusa de un triángulo.
			2. Cálculo de la superficie de una circunferencia.
			3. Cálculo del perímetro de una circunferencia.
			4. Cálculo del área de un rectángulo.
			5. Cálculo del área de un triángulo.*/
		Scanner s=new Scanner(System.in);
		mostrarOpciones();
		int opcion=s.nextInt();
		switch(opcion) {
		case 1:
			System.out.println("Altura:");
			double altura=s.nextDouble();
			System.out.println("Base:");
			double base=s.nextDouble();
			calcular_Hipotenusa(base,altura);
			break;
		case 2:
			System.out.println("Radio");
			double radio=s.nextDouble();
			calcular_Superficie_Circ(radio);
			break;
		case 3:
			System.out.println("Longitud");
			double longitud=s.nextDouble();
			calcular_Perimetro_Circ(longitud);
			break;
		case 4:
			System.out.println("Base");
			double base_Cua=s.nextDouble();
			System.out.println("Altura");
			double altura_Cua=s.nextDouble();
			calcular_Area_Cua(base_Cua,altura_Cua);
			break;
		case 5:
			System.out.println("Base");
			double base_Tria=s.nextDouble();
			System.out.println("Altura");
			double altura_Tria=s.nextDouble();
			calcular_Area_Tria(base_Tria,altura_Tria);
			break;
		default:
			System.out.println("Opcion no válida");
		}
		
		s.close();
	}

	
	
	
	
	private static void calcular_Area_Tria(double base_Tria, double altura_Tria) {
		double area_Tria=(base_Tria*altura_Tria)/2;
		System.out.println("Area: "+area_Tria);
		
	}
	private static void calcular_Area_Cua(double base_Cua, double altura_Cua) {
		double area_Cua=(base_Cua*altura_Cua);
		System.out.println("Area: "+area_Cua);
		
	}
	private static void calcular_Perimetro_Circ(double longitud) {
		double radio_long=(longitud/2);
		double PI_2=Math.PI;
		double perimetro_Circ=(2*PI_2*radio_long);
		System.out.println("Perimetro: "+perimetro_Circ);
		
	}

	private static void calcular_Superficie_Circ(double radio) {
		double PI=Math.PI;
		double superficie_Circ=(Math.pow(radio, 2))*PI;
		System.out.println("Superficie: "+superficie_Circ);
		
	}

	private static void calcular_Hipotenusa(double base, double altura) {
		double hipotenusa=((Math.pow(altura, 2))+(Math.pow(base, 2)));
		System.out.println("Hipotenusa: "+hipotenusa);
	}

	private static void mostrarOpciones() {
		System.out.println("1. Cálculo de la hipotenusa de un triángulo.");
		System.out.println("2. Cálculo de la superficie de una circunferencia.");
		System.out.println("3. Cálculo del perímetro de una circunferencia");
		System.out.println("4. Cálculo del área de un rectángulo.");
		System.out.println("5. Cálculo del área de un triángulo.");
		
	}

}
