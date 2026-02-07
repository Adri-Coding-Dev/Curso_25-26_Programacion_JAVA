package ui;

import utils.DefaultsImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGUI extends JFrame {

    private static final Color MORADO = new Color(132, 0, 255);
    private static final Color HOVER = new Color(49, 3, 85);
    private static final Color TEXTO = new Color(130, 0, 255);

    public MainGUI() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Mi GUI Personalizada");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        setIconImage(new ImageIcon("/home/Adrian/Imágenes/YT/Foto_Perfil.png").getImage());
        add(upBar(), BorderLayout.NORTH);
        add(sideBar(new String[]{"Bus", "Conductor", "Lugar"}), BorderLayout.WEST);
        add(contentPanel(), BorderLayout.CENTER);
    }

    /* --------------------- BARRA SUPERIOR --------------------- */

    private JPanel upBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(0, 45));

        JLabel icon = new JLabel(
                DefaultsImage.getResizeImage("/home/Adrian/Imágenes/YT/Foto_Perfil.png", 25, 25)
        );

        JLabel title = new JLabel("Mi Ventana en Swing");

        panel.add(icon);
        panel.add(title);

        return panel;
    }

    /* --------------------- SIDEBAR --------------------- */

    private JPanel sideBar(String[] values) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(MORADO);
        panel.setPreferredSize(new Dimension(300, 0));

        panel.add(Box.createVerticalStrut(10));

        for (String value : values) {
            panel.add(menuItem(value));
            panel.add(Box.createVerticalStrut(5));
        }

        return panel;
    }

    private JPanel menuItem(String text) {
        JPanel item = new JPanel(new BorderLayout());
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        item.setBackground(Color.WHITE);

        JLabel label = new JLabel("    " + text);
        label.setForeground(TEXTO);
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 13f));

        item.add(label, BorderLayout.CENTER);

        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                item.setBackground(HOVER);
                label.setForeground(Color.WHITE);
                item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                item.setBackground(Color.WHITE);
                label.setForeground(TEXTO);
                item.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click en: " + text);
            }
        });

        return item;
    }

    /* --------------------- PANEL CENTRAL --------------------- */

    private JPanel contentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 122, 255));

        JLabel placeholder = new JLabel("Contenido principal", SwingConstants.CENTER);
        placeholder.setForeground(Color.WHITE);
        placeholder.setFont(placeholder.getFont().deriveFont(Font.BOLD, 18f));

        panel.add(placeholder, BorderLayout.CENTER);

        return panel;
    }

    /* --------------------- MAIN --------------------- */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }
}
