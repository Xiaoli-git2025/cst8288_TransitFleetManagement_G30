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
import model.VehicleDTO;
/**
 * DAO implements DAOInterface
 * @author 
 */
public class VehicleDAO implements DAOInterface<VehicleDTO>{
    /**
     * get all 
     * @return list
     * @throws SQLException throw SQLExpection
     */
    @Override
    public List<VehicleDTO> getAll()throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<VehicleDTO> objs = null;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM vehicle ORDER BY vehicle_id");
            rs = pstmt.executeQuery();
            objs = new ArrayList<VehicleDTO>();
            while (rs.next()) {
                VehicleDTO obj = new VehicleDTO();
                obj.setVehicleNumber(rs.getString("vehicle_number"));
                obj.setConsumptionRate(rs.getBigDecimal("consumption_rate"));
                obj.setMaxPassenger(rs.getInt("max_passenger"));
                obj.setFuelType(rs.getString("fuel_type"));
                obj.setRouteId(rs.getInt("route_id"));
                obj.setCapacity(rs.getInt("capacity"));
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
    public boolean add(VehicleDTO obj) {
        Connection con =null;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement("INSERT INTO vehicle (vehicle_number,consumption_rate,max_passenger,fuel_type,route_id,capacity) VALUES(?, ?, ?,?,?,?)");
            
            pstmt.setString(1,obj.getVehicleNumber());
            pstmt.setBigDecimal(2, obj.getConsumptionRate());
            pstmt.setInt(3, obj.getMaxPassenger());
            pstmt.setString(4, obj.getFuelType());
            pstmt.setInt(5, obj.getRouteId());
            pstmt.setInt(6, obj.getCapacity());
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
    public VehicleDTO getById(int objId) {
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        VehicleDTO obj = null;

        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM vehicle WHERE vehicle_number = ?");
            pstmt.setInt(1, objId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                obj = new VehicleDTO();
                obj.setVehicleNumber(rs.getString("vehicle_number"));
                obj.setConsumptionRate(rs.getBigDecimal("consumption_rate"));
                obj.setMaxPassenger(rs.getInt("max_passenger"));
                obj.setFuelType(rs.getString("fuel_type"));
                obj.setRouteId(rs.getInt("route_id"));
                obj.setCapacity(rs.getInt("capacity"));
    
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
    public boolean update(VehicleDTO obj) {
        Connection con;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "UPDATE vehivle SET vehicle_number = ?, consumption_rate = ?, max_passager = ?, fuel_type = ?, "
                    + "route_id = ?, capacity = ?  WHERE vehicle_number = ?");
            pstmt.setString(1, obj.getVehicleNumber());
            pstmt.setBigDecimal(2, obj.getConsumptionRate());
            pstmt.setInt(3, obj.getMaxPassenger());
            pstmt.setString(4, obj.getFuelType());
            pstmt.setInt(5, obj.getRouteId());
            pstmt.setInt(6, obj.getCapacity()); 
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
                    "DELETE FROM vehicle WHERE vehicle_number = ?");
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
