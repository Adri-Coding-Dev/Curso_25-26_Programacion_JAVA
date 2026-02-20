package dao;

import model.Lugar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de la logica del Conductor
 */
public class LugarDAO {
    /* ===========================
       LISTAR TODOS
       =========================== */
    public static List<Lugar> findAll(Connection con) {
        String sql = "SELECT Id_L, Cod_Postal, Ciudad, Ubicacion FROM Lugar;";
        List<Lugar> lugares = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lugares.add(new Lugar(
                        rs.getInt("Id_L"),
                        rs.getString("Cod_Postal"),
                        rs.getString("Ciudad"),
                        rs.getString("Ubicacion")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lugares; // NUNCA null
    }

    /* ===========================
       BUSCAR POR ID
       =========================== */
    public static Lugar findById(Connection con, int idL) {
        // Corrección: columnas correctas
        String sql = "SELECT Id_L, Cod_Postal, Ciudad, Ubicacion FROM Lugar WHERE Id_L = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idL);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Lugar(
                            rs.getInt("Id_L"),
                            rs.getString("Cod_Postal"),
                            rs.getString("Ciudad"),
                            rs.getString("Ubicacion")
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
    public static boolean insert(Connection con, Lugar lugar) {
        String sql = "INSERT INTO Lugar (Id_L, Cod_Postal, Ciudad, Ubicacion) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, lugar.getId_L());
            ps.setString(2, lugar.getCod_Post());
            ps.setString(3, lugar.getCiudad());
            ps.setString(4, lugar.getUbicacion());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===========================
       ACTUALIZAR
       =========================== */
    public static boolean update(Connection con, Lugar lugar) {
        String sql = "UPDATE Lugar SET Cod_Postal = ?, Ciudad = ?, Ubicacion = ? WHERE Id_L = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lugar.getCod_Post());
            ps.setString(2, lugar.getCiudad());
            ps.setString(3, lugar.getUbicacion());
            ps.setInt(4, lugar.getId_L());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===========================
       BORRAR (con transacción)
       =========================== */
    public static boolean delete(Connection con, int idL) {
        String sqlLugar = "DELETE FROM Lugar WHERE Id_L = ?";
        String sqlBcl = "DELETE FROM BCL WHERE Id_L = ?";

        try {
            con.setAutoCommit(false);

            try (PreparedStatement ps1 = con.prepareStatement(sqlBcl);
                 PreparedStatement ps2 = con.prepareStatement(sqlLugar)) {

                ps1.setInt(1, idL);
                ps1.executeUpdate();

                ps2.setInt(1, idL);
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