package adrian.dev.app.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Utils {
    /**
     * Metodo para formatear la fecha
     * @return -> Fecha formateada como la pide en el ejercicio
     */
    public static String mostrarFechaFormateada(){
        LocalDateTime fechaActual = LocalDateTime.now();
        String dia = LocalDate.now().toString(); //Convertimos a String
        String minutos = String.valueOf(fechaActual.getMinute());
        if(String.valueOf(fechaActual.getMinute()).length() < 2){
            minutos = 0 + String.valueOf(fechaActual.getMinute());
        }
        String hora = String.valueOf(fechaActual.getHour() + " : " + minutos); //Obtenemos la hora y los minutos
        return "Hoy es " + dia + " y son las " + hora; //Devolvemos la fecha

    }
}
