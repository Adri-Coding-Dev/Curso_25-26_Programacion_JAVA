package ui;

import connection.ConnexionBBDD;
import dao.BusDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class BusPanel extends JPanel {
    private static Connection con;

    static {
        try {
            con = ConnexionBBDD.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private BusDAO busService = new BusDAO();

    public BusPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(title(), BorderLayout.NORTH);
        add(tabs(), BorderLayout.CENTER);
    }

    private JLabel title() {
        JLabel label = new JLabel("GESTIÓN DE BUSES", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 22f));
        label.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        return label;
    }

    private JTabbedPane tabs() {
        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Mostrar", showAllPanel());
        tabs.add("Insertar", insertPanel());
        tabs.add("Editar", editPanel());
        tabs.add("Borrar", deletePanel());

        return tabs;
    }

    private JPanel showAllPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);

        JButton load = new JButton("Cargar buses");
        load.addActionListener(e ->
                area.setText(busService.mostrarTodosLosBuses(con).toString())
        );

        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        panel.add(load, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel insertPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        JTextField id = new JTextField();
        JTextField modelo = new JTextField();

        JButton insert = new JButton("Insertar");
        insert.addActionListener(e ->
                busService.insertarBus(con)
        );

        panel.add(new JLabel("ID:"));
        panel.add(id);
        panel.add(new JLabel("Modelo:"));
        panel.add(modelo);
        panel.add(insert);

        return panel;
    }

    private JPanel editPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        JTextField id = new JTextField();
        JTextField nuevoModelo = new JTextField();

        JButton edit = new JButton("Editar");
        edit.addActionListener(e ->
                busService.editarBus(con,id.getText())
        );

        panel.add(new JLabel("ID Bus:"));
        panel.add(id);
        panel.add(new JLabel("Nuevo modelo:"));
        panel.add(nuevoModelo);
        panel.add(edit);

        return panel;
    }

    private JPanel deletePanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        JTextField id = new JTextField();

        JButton delete = new JButton("Borrar");
        delete.addActionListener(e ->
                {
                    try {
                        busService.borrarBus(con,id.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );

        panel.add(new JLabel("ID Bus:"));
        panel.add(id);
        panel.add(delete);

        return panel;
    }
}