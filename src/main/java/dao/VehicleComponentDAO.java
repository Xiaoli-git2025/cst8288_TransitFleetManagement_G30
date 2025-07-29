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
import model.VehicleComponentDTO;
/**
 * DAO implements DAOInterface
 * @author 
 */
public class VehicleComponentDAO implements DAOInterface<VehicleComponentDTO>{
    /**
     * get all 
     * @return list
     * @throws SQLException throw SQLExpection
     */
    @Override
    public List<VehicleComponentDTO> getAll()throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<VehicleComponentDTO> objs = null;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM vehiclecomponent ORDER BY component_id");
            rs = pstmt.executeQuery();
            objs = new ArrayList<VehicleComponentDTO>();
            while (rs.next()) {
                VehicleComponentDTO obj = new VehicleComponentDTO();
                obj.setVehicleId(rs.getInt("vehicle_id"));
                obj.setComponentId(rs.getInt("component_id"));
                obj.setComponentName(rs.getString("component_name"));
                obj.setUsedHour(rs.getInt("used_hour"));
                obj.setThresholdHour(rs.getInt("threshold_hour"));
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
    @Override
    public boolean add(VehicleComponentDTO obj) {
        Connection con =null;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement("INSERT INTO vehiclecomponent (component_name,vehicle_id, used_hour,threshold_hour) VALUES(?, ?, ?,?)");
            pstmt.setString(1, obj.getComponentName());
            pstmt.setInt(2, obj.getVehicleId());
            pstmt.setInt(3, obj.getUsedHour());
            pstmt.setInt(4, obj.getThresholdHour());
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
     * @param objId object id
     * @return object
     */
    @Override
    public VehicleComponentDTO getById(int objId) {
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        VehicleComponentDTO obj = null;

        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM vehiclecomponent WHERE component_id = ?");
            pstmt.setInt(1, objId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                obj = new VehicleComponentDTO();
                obj.setComponentId(rs.getInt("component_id"));
                obj.setVehicleId(rs.getInt("vehicle_id"));
                obj.setComponentName(rs.getString("component_name"));
                obj.setUsedHour(rs.getInt("used_hour"));
                obj.setThresholdHour(rs.getInt("threshold_hour"));
                
                
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
     * @param obj updated object
     * @return true for success, false for fail
     */
    @Override
    public boolean update(VehicleComponentDTO obj) {
        Connection con;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                "UPDATE vehiclecomponent SET component_name = ?, vehicle_id = ?, used_hour = ?, threshold_hour = ? "
                + "WHERE component_id = ?");
            pstmt.setString(1, obj.getComponentName());
            pstmt.setInt(2, obj.getVehicleId());
            pstmt.setInt(3, obj.getUsedHour());
            pstmt.setInt(4, obj.getThresholdHour());
            pstmt.setInt(5, obj.getComponentId());
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
    @Override
    public boolean delete(int objId) {
        Connection con;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "DELETE FROM vehiclecomponent WHERE component_id = ?");
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
