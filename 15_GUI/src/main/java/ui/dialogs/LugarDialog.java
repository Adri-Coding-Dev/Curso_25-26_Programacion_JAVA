package ui.dialogs;

import model.Lugar;
import model.LugarDialogMode;

import javax.swing.*;
import java.awt.*;

public class LugarDialog extends JDialog {

    private JTextField txtId;
    private JTextField txtCod;
    private JTextField txtCiudad;
    private JTextField txtUbicacion;

    private JButton btnConfirm;
    private JButton btnCancelar;

    private LugarDialogMode mode;
    private Lugar lugar; // Resultado

    private final Color colorBotones = Color.decode("#42A5F5");

    public LugarDialog(Frame parent, LugarDialogMode mode, Lugar existing) {
        super(parent, true);
        this.mode = mode;

        initComponents();
        configureByMode();

        if (existing != null) {
            loadLugarData(existing);
        }

        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout(20, 20));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("ID Lugar:"));
        txtId = new JTextField();
        formPanel.add(txtId);

        formPanel.add(new JLabel("Código Postal:"));
        txtCod = new JTextField();
        formPanel.add(txtCod);

        formPanel.add(new JLabel("Ciudad:"));
        txtCiudad = new JTextField();
        formPanel.add(txtCiudad);

        formPanel.add(new JLabel("Ubicación:"));
        txtUbicacion = new JTextField();
        formPanel.add(txtUbicacion);

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
                setTitle("Agregar Lugar");
                btnConfirm.setText("Guardar");
                break;
            case EDIT:
                setTitle("Editar Lugar");
                btnConfirm.setText("Actualizar");
                txtId.setEditable(false);
                break;
            case SEARCH:
                setTitle("Buscar Lugar");
                btnConfirm.setText("Buscar");
                txtCod.setEditable(false);
                txtCiudad.setEditable(false);
                txtUbicacion.setEditable(false);
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

        try {
            int id = Integer.parseInt(txtId.getText().trim());
            lugar = new Lugar(
                    id,
                    txtCod.getText().trim(),
                    txtCiudad.getText().trim(),
                    txtUbicacion.getText().trim()
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
            lugar = new Lugar(id, null, null, null);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "El ID debe ser un número entero",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadLugarData(Lugar existing) {
        txtId.setText(String.valueOf(existing.getId_L()));
        txtCod.setText(existing.getCod_Post());
        txtCiudad.setText(existing.getCiudad());
        txtUbicacion.setText(existing.getUbicacion());
    }

    private void cancelar() {
        lugar = null;
        dispose();
    }

    public Lugar getLugar() {
        return lugar;
    }
}