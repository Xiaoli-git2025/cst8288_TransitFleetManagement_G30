/* File: DataSource.java
 * Author: Xiaoli He
 * Date: 2025/7/22
 * Description: user dao
 */
package dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.MaintenanceAlertDTO;

/**
 * DAO implements DAOInterface
 *
 * @author
 */
public class MaintenanceAlertDAO implements DAOInterface<MaintenanceAlertDTO> {

    public List<MaintenanceAlertDTO> getMaintenanceAlertReport() {
        List<MaintenanceAlertDTO> reportList = new ArrayList<>();
        String sql = "SELECT v.vehicle_id, v.vehicle_number, "
                + "c.component_name, a.alert_type, a.alert_description, "
                + "ma.alert_date, ms.schedule_date, ms.note, ma.resolved "
                + "FROM MaintenanceAlert ma "
                + "JOIN Alert a ON ma.alert_id = a.alert_id "
                + "JOIN VehicleComponent c ON ma.component_id = c.component_id "
                + "JOIN Vehicle v ON c.vehicle_id = v.vehicle_id "
                + "LEFT JOIN MaintenanceSchedule ms ON ma.maintenance_id = ms.maintenance_id";

        try (Connection con = DataSource.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                MaintenanceAlertDTO dto = new MaintenanceAlertDTO();
                dto.setVehicleId(rs.getInt("vehicle_id"));
                dto.setVehicleNumber(rs.getString("vehicle_number"));
                dto.setComponentName(rs.getString("component_name"));
                dto.setAlertType(rs.getString("alert_type"));
                dto.setAlertDescription(rs.getString("alert_description"));
                dto.setAlertDate(rs.getDate("alert_date"));
                dto.setScheduleDate(rs.getString("schedule_date")); // From MaintenanceSchedule
                dto.setNote(rs.getString("note"));
                dto.setResolved(rs.getBoolean("resolved"));
                reportList.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportList;
    }

    public List<MaintenanceAlertDTO> getMaintenanceCostReport() {
        List<MaintenanceAlertDTO> reportList = new ArrayList<>();
        String sql = "SELECT v.vehicle_id, v.vehicle_number, "
                + "c.component_name, ms.schedule_date, ms.note, ms.maintenance_cost "
                + "FROM MaintenanceAlert ma "
                + "JOIN VehicleComponent c ON ma.component_id = c.component_id "
                + "JOIN Vehicle v ON c.vehicle_id = v.vehicle_id "
                + "JOIN MaintenanceSchedule ms ON ma.maintenance_id = ms.maintenance_id";

        try (Connection con = DataSource.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                MaintenanceAlertDTO dto = new MaintenanceAlertDTO();
                dto.setVehicleId(rs.getInt("vehicle_id"));
                dto.setVehicleNumber(rs.getString("vehicle_number"));
                dto.setComponentName(rs.getString("component_name"));
                dto.setScheduleDate(rs.getString("schedule_date"));
                dto.setNote(rs.getString("note"));
                dto.setMaintenanceCost(rs.getBigDecimal("maintenance_cost"));
                reportList.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportList;
    }

    /**
     * get all
     *
     * @return list
     * @throws SQLException throw SQLExpection
     */
    @Override
    public List<MaintenanceAlertDTO> getAll() throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MaintenanceAlertDTO> objs = null;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM maintenancealert ORDER BY alert_id");
            rs = pstmt.executeQuery();
            objs = new ArrayList<MaintenanceAlertDTO>();
            while (rs.next()) {
                MaintenanceAlertDTO obj = new MaintenanceAlertDTO();
                obj.setMaintenanceId(rs.getInt("maintenance_id"));
                obj.setAlertId(rs.getInt("alert_id"));
                obj.setComponentId(rs.getInt("component_id"));
                obj.setAlertDate(rs.getDate("alert_date"));
                obj.setReporterId(rs.getInt("reporter_id"));
                obj.setResolved(rs.getBoolean("resolved"));
                objs.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;

        } finally {
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
     * add object
     *
     * @param obj added object
     * @return true for success, false for fail
     */
    public boolean add(MaintenanceAlertDTO obj) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement("INSERT INTO maintenancealert (alert_id, component_id, alert_date, reporter_id, resolved) VALUES(?, ?, ?, ?,?)");
            pstmt.setInt(1, obj.getAlertId());
            pstmt.setInt(2, obj.getComponentId());
            pstmt.setDate(3, obj.getAlertDate());
            pstmt.setInt(4, obj.getReporterId());
            pstmt.setBoolean(5, obj.isResolved());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            ret = false;
        } finally {
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
        return ret;
    }

    /**
     * get object by id
     *
     * @param objId object id
     * @return object
     */
    public MaintenanceAlertDTO getById(int objId) {
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MaintenanceAlertDTO obj = null;

        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM maintenancealert WHERE maintenance_id = ?");
            pstmt.setInt(1, objId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                obj = new MaintenanceAlertDTO();
                obj.setMaintenanceId(rs.getInt("maintenance_id"));
                obj.setAlertId(rs.getInt("alert_id"));
                obj.setComponentId(rs.getInt("component_id"));
                obj.setAlertDate(rs.getDate("alert_date"));
                obj.setReporterId(rs.getInt("reporter_id"));
                obj.setResolved(rs.getBoolean("resolved"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                //if (con != null) {
                //    con.close();
                //}
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return obj;
    }

    /**
     * update object
     *
     * @param object updated object
     * @return true for success, false for fail
     */
    public boolean update(MaintenanceAlertDTO object) {
        Connection con;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "UPDATE maintenancealert SET alert_id = ?, component_id = ?, alert_date = ?, reporter_id = ?, "
                    + "resolved = ? WHERE maintenance_id = ?");
            pstmt.setInt(1, object.getAlertId());
            pstmt.setInt(2, object.getComponentId());
            pstmt.setDate(3, object.getAlertDate());
            pstmt.setInt(4, object.getReporterId());
            pstmt.setBoolean(5, object.isResolved());
            pstmt.setInt(6, object.getMaintenanceId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                ret = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ret = false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                //if (con != null) {
                //    con.close();
                //}
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ret;
    }

    /**
     * delete object
     *
     * @param objId object id
     * @return true for success, false for fail
     */
    public boolean delete(int objId) {
        Connection con;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "DELETE FROM maintenancealert WHERE maintenance_id = ?");
            pstmt.setInt(1, objId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            ret = false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                //if (con != null) {
                //    con.close();
                //}
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ret;
    }
}
