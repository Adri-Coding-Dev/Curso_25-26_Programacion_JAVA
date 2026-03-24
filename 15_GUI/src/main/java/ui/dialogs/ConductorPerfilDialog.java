package ui.dialogs;

import dao.ConductorDAO;
import model.Conductor;
import utils.DefaultsImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.List;

/**
 * Diálogo de perfil detallado de un conductor.
 *
 * <p>Permite visualizar la información completa de un conductor con su foto,
 * navegar entre conductores (anterior / siguiente), y editar sus datos.
 * La foto puede actualizarse desde el sistema de archivos; la copia se guarda
 * automáticamente en {@code src/main/resources/images/} con el nombre {@code driver{Id_C}.jpg}.
 *
 * <p>El ID del conductor solo se puede modificar introduciendo la contraseña ADMIN.
 */
public class ConductorPerfilDialog extends JDialog {

    // ===== CONSTANTES DE DISEÑO (paleta consistente con PrincipalView) =====

    private static final Color COLOR_FONDO       = Color.decode("#0B0B12");
    private static final Color COLOR_SUPERFICIE   = Color.decode("#14141F");
    private static final Color COLOR_TOOLBAR      = Color.decode("#0A1620");
    private static final Color COLOR_BOTONES      = Color.decode("#1E4A8C");
    private static final Color COLOR_TEXTO        = Color.decode("#F0F4FA");
    private static final Color COLOR_TEXTO_BOTON  = Color.decode("#A0B3CC");
    private static final Color COLOR_BORDE        = Color.decode("#2A3344");
    private static final Color COLOR_ACENTO       = Color.decode("#2979C8"); // Azul más brillante para acentos
    private static final Color COLOR_CAMPO_FONDO  = Color.decode("#1A1A2E");
    private static final Color COLOR_ADVERTENCIA  = Color.decode("#C8542C"); // Naranja/rojo para errores

    private static final String CONTRASENIA_ADMIN = "admin1234"; // Contraseña necesaria para cambiar el ID
    private static final int ANCHO_FOTO  = 180;
    private static final int ALTO_FOTO   = 200;

    // ===== ESTADO INTERNO =====

    /** Lista completa de conductores para la navegación. */
    private final List<Conductor> listaConductores;

    /** Índice del conductor actualmente visible en el diálogo. */
    private int indiceActual;

    /** Conexión activa a la base de datos. */
    private final Connection con;

    // ===== COMPONENTES DE LA INTERFAZ =====

    // Panel y etiqueta de imagen
    private JLabel   lblFoto;
    private JPanel   panelFoto;

    // Campos de información del conductor
    private JLabel lblIdValor;
    private JLabel lblNombreValor;
    private JLabel lblApellidoValor;

    // Campos de edición (visibles solo en modo edición)
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;

    // Panel que contiene la info (para intercambiar vista/edición)
    private JPanel panelInfo;

    // Botones de navegación y acciones
    private JButton btnAnterior;
    private JButton btnSiguiente;
    private JButton btnEditar;
    private JButton btnActualizarFoto;
    private JButton btnCerrar;

    /** Indica si el diálogo está en modo edición activa. */
    private boolean modoEdicion = false;

    // ===== CONSTRUCTOR =====

    /**
     * Crea el diálogo de perfil para el conductor en la posición dada de la lista.
     *
     * @param padre            Ventana padre
     * @param listaConductores Lista completa de conductores disponibles
     * @param indiceInicial    Índice del conductor a mostrar inicialmente
     * @param con              Conexión activa a la base de datos
     */
    public ConductorPerfilDialog(Frame padre, List<Conductor> listaConductores,
                                  int indiceInicial, Connection con) {
        super(padre, "Perfil del Conductor", true);
        this.listaConductores = listaConductores;
        this.indiceActual     = indiceInicial;
        this.con              = con;

        construirInterfaz();
        cargarDatosConductorActual();

        setSize(620, 460);
        setMinimumSize(new Dimension(580, 420));
        setLocationRelativeTo(padre);
        setResizable(true);
    }

    // ===== CONSTRUCCIÓN DE LA INTERFAZ =====

    /** Orquesta la creación de todos los paneles del diálogo. */
    private void construirInterfaz() {
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new BorderLayout(0, 0));

