package ui;

import model.Bus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PrincipalView extends JFrame {
    //Botones declaracion
    private final JButton btnAdd = new JButton("Añadir");
    private final JButton btnBuscar = new JButton("Buscar");
    private final JButton btnBorrar = new JButton("Borrar");
    private final JButton btnEditar = new JButton("Editar");
    private final JButton btnListar = new JButton("Listar");
    private final JButton btnRefrescar = new JButton("Refrescar");

    //Label Aucorsa
    private final JLabel lblEstado = new JLabel("Invitame a un Cafe <CAFE>");

    private DefaultTableModel tmdl = new DefaultTableModel();
    private JTable tabla = new JTable();

    //Constructor con titulo
    public PrincipalView(){
        this.setTitle("Ventana principal");
        setDefaultCloseOperation(3);

        setSize(850,450);
        //Centrado en el medio
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10,10));
        //Botones separados arriba
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
        top.add(btnAdd);
        top.add(btnBuscar);
        top.add(btnBorrar);
        top.add(btnEditar);
        top.add(btnListar);
        top.setBackground(Color.BLACK);

        JPanel westSide = new JPanel();
        westSide.add(btnRefrescar);
        add(westSide, BorderLayout.WEST);
        add(top, BorderLayout.NORTH);

        JPanel bot = new JPanel(new FlowLayout((FlowLayout.CENTER)));
        bot.setBackground(Color.blue);
        lblEstado.setForeground(Color.WHITE);
        bot.add(lblEstado);


        add(bot, BorderLayout.SOUTH);

        //Agregar una tabla al centro
        tmdl = new DefaultTableModel(new String[]{"Numero","Nombre","Apellido"}, 0);
        tabla = new JTable(tmdl);
        tabla.setBackground(Color.green); //Se la pela
        add(new JScrollPane(tabla),BorderLayout.CENTER);

        setVisible(true);
    }
}
