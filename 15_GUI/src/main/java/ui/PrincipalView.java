package ui;

import dao.BusDAO;
import dao.ConductorDAO;
import dao.LugarDAO;
import model.Bus;
import model.Conductor;
import model.Lugar;
import ui.dialogs.BusDialog;
import ui.dialogs.ConductorDialog;
import ui.dialogs.LugarDialog;
import utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class PrincipalView extends JFrame {

    // ===== BOTONES =====
    private final JButton btnAdd = new JButton("Añadir");
    private final JButton btnBuscar = new JButton("Buscar");
    private final JButton btnBorrar = new JButton("Borrar");
    private final JButton btnEditar = new JButton("Editar");
    private final JButton btnRefrescar = new JButton("Refrescar");

    // ===== LABELS =====
    private final JLabel lblEstado = new JLabel("Invitame a un café <CAFÉ>. BBDD Utilizada: " + Utils.sacarNombreBBDD());
    private final JLabel lblEntidad = new JLabel("Entidad actual: Buses");

    // ===== TABLA =====
    private DefaultTableModel tmdl;
    private JTable tabla;

    // ===== SELECTOR ENTIDAD =====
    private final JComboBox<String> cbEntidad = new JComboBox<>(new String[]{"Buses", "Conductores", "Lugares"});

    // ===== CONEXIÓN =====
    private final Connection con;

    // ===== COLORES =====
    private final Color colorFondo = Color.decode("#E3F2FD");
    private final Color colorTop = Color.decode("#1565C0");
    private final Color colorBot = Color.decode("#1E88E5");
    private final Color colorBotones = Color.decode("#42A5F5");
    private final Color colorTablaAlt = Color.decode("#F1F1F1");

    // ===== CONSTRUCTOR =====
    public PrincipalView(Connection con) {
        this.con = con;
        setTitle("Gestión de Aucorsa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(colorFondo);

        initTopPanel();
        initWestPanel();
        initBottomPanel();
        initTable();
        initActions();

        setVisible(true);
    }

    // ===== PANEL SUPERIOR =====
    private void initTopPanel() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        top.setBackground(colorTop);

        // Botones
        JButton[] botones = {btnAdd, btnBuscar, btnBorrar, btnEditar};
        for (JButton b : botones) {
            b.setBackground(colorBotones);
            b.setForeground(Color.WHITE);
            top.add(b);
        }

        // ComboBox selector entidad
        cbEntidad.setBackground(Color.WHITE);
        top.add(Box.createHorizontalStrut(20));
        top.add(new JLabel("Seleccionar entidad:"));
        top.add(cbEntidad);

        // Label entidad
        lblEntidad.setForeground(Color.WHITE);
        lblEntidad.setFont(lblEntidad.getFont().deriveFont(Font.BOLD));
        top.add(Box.createHorizontalStrut(20));
        top.add(lblEntidad);

        add(top, BorderLayout.NORTH);
    }

    // ===== PANEL IZQUIERDO =====
    private void initWestPanel() {
        JPanel westSide = new JPanel();
        westSide.setBackground(colorFondo);
        btnRefrescar.setBackground(colorBotones);
        btnRefrescar.setForeground(Color.WHITE);
        westSide.add(btnRefrescar);
        add(westSide, BorderLayout.WEST);
    }

    // ===== PANEL INFERIOR =====
    private void initBottomPanel() {
        JPanel bot = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bot.setBackground(colorBot);
        lblEstado.setForeground(Color.WHITE);
        bot.add(lblEstado);
        add(bot, BorderLayout.SOUTH);
    }

    // ===== TABLA CENTRAL =====
    private void initTable() {
        tmdl = new DefaultTableModel(new String[]{"ID", "Tipo", "Licencia"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabla = new JTable(tmdl);

        // Filas alternadas
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : colorTablaAlt);
                }
                return c;
            }
        });

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    // ===== EVENTOS =====
    private void initActions() {
        btnAdd.addActionListener(e -> {
            if (cbEntidad.getSelectedItem().equals("Buses")) abrirBusDialog();
            else if (cbEntidad.getSelectedItem().equals("Conductores")) abrirConductorDialog();
            else if (cbEntidad.getSelectedItem().equals("Lugares")) abrirLugarDialog();
        });

        btnRefrescar.addActionListener(e -> cargarTablaActual());

        btnBorrar.addActionListener(e -> {
            if (cbEntidad.getSelectedItem().equals("Buses")) borrarBusSeleccionado();
            else if (cbEntidad.getSelectedItem().equals("Conductores")) borrarConductorSeleccionado();
            else if (cbEntidad.getSelectedItem().equals("Lugares")) borrarLugarSeleccionado();
        });

        cbEntidad.addActionListener(e -> {
            lblEntidad.setText("Entidad actual: " + cbEntidad.getSelectedItem());
            cargarTablaActual();
        });
    }

    // ===== FUNCIONALIDAD TABLA =====
    private void cargarTablaActual() {
        String entidad = (String) cbEntidad.getSelectedItem();
        if (entidad.equals("Buses")) {
            cargarBuses();
        }else if (entidad.equals("Conductores")) {
            cargarConductores();
        }else if (entidad.equals("Lugares")){
            cargarLugares();
        }else {
            tmdl.setRowCount(0);
        }
    }

    private void cargarBuses() {
        tmdl.setRowCount(0);
        List<Bus> buses = BusDAO.findAll(con);

        if (buses == null || buses.isEmpty()) {
            return;
        }

        for (Bus b : buses) {
            tmdl.addRow(new Object[]{b.getId_bus(), b.getTipo(), b.getLicencia()});
        }

    }

    private void cargarConductores(){
        tmdl.setRowCount(0);
        List<Conductor> conductores = ConductorDAO.findAll(con);

        if(conductores == null || conductores.isEmpty()){
            return;
        }
        for(Conductor cond : conductores){
            tmdl.addRow(new Object[]{cond.getId_C(), cond.getNombre(), cond.getApellido()});
        }

    }

    private void cargarLugares(){
        tmdl.setRowCount(0);
        List<Lugar> lugares = LugarDAO.findAll(con);

        if(lugares == null || lugares.isEmpty()){
            return;
        }
        for(Lugar lugar : lugares){
            tmdl.addRow(new Object[]{lugar.getId_L(),lugar.getCod_Post(),lugar.getCiudad(),lugar.getUbicacion()});
        }
    }

    private void abrirBusDialog() {
        BusDialog dialog = new BusDialog(this);
        dialog.setVisible(true);

        Bus bus = dialog.getBus();
        if (bus != null) {
            boolean ok = BusDAO.insert(con, bus);
            if (ok) {
                cargarBuses();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo insertar el bus", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void abrirConductorDialog(){
        ConductorDialog dialog = new ConductorDialog(this);
        dialog.setVisible(true);

        Conductor cond = dialog.getConductor();
        if(cond != null){
            boolean ok = ConductorDAO.insert(con, cond);
            if (ok) {
                cargarConductores();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo insertar el Conductor", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void abrirLugarDialog(){
        LugarDialog dialog = new LugarDialog(this);
        dialog.setVisible(true);

        Lugar lugar = dialog.getLugar();
        if(lugar != null){
            boolean ok = LugarDAO.insert(con,lugar);
            if(ok){
                cargarLugares();
            }else{
                JOptionPane.showMessageDialog(this,"No se pudo insertar el Lugar", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void borrarBusSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un bus de la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idBus = tmdl.getValueAt(fila, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar el bus " + idBus + "?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (BusDAO.delete(con, idBus)) {
                cargarBuses();
            }
        }
    }

    private void borrarConductorSeleccionado(){
        int fila = tabla.getSelectedRow();
        if (fila == -1){
            JOptionPane.showMessageDialog(this,"Selecciona un conductor de la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idC = Integer.parseInt(tmdl.getValueAt(fila, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar el Conductor "+ idC +"?", "Confirmar",JOptionPane.YES_NO_OPTION);

        if(confirm == JOptionPane.YES_OPTION){
            if(ConductorDAO.delete(con,idC)){
                cargarConductores();
            }
        }
    }

    private void borrarLugarSeleccionado(){
        int fila = tabla.getSelectedRow();
        if (fila == -1){
            JOptionPane.showMessageDialog(this,"Selecciona un Lugar de la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idL = Integer.parseInt(tmdl.getValueAt(fila, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar el Lugar "+ idL +"?", "Confirmar",JOptionPane.YES_NO_OPTION);

        if(confirm == JOptionPane.YES_OPTION){
            if(LugarDAO.delete(con,idL)){
                cargarLugares();
            }
        }
    }
}
