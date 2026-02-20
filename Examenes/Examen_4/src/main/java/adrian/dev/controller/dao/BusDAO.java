package adrian.dev.controller.dao;

import adrian.dev.model.Bus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de la entidad Bus.
 * Encargado exclusivamente del acceso a datos (JDBC).
 */
public class BusDAO {

    /* ===========================
       LISTAR TODOS
       =========================== */
    public static List<Bus> findAll(Connection con) {
        String sql = "SELECT Id_B, Tipo, Licencia FROM Bus";
        List<Bus> buses = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                buses.add(new Bus(
                        rs.getString("Id_B"),
                        rs.getString("Tipo"),
                        rs.getString("Licencia")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return buses; // NUNCA null
    }

    /* ===========================
       BUSCAR POR ID
       =========================== */
    public static Bus findById(Connection con, String idBus) {
        String sql = "SELECT Id_B, Tipo, Licencia FROM Bus WHERE Id_B = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idBus);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Bus(
                            rs.getString("Id_B"),
                            rs.getString("Tipo"),
                            rs.getString("Licencia")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /* ===========================
       INSERTAR
       =========================== */
    public static boolean insert(Connection con, Bus bus) {
        String sql = "INSERT INTO Bus (Id_B, Tipo, Licencia) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bus.getId_Bus());
            ps.setString(2, bus.getTipo());
            ps.setString(3, bus.getLicencia());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===========================
       ACTUALIZAR
       =========================== */
    public static boolean update(Connection con, Bus bus) {
        String sql = "UPDATE Bus SET Tipo = ?, Licencia = ? WHERE Id_B = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bus.getTipo());
            ps.setString(2, bus.getLicencia());
            ps.setString(3, bus.getId_Bus());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===========================
       BORRAR (con transacción)
       =========================== */
    public static boolean delete(Connection con, String idBus) {
        String sqlBus = "DELETE FROM Bus WHERE Id_B = ?";
        String sqlBcl = "DELETE FROM BCL WHERE Id_B = ?";

        try {
            con.setAutoCommit(false);

            try (PreparedStatement ps1 = con.prepareStatement(sqlBcl);
                 PreparedStatement ps2 = con.prepareStatement(sqlBus)) {

                ps1.setString(1, idBus);
                ps1.executeUpdate();

                ps2.setString(1, idBus);
                int afectados = ps2.executeUpdate();

                con.commit();
                return afectados == 1;
            }

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;

        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
