package dao;

import model.Bus;
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
    /* ===========================
       LISTAR TODOS
       =========================== */
    public static List<Conductor> findAll(Connection con) {
        String sql = "SELECT Id_C, Nombre, Apellido FROM Conductor;";
        List<Conductor> conductores = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                conductores.add(new Conductor(
                        rs.getInt("Id_C"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conductores; // NUNCA null
    }

    /* ===========================
       BUSCAR POR ID
       =========================== */
    public static Conductor findById(Connection con, int idC) {
        String sql = "SELECT * FROM Conductor WHERE Id_C = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idC);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Conductor(
                            rs.getInt("Id_C"),
                            rs.getString("Nombre"),
                            rs.getString("Apellido")
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
    public static boolean insert(Connection con, Conductor conductor) {
        String sql = "INSERT INTO Conductor (Id_C, Nombre, Apellido) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1,conductor.getId_C());
            ps.setString(2, conductor.getNombre());
            ps.setString(3, conductor.getApellido());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===========================
       ACTUALIZAR
       =========================== */
    public static boolean update(Connection con, Conductor conductor) {
        String sql = "UPDATE Bus SET Nombre = ?, Apellido = ? WHERE Id_C = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, conductor.getNombre());
            ps.setString(2, conductor.getApellido());
            ps.setInt(3, conductor.getId_C());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===========================
       BORRAR (con transacción)
       =========================== */
    public static boolean delete(Connection con, int idC) {
        String sqlCond = "DELETE FROM Conductor WHERE Id_C = ?";
        String sqlBcl = "DELETE FROM BCL WHERE Id_C = ?";

        try {
            con.setAutoCommit(false);

            try (PreparedStatement ps1 = con.prepareStatement(sqlBcl);
                 PreparedStatement ps2 = con.prepareStatement(sqlCond)) {

                ps1.setInt(1, idC);
                ps1.executeUpdate();

                ps2.setInt(1, idC);
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
