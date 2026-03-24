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
     * Metodo para averiguar el nombre de la BBDD con Expresiones regulares
     * @return -> Nombre de la BBDD
     */
    public static String sacarNombreBBDD(){
        ConfigDB cf = new ConfigDB();
        Pattern pattern = Pattern.compile("jdbc:mariadb://[^/]+/([^?;]+)"); // REGEX para sacar el nombre de la BBDD
        Matcher matcher = pattern.matcher(cf.getUrl());
        String nombreBBDD = null;
        if (matcher.find()) {
            nombreBBDD = matcher.group(1);
        }
        return nombreBBDD;
    }
}
