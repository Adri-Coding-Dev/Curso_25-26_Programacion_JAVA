package ui;

import dao.BusDAO;
import dao.ConductorDAO;
import dao.LugarDAO;
import exceptions.EstadoExcepcion;
import model.*;
import ui.dialogs.BusDialog;
import ui.dialogs.ConductorDialog;
import ui.dialogs.ConductorPerfilDialog;
import ui.dialogs.LugarDialog;
import utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.List;

/**
 * Ventana principal de la aplicación de gestión de Aucorsa.
 *
 * <p>Presenta tres pestañas (Buses, Conductores, Lugares) con tablas de datos.
 * Proporciona botones comunes en la barra de herramientas superior para añadir,
 * buscar, editar, borrar y refrescar registros de la entidad activa.
 *
 * <p>En la pestaña de Conductores, el doble clic sobre una fila abre el
 * {@link ConductorPerfilDialog} con la información detallada del conductor.
 */
public class PrincipalView extends JFrame {

    // ===== PALETA MODO OSCURO (tonos negros con acentos azules, profesional y futurista) =====

    private final Color colorFondo      = Color.decode("#0B0B12");
    private final Color colorToolbar    = Color.decode("#0A1620");
    private final Color colorBotones    = Color.decode("#1E4A8C");
    private final Color colorTexto      = Color.decode("#F0F4FA");
    private final Color colorTextoBoton = Color.decode("#A0B3CC");
    private final Color colorBorde      = Color.decode("#2A3344");
    private final Color colorTablaFondo = Color.decode("#0F0F18");
    private final Color colorTablaAlt   = Color.decode("#181F2C");

    // ===== BOTONES COMPARTIDOS DE LA BARRA DE HERRAMIENTAS =====

    private final JButton btnAdd       = new JButton("Añadir");
    private final JButton btnBuscar    = new JButton("Buscar");
    private final JButton btnBorrar    = new JButton("Borrar");
    private final JButton btnEditar    = new JButton("Editar");
    private final JButton btnRefrescar = new JButton("Refrescar");

    // ===== PANEL DE PESTAÑAS Y TABLAS =====

    private JTabbedPane tabbedPane;
    private JTable      tableBuses, tableConductores, tableLugares;
    private DefaultTableModel modelBuses, modelConductores, modelLugares;

    // ===== BARRA DE ESTADO INFERIOR =====

    private final JLabel lblEstado = new JLabel(
            "Invitame a un café ☕. BBDD Utilizada: " + utils.Utils.sacarNombreBBDD()
    );

    // ===== CONEXIÓN A BASE DE DATOS =====

    private final Connection con;

    // ===== CONSTRUCTOR =====

    /**
     * Crea y muestra la ventana principal de la aplicación.
     *
     * @param con Conexión activa a la base de datos
     */
    public PrincipalView(Connection con) {
        this.con = con;

        configurarVentana();
        initToolbar();
        initTabbedPane();
        initBottomPanel();

        // Cargar datos iniciales de todas las entidades
        cargarBuses();
        cargarConductores();
        cargarLugares();

        setVisible(true);
    }

    // ===== CONFIGURACIÓN INICIAL DE LA VENTANA =====

