package ui.dialogs;

import model.Conductor;
import model.ConductorDialogMode;

import javax.swing.*;
import java.awt.*;

public class ConductorDialog extends JDialog {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;

    private JButton btnConfirm;
    private JButton btnCancelar;

    private ConductorDialogMode mode;
    private Conductor conductor; // Resultado

    private final Color colorBotones = Color.decode("#42A5F5");

    public ConductorDialog(Frame parent, ConductorDialogMode mode, Conductor existing) {
        super(parent, true);
        this.mode = mode;

        initComponents();
        configureByMode();

        if (existing != null) {
            loadConductorData(existing);
        }

        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout(20, 20));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnConfirm = new JButton();
        btnConfirm.setBackground(colorBotones);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(colorBotones);

        buttonPanel.add(btnConfirm);
        buttonPanel.add(btnCancelar);

        add(buttonPanel, BorderLayout.SOUTH);

        btnConfirm.addActionListener(e -> handleAction());
        btnCancelar.addActionListener(e -> cancelar());

        pack();
    }

    private void configureByMode() {
        switch (mode) {
            case CREATE:
                setTitle("Agregar Conductor");
                btnConfirm.setText("Guardar");
                break;
            case EDIT:
                setTitle("Editar Conductor");
                btnConfirm.setText("Actualizar");
                txtId.setEditable(false);
                break;
            case SEARCH:
                setTitle("Buscar Conductor");
                btnConfirm.setText("Buscar");
                txtNombre.setEditable(false);
                txtApellido.setEditable(false);
                break;
        }
    }

    private void handleAction() {
        switch (mode) {
            case CREATE:
            case EDIT:
                saveOrUpdate();
                break;
            case SEARCH:
                performSearch();
                break;
        }
    }

    private void saveOrUpdate() {
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

        try {
            int id = Integer.parseInt(txtId.getText().trim());
            conductor = new Conductor(
                    id,
                    txtNombre.getText().trim(),
                    txtApellido.getText().trim()
            );
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "El ID debe ser un número entero",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performSearch() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            conductor = new Conductor(id, null, null);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "El ID debe ser un número entero",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadConductorData(Conductor existing) {
        txtId.setText(String.valueOf(existing.getId_C()));
        txtNombre.setText(existing.getNombre());
        txtApellido.setText(existing.getApellido());
    }

    private void cancelar() {
        conductor = null;
        dispose();
    }

    public Conductor getConductor() {
        return conductor;
    }
}