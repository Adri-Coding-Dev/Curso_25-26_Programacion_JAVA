package ui.dialogs;

import model.Bus;
import model.Conductor;

import javax.swing.*;
import java.awt.*;

public class ConductorDialog extends JDialog {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;

    private Conductor cond; // Resultado del diálogo

    public ConductorDialog(Frame parent) {
        super(parent, "Nuevo Conductor", true);
        initComponents();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // ===== Panel de campos =====
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        formPanel.add(new JLabel("ID Conductor:"));
        txtId = new JTextField();
        formPanel.add(txtId);

        formPanel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        formPanel.add(txtNombre);

        formPanel.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        formPanel.add(txtApellido);

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
                || txtNombre.getText().isBlank()
                || txtApellido.getText().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Todos los campos son obligatorios",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        cond = new Conductor(
                Integer.parseInt(txtId.getText().trim()),
                txtNombre.getText().trim(),
                txtApellido.getText().trim()
        );

        dispose();
    }

    private void cancelar() {
        cond = null;
        dispose();
    }

    public Conductor getConductor() {
        return cond;
    }
}
