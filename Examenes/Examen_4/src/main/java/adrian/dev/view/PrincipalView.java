package adrian.dev.view;

//Clases del proyecto
import adrian.dev.controller.dao.BusDAO;
import adrian.dev.model.*;
import adrian.dev.view.dialogs.BusDialog;
import adrian.dev.app.utils.Utils;
import adrian.dev.view.enums.BusDialogMode;

//Clases de Swing
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.util.List;


public class PrincipalView extends JFrame {
    //ATRIBUTOS

    // ===== BOTONES =====
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
    private final JLabel lblEstado = new JLabel("Listo " + Utils.mostrarFechaFormateada());

    // ===== CONEXIÓN =====
    private final Connection con;

    // ===== PALETA DE COLORES MODO OSCURO =====
    private final Color colorFondo = Color.decode("#121212");
    private final Color colorToolbar = Color.decode("#1E1E1E");
    private final Color colorBotones = Color.decode("#2D2D2D");
    private final Color colorTextoBoton = Color.WHITE;
    private final Color colorTablaFondo = Color.decode("#1E1E1E");
    private final Color colorTablaAlt = Color.decode("#2D2D2D");
    private final Color colorTexto = Color.decode("#E0E0E0");
    private final Color colorBorde = Color.decode("#444444");

    // ===== CONSTRUCTOR =====
    public PrincipalView(Connection con) {
        this.con = con;
        setTitle("AUCORSA – Buses");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(colorFondo);
        initToolbar();
        initTabbedPane();
        initBottomPanel();

        cargarBuses();

        activarListenerCerrar();

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
        tabbedPane.setBackground(colorFondo);
        tabbedPane.setForeground(colorTexto); // Color por defecto para todas las pestañas
        tabbedPane.setBorder(BorderFactory.createLineBorder(colorBorde));

        // Crear las pestañas con sus tablas
        modelBuses = crearModelo(new String[]{"ID", "Tipo", "Licencia"});
        tableBuses = crearTabla(modelBuses);
        tabbedPane.addTab("Buses", crearPanelConTabla(tableBuses));

        //Crear Pestaña Conductor (No efecto)
        modelConductores = crearModelo(new String[]{"ID", "Nombre", "Apellidos"});
        tableConductores = crearTabla(modelConductores);
        tabbedPane.addTab("Conductores", crearPanelConTabla(tableConductores));

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
        tabla.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        // Cabecera de la tabla
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(colorToolbar);
        header.setForeground(colorTexto);

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
                modelBuses.addRow(new Object[]{b.getId_Bus(), b.getTipo(), b.getLicencia()});
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
        }
    }

    // ===== ACCIONES DE BOTONES =====
    private void accionAdd() {
        abrirBusCreate();
    }

    private void accionBuscar() {
        abrirBusSearch();
    }

    private void accionEditar() {
        abrirBusEdit();
    }

    private void accionBorrar() {
        borrarBusSeleccionado();
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
            Bus encontrado = BusDAO.findById(con, criterio.getId_Bus());
            if (encontrado != null) {
                modelBuses.setRowCount(0);
                modelBuses.addRow(new Object[]{encontrado.getId_Bus(), encontrado.getTipo(), encontrado.getLicencia()});
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

    private void activarListenerCerrar(){
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                int status = cerrarApp("¿Seguro que quieres cerrar?");
                int contador = 0;
                String seguro = "estas seguro de que ";
                do{
                    cerrarApp("¿Estas seguro de que " + seguro.repeat(contador) + "quieres salir?");
                    contador ++;
                }while(status == 0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                int status = cerrarApp("¿Seguro que quieres cerrar?");
                if (status == 0) ;
            }
            @Override
            public void windowIconified(WindowEvent e) {
            }
            @Override
            public void windowDeiconified(WindowEvent e) {
            }
            @Override
            public void windowActivated(WindowEvent e) {
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
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

    private int cerrarApp(String pregunta){
        boolean cerrar = JOptionPane.showConfirmDialog(this, pregunta, "Salir?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        if(cerrar){
            return 0;
        }
        return 2;
    }
}