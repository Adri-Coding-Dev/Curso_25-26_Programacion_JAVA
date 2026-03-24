package ui;

import dao.BusDAO;
import dao.ConductorDAO;
import dao.LugarDAO;
import model.*;
import ui.dialogs.BusDialog;
import ui.dialogs.ConductorDialog;
import ui.dialogs.LugarDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class PrincipalView extends JFrame {

    // ===== BOTONES COMPARTIDOS =====
    private final JButton btnAdd = new JButton("Añadir");
    private final JButton btnBuscar = new JButton("Buscar");
    private final JButton btnBorrar = new JButton("Borrar");
    private final JButton btnEditar = new JButton("Editar");
    private final JButton btnRefrescar = new JButton("Refrescar");

    // ===== PANEL DE PESTAÑAS =====
    private JTabbedPane tabbedPane;
    private JTable tableBuses, tableConductores, tableLugares;
    private DefaultTableModel modelBuses, modelConductores, modelLugares;

    // ===== ETIQUETA DE ESTADO =====
    private final JLabel lblEstado = new JLabel("Invitame a un café ☕. BBDD Utilizada: " + utils.Utils.sacarNombreBBDD());

    // ===== CONEXIÓN =====
    private final Connection con;

    // ===== PALETA MODO OSCURO - TONOS NEGROS CON ACENTOS AZULES (PROFESIONAL Y FUTURISTA) =====
    private final Color colorFondo = Color.decode("#0B0B12");        // Fondo principal: negro con matiz índigo muy sutil
    private final Color colorSuperficie = Color.decode("#14141F");   // Superficies elevadas (cards, paneles) ligeramente más claras
    private final Color colorToolbar = Color.decode("#0A1620");      // Barra de herramientas: azul marino casi negro
    private final Color colorBotones = Color.decode("#1E4A8C");
    private final Color colorTexto = Color.decode("#F0F4FA"); // Texto principal: blanco azulado suave
    private final Color colorTextoBoton = Color.decode("#A0B3CC"); // Texto secundario: gris azulado claro
    private final Color colorBorde = Color.decode("#2A3344");        // Bordes y separadores: azul grisáceo oscuro
    private final Color colorTablaFondo = Color.decode("#0F0F18");   // Fondo de tabla: muy oscuro
    private final Color colorTablaAlt = Color.decode("#181F2C");     // Filas alternas: azul muy oscuro

    // ===== CONSTRUCTOR =====
    public PrincipalView(Connection con) {
        this.con = con;
        setTitle("Gestión de Aucorsa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(colorFondo);

        initToolbar();
        initTabbedPane();
        initBottomPanel();

        cargarBuses();
        cargarConductores();
        cargarLugares();

        setVisible(true);
    }

    // ===== BARRA DE HERRAMIENTAS SUPERIOR =====
    private void initToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(colorToolbar);
        toolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorBorde));

        JButton[] botones = {btnAdd, btnBuscar, btnBorrar, btnEditar, btnRefrescar};
        for (JButton b : botones) {
            b.setBackground(colorBotones);
            b.setForeground(colorTextoBoton);
            b.setFocusPainted(false);
            b.setFont(b.getFont().deriveFont(Font.BOLD));
            b.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(colorBorde),
                    BorderFactory.createEmptyBorder(8, 15, 8, 15)
            ));
            toolBar.add(b);
            toolBar.addSeparator(new Dimension(10, 10));
        }

        add(toolBar, BorderLayout.NORTH);

        btnAdd.addActionListener(e -> accionAdd());
        btnBuscar.addActionListener(e -> accionBuscar());
        btnEditar.addActionListener(e -> accionEditar());
        btnBorrar.addActionListener(e -> accionBorrar());
        btnRefrescar.addActionListener(e -> refrescarTablaActiva());
    }

    // ===== PANEL DE PESTAÑAS =====
    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(tabbedPane.getFont().deriveFont(Font.BOLD));
        tabbedPane.setBackground(colorFondo);
        tabbedPane.setForeground(colorTexto); // Color por defecto para todas las pestañas
        tabbedPane.setBorder(BorderFactory.createLineBorder(colorBorde));

        // Crear las pestañas con sus tablas
        modelBuses = crearModelo(new String[]{"ID", "Tipo", "Licencia"});
        tableBuses = crearTabla(modelBuses);
        tabbedPane.addTab("Buses", crearPanelConTabla(tableBuses));

        modelConductores = crearModelo(new String[]{"ID", "Nombre", "Apellidos"});
        tableConductores = crearTabla(modelConductores);
        tabbedPane.addTab("Conductores", crearPanelConTabla(tableConductores));

        modelLugares = crearModelo(new String[]{"ID", "Código Postal", "Ciudad", "Ubicación"});
        tableLugares = crearTabla(modelLugares);
        tabbedPane.addTab("Lugares", crearPanelConTabla(tableLugares));

        // Listener para cambiar el color del texto de la pestaña seleccionada a blanco
        tabbedPane.addChangeListener(e -> {
            int selected = tabbedPane.getSelectedIndex();
            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                if (i == selected) {
                    tabbedPane.setForegroundAt(i, Color.BLACK);
                } else {
                    tabbedPane.setForegroundAt(i, colorTexto);
                }
            }
        });

        add(tabbedPane, BorderLayout.CENTER);
    }

    private DefaultTableModel crearModelo(String[] columnas) {
        return new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
    }

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

        // Cabecera de la tabla
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(colorToolbar);
        header.setForeground(colorTexto);
        header.setFont(header.getFont().deriveFont(Font.BOLD));

        // Renderer para filas alternadas y colores de texto/fondo
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? colorTablaFondo : colorTablaAlt);
                    c.setForeground(colorTexto);
                } else {
                    c.setBackground(colorBotones);
                    c.setForeground(Color.WHITE);
                }
                return c;
            }
        });

        return tabla;
    }

    private JScrollPane crearPanelConTabla(JTable tabla) {
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(colorBorde));
        scroll.getViewport().setBackground(colorTablaFondo);
        return scroll;
    }

    // ===== PANEL INFERIOR =====
    private void initBottomPanel() {
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottom.setBackground(colorToolbar);
        bottom.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, colorBorde));
        lblEstado.setForeground(colorTexto);
        bottom.add(lblEstado);
        add(bottom, BorderLayout.SOUTH);
    }

    // ===== MÉTODOS DE CARGA DE DATOS =====
    private void cargarBuses() {
        modelBuses.setRowCount(0);
        List<Bus> buses = BusDAO.findAll(con);
        if (buses != null) {
            for (Bus b : buses) {
                modelBuses.addRow(new Object[]{b.getId_bus(), b.getTipo(), b.getLicencia()});
            }
        }
    }

    private void cargarConductores() {
        modelConductores.setRowCount(0);
        List<Conductor> conductores = ConductorDAO.findAll(con);
        if (conductores != null) {
            for (Conductor c : conductores) {
                modelConductores.addRow(new Object[]{c.getId_C(), c.getNombre(), c.getApellido()});
            }
        }
    }

    private void cargarLugares() {
        modelLugares.setRowCount(0);
        List<Lugar> lugares = LugarDAO.findAll(con);
        if (lugares != null) {
            for (Lugar l : lugares) {
                modelLugares.addRow(new Object[]{l.getId_L(), l.getCod_Post(), l.getCiudad(), l.getUbicacion()});
            }
        }
    }

    // ===== MÉTODOS AUXILIARES PARA LA PESTAÑA ACTIVA =====
    private String getEntidadActiva() {
        return tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
    }

    private JTable getTablaActiva() {
        int index = tabbedPane.getSelectedIndex();
        switch (index) {
            case 0: return tableBuses;
            case 1: return tableConductores;
            case 2: return tableLugares;
            default: return null;
        }
    }

    private DefaultTableModel getModeloActivo() {
        int index = tabbedPane.getSelectedIndex();
        switch (index) {
            case 0: return modelBuses;
            case 1: return modelConductores;
            case 2: return modelLugares;
            default: return null;
        }
    }

    private void refrescarTablaActiva() {
        switch (getEntidadActiva()) {
            case "Buses": cargarBuses(); break;
            case "Conductores": cargarConductores(); break;
            case "Lugares": cargarLugares(); break;
        }
    }

    // ===== ACCIONES DE BOTONES =====
    private void accionAdd() {
        switch (getEntidadActiva()) {
            case "Buses" -> abrirBusCreate();
            case "Conductores" -> abrirConductorCreate();
            case "Lugares" -> abrirLugarCreate();
        }
    }

    private void accionBuscar() {
        switch (getEntidadActiva()) {
            case "Buses" -> abrirBusSearch();
            case "Conductores" -> abrirConductorSearch();
            case "Lugares" -> abrirLugarSearch();
        }
    }

    private void accionEditar() {
        switch (getEntidadActiva()) {
            case "Buses" -> abrirBusEdit();
            case "Conductores" -> abrirConductorEdit();
            case "Lugares" -> abrirLugarEdit();
        }
    }

    private void accionBorrar() {
        switch (getEntidadActiva()) {
            case "Buses" -> borrarBusSeleccionado();
            case "Conductores" -> borrarConductorSeleccionado();
            case "Lugares" -> borrarLugarSeleccionado();
        }
    }

    // ===== OPERACIONES PARA BUS =====
    private void abrirBusCreate() {
        BusDialog dialog = new BusDialog(this, BusDialogMode.CREATE, null);
        dialog.setVisible(true);
        Bus bus = dialog.getBus();
        if (bus != null && BusDAO.insert(con, bus)) {
            cargarBuses();
        }
    }

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
                mostrarInfo("No se encontró el bus");
            }
        }
    }

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
    private void abrirConductorCreate() {
        ConductorDialog dialog = new ConductorDialog(this, ConductorDialogMode.CREATE, null);
        dialog.setVisible(true);
        Conductor c = dialog.getConductor();
        if (c != null && ConductorDAO.insert(con, c)) {
            cargarConductores();
        }
    }

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
    private void abrirLugarCreate() {
        LugarDialog dialog = new LugarDialog(this, LugarDialogMode.CREATE, null);
        dialog.setVisible(true);
        Lugar l = dialog.getLugar();
        if (l != null && LugarDAO.insert(con, l)) {
            cargarLugares();
        }
    }

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
    private void mostrarAviso(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    private void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean confirmar(String pregunta) {
        return JOptionPane.showConfirmDialog(this, pregunta, "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}