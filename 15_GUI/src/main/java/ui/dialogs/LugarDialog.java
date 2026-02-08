package ui.dialogs;

import model.Bus;
import model.Conductor;
import model.Lugar;

import javax.swing.*;
import java.awt.*;

public class LugarDialog extends JDialog {

    private JTextField txtId;
    private JTextField txtCod;
    private JTextField txtCiudad;
    private JTextField txtUbicacion;

    private Lugar lugar; // Resultado del diálogo

    public LugarDialog(Frame parent) {
        super(parent, "Nuevo Lugar", true);
        initComponents();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // ===== Panel de campos =====
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        formPanel.add(new JLabel("ID Lugar:"));
        txtId = new JTextField();
        formPanel.add(txtId);

        formPanel.add(new JLabel("Codigo Postal:"));
        txtCod = new JTextField();
        formPanel.add(txtCod);

        formPanel.add(new JLabel("Ciudad:"));
        txtCiudad = new JTextField();
        formPanel.add(txtCiudad);

        formPanel.add(new JLabel("Ubicacion:"));
        txtUbicacion = new JTextField();
        formPanel.add(txtUbicacion);

        add(formPanel, BorderLayout.CENTER);

        // ===== Panel de botones =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);

        add(buttonPanel, BorderLayout.SOUTH);

        // ===== Eventos =====
        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> cancelar());

        pack();
    }

    private void guardar() {
        if (txtId.getText().isBlank()
                || txtCod.getText().isBlank()
                || txtCiudad.getText().isBlank()
                || txtUbicacion.getText().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Todos los campos son obligatorios",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        lugar = new Lugar(
                Integer.parseInt(txtId.getText().trim()),
                txtCod.getText().trim(),
                txtCiudad.getText().trim(),
                txtUbicacion.getText().trim()
        );

        dispose();
    }

    private void cancelar() {
        lugar = null;
        dispose();
    }

    public Lugar getLugar() {
        return lugar;
    }
}
