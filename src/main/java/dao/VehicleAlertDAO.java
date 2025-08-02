package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.VehicleAlertDTO;

/**
 *
 * @author shano
 */
/**
 * Data Access Object (DAO) class for retrieving vehicle alert information. This
 * class provides methods to interact with the database and retrieve vehicle
 * alert data as a list of VehicleAlertDTO objects.
 */
public class VehicleAlertDAO {

    /**
     * Retrieves all vehicle alerts from the database.
     *
     * @return List of VehicleAlertDTO objects containing all vehicle alerts
     * @throws SQLException if a database access error occurs
     */
    public List<VehicleAlertDTO> getAll() throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<VehicleAlertDTO> objs = null;

        try {
            // Get database connection
            con = DataSource.getConnection();

            // Prepare SQL query
            pstmt = con.prepareStatement(
                    "SELECT * FROM vehicle_alerts_view ORDER BY vehicle_id");

            // Execute query
            rs = pstmt.executeQuery();
            objs = new ArrayList<VehicleAlertDTO>();

            // Process result set
            while (rs.next()) {
                VehicleAlertDTO obj = new VehicleAlertDTO();
                obj.setUserId(rs.getInt("user_id"));
                obj.setVehicleId(rs.getInt("vehicle_id"));
                obj.setAlertType(rs.getString("alert_type"));
                obj.setDescription(rs.getString("alert_description"));
                objs.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Close resources in reverse order of creation
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return objs;
    }

    /**
     * Retrieves a specific vehicle alert by its ID from the database.
     *
     * @param userId The ID of the vehicle alert to retrieve
     * @return VehicleAlertDTO object containing the requested vehicle alert, or
     * null if not found
     * @throws SQLException if a database access error occurs
     */
    public List<VehicleAlertDTO> getAlertsByUserId(int userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<VehicleAlertDTO> alerts = new ArrayList<>();

        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM vehicle_alerts_view WHERE user_id = ?");
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                VehicleAlertDTO alert = new VehicleAlertDTO();
                alert.setUserId(rs.getInt("user_id"));
                alert.setVehicleId(rs.getInt("vehicle_id"));
                alert.setAlertType(rs.getString("alert_type"));
                alert.setDescription(rs.getString("alert_description"));
                alerts.add(alert);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
        return alerts;
    }
}
