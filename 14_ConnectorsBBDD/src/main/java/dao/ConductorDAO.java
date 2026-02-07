package dao;

import model.Conductor;
import utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de la logica del Conductor
 */
public class ConductorDAO {
    /**
     * Metodo para listar todos los Conductores
     * @param con -> Conexion estatica dentro del programa (definida en el el main)
     * @return -> Lista con todos los conductores o NULL en caso de que la lista este vacia
     */
    public static List<Conductor> mostrarTodosLosConductores(Connection con){
        String consulta = "SELECT * FROM Conductor";//Consulta
        try{
            //Preparamos la consulta (para evitar Inyeccion SQL)
            PreparedStatement ps = con.prepareStatement(consulta);
            //Ejecutamos la consulta
            ResultSet rs = ps.executeQuery();
            List<Conductor> conductores = new ArrayList<>();

            //Mientras encuentre registros...
            while(rs.next()){
                //Guardamos sus datos
                int id_c = rs.getInt(1);
                String tipo = rs.getString(2);
                String licencia = rs.getString(3);
                //Agregamos el Conductor a la lista
                conductores.add(new Conductor(id_c,tipo,licencia));
            }
            //Devolvemos la lista
            return conductores;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para insertar un Conductor
     * @param con -> Conexion estatica dentro del programa (definida en main)
     * @return -> TRUE si se ha insertado el Conductor correctamente | FALSE si NO se ha insertado el Conductor
     */
    public static boolean insertarConductor(Connection con){
        String consulta = "INSERT INTO Conductor ( Id_C, Nombre, Apellido) VALUES ( ?, ?, ?);";//Consulta
        try{
            //Preparamos la consulta
            PreparedStatement ps = con.prepareStatement(consulta);
            //Pedimos los datos al usuario
            ps.setInt(1, Utils.pedirNumeroAlUsuario("Introduce un Id del Conductor: "));
            ps.setString(2,Utils.pedirCadenaAlUsuario("Introduce un Nombre para el Conductor: "));
            ps.setString(3, Utils.pedirCadenaAlUsuario("Introduce un Apellido para el Conductor: "));
            //Ejecutamos la consulta
            return ps.executeUpdate()==1;//Si el numero de filas alteradas es 1(se ha tramitado correctamente)
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Metodo para borrar un Conductor
     * @param con -> Conexion estatica dentro del programa (definida en main)
     * @param id_C -> ID del Conductor que queramos borrar
     * @return -> Numero de registros alterados (2 es lo normal, porque borramos de dos tablas*)
     * @throws SQLException -> En caso de que haya fallos en la consulta
     */
    public static int borrarConductor(Connection con, int id_C) throws SQLException {
        String consulta1 = "DELETE FROM Conductor WHERE Id_C = ?;";//Consulta para borrar en la Tabla Conductor
        String consulta2 = "DELETE FROM BCL WHERE Id_C = ?;";//Consulta para borrar en la Tabla BCL
        int registrosAlterados = 0;
        try {
            //Evitamos trabajar directamente con la BBDD
            con.setAutoCommit(false);

            //Intentamos la primera consulta
            try(PreparedStatement ps = con.prepareStatement(consulta1)){
                ps.setInt(1,id_C);
                registrosAlterados += ps.executeUpdate();
            }

            //Intentamos la segunda consulta
            try(PreparedStatement ps = con.prepareStatement(consulta2)){
                ps.setInt(1,id_C);
                registrosAlterados += ps.executeUpdate();
            }

            //Si va bien se aplican los cambios
            con.commit();
            return registrosAlterados;

        }catch (SQLException e){
            e.printStackTrace();
            //Si falla, se vuelve al estado original
            con.rollback();
        }finally{
            con.setAutoCommit(true);
        }

        return 0;
    }

    /**
     * Metodo para editar un Conductor
     * @param con -> Conexion estatica dentro del programa (definida en main)
     * @param id_C -> ID del Conductor que queramos editar
     * @return -> Numero de registros alterados
     */
    public static int editarConductor(Connection con,int id_C){
        String consulta = "UPDATE Conductor SET Nombre = ?, Apellido = ? WHERE Id_C = ?";//Consulta
        try{
            //Preparamos la consulta
            PreparedStatement ps = con.prepareStatement(consulta);
            //Pedimos los datos al usuario
            ps.setString(1,Utils.pedirCadenaAlUsuario("Introduce el nuevo nombre para el Conductor: "));
            ps.setString(2, Utils.pedirCadenaAlUsuario("Introduce el nuevo apellido para el Conductor: "));
            ps.setInt(3,id_C);

            //Retornamos el numero de registros alterados
            return ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Metodo para buscar un Conductor
     * @param con -> Conexion estatica dentro del programa (definida en main)
     * @param id_C -> ID del Conductor que queramos borrar
     * @return -> El registro completo en forma de Conductor
     */
    public static Conductor buscarConductor(Connection con,int id_C){
        String consulta = "SELECT * FROM Conductor WHERE Id_C = ?;";//Consulta
        try{
            //Preparamos la consulta
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setInt(1, id_C);
            //Ejecutamos la consulta
            ResultSet rs = ps.executeQuery();
            //Si hay registro...
            while(rs.next()){
                //Guardamos los datos
                int id_Co = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                //Retornamos el Conductor con los datos guardados
                return new Conductor(id_Co,nombre,apellido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
