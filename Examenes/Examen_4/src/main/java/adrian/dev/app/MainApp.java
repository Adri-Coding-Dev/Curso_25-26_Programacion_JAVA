package adrian.dev.app;
/**
 * @autor Adrian
 */

import adrian.dev.controller.db.ConnectionDDBB;
import adrian.dev.view.PrincipalView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class MainApp {
    /**
     * Metodo main necesario para ejecutar la aplicacion
     * @param args -> Conjunto de parametros que puede contener la llamada del metodo main
     */
    public static void main (String[]args){
        try{
            Connection con = ConnectionDDBB.getConnection();
            PrincipalView pv = new PrincipalView(con);

        }catch (SQLException e){
            JOptionPane.showMessageDialog(
                    null,
                    "No se ha podido entablar conexion con la BBDD",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
