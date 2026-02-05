package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import excepciones.FechaNoValidaException;
import excepciones.FechaPasada;

public class Utils {

    /**
     * Parseo flexible de fecha con múltiples formatos
     * 
     * @return -> La fecha del evento
     * @throws FechaNoValidaException -> En caso de que los eventos no tengan fecha
     *                                valida
     */
    public static LocalDateTime parsearFecha(Scanner s) throws FechaNoValidaException {

        String input = s.nextLine();
        DateTimeFormatter[] formatos = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")
        };

        for (DateTimeFormatter fmt : formatos) {
            try {
                LocalDateTime fecha = LocalDateTime.parse(input, fmt);
                if (fecha.isBefore(LocalDateTime.now())) {
                    throw new FechaPasada();
                }
                return fecha;
            } catch (DateTimeParseException e) {
                // probar siguiente formato
            }
        }
        throw new FechaNoValidaException("Formato de fecha no válido. Ejemplo: yyyy-MM-dd HH:mm o dd/MM/yyyy HH:mm");
    }
}
