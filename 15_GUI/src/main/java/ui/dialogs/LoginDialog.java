package ui.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog {

    private boolean authenticated = false; // Resultado del login
    private JTextField tfUsuario;
    private JPasswordField pfPassword;

    // Colores coherentes con PrincipalView
    private final Color colorFondo = Color.decode("#E3F2FD");
    private final Color colorBotones = Color.decode("#42A5F5");
    private final Color colorTextBoton = Color.WHITE;

    public LoginDialog(Frame parent) {
        super(parent, "Login", true); // Modal
        setSize(350, 200);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getContentPane().setBackground(colorFondo);

        initComponents();
    }

    private void initComponents() {
        // ===== PANEL CENTRAL =====
        JPanel center = new JPanel(new GridLayout(2, 2, 10, 10));
        center.setBackground(colorFondo);
        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        center.add(new JLabel("Usuario:"));
        tfUsuario = new JTextField();
        center.add(tfUsuario);

        center.add(new JLabel("Contraseña:"));
        pfPassword = new JPasswordField();
        center.add(pfPassword);

        add(center, BorderLayout.CENTER);

        // ===== PANEL INFERIOR BOTONES =====
        JPanel bot = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bot.setBackground(colorFondo);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(colorBotones);
        btnEntrar.setForeground(colorTextBoton);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(colorBotones);
        btnCancelar.setForeground(colorTextBoton);

        bot.add(btnEntrar);
        bot.add(btnCancelar);
        add(bot, BorderLayout.SOUTH);

        // ===== EVENTOS =====
        btnEntrar.addActionListener(e -> validarLogin());
        btnCancelar.addActionListener(e -> {
            authenticated = false;
            dispose();
        });
    }

    // ===== MÉTODO DE VALIDACIÓN =====
    private void validarLogin() {
        String usuario = tfUsuario.getText().trim();
        String password = new String(pfPassword.getPassword()).trim();

        // Aquí podemos usar BBDD real, pero para ejemplo usamos hardcode
        if (usuario.equals("test") && password.equals("test")) {
            authenticated = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Usuario o contraseña incorrectos",
                    "Error de login",
                    JOptionPane.ERROR_MESSAGE
            );
            tfUsuario.setText("");
            pfPassword.setText("");
        }
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
