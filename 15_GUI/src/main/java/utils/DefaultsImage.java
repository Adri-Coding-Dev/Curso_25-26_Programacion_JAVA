package utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

/**
 * Utilidad para la carga y escalado de imágenes en la interfaz gráfica.
 * Encapsula la lógica de búsqueda de imágenes por ID de conductor,
 * con fallback automático a una imagen por defecto.
 */
public class DefaultsImage {

    // ===== CONSTANTES =====

    /** Ruta base de las imágenes dentro del directorio de recursos. */
    private static final String RUTA_RECURSOS = "src/main/resources/images/";

    /** Nombre del archivo de imagen por defecto cuando no se encuentra la del conductor. */
    private static final String NOMBRE_IMAGEN_DEFAULT = "default.jpg";

    // ===== MÉTODOS PÚBLICOS =====

    /**
     * Escala una imagen desde una ruta absoluta al tamaño especificado.
     *
     * @param rutaAbsoluta Ruta absoluta al archivo de imagen
     * @param ancho        Ancho deseado en píxeles
     * @param alto         Alto deseado en píxeles
     * @return {@link ImageIcon} escalado, o {@code null} si la ruta es inválida
     */
    public static ImageIcon getResizeImage(String rutaAbsoluta, int ancho, int alto) {
        if (rutaAbsoluta == null || rutaAbsoluta.isBlank()) {
            return null;
        }

        Image imagenOriginal = new ImageIcon(rutaAbsoluta).getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }

    /**
     * Carga la imagen asociada al ID de un conductor.
     * Primero intenta cargar "{idConductor}.jpg" desde resources/images.
     * Si no existe, carga la imagen por defecto.
     *
     * @param idConductor Identificador del conductor (usado como nombre de archivo)
     * @param ancho       Ancho deseado en píxeles
     * @param alto        Alto deseado en píxeles
     * @return ImageIcon escalado con la imagen del conductor o la imagen por defecto
     */
    public static ImageIcon cargarImagenConductor(int idConductor, int ancho, int alto) {
        // Intentar cargar la imagen específica del conductor
        String nombreArchivo = "driver" + idConductor + ".jpg";
        String rutaEspecifica = RUTA_RECURSOS + nombreArchivo;

        File archivoConductor = new File(rutaEspecifica);
        if (archivoConductor.exists()) {
            return getResizeImage(rutaEspecifica, ancho, alto);
        }

        // Intentar también extensión .png por compatibilidad
        String rutaPng = RUTA_RECURSOS + "driver" + idConductor + ".png";
        File archivoPng = new File(rutaPng);
        if (archivoPng.exists()) {
            return getResizeImage(rutaPng, ancho, alto);
        }

        // Fallback: imagen por defecto
        return cargarImagenDefault(ancho, alto);
    }

    /**
     * Carga la imagen por defecto escalada al tamaño especificado.
     *
     * @param ancho Ancho deseado en píxeles
     * @param alto  Alto deseado en píxeles
     * @return {@link ImageIcon} de la imagen por defecto, o icono vacío si no se encuentra
     */
    public static ImageIcon cargarImagenDefault(int ancho, int alto) {
        String rutaDefault = RUTA_RECURSOS + NOMBRE_IMAGEN_DEFAULT;
        File archivoDefault = new File(rutaDefault);

        if (archivoDefault.exists()) {
            return getResizeImage(rutaDefault, ancho, alto);
        }

        // Si tampoco existe el default, devolvemos un icono en blanco como último recurso
        return new ImageIcon(new BufferedImagePlaceholder(ancho, alto).crear());
    }

    /**
     * Devuelve la ruta base de recursos de imágenes.
     */
    public static String getRutaRecursos() {
        return RUTA_RECURSOS;
    }

    // ===== CLASE AUXILIAR INTERNA =====

    /**
     * Genera un placeholder gris oscuro cuando no hay ninguna imagen disponible.
     */
    private static class BufferedImagePlaceholder {
        private final int ancho;
        private final int alto;

        BufferedImagePlaceholder(int ancho, int alto) {
            this.ancho = ancho;
            this.alto  = alto;
        }

        /** Crea una imagen gris oscura con texto indicativo. */
        Image crear() {
            java.awt.image.BufferedImage img =
                new java.awt.image.BufferedImage(ancho, alto, java.awt.image.BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = img.createGraphics();
            g2.setColor(Color.decode("#14141F"));
            g2.fillRect(0, 0, ancho, alto);
            g2.setColor(Color.decode("#2A3344"));
            g2.drawString("Sin imagen", ancho / 4, alto / 2);
            g2.dispose();
            return img;
        }
    }
}
