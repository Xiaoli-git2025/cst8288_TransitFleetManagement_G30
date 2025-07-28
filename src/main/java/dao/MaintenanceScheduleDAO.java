/* File: DataSource.java
 * Author: Xiaoli He
 * Date: 2025/7/22
 * Description: user dao
 */
package dao;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.MaintenanceScheduleDTO;
/**
 * DAO implements DAOInterface
 * @author 
 */
public class MaintenanceScheduleDAO implements DAOInterface<MaintenanceScheduleDTO>{
    /**
     * get all 
     * @return list
     * @throws SQLException throw SQLExpection
     */
    @Override
    public List<MaintenanceScheduleDTO> getAll()throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MaintenanceScheduleDTO> objs = null;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM maintenanceschedule ORDER BY schedule_id");
            rs = pstmt.executeQuery();
            objs = new ArrayList<MaintenanceScheduleDTO>();
            while (rs.next()) {
                MaintenanceScheduleDTO obj = new MaintenanceScheduleDTO();
                obj.setScheduleId(rs.getInt("schedule_id"));
                obj.setMaintenanceId(rs.getInt("maintenance_id"));
                obj.setScheduleDate(rs.getDate("schedule_date"));
                obj.setNote(rs.getString("note"));
                obj.setMaintenanceCost(rs.getBigDecimal("maintenance_cost"));
                obj.setCompleted(rs.getBoolean("completed"));
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
     * @param obj added object
     * @return true for success, false for fail
     */
    public boolean add(MaintenanceScheduleDTO obj) {
        Connection con =null;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement("INSERT INTO maintenanceschedule (maintenance_id, schedule_date, note, maintenance_cost, completed) VALUES(?, ?, ?,?,?)");
            pstmt.setInt(1, obj.getMaintenanceId());
            pstmt.setDate(2, obj.getScheduleDate());
            pstmt.setString(3, obj.getNote());
            pstmt.setBigDecimal(4, obj.getMaintenanceCost());
            pstmt.setBoolean(5, obj.isCompleted());
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
     * @param objId object id
     * @return object
     */
    public MaintenanceScheduleDTO getById(int objId) {
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MaintenanceScheduleDTO obj = null;

        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM maintenanceschedule WHERE schedule_id = ?");
            pstmt.setInt(1, objId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                obj = new MaintenanceScheduleDTO();
                obj.setScheduleId(rs.getInt("schedule_id"));
                obj.setMaintenanceId(rs.getInt("maintenance_id"));
                obj.setScheduleDate(rs.getDate("schedule_date"));
                obj.setNote(rs.getString("note"));
                obj.setMaintenanceCost(rs.getBigDecimal("maintenance_cost"));
                obj.setCompleted(rs.getBoolean("completed"));
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
     * @param object updated object
     * @return true for success, false for fail
     */
    public boolean update(MaintenanceScheduleDTO object) {
        Connection con;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "UPDATE maintenanceschedule SET maintenance_id = ?, schedule_date = ?, note = ?, maintenance_cost = ?, "
                    + "completed = ? WHERE schedule_id = ?");
            pstmt.setInt(1, object.getMaintenanceId());
            pstmt.setDate(2, object.getScheduleDate());
            pstmt.setString(3, object.getNote());
            pstmt.setBigDecimal(4, object.getMaintenanceCost());
            pstmt.setBoolean(5, object.isCompleted());
            pstmt.setInt(6, object.getScheduleId());
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
                    "DELETE FROM maintenanceschedule WHERE schedule_id = ?");
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