        add(crearPanelCentral(), BorderLayout.CENTER);
        add(crearPanelBotones(), BorderLayout.SOUTH);
    }

    /**
     * Crea el panel central que contiene la foto (izquierda) y la información (derecha).
     */
    private JPanel crearPanelCentral() {
        JPanel panelCentral = new JPanel(new BorderLayout(20, 0));
        panelCentral.setBackground(COLOR_FONDO);
        panelCentral.setBorder(new EmptyBorder(20, 20, 10, 20));

        panelCentral.add(crearPanelIzquierdaFoto(), BorderLayout.WEST);
        panelCentral.add(crearPanelDerechaInfo(), BorderLayout.CENTER);

        return panelCentral;
    }

    /**
     * Crea el panel izquierdo con la foto del conductor y el botón para actualizarla.
     */
    private JPanel crearPanelIzquierdaFoto() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setPreferredSize(new Dimension(ANCHO_FOTO + 20, 0));

        // Contenedor de la foto con borde decorativo
        panelFoto = new JPanel(new BorderLayout());
        panelFoto.setBackground(COLOR_SUPERFICIE);
        panelFoto.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_BORDE, 2),
                new EmptyBorder(4, 4, 4, 4)
        ));
        panelFoto.setPreferredSize(new Dimension(ANCHO_FOTO, ALTO_FOTO));

        // Etiqueta donde se mostrará la imagen
        lblFoto = new JLabel("", SwingConstants.CENTER);
        lblFoto.setBackground(COLOR_SUPERFICIE);
        panelFoto.add(lblFoto, BorderLayout.CENTER);

        // Botón para actualizar la foto
        btnActualizarFoto = crearBoton("📷 Actualizar foto");
        btnActualizarFoto.addActionListener(e -> accionActualizarFoto());

        panel.add(panelFoto, BorderLayout.NORTH);
        panel.add(btnActualizarFoto, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Crea el panel derecho con la información del conductor.
     * Alterna entre vista de solo lectura y campos de edición.
     */
    private JPanel crearPanelDerechaInfo() {
        panelInfo = new JPanel();
        panelInfo.setBackground(COLOR_FONDO);
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBorder(new EmptyBorder(10, 0, 0, 0));

        // Título del panel
        JLabel lblTitulo = new JLabel("Información del conductor");
        lblTitulo.setForeground(COLOR_ACENTO);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 14f));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Separador decorativo
        JSeparator separador = new JSeparator();
        separador.setForeground(COLOR_BORDE);
        separador.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        separador.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelInfo.add(lblTitulo);
        panelInfo.add(Box.createVerticalStrut(8));
        panelInfo.add(separador);
        panelInfo.add(Box.createVerticalStrut(16));

        // Campos de información (etiqueta + valor)
        lblIdValor       = crearLabelValor();
        lblNombreValor   = crearLabelValor();
        lblApellidoValor = crearLabelValor();

        // Campos de edición (inicialmente ocultos)
        txtId       = crearCampoEdicion();
        txtNombre   = crearCampoEdicion();
        txtApellido = crearCampoEdicion();

        panelInfo.add(crearFilaInfo("ID Conductor:", lblIdValor, txtId));
        panelInfo.add(Box.createVerticalStrut(12));
        panelInfo.add(crearFilaInfo("Nombre:", lblNombreValor, txtNombre));
        panelInfo.add(Box.createVerticalStrut(12));
        panelInfo.add(crearFilaInfo("Apellido:", lblApellidoValor, txtApellido));

        // Nota de modo edición (se muestra solo al editar)
        panelInfo.add(Box.createVerticalStrut(16));
        panelInfo.add(crearNotaEdicion());

        return panelInfo;
    }

    /**
     * Crea una fila de información con etiqueta, label de vista y campo de edición.
     * El label y el campo se apilan; solo uno es visible a la vez.
     *
     * @param etiqueta   Texto descriptivo del campo
     * @param lblVista   Label de solo lectura
     * @param campoEdic  Campo de texto para edición
     */
    private JPanel crearFilaInfo(String etiqueta, JLabel lblVista, JTextField campoEdic) {
        JPanel fila = new JPanel();
        fila.setLayout(new BoxLayout(fila, BoxLayout.Y_AXIS));
        fila.setBackground(COLOR_FONDO);
        fila.setAlignmentX(Component.LEFT_ALIGNMENT);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        // Etiqueta descriptiva
        JLabel lblEtiqueta = new JLabel(etiqueta);
        lblEtiqueta.setForeground(COLOR_TEXTO_BOTON);
        lblEtiqueta.setFont(lblEtiqueta.getFont().deriveFont(Font.PLAIN, 11f));
        lblEtiqueta.setAlignmentX(Component.LEFT_ALIGNMENT);

        // El label de vista y el campo de edición comparten espacio
        lblVista.setAlignmentX(Component.LEFT_ALIGNMENT);
        campoEdic.setAlignmentX(Component.LEFT_ALIGNMENT);
        campoEdic.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        campoEdic.setVisible(false); // Oculto por defecto (modo vista)

        fila.add(lblEtiqueta);
        fila.add(Box.createVerticalStrut(3));
        fila.add(lblVista);
        fila.add(campoEdic);

        return fila;
    }

    /**
     * Crea una pequeña nota informativa sobre la contraseña ADMIN
     * para cambiar el ID del conductor.
     */
    private JLabel crearNotaEdicion() {
        JLabel nota = new JLabel("<html><i>* Para cambiar el ID se requiere contraseña ADMIN</i></html>");
        nota.setForeground(COLOR_TEXTO_BOTON);
        nota.setFont(nota.getFont().deriveFont(Font.ITALIC, 10f));
        nota.setAlignmentX(Component.LEFT_ALIGNMENT);
        nota.setVisible(false); // Solo visible en modo edición
        // Guardamos referencia para activarla en modo edición
        nota.setName("notaAdmin");
        return nota;
    }

    /**
     * Crea el panel inferior con todos los botones de acción del diálogo.
     */
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_TOOLBAR);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, COLOR_BORDE),
                new EmptyBorder(10, 20, 10, 20)
        ));

        // Botones de navegación (izquierda)
        JPanel panelNavegacion = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panelNavegacion.setBackground(COLOR_TOOLBAR);

        btnAnterior  = crearBoton("◀ Anterior");
        btnSiguiente = crearBoton("Siguiente ▶");

        btnAnterior.addActionListener(e -> navegarAnterior());
        btnSiguiente.addActionListener(e -> navegarSiguiente());

        panelNavegacion.add(btnAnterior);
        panelNavegacion.add(btnSiguiente);

        // Botones de acción (derecha)
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        panelAcciones.setBackground(COLOR_TOOLBAR);

        btnEditar = crearBoton("✏ Editar");
        btnCerrar = crearBoton("Cerrar");

        btnEditar.addActionListener(e -> accionEditar());
        btnCerrar.addActionListener(e -> dispose());

        panelAcciones.add(btnEditar);
        panelAcciones.add(btnCerrar);

        panel.add(panelNavegacion, BorderLayout.WEST);
        panel.add(panelAcciones, BorderLayout.EAST);

        return panel;
    }

    // ===== FÁBRICA DE COMPONENTES =====

    /**
     * Crea un botón estilizado con la paleta del sistema.
     *
     * @param texto Texto a mostrar en el botón
     * @return Botón configurado con el estilo del sistema
     */
    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(COLOR_BOTONES);
        btn.setForeground(COLOR_TEXTO_BOTON);
        btn.setFocusPainted(false);
        btn.setFont(btn.getFont().deriveFont(Font.BOLD));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_BORDE),
                new EmptyBorder(6, 14, 6, 14)
        ));
        // Efecto hover sutil
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(COLOR_ACENTO);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(COLOR_BOTONES);
            }
        });
        return btn;
    }

    /** Crea un JLabel estilizado para mostrar valores de solo lectura. */
    private JLabel crearLabelValor() {
        JLabel lbl = new JLabel("—");
        lbl.setForeground(COLOR_TEXTO);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 13f));
        return lbl;
    }

    /** Crea un JTextField estilizado para edición de datos. */
    private JTextField crearCampoEdicion() {
        JTextField txt = new JTextField();
        txt.setBackground(COLOR_CAMPO_FONDO);
        txt.setForeground(COLOR_TEXTO);
        txt.setCaretColor(COLOR_TEXTO);
        txt.setFont(txt.getFont().deriveFont(Font.PLAIN, 13f));
        txt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_BORDE),
                new EmptyBorder(3, 6, 3, 6)
        ));
        return txt;
    }

    // ===== LÓGICA DE NAVEGACIÓN =====

    /** Navega al conductor anterior en la lista. Muestra aviso si ya está en el primero. */
    private void navegarAnterior() {
        if (indiceActual <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Ya estás en el primer conductor de la lista.",
                    "Inicio de lista",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        indiceActual--;
        cargarDatosConductorActual();
        salirModoEdicion(); // Resetear modo edición al cambiar de conductor
    }

    /** Navega al siguiente conductor en la lista. Muestra aviso si ya está en el último. */
    private void navegarSiguiente() {
        if (indiceActual >= listaConductores.size() - 1) {
            JOptionPane.showMessageDialog(this,
                    "Ya estás en el último conductor de la lista.",
                    "Fin de lista",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        indiceActual++;
        cargarDatosConductorActual();
        salirModoEdicion(); // Resetear modo edición al cambiar de conductor
    }

    // ===== CARGA DE DATOS =====

    /**
     * Carga y muestra todos los datos del conductor en la posición {@link #indiceActual}.
     */
    private void cargarDatosConductorActual() {
        Conductor conductor = listaConductores.get(indiceActual);

        // Actualizar título del diálogo con el nombre del conductor
        setTitle("Perfil: " + conductor.getNombre() + " " + conductor.getApellido()
                + "  [" + (indiceActual + 1) + " / " + listaConductores.size() + "]");

        // Actualizar etiquetas de información
        lblIdValor.setText(String.valueOf(conductor.getId_C()));
        lblNombreValor.setText(conductor.getNombre());
        lblApellidoValor.setText(conductor.getApellido());

        // Cargar la imagen correspondiente al conductor
        actualizarImagenPerfil(conductor.getId_C());
    }

    /**
     * Carga la imagen del conductor y la muestra en el panel de foto.
     * Intenta primero la imagen específica del conductor; si no existe, usa la imagen por defecto.
     *
     * @param idConductor Identificador del conductor
     */
    private void actualizarImagenPerfil(int idConductor) {
        ImageIcon icono = DefaultsImage.cargarImagenConductor(idConductor, ANCHO_FOTO, ALTO_FOTO);
        lblFoto.setIcon(icono);
        lblFoto.repaint();
    }

    // ===== ACCIONES =====

    /**
     * Acción del botón "Actualizar foto".
     * Abre un selector de archivos para elegir una imagen del sistema.
     * La imagen seleccionada se copia como {@code driver{Id_C}.jpg} en resources/images.
     */
    private void accionActualizarFoto() {
        Conductor conductorActual = listaConductores.get(indiceActual);

        // Configurar el selector de archivos solo para imágenes
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setDialogTitle("Seleccionar foto del conductor");
        selectorArchivo.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Imágenes (JPG, PNG, GIF)", "jpg", "jpeg", "png", "gif"
        ));

        // Aplicar estilo oscuro al selector (LookAndFeel base)
        selectorArchivo.setBackground(COLOR_SUPERFICIE);
        selectorArchivo.setForeground(COLOR_TEXTO);

        int respuesta = selectorArchivo.showOpenDialog(this);
        if (respuesta != JFileChooser.APPROVE_OPTION) {
            return; // El usuario canceló la selección
        }

        File archivoOrigen = selectorArchivo.getSelectedFile();
        String nombreDestino = "driver" + conductorActual.getId_C() + ".jpg";
        File archivoDestino = new File(DefaultsImage.getRutaRecursos() + nombreDestino);

        // Copiar la imagen elegida al directorio de recursos
        try {
            // Crear el directorio de destino si no existe
            archivoDestino.getParentFile().mkdirs();
            Files.copy(archivoOrigen.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Refrescar la imagen mostrada en el diálogo
            actualizarImagenPerfil(conductorActual.getId_C());

            JOptionPane.showMessageDialog(this,
                    "Foto actualizada correctamente.",
                    "Foto guardada",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo guardar la imagen: " + e.getMessage(),
                    "Error al guardar",
                    JOptionPane.ERROR_MESSAGE);
            System.err.println("[ConductorPerfilDialog] Error al copiar imagen: " + e.getMessage());
        }
    }

    /**
     * Alterna entre modo visualización y modo edición.
     * En modo edición, los campos se vuelven editables.
     * Al guardar, valida y persiste los cambios en la BBDD.
     */
    private void accionEditar() {
        if (!modoEdicion) {
            activarModoEdicion();
        } else {
            guardarCambiosEdicion();
        }
    }

    /** Activa el modo edición: muestra campos de texto y oculta los labels de solo lectura. */
    private void activarModoEdicion() {
        Conductor actual = listaConductores.get(indiceActual);

        // Rellenar campos de edición con los valores actuales
        txtId.setText(String.valueOf(actual.getId_C()));
        txtNombre.setText(actual.getNombre());
        txtApellido.setText(actual.getApellido());

        // Mostrar campos, ocultar labels
        alternarVistaEdicion(true);

        // El ID solo se puede editar con contraseña ADMIN; por defecto, bloqueado
        txtId.setEditable(false);
        txtId.setBackground(COLOR_CAMPO_FONDO.darker());
        txtId.setToolTipText("Requiere contraseña ADMIN para editar");

        // Doble clic en el campo ID abre el diálogo de contraseña ADMIN
        txtId.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    solicitarContrasenaAdmin();
                }
            }
        });

        btnEditar.setText("💾 Guardar");
        modoEdicion = true;

        // Mostrar nota informativa sobre la contraseña ADMIN
        mostrarNotaAdmin(true);
    }

    /**
     * Valida los campos de edición y, si son correctos, persiste los cambios en la BBDD.
     * Si hay error de validación, muestra un mensaje al usuario.
     */
    private void guardarCambiosEdicion() {
        // Validar que los campos obligatorios no estén vacíos
        if (txtNombre.getText().isBlank() || txtApellido.getText().isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "El nombre y el apellido no pueden estar vacíos.",
                    "Campos requeridos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar que el ID sea un número entero
        int nuevoId;
        try {
            nuevoId = Integer.parseInt(txtId.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "El ID debe ser un número entero.",
                    "ID inválido",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Conductor conductorActual = listaConductores.get(indiceActual);

        // Construir objeto con los datos modificados
        Conductor conductorActualizado = new Conductor(
                nuevoId,
                txtNombre.getText().trim(),
                txtApellido.getText().trim()
        );

        // Persistir en la BBDD
        boolean exito = ConductorDAO.update(con, conductorActualizado);
        if (exito) {
            // Actualizar la lista en memoria para mantener consistencia
            listaConductores.set(indiceActual, conductorActualizado);

            // Refrescar la vista con los nuevos datos
            cargarDatosConductorActual();
            salirModoEdicion();

            JOptionPane.showMessageDialog(this,
                    "Datos del conductor actualizados correctamente.",
                    "Guardado",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudieron guardar los cambios. Comprueba la conexión a la BBDD.",
                    "Error al guardar",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Solicita la contraseña de administrador para habilitar la edición del ID del conductor.
     * Si la contraseña es correcta, desbloquea el campo de ID.
     */
    private void solicitarContrasenaAdmin() {
        JPasswordField campoContrasena = new JPasswordField(20);
        campoContrasena.setBackground(COLOR_CAMPO_FONDO);
        campoContrasena.setForeground(COLOR_TEXTO);
        campoContrasena.setCaretColor(COLOR_TEXTO);

        int resultado = JOptionPane.showConfirmDialog(
                this,
                new Object[]{"Introduce la contraseña de administrador:", campoContrasena},
                "Verificación ADMIN",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (resultado != JOptionPane.OK_OPTION) {
            return; // El usuario canceló
        }

        String contrasenaIntroducida = new String(campoContrasena.getPassword());
        if (CONTRASENIA_ADMIN.equals(contrasenaIntroducida)) {
            // Contraseña correcta: desbloquear el campo de ID
            txtId.setEditable(true);
            txtId.setBackground(COLOR_CAMPO_FONDO);
            txtId.setToolTipText("Campo desbloqueado (ADMIN)");

            JOptionPane.showMessageDialog(this,
                    "Acceso ADMIN concedido. Ahora puedes modificar el ID.",
                    "Acceso ADMIN",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Contraseña incorrecta. No se puede editar el ID.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Desactiva el modo edición: oculta los campos de texto y muestra los labels de solo lectura. */
    private void salirModoEdicion() {
        alternarVistaEdicion(false);
        btnEditar.setText("✏ Editar");
        modoEdicion = false;
        mostrarNotaAdmin(false);
    }

    /**
     * Alterna la visibilidad de los componentes de vista vs. edición.
     *
     * @param modoEdicionActivo {@code true} para mostrar campos de edición, {@code false} para vista
     */
    private void alternarVistaEdicion(boolean modoEdicionActivo) {
        // Labels de solo lectura: visibles en modo vista, ocultos en edición
        lblIdValor.setVisible(!modoEdicionActivo);
        lblNombreValor.setVisible(!modoEdicionActivo);
        lblApellidoValor.setVisible(!modoEdicionActivo);

        // Campos de texto: visibles en modo edición, ocultos en vista
        txtId.setVisible(modoEdicionActivo);
        txtNombre.setVisible(modoEdicionActivo);
        txtApellido.setVisible(modoEdicionActivo);

        panelInfo.revalidate();
        panelInfo.repaint();
    }

    /**
     * Muestra u oculta la nota informativa sobre la contraseña ADMIN.
     *
     * @param visible {@code true} para mostrarla, {@code false} para ocultarla
     */
    private void mostrarNotaAdmin(boolean visible) {
        // Buscar el componente por nombre y cambiar su visibilidad
        for (Component c : panelInfo.getComponents()) {
            if ("notaAdmin".equals(c.getName())) {
                c.setVisible(visible);
                break;
            }
        }
        panelInfo.revalidate();
    }
}
