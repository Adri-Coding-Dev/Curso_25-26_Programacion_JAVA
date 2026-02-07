package dao;

import model.Bus;
import utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de desarrollar la logica de los metodos de la tabla BUS
 */

public class BusDAO {
    /**
     * Metodo para listar todos los buses
     * @param con -> Conexion estatica dentro del programa (definida en el el main)
     * @return -> Lista con todos los buses o NULL en caso de que la lista este vacia
     */
    public static List<Bus> mostrarTodosLosBuses(Connection con){
        String consulta = "SELECT * FROM Bus"; //Consulta
        try{
            //Preparamos la consulta para evitar Inyeccion SQL
            PreparedStatement ps = con.prepareStatement(consulta);
            //Ejecutamos la consulta y lo guardamos en un ResultSet -> Como una lista con los registros
            ResultSet rs = ps.executeQuery();
            List<Bus> buses = new ArrayList<>();
            //Mientras haya registros...
            while(rs.next()){
                //Guardamos los datos en variables
                String id_bus = rs.getString(1);
                String tipo = rs.getString(2);
                String licencia = rs.getString(3);
                //Creamos un bus y lo agregamos a la lista
                buses.add(new Bus(id_bus,tipo,licencia));
            }
            return buses;
        }catch (SQLException e){
            e.printStackTrace();//Error en la consulta
        }
        return null;
    }

    /**
     * Metodo para insertar un Bus
     * @param con -> Conexion estatica dentro del programa (definida en main)
     * @return -> TRUE si se ha insertado el BUS correctamente | FALSE si NO se ha insertado el BUS
     */
    public static boolean insertarBus(Connection con){
        String consulta = "INSERT INTO Bus ( Id_B, Tipo, Licencia) VALUES ( ?, ?, ?);";//Consulta
        try{
            //Preparamos la consulta
            PreparedStatement ps = con.prepareStatement(consulta);
            //Pedimos al usuario los datos
            ps.setString(1, Utils.pedirCadenaAlUsuario("Introduce un Id del Bus (BXXX): "));
            ps.setString(2,Utils.pedirCadenaAlUsuario("Introduce un tipo del Bus [Urbano/Interurbano/Turismo/Escolar]: "));
            ps.setString(3, Utils.pedirCadenaAlUsuario("Introude una licencia para el Bus (LICXXX): "));
            //Ejecutamos la consulta con los parametros del usuario
            int registrosAlterados = ps.executeUpdate();

            return registrosAlterados==1; //Devuelve TRUE si registros alterados es 1 (se ha tramitado bien la consulta)
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false; //En caso de que no se haya alterado ningun registro (no se ha insertado nada)
    }


    /**
     * Metodo para borrar un BUS
     * @param con -> Conexion estatica dentro del programa (definida en main)
     * @param id_Bus -> ID del Bus que queramos borrar
     * @return -> Numero de registros alterados (2 es lo normal, porque borramos de dos tablas*)
     * @throws SQLException -> En caso de que haya fallos en la consulta
     */
    public static int borrarBus(Connection con, String id_Bus) throws SQLException {
        String consulta1 = "DELETE FROM Bus WHERE Id_B = ?;"; //Consulta dentro de la Tabla BUS
        String consulta2 = "DELETE FROM BCL WHERE Id_B = ?;"; //Consulta dentro de la TABLA BCL -> *Tiene Clave Foranea
        int registrosAlterados = 0;
        try {
            //Para que no se ejecute la consulta (por temas de integridad de la consulta)
            con.setAutoCommit(false);

            //Preparamos la primera consulta y la ejecutamos
            try(PreparedStatement ps = con.prepareStatement(consulta1)){
                ps.setString(1,id_Bus);
                registrosAlterados += ps.executeUpdate();
            }

            //Preparamos la segunda consulta y la ejecutamos
            try(PreparedStatement ps = con.prepareStatement(consulta2)){
                ps.setString(1,id_Bus);
                registrosAlterados += ps.executeUpdate();
            }

            //En caso de que no haya fallos en ninguna consulta, se realiza sobre la BBDD
            con.commit();
            return registrosAlterados;

        }catch (SQLException e){
            e.printStackTrace();
            //En caso de que haya fallos, se restablece la consulta (no se tramita)
            con.rollback();
        }finally{
            //Volvemos al flujo de antes
            con.setAutoCommit(true);
        }

        return 0;
    }

    /**
     * Metodo para editar un BUS
     * @param con -> Conexion estatica dentro del programa (definida en main)
     * @param id_Bus -> ID del Bus que queramos editar
     * @return -> Numero de registros alterados
     */
    public static int editarBus(Connection con,String id_Bus){
        String consulta = "UPDATE Bus SET Tipo = ?, Licencia = ? WHERE Id_B = ?";//Consulta
        try{
            //Preparamos la consulta
            PreparedStatement ps = con.prepareStatement(consulta);
            //Pedimos los datos al usuario
            ps.setString(1,Utils.pedirCadenaAlUsuario("Introduce el nuevo tipo de Bus [Urbano/Interurbano/Turismo/Escolar]: "));
            ps.setString(2, Utils.pedirCadenaAlUsuario("Introduce la nueva licencia para el Bus"));
            ps.setString(3,id_Bus);
            //Ejecutamos la consulta y devolvemos los registros
            return ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Metodo para buscar un BUS
     * @param con -> Conexion estatica dentro del programa (definida en main)
     * @param id_Bus -> ID del Bus que queramos borrar
     * @return -> El registro completo en forma de BUS
     */
    public static Bus buscarBus(Connection con,String id_Bus){
        String consulta = "SELECT * FROM Bus WHERE Id_B = ?;";//Consulta
        try{
            //Preparamos la consulta
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setString(1, id_Bus);
            //Ejecutamos la consulta
            ResultSet rs = ps.executeQuery();
            //Si hay algo(Bus encontrado), guardamos sus datos
            if(rs.next()){
                String id_B = rs.getString(1);
                String tipo = rs.getString(2);
                String licencia = rs.getString(3);
                //Devolvemos el Bus con los datos guardados
                return new Bus(id_B,tipo,licencia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
