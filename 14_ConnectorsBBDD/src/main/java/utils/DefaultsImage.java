package utils;

import javax.swing.*;
import java.awt.*;

/**
 * Clase para poder escalar la imagen de la Interfaz grafica
 */
public class DefaultsImage {

    /**
     * Metodo para escalar la imagen con proporciones
     * @param path -> Ruta a la imagen
     * @param width -> Anchura de la imagen
     * @param height -> Altura de la imagen
     * @return -> Imagen escalada
     */
    public static ImageIcon getResizeImage(String path, int width, int height){
        ImageIcon imgI = new ImageIcon(
            new ImageIcon(path).getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH)
        );

        return imgI;
    }
}
