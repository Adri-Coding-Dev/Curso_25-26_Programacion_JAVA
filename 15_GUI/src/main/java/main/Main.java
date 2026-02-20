package main;

import connection.ConnexionBBDD;
import ui.PrincipalView;
import ui.dialogs.LoginDialog;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase MAIN donde se orquesta el programa
 */
public class Main {
    public static void main(String[] args) {
        int numero1 = 12;
        int numero2 = 14;
        boolean numero1Mayor = numero1 > numero2;
        System.out.println(numero1Mayor);

        try {
              Connection con = ConnexionBBDD.getConnection();
//              LoginDialog login = new LoginDialog(null);
//              login.setVisible(true);
//
//            if (login.isAuthenticated()) {
                  PrincipalView pv = new PrincipalView(con);
//            } else {
//                System.exit(0); // usuario canceló o falló login
//            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error de conexión",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }
}


