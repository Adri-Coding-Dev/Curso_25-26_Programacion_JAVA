package ui.dialogs;

import model.Conductor;
import model.ConductorDialogMode;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Diálogo genérico para operaciones CRUD sobre un {@link Conductor}.
 *
 * <p>Soporta tres modos de operación definidos por {@link ConductorDialogMode}:
 * <ul>
 *   <li>{@code CREATE} — Permite introducir todos los campos para crear un conductor nuevo.</li>
 *   <li>{@code EDIT}   — Permite modificar nombre y apellido; el ID no es editable.</li>
 *   <li>{@code SEARCH} — Solo permite introducir el ID para buscar un conductor.</li>
 * </ul>
 *
 * <p>El conductor resultante se obtiene mediante {@link #getConductor()}.
 * Si el usuario cancela, el resultado es {@code null}.
 */
public class ConductorDialog extends JDialog {

    // ===== PALETA (coherente con PrincipalView) =====

    private static final Color COLOR_FONDO      = Color.decode("#0B0B12");
    private static final Color COLOR_SUPERFICIE  = Color.decode("#14141F");
    private static final Color COLOR_BOTONES     = Color.decode("#1E4A8C");
    private static final Color COLOR_TEXTO       = Color.decode("#F0F4FA");
    private static final Color COLOR_TEXTO_BOTON = Color.decode("#A0B3CC");
    private static final Color COLOR_BORDE       = Color.decode("#2A3344");
    private static final Color COLOR_CAMPO       = Color.decode("#1A1A2E");

    // ===== CAMPOS DE FORMULARIO =====

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;

    // ===== BOTONES =====

    private JButton btnConfirmar;
    private JButton btnCancelar;

    // ===== ESTADO =====

    /** Modo de operación actual del diálogo. */
    private final ConductorDialogMode mode;

    /** Conductor resultante de la acción; {@code null} si el usuario cancela. */
    private Conductor conductor;

    // ===== CONSTRUCTOR =====

    /**
     * Crea el diálogo en el modo indicado, opcionalmente con datos previos del conductor.
     *
     * @param padre    Ventana padre
     * @param mode     Modo de operación (CREATE, EDIT, SEARCH)
     * @param existing Conductor con datos a precargar, o {@code null} si no aplica
     */
    public ConductorDialog(Frame padre, ConductorDialogMode mode, Conductor existing) {
        super(padre, true);
        this.mode = mode;

        initComponentes();
        configurarSegunModo();

        // Precargar datos si se proporciona un conductor existente
        if (existing != null) {
            cargarDatosConductor(existing);
        }

        setLocationRelativeTo(padre);
    }

    // ===== CONSTRUCCIÓN DE LA INTERFAZ =====

    /** Construye y posiciona todos los componentes del formulario. */
    private void initComponentes() {
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new BorderLayout(20, 20));

        add(crearPanelFormulario(), BorderLayout.CENTER);
        add(crearPanelBotones(), BorderLayout.SOUTH);

        pack();
    }

    /**
     * Crea el panel de formulario con los tres campos de entrada.
     */
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(new EmptyBorder(20, 20, 10, 20));

        // Campo: ID del conductor
        panel.add(crearEtiqueta("ID Conductor:"));
        txtId = crearCampoTexto();
        panel.add(txtId);

        // Campo: Nombre
        panel.add(crearEtiqueta("Nombre:"));
        txtNombre = crearCampoTexto();
        panel.add(txtNombre);

        // Campo: Apellido
        panel.add(crearEtiqueta("Apellido:"));
        txtApellido = crearCampoTexto();
        panel.add(txtApellido);

        return panel;
    }

    /**
     * Crea el panel inferior con los botones de confirmación y cancelación.
     */
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(new EmptyBorder(0, 20, 10, 20));

        btnConfirmar = crearBoton("");  // El texto se establece en configurarSegunModo()
        btnCancelar  = crearBoton("Cancelar");

        btnConfirmar.addActionListener(e -> manejarAccion());
        btnCancelar.addActionListener(e  -> cancelar());

        panel.add(btnConfirmar);
        panel.add(btnCancelar);

        return panel;
    }

    // ===== CONFIGURACIÓN POR MODO =====

    /** Adapta el título, botón y campos editables según el modo de operación. */
    private void configurarSegunModo() {
        switch (mode) {
            case CREATE -> {
                setTitle("Agregar Conductor");
                btnConfirmar.setText("Guardar");
                // Todos los campos son editables en creación
            }
            case EDIT -> {
                setTitle("Editar Conductor");
                btnConfirmar.setText("Actualizar");
                txtId.setEditable(false); // El ID no se puede cambiar en edición normal
                txtId.setBackground(COLOR_CAMPO.darker());
            }
            case SEARCH -> {
                setTitle("Buscar Conductor");
                btnConfirmar.setText("Buscar");
                txtNombre.setEditable(false);   // Solo se busca por ID
                txtApellido.setEditable(false);
                txtNombre.setBackground(COLOR_CAMPO.darker());
                txtApellido.setBackground(COLOR_CAMPO.darker());
            }
        }
    }

    // ===== LÓGICA DE ACCIONES =====

    /** Delega la acción según el modo activo del diálogo. */
    private void manejarAccion() {
        switch (mode) {
            case CREATE, EDIT -> guardarOActualizar();
            case SEARCH       -> realizarBusqueda();
        }
    }

    /**
     * Valida los campos y construye el objeto {@link Conductor} resultante para CREATE/EDIT.
     * Si la validación falla, muestra un mensaje de error al usuario.
     */
    private void guardarOActualizar() {
        // Validar que todos los campos están rellenos
        if (txtId.getText().isBlank() || txtNombre.getText().isBlank() || txtApellido.getText().isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios.",
                    "Campos requeridos",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que el ID sea un número entero
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            conductor = new Conductor(id, txtNombre.getText().trim(), txtApellido.getText().trim());
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "El ID debe ser un número entero.",
                    "ID inválido",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Construye el objeto {@link Conductor} de búsqueda con solo el ID.
     * El nombre y apellido quedan como {@code null}.
     */
    private void realizarBusqueda() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            conductor = new Conductor(id, null, null);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "El ID debe ser un número entero.",
                    "ID inválido",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Precarga los campos del formulario con los datos de un conductor existente. */
    private void cargarDatosConductor(Conductor existente) {
        txtId.setText(String.valueOf(existente.getId_C()));
        txtNombre.setText(existente.getNombre());
        txtApellido.setText(existente.getApellido());
    }

    /** Cancela la operación y cierra el diálogo sin resultado. */
    private void cancelar() {
        conductor = null;
        dispose();
    }

    // ===== RESULTADO =====

    /**
     * Devuelve el conductor resultante de la operación.
     *
     * @return Conductor creado/editado/buscado, o {@code null} si se canceló
     */
    public Conductor getConductor() {
        return conductor;
    }

    // ===== FÁBRICA DE COMPONENTES =====

    /** Crea una etiqueta estilizada con la paleta del sistema. */
    private JLabel crearEtiqueta(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(COLOR_TEXTO);
        return lbl;
    }

    /** Crea un campo de texto estilizado con la paleta del sistema. */
    private JTextField crearCampoTexto() {
        JTextField txt = new JTextField();
        txt.setBackground(COLOR_CAMPO);
        txt.setForeground(COLOR_TEXTO);
        txt.setCaretColor(COLOR_TEXTO);
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE),
                new EmptyBorder(3, 6, 3, 6)
        ));
        return txt;
    }

    /** Crea un botón estilizado con la paleta del sistema. */
    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(COLOR_BOTONES);
        btn.setForeground(COLOR_TEXTO_BOTON);
        btn.setFocusPainted(false);
        btn.setFont(btn.getFont().deriveFont(Font.BOLD));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE),
                new EmptyBorder(6, 14, 6, 14)
        ));
        return btn;
    }
}
