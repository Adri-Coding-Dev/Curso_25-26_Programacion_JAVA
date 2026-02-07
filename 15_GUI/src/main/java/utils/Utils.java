package utils;

import connection.ConfigDB;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase Utils para metodos necesarios en el programa
 */
public class Utils {

    /**
     * Metodo para mostrar el menu
     * @return -> Opcion del usuario
     */
    public static int menu(){
        Scanner s = new Scanner(System.in);

        System.out.println("=".repeat(100));
        System.out.println("OPCIONES");
        System.out.println("=".repeat(100));

        System.out.println("0.- Salir");
        System.out.println("1.- Mostrar Todos los Buses  | " + "6.- Mostrar Todos los Conductores    | " + "11.- Mostrar Todos los Lugares");
        System.out.println("2.- Insertar un Bus          | " + "7.- Insertar un Conductor            | " + "12.- Insertar un Lugar");
        System.out.println("3.- Borrar un Bus (ID)       | " + "8.- Borrar un Conductor (ID)         | " + "13.- Borrar un Lugar (ID)");
        System.out.println("4.- Modificar un Bus (ID)    | " + "9.- Modificar un Conductor (ID)      | " + "14.- Modificar un Lugar (ID)");
        System.out.println("5.- Encontrar un Bus (ID)    | " + "10.- Encontrar un Conductor (ID)     | " + "15.- Encontrar un Lugar (ID)");
        System.out.println("=".repeat(100));

        return s.nextInt();
    }

    /**
     * Metodo para averiguar el nombre de la BBDD con Expresiones regulares
     * @return -> Nombre de la BBDD
     */
    public static String sacarNombreBBDD(){
        ConfigDB cf = new ConfigDB();
        Pattern pattern = Pattern.compile("jdbc:mysql://[^/]+/([^?;]+)"); // REGEX para sacar el nombre de la BBDD
        Matcher matcher = pattern.matcher(cf.getUrl());
        String nombreBBDD = null;
        if (matcher.find()) {
            nombreBBDD = matcher.group(1);
        }
        return nombreBBDD;
    }

    /**
     * Metodo para pedir un String al usuario
     * @param msg -> Mensaje de peticion
     * @return -> Cadena que el usuario ha introducido
     */
    public static String pedirCadenaAlUsuario(String msg){
        System.out.println(msg);
        Scanner s = new Scanner(System.in);
        return s.next();
    }

    /**
     * Metodo para pedir un numero al usuario
     * @param msg -> Mensaje de peticion
     * @return -> Numero que el usuario ha introducido
     */
    public static int pedirNumeroAlUsuario(String msg){
        System.out.println(msg);
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }

    /**
     * Metodo para presentar una opcion
     * @param titulo -> Titulo u opcion que el usuario tiene que ver
     */
    public static void mostrarOpcionElegida(String titulo){
        System.out.println("=".repeat(100));
        System.out.println(titulo);
        System.out.println("=".repeat(100));
    }
}
