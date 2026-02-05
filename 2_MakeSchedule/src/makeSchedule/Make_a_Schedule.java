package makeSchedule;

public class Make_a_Schedule {
	public static final String RESET = "\u001B[0m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String MORADO = "\u001B[35m";
    public static final String GRIS = "\u001B[37m";

	public static void main(String[] args) {
		/*
		  ------------------------------------------------------------------
		 | 																	|
		 | 						Ejercicio_1									|
		 | 																	|
		  ------------------------------------------------------------------
		 *																	*
		 * -> Realiza un programa que Mueste el horario con colores		    *
		 * 																	*
		 -------------------------------------------------------------------- */
		//Declaramos las asignaturas con sus respectivos colores
		String asign_Programacion=VERDE+"PROG"+RESET;
		String asign_Entornos=AZUL+"ED"+RESET;
		String asign_FOL=AMARILLO+"FOL"+RESET;
		String asign_Sistemas=ROJO+"SIN"+RESET;
		String asign_Base_Datos=MORADO+"BDATO"+RESET;
		String asign_Lenguaje_Marcas=GRIS+"LM"+RESET;
		
		//Declaramos los dias de la semana y la separacion (========)
		String lunes=AMARILLO+"Monday"+RESET;
		String martes=AMARILLO+"Tuesday"+RESET;
		String miercoles=AMARILLO+"Wedne."+RESET;
		String jueves=AMARILLO+"Thurs."+RESET;
		String viernes=AMARILLO+"Friday"+RESET;
		String separacion=AMARILLO+"======"+RESET;
		
		//Creamos el horario usando println
		//DIAS SEMANA
		System.out.println(lunes+" "+martes+" "+miercoles+" "+jueves+" "+viernes);
		System.out.println(separacion+" "+separacion+" "+separacion+" "+separacion+" "+separacion);
		//PRIMERA HORA
		System.out.println(asign_Programacion+"   "+asign_Programacion+"   "+asign_Programacion+"   "+asign_Programacion+"   "+asign_Sistemas);
		//SEGUNDA HORA
		System.out.println(asign_Programacion+"   "+asign_Programacion+"   "+asign_Programacion+"   "+asign_Programacion+"   "+asign_Sistemas);
		//TERCERA HORA
		System.out.println(asign_Entornos+"     "+asign_Sistemas+"    "+asign_Sistemas+"    "+asign_Lenguaje_Marcas+"     "+asign_Base_Datos);
		//CUARTA HORA
		System.out.println(asign_FOL+"    "+asign_Sistemas+"    "+asign_Sistemas+"    "+asign_Lenguaje_Marcas+"     "+asign_Base_Datos);
		//QUINTA HORA
		System.out.println(asign_FOL+"    "+asign_Base_Datos+"  "+asign_Entornos+"     "+asign_Base_Datos+"  "+asign_Entornos);
		//SEXTA HORA
		System.out.println(asign_FOL+"    "+asign_Base_Datos+"  "+asign_Entornos+"     "+asign_Base_Datos+"  "+asign_Entornos);
	}

}
