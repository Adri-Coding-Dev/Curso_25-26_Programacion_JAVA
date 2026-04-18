package utils;

import connection.ConfigDB;
import exceptions.EstadoExcepcion;

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

    public static String mostrarCodigoEstado(EstadoExcepcion codigoEstado){
        switch (codigoEstado){
            case FIND_ERROR:
                return "ERROR 104: Registro no Encontrado. Comunicalo con el Administrador de la aplicacion";

            case CONECTING_ERROR:
                return "ERROR 100: La BBDD No esta conectada. Por favor conectela correctamente";

            case DELETE_ERROR:
                return "ERROR 103: No se puede borrar la BBDD correctamente";

            case INSERT_ERROR:
                return "ERROR 102: No se puede Insertar el registro debido a un error en la Consulta";
        }
        return null;
    }
}