    /** Establece las propiedades básicas del JFrame. */
    private void configurarVentana() {
        setTitle("Gestión de Aucorsa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(colorFondo);
    }

    // ===== BARRA DE HERRAMIENTAS SUPERIOR =====

    /**
     * Construye y añade la barra de herramientas con los botones de acción comunes.
     * Aplica estilos consistentes a todos los botones y registra los listeners.
     */
    private void initToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(colorToolbar);
        toolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorBorde));

        // Aplicar estilo a todos los botones de la barra
        JButton[] botones = {btnAdd, btnBuscar, btnBorrar, btnEditar, btnRefrescar};
        for (JButton btn : botones) {
            estilizarBoton(btn);
            toolBar.add(btn);
            toolBar.addSeparator(new Dimension(10, 10));
        }

        add(toolBar, BorderLayout.NORTH);

        // Registrar listeners de cada botón
        btnAdd.addActionListener(e       -> accionAdd());
        btnBuscar.addActionListener(e    -> accionBuscar());
        btnEditar.addActionListener(e    -> accionEditar());
        btnBorrar.addActionListener(e    -> accionBorrar());
        btnRefrescar.addActionListener(e -> refrescarTablaActiva());
    }

    /**
     * Aplica el estilo de la paleta a un botón de la barra de herramientas.
     *
     * @param btn Botón a estilizar
     */
    private void estilizarBoton(JButton btn) {
        btn.setBackground(colorBotones);
        btn.setForeground(colorTextoBoton);
        btn.setFocusPainted(false);
        btn.setFont(btn.getFont().deriveFont(Font.BOLD));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorBorde),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
    }

    // ===== PANEL DE PESTAÑAS =====

    /**
     * Construye el panel de pestañas con sus respectivas tablas para
     * Buses, Conductores y Lugares. También registra el listener de doble clic
     * en la tabla de conductores para abrir el diálogo de perfil.
     */
    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(tabbedPane.getFont().deriveFont(Font.BOLD));
        tabbedPane.setBackground(colorFondo);
        tabbedPane.setForeground(colorTexto);
        tabbedPane.setBorder(BorderFactory.createLineBorder(colorBorde));

        // Pestaña Buses
        modelBuses  = crearModelo(new String[]{"ID", "Tipo", "Licencia"});
        tableBuses  = crearTabla(modelBuses);
        tabbedPane.addTab("Buses", crearScrollConTabla(tableBuses));

        // Pestaña Conductores (con doble clic para abrir el perfil detallado)
        modelConductores  = crearModelo(new String[]{"ID", "Nombre", "Apellidos"});
        tableConductores  = crearTabla(modelConductores);
        registrarDobleClicConductores(); // <-- Nueva funcionalidad
        tabbedPane.addTab("Conductores", crearScrollConTabla(tableConductores));

        // Pestaña Lugares
        modelLugares  = crearModelo(new String[]{"ID", "Código Postal", "Ciudad", "Ubicación"});
        tableLugares  = crearTabla(modelLugares);
        tabbedPane.addTab("Lugares", crearScrollConTabla(tableLugares));

        // Resaltar visualmente la pestaña seleccionada
        tabbedPane.addChangeListener(e -> actualizarColorPestanaSeleccionada());

        add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Registra el listener de doble clic en la tabla de conductores.
     * Al hacer doble clic en una fila, abre el {@link ConductorPerfilDialog}
     * con la lista completa de conductores y el índice del conductor seleccionado.
     */
    private void registrarDobleClicConductores() {
        tableConductores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Solo actuar ante doble clic
                if (e.getClickCount() != 2) {
                    return;
                }

                int filaSeleccionada = tableConductores.getSelectedRow();
                if (filaSeleccionada == -1) {
                    return; // Ninguna fila seleccionada
                }

                // Cargar la lista completa de conductores para la navegación en el perfil
                List<Conductor> listaConductores = ConductorDAO.findAll(con);
                if (listaConductores == null || listaConductores.isEmpty()) {
                    mostrarAviso("No hay conductores disponibles.");
                    return;
                }

                // Obtener el ID del conductor seleccionado para encontrar su índice en la lista
                int idConductorSeleccionado = (int) modelConductores.getValueAt(filaSeleccionada, 0);
                int indiceEnLista = buscarIndicePorId(listaConductores, idConductorSeleccionado);

                if (indiceEnLista == -1) {
                    mostrarAviso("No se encontró el conductor seleccionado.");
                    return;
                }

                // Abrir el diálogo de perfil con el conductor seleccionado
                ConductorPerfilDialog perfil = new ConductorPerfilDialog(
                        PrincipalView.this,
                        listaConductores,
                        indiceEnLista,
                        con
                );
                perfil.setVisible(true);

                // Refrescar la tabla al cerrar el perfil (por si se editaron datos)
                cargarConductores();
            }
        });
    }

    /**
     * Busca el índice de un conductor en la lista por su ID.
     *
     * @param lista Lista de conductores donde buscar
     * @param idC   Identificador del conductor buscado
     * @return Índice en la lista, o {@code -1} si no se encuentra
     */
    private int buscarIndicePorId(List<Conductor> lista, int idC) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId_C() == idC) {
                return i;
            }
        }
        return -1;
    }

    /** Cambia el color del texto de las pestañas para resaltar la seleccionada. */
    private void actualizarColorPestanaSeleccionada() {
        int seleccionada = tabbedPane.getSelectedIndex();
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            tabbedPane.setForegroundAt(i, i == seleccionada ? Color.BLACK : colorTexto);
        }
    }

    // ===== FÁBRICA DE COMPONENTES DE TABLA =====

    /**
     * Crea un {@link DefaultTableModel} no editable con las columnas indicadas.
     *
     * @param columnas Nombres de las columnas de la tabla
     * @return Modelo de tabla no editable
     */
    private DefaultTableModel crearModelo(String[] columnas) {
        return new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false; // Las celdas no son editables directamente
            }
        };
    }

    /**
     * Crea y configura una JTable con el estilo de la paleta del sistema.
     * Incluye filas alternas y renderizado personalizado.
     *
     * @param modelo Modelo de datos de la tabla
     * @return Tabla configurada y estilizada
     */
    private JTable crearTabla(DefaultTableModel modelo) {
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(25);
        tabla.setShowGrid(true);
        tabla.setGridColor(colorBorde);
        tabla.setBackground(colorTablaFondo);
        tabla.setForeground(colorTexto);
        tabla.setSelectionBackground(colorBotones);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setBorder(BorderFactory.createLineBorder(colorBorde));

        // Estilizar la cabecera de la tabla
        JTableHeader cabecera = tabla.getTableHeader();
        cabecera.setBackground(colorToolbar);
        cabecera.setForeground(colorTexto);
        cabecera.setFont(cabecera.getFont().deriveFont(Font.BOLD));

        // Renderizador personalizado para filas alternas y colores de selección
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component celda = super.getTableCellRendererComponent(
                        t, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    celda.setBackground(row % 2 == 0 ? colorTablaFondo : colorTablaAlt);
                    celda.setForeground(colorTexto);
                } else {
                    celda.setBackground(colorBotones);
                    celda.setForeground(Color.WHITE);
                }
                return celda;
            }
        });

        return tabla;
    }

    /**
     * Envuelve una tabla en un {@link JScrollPane} estilizado.
     *
     * @param tabla Tabla a envolver
     * @return JScrollPane con la tabla y estilo de la paleta
     */
    private JScrollPane crearScrollConTabla(JTable tabla) {
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(colorBorde));
        scroll.getViewport().setBackground(colorTablaFondo);
        return scroll;
    }

    // ===== PANEL INFERIOR DE ESTADO =====

    /** Construye el panel inferior con la etiqueta de estado de la aplicación. */
    private void initBottomPanel() {
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBackground(colorToolbar);
        panelInferior.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, colorBorde));
        lblEstado.setForeground(colorTexto);
        panelInferior.add(lblEstado);
        add(panelInferior, BorderLayout.SOUTH);
    }

    // ===== CARGA DE DATOS =====

    /** Recarga la tabla de buses desde la base de datos. */
    private void cargarBuses() {
        modelBuses.setRowCount(0);
        List<Bus> buses = BusDAO.findAll(con);
        if (buses != null) {
            for (Bus b : buses) {
                modelBuses.addRow(new Object[]{b.getId_bus(), b.getTipo(), b.getLicencia()});
            }
        }
    }

    /** Recarga la tabla de conductores desde la base de datos. */
    private void cargarConductores() {
        modelConductores.setRowCount(0);
        List<Conductor> conductores = ConductorDAO.findAll(con);
        if (conductores != null) {
            for (Conductor c : conductores) {
                modelConductores.addRow(new Object[]{c.getId_C(), c.getNombre(), c.getApellido()});
            }
        }
    }

    /** Recarga la tabla de lugares desde la base de datos. */
    private void cargarLugares() {
        modelLugares.setRowCount(0);
        List<Lugar> lugares = LugarDAO.findAll(con);
        if (lugares != null) {
            for (Lugar l : lugares) {
                modelLugares.addRow(new Object[]{l.getId_L(), l.getCod_Post(), l.getCiudad(), l.getUbicacion()});
            }
        }
    }

    // ===== MÉTODOS AUXILIARES DE PESTAÑA ACTIVA =====

    /** Devuelve el título de la pestaña actualmente seleccionada. */
    private String getEntidadActiva() {
        return tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
    }

    /** Devuelve la tabla correspondiente a la pestaña activa. */
    private JTable getTablaActiva() {
        return switch (tabbedPane.getSelectedIndex()) {
            case 0 -> tableBuses;
            case 1 -> tableConductores;
            case 2 -> tableLugares;
            default -> null;
        };
    }

    /** Devuelve el modelo de datos de la pestaña activa. */
    private DefaultTableModel getModeloActivo() {
        return switch (tabbedPane.getSelectedIndex()) {
            case 0 -> modelBuses;
            case 1 -> modelConductores;
            case 2 -> modelLugares;
            default -> null;
        };
    }

    /** Recarga los datos de la tabla activa desde la base de datos. */
    private void refrescarTablaActiva() {
        switch (getEntidadActiva()) {
            case "Buses"       -> cargarBuses();
            case "Conductores" -> cargarConductores();
            case "Lugares"     -> cargarLugares();
        }
    }

    // ===== ACCIONES DE BOTONES =====

    /** Acción del botón "Añadir": delega según la entidad activa. */
    private void accionAdd() {
        switch (getEntidadActiva()) {
            case "Buses"       -> abrirBusCreate();
            case "Conductores" -> abrirConductorCreate();
            case "Lugares"     -> abrirLugarCreate();
        }
    }

    /** Acción del botón "Buscar": delega según la entidad activa. */
    private void accionBuscar() {
        switch (getEntidadActiva()) {
            case "Buses"       -> abrirBusSearch();
            case "Conductores" -> abrirConductorSearch();
            case "Lugares"     -> abrirLugarSearch();
        }
    }

    /** Acción del botón "Editar": delega según la entidad activa. */
    private void accionEditar() {
        switch (getEntidadActiva()) {
            case "Buses"       -> abrirBusEdit();
            case "Conductores" -> abrirConductorEdit();
            case "Lugares"     -> abrirLugarEdit();
        }
    }

    /** Acción del botón "Borrar": delega según la entidad activa. */
    private void accionBorrar() {
        switch (getEntidadActiva()) {
            case "Buses"       -> borrarBusSeleccionado();
            case "Conductores" -> borrarConductorSeleccionado();
            case "Lugares"     -> borrarLugarSeleccionado();
        }
    }

    // ===== OPERACIONES PARA BUS =====

    /** Abre el diálogo de creación de un nuevo bus. */
    private void abrirBusCreate() {
        BusDialog dialog = new BusDialog(this, BusDialogMode.CREATE, null);
        dialog.setVisible(true);
        Bus bus = dialog.getBus();
        if (bus != null && BusDAO.insert(con, bus)) {
            cargarBuses();
        }
    }

    /** Abre el diálogo de edición del bus seleccionado en la tabla. */
    private void abrirBusEdit() {
        int fila = tableBuses.getSelectedRow();
        if (fila == -1) {
            mostrarAviso("Selecciona un bus para editar");
            return;
        }
        Bus busSeleccionado = new Bus(
                modelBuses.getValueAt(fila, 0).toString(),
                modelBuses.getValueAt(fila, 1).toString(),
                modelBuses.getValueAt(fila, 2).toString()
        );
        BusDialog dialog = new BusDialog(this, BusDialogMode.EDIT, busSeleccionado);
        dialog.setVisible(true);
        Bus busEditado = dialog.getBus();
        if (busEditado != null && BusDAO.update(con, busEditado)) {
            cargarBuses();
        }
    }

    /** Abre el diálogo de búsqueda de un bus por ID. */
    private void abrirBusSearch() {
        BusDialog dialog = new BusDialog(this, BusDialogMode.SEARCH, null);
        dialog.setVisible(true);
        Bus criterio = dialog.getBus();
        if (criterio != null) {
            Bus encontrado = BusDAO.findById(con, criterio.getId_bus());
            if (encontrado != null) {
                modelBuses.setRowCount(0);
                modelBuses.addRow(new Object[]{encontrado.getId_bus(), encontrado.getTipo(), encontrado.getLicencia()});
            } else {
                mostrarInfo(Utils.mostrarCodigoEstado(EstadoExcepcion.FIND_ERROR));
            }
        }
    }

    /** Elimina el bus seleccionado en la tabla tras confirmar la acción. */
    private void borrarBusSeleccionado() {
        int fila = tableBuses.getSelectedRow();
        if (fila == -1) {
            mostrarAviso("Selecciona un bus de la tabla");
            return;
        }
        String idBus = modelBuses.getValueAt(fila, 0).toString();
        if (confirmar("¿Eliminar el bus " + idBus + "?")) {
            if (BusDAO.delete(con, idBus)) {
                cargarBuses();
            }
        }
    }

    // ===== OPERACIONES PARA CONDUCTOR =====

    /** Abre el diálogo de creación de un nuevo conductor. */
    private void abrirConductorCreate() {
        ConductorDialog dialog = new ConductorDialog(this, ConductorDialogMode.CREATE, null);
        dialog.setVisible(true);
        Conductor c = dialog.getConductor();
        if (c != null && ConductorDAO.insert(con, c)) {
            cargarConductores();
        }
    }

    /** Abre el diálogo de edición del conductor seleccionado en la tabla. */
    private void abrirConductorEdit() {
        int fila = tableConductores.getSelectedRow();
        if (fila == -1) {
            mostrarAviso("Selecciona un conductor para editar");
            return;
        }
        Conductor existente = new Conductor(
                (int) modelConductores.getValueAt(fila, 0),
                (String) modelConductores.getValueAt(fila, 1),
                (String) modelConductores.getValueAt(fila, 2)
        );
        ConductorDialog dialog = new ConductorDialog(this, ConductorDialogMode.EDIT, existente);
        dialog.setVisible(true);
        Conductor actualizado = dialog.getConductor();
        if (actualizado != null && ConductorDAO.update(con, actualizado)) {
            cargarConductores();
        }
    }

    /** Abre el diálogo de búsqueda de un conductor por ID. */
    private void abrirConductorSearch() {
        ConductorDialog dialog = new ConductorDialog(this, ConductorDialogMode.SEARCH, null);
        dialog.setVisible(true);
        Conductor criterio = dialog.getConductor();
        if (criterio != null) {
            Conductor encontrado = ConductorDAO.findById(con, criterio.getId_C());
            if (encontrado != null) {
                modelConductores.setRowCount(0);
                modelConductores.addRow(new Object[]{encontrado.getId_C(), encontrado.getNombre(), encontrado.getApellido()});
            } else {
                mostrarInfo("No se encontró el conductor");
            }
        }
    }

    /** Elimina el conductor seleccionado en la tabla tras confirmar la acción. */
    private void borrarConductorSeleccionado() {
        int fila = tableConductores.getSelectedRow();
        if (fila == -1) {
            mostrarAviso("Selecciona un conductor de la tabla");
            return;
        }
        int idC = (int) modelConductores.getValueAt(fila, 0);
        if (confirmar("¿Eliminar el conductor " + idC + "?")) {
            if (ConductorDAO.delete(con, idC)) {
                cargarConductores();
            }
        }
    }

    // ===== OPERACIONES PARA LUGAR =====

    /** Abre el diálogo de creación de un nuevo lugar. */
    private void abrirLugarCreate() {
        LugarDialog dialog = new LugarDialog(this, LugarDialogMode.CREATE, null);
        dialog.setVisible(true);
        Lugar l = dialog.getLugar();
        if (l != null && LugarDAO.insert(con, l)) {
            cargarLugares();
        }
    }

    /** Abre el diálogo de edición del lugar seleccionado en la tabla. */
    private void abrirLugarEdit() {
        int fila = tableLugares.getSelectedRow();
        if (fila == -1) {
            mostrarAviso("Selecciona un lugar para editar");
            return;
        }
        Lugar existente = new Lugar(
                (int) modelLugares.getValueAt(fila, 0),
                (String) modelLugares.getValueAt(fila, 1),
                (String) modelLugares.getValueAt(fila, 2),
                (String) modelLugares.getValueAt(fila, 3)
        );
        LugarDialog dialog = new LugarDialog(this, LugarDialogMode.EDIT, existente);
        dialog.setVisible(true);
        Lugar actualizado = dialog.getLugar();
        if (actualizado != null && LugarDAO.update(con, actualizado)) {
            cargarLugares();
        }
    }

    /** Abre el diálogo de búsqueda de un lugar por ID. */
    private void abrirLugarSearch() {
        LugarDialog dialog = new LugarDialog(this, LugarDialogMode.SEARCH, null);
        dialog.setVisible(true);
        Lugar criterio = dialog.getLugar();
        if (criterio != null) {
            Lugar encontrado = LugarDAO.findById(con, criterio.getId_L());
            if (encontrado != null) {
                modelLugares.setRowCount(0);
                modelLugares.addRow(new Object[]{encontrado.getId_L(), encontrado.getCod_Post(), encontrado.getCiudad(), encontrado.getUbicacion()});
            } else {
                mostrarInfo("No se encontró el lugar");
            }
        }
    }

    /** Elimina el lugar seleccionado en la tabla tras confirmar la acción. */
    private void borrarLugarSeleccionado() {
        int fila = tableLugares.getSelectedRow();
        if (fila == -1) {
            mostrarAviso("Selecciona un lugar de la tabla");
            return;
        }
        int idL = (int) modelLugares.getValueAt(fila, 0);
        if (confirmar("¿Eliminar el lugar " + idL + "?")) {
            if (LugarDAO.delete(con, idL)) {
                cargarLugares();
            }
        }
    }

    // ===== MÉTODOS DE UTILIDAD PARA DIÁLOGOS =====

    /** Muestra un diálogo de aviso con el mensaje indicado. */
    private void mostrarAviso(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    /** Muestra un diálogo informativo con el mensaje indicado. */
    private void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un diálogo de confirmación Sí/No.
     *
     * @param pregunta Mensaje de la pregunta a confirmar
     * @return {@code true} si el usuario confirma con "Sí"
     */
    private boolean confirmar(String pregunta) {
        return JOptionPane.showConfirmDialog(
                this, pregunta, "Confirmar", JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION;
    }
}
