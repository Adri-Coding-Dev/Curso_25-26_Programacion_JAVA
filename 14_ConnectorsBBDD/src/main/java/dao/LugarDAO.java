package dao;

import model.Lugar;
import utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para implementar la logica de Lugar
 */
public class LugarDAO {
    /**
     * Metodo para listar todos los Lugares
     * @param con -> Conexion estatica dentro del programa (definida en el el main)
     * @return -> Lista con todos los lugares o NULL en caso de que la lista este vacia
     */
    public static List<Lugar> mostrarTodosLosLugares(Connection con){
        String consulta = "SELECT * FROM Lugar;";//Consulta
        try{
            //Preparamos la consulta
            PreparedStatement ps = con.prepareStatement(consulta);
            //Ejecutamos la consulta
            ResultSet rs = ps.executeQuery();
            List<Lugar> lugares = new ArrayList<>();

            //Mientras haya registros...
            while(rs.next()){
                //Guardamos los datos
                int id_Lugar = rs.getInt(1);
                int codigo_Postal = rs.getInt(2);
                String ciudad = rs.getString(3);
                String ubicacion = rs.getString(4);
                //Agregamos un lugar a la lista
                lugares.add(new Lugar(id_Lugar,codigo_Postal,ciudad,ubicacion));
            }

            return lugares;

        }catch (SQLException e){
            System.out.println("ERROR al realizar la consulta: ");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para insertar un Lugar
     * @param con -> Conexion estatica dentro del programa (definida en main)
     * @return -> TRUE si se ha insertado el Lugar correctamente | FALSE si NO se ha insertado el Lugar
     */
    public static int insertarLugar(Connection con){
        String consulta = "INSERT INTO Lugar (Id_L,Cod_Postal,Ciudad,Ubicacion) VALUES (?,?,?,?);";//Consulta

        try{
            //Prepara la consulta
            PreparedStatement ps = con.prepareStatement(consulta);
            //Pedimos los datos al usuario
            ps.setInt(1, Utils.pedirNumeroAlUsuario("Introduce un ID para el lugar: "));
            ps.setInt(2,Utils.pedirNumeroAlUsuario("Introduce un Codigo Postal para el lugar: "));
            ps.setString(3,Utils.pedirCadenaAlUsuario("Introduce una Ciudad para el lugar: "));
            ps.setString(3,Utils.pedirCadenaAlUsuario("Introduce una Ubicacion para el lugar: "));
            //Devolvemos el numero de registros alterados
            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Metodo para borrar un Lugar
     * @param con -> Conexion estatica dentro del programa (definida en main)
     * @param id_L -> ID del Lugar que queramos borrar
     * @return -> Numero de registros alterados (2 es lo normal, porque borramos de dos tablas*)
     * @throws SQLException -> En caso de que haya fallos en la consulta
     */
    public static int borrarLugar(Connection con, int id_L) throws SQLException {
        String consulta1 = "DELETE FROM Lugar WHERE Id_L = ?;";//Consulta de la tabla Lugar
        String consulta2 = "DELETE FROM BCL WHERE Id_L = ?";//Consulta de la tabla BCL

        int registrosAlterados = 0;
        try {
            //No trabajamos sobre la BBDD todavia
            con.setAutoCommit(false);

            //Intentamos la primera consulta
            try(PreparedStatement ps = con.prepareStatement(consulta1)){
                ps.setInt(1,id_L);
                registrosAlterados += ps.executeUpdate();
            }

            //Intentamos la segunda consulta
            try(PreparedStatement ps = con.prepareStatement(consulta2)){
                ps.setInt(1,id_L);
                registrosAlterados += ps.executeUpdate();
            }

            //Si ambas consultas van bien, se aplica en la BBDD
            con.commit();
            return registrosAlterados;

        }catch (SQLException e){
            e.printStackTrace();
            //Se restaura la BBDD
            con.rollback();
        }finally{
            con.setAutoCommit(true);
        }

        return registrosAlterados;
    }

    /**
     * Metodo para editar un Lugar
     * @param con -> Conexion estatica dentro del programa (definida en main)
     * @param id_L -> ID del Lugar que queramos editar
     * @return -> Numero de registros alterados
     */
    public static int editarLugar(Connection con, int id_L) throws SQLException{
        String consulta = "UPDATE Lugar SET Cod_Postal = ?, Ciudad = ?, Ubicacion = ? WHERE Id_L = ?";//Consulta
        try{
            //Preparamos la consulta
            PreparedStatement ps = con.prepareStatement(consulta);
            //Devolvemos el numero de registros alterados
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Metodo para buscar un Lugar
     * @param con -> Conexion estatica dentro del programa (definida en main)
     * @param id_L -> ID del Lugar que queramos borrar
     * @return -> El registro completo en forma de Conductor
     */
    public static Lugar buscarLugar(Connection con,  int id_L) throws SQLException{
        String consulta = "SELECT * FROM Lugar WHERE Id_L = ?;";//Consulta

        try{
            //Preparamos la consulta
            PreparedStatement ps = con.prepareStatement(consulta);
            //Ejecutamos la consulta
            ResultSet rs = ps.executeQuery();
            //Mientras haya registros...
            while(rs.next()){
                //Guardamos los datos
                int id_Lugar = rs.getInt(1);
                int codigo_Postal = rs.getInt(2);
                String ciudad = rs.getString(3);
                String ubicacion = rs.getString(4);
                //Retornamos el Lugar buscado
                return new Lugar(id_Lugar,codigo_Postal,ciudad,ubicacion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
