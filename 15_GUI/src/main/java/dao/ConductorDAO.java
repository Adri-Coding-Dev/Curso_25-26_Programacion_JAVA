package dao;

import model.Conductor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Objeto de Acceso a Datos (DAO) para la entidad {@link Conductor}.
 * Encapsula todas las operaciones CRUD contra la tabla "Conductor" de la BBDD.
 * Todos los métodos son estáticos; no se necesita instanciar esta clase.
 */
public class ConductorDAO {

    // ===== CONSULTAS SQL (constantes para facilitar mantenimiento) =====

    private static final String SQL_FIND_ALL  = "SELECT Id_C, Nombre, Apellido FROM Conductor";
    private static final String SQL_FIND_BY_ID = "SELECT Id_C, Nombre, Apellido FROM Conductor WHERE Id_C = ?";
    private static final String SQL_INSERT     = "INSERT INTO Conductor (Id_C, Nombre, Apellido) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE     = "UPDATE Conductor SET Nombre = ?, Apellido = ? WHERE Id_C = ?";
    private static final String SQL_DELETE_BCL = "DELETE FROM BCL WHERE Id_C = ?";
    private static final String SQL_DELETE     = "DELETE FROM Conductor WHERE Id_C = ?";

    // ===== OPERACIONES DE LECTURA =====

    /**
     * Devuelve todos los conductores almacenados en la base de datos.
     *
     * @param con Conexión activa a la BBDD
     * @return Lista de conductores (nunca {@code null}, puede estar vacía)
     */
    public static List<Conductor> findAll(Connection con) {
        List<Conductor> conductores = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                conductores.add(mapearFila(rs));
            }

        } catch (SQLException e) {
            System.err.println("[ConductorDAO] Error al obtener todos los conductores: " + e.getMessage());
        }

        return conductores; // Nunca null, lista vacía si algo falla
    }

    /**
     * Busca un conductor por su identificador único.
     *
     * @param con Conexión activa a la BBDD
     * @param idC Identificador del conductor
     * @return El conductor encontrado, o {@code null} si no existe
     */
    public static Conductor findById(Connection con, int idC) {
        try (PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, idC);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearFila(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("[ConductorDAO] Error al buscar conductor con Id_C=" + idC + ": " + e.getMessage());
        }

        return null;
    }

    // ===== OPERACIONES DE ESCRITURA =====

    /**
     * Inserta un nuevo conductor en la base de datos.
     *
     * @param con       Conexión activa a la BBDD
     * @param conductor Conductor a insertar
     * @return {@code true} si la inserción fue exitosa
     */
    public static boolean insert(Connection con, Conductor conductor) {
        try (PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
            ps.setInt(1, conductor.getId_C());
            ps.setString(2, conductor.getNombre());
            ps.setString(3, conductor.getApellido());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("[ConductorDAO] Error al insertar conductor: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza los datos de un conductor existente (nombre y apellido).
     * El Id_C no se modifica, ya que es la clave primaria.
     *
     * @param con       Conexión activa a la BBDD
     * @param conductor Conductor con los datos actualizados
     * @return {@code true} si la actualización fue exitosa
     */
    public static boolean update(Connection con, Conductor conductor) {
        try (PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, conductor.getNombre());
            ps.setString(2, conductor.getApellido());
            ps.setInt(3, conductor.getId_C());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("[ConductorDAO] Error al actualizar conductor Id_C=" + conductor.getId_C() + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un conductor y sus registros relacionados en BCL usando una transacción.
     * Si alguna de las dos operaciones falla, se hace rollback completo.
     *
     * @param con Conexión activa a la BBDD
     * @param idC Identificador del conductor a eliminar
     * @return {@code true} si la eliminación fue exitosa
     */
    public static boolean delete(Connection con, int idC) {
        try {
            // Iniciar transacción manual para garantizar consistencia
            con.setAutoCommit(false);

            try (PreparedStatement psBcl = con.prepareStatement(SQL_DELETE_BCL);
                 PreparedStatement psCond = con.prepareStatement(SQL_DELETE)) {

                // 1. Eliminar dependencias en tabla BCL
                psBcl.setInt(1, idC);
                psBcl.executeUpdate();

                // 2. Eliminar el conductor
                psCond.setInt(1, idC);
                int filasEliminadas = psCond.executeUpdate();

                con.commit();
                return filasEliminadas == 1;
            }

        } catch (SQLException e) {
            // Si algo falla, revertir todos los cambios
            intentarRollback(con);
            System.err.println("[ConductorDAO] Error al eliminar conductor Id_C=" + idC + ": " + e.getMessage());
            return false;

        } finally {
            // Restaurar el modo de auto-commit siempre, incluso si hay excepción
            restaurarAutoCommit(con);
        }
    }

    // ===== MÉTODOS PRIVADOS DE APOYO =====

    /**
     * Convierte la fila actual del {@link ResultSet} en un objeto {@link Conductor}.
     *
     * @param rs ResultSet posicionado en la fila a mapear
     * @return Instancia de {@link Conductor} con los datos de la fila
     * @throws SQLException si alguna columna no se puede leer
     */
    private static Conductor mapearFila(ResultSet rs) throws SQLException {
        return new Conductor(
                rs.getInt("Id_C"),
                rs.getString("Nombre"),
                rs.getString("Apellido")
        );
    }

    /** Intenta hacer rollback de forma segura, sin propagar excepciones. */
    private static void intentarRollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException ex) {
            System.err.println("[ConductorDAO] Error al hacer rollback: " + ex.getMessage());
        }
    }

    /** Restaura el modo auto-commit de la conexión de forma segura. */
    private static void restaurarAutoCommit(Connection con) {
        try {
            con.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("[ConductorDAO] Error al restaurar auto-commit: " + e.getMessage());
        }
    }
}
