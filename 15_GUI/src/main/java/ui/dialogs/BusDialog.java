package ui.dialogs;

import model.Bus;

import javax.swing.*;
import java.awt.*;

public class BusDialog extends JDialog {

    private JTextField txtId;
    private JTextField txtTipo;
    private JTextField txtLicencia;

    private Bus bus; // Resultado del diálogo

    public BusDialog(Frame parent) {
        super(parent, "Nuevo Bus", true);
        initComponents();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // ===== Panel de campos =====
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        formPanel.add(new JLabel("ID Bus:"));
        txtId = new JTextField();
        formPanel.add(txtId);

        formPanel.add(new JLabel("Tipo:"));
        txtTipo = new JTextField();
        formPanel.add(txtTipo);

        formPanel.add(new JLabel("Licencia:"));
        txtLicencia = new JTextField();
        formPanel.add(txtLicencia);

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
                || txtTipo.getText().isBlank()
                || txtLicencia.getText().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Todos los campos son obligatorios",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        bus = new Bus(
                txtId.getText().trim(),
                txtTipo.getText().trim(),
                txtLicencia.getText().trim()
        );

        dispose();
    }

    private void cancelar() {
        bus = null;
        dispose();
    }

    public Bus getBus() {
        return bus;
    }
}
