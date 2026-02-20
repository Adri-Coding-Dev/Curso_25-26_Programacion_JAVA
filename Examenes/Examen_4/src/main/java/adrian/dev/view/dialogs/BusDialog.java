package adrian.dev.view.dialogs;

import adrian.dev.model.Bus;
import adrian.dev.view.enums.BusDialogMode;

import javax.swing.*;
import java.awt.*;

public class BusDialog extends JDialog {

    private JTextField txtId;
    private JTextField txtTipo;
    private JTextField txtLicencia;

    private JButton btnConfirm;
    private JButton btnCancelar;

    private BusDialogMode mode;
    private Bus bus; // Resultado

    private final Color colorBotones = Color.decode("#42A5F5");

    public BusDialog(JFrame parent, BusDialogMode mode, Bus existingBus) {
        super(parent, true);
        this.mode = mode;

        initComponents();
        configureByMode();

        if (existingBus != null) {
            loadBusData(existingBus);
        }

        setLocationRelativeTo(parent);
    }
    private void initComponents() {
        setLayout(new BorderLayout(20, 20));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15)); //IMPORTANTE

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
                setTitle("Agregar Bus");
                btnConfirm.setText("Guardar");
                break;

            case EDIT:
                setTitle("Editar Bus");
                btnConfirm.setText("Actualizar");
                txtId.setEditable(false); // ID no editable
                break;

            case SEARCH:
                setTitle("Buscar Bus");
                btnConfirm.setText("Buscar");
                txtTipo.setEditable(false);
                txtLicencia.setEditable(false);
                break;
        }
    }
    private void handleAction() {
        switch (mode) {
            case CREATE,EDIT:
                saveOrUpdate();
                break;

            case SEARCH:
                performSearch();
                break;
        }
    }
    private void saveOrUpdate() {

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
    private void performSearch() {
        bus = new Bus(txtId.getText().trim(), null, null);
        dispose();
    }
    private void loadBusData(Bus existingBus) {
        txtId.setText(existingBus.getId_Bus());
        txtTipo.setText(existingBus.getTipo());
        txtLicencia.setText(existingBus.getLicencia());
    }
    private void cancelar() {
        bus = null;
        dispose();
    }

    public Bus getBus() {
        return bus;
    }
}
