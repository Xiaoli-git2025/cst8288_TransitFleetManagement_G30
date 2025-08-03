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
import model.FuelConsumptionDTO;
/**
 * DAO implements DAOInterface
 * @author 
 */
public class FuelConsumptionDAO implements DAOInterface<FuelConsumptionDTO>{
    /**
     * get all 
     * @return list
     * @throws SQLException throw SQLExpection
     */
    @Override
    public List<FuelConsumptionDTO> getAll()throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<FuelConsumptionDTO> objs = null;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM fuelconsumption ORDER BY fc_id");
            rs = pstmt.executeQuery();
            objs = new ArrayList<FuelConsumptionDTO>();
            while (rs.next()) {
                FuelConsumptionDTO obj = new FuelConsumptionDTO();
                obj.setFcId(rs.getInt("fc_id"));
                obj.setVehicleId(rs.getInt("vehicle_id"));
                obj.setDate(rs.getDate("date"));
                obj.setMilesTraveled(rs.getBigDecimal("miles_traveled"));
                obj.setUnitPrice(rs.getBigDecimal("unit_price"));
                
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
    public boolean add(FuelConsumptionDTO obj) {
        Connection con =null;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement("INSERT INTO fuelconsumption (vehicle_id,date,miles_traveled,unit_price) VALUES(?, ?, ?,?)");
            
            pstmt.setInt(1, obj.getVehicleId());
            pstmt.setDate(2, obj.getDate());
            pstmt.setBigDecimal(3, obj.getMilesTraveled());
            pstmt.setBigDecimal(4, obj.getUnitPrice());
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
    public FuelConsumptionDTO getById(int objId) {
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FuelConsumptionDTO obj = null;

        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM  fuelconsumption WHERE fc_id = ?");
            pstmt.setInt(1, objId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                obj = new FuelConsumptionDTO();
                obj.setFcId(objId);
                obj.setVehicleId(rs.getInt("vehicle_id"));
                obj.setDate(rs.getDate("date"));
                obj.setMilesTraveled(rs.getBigDecimal("miles_traveled"));
                obj.setUnitPrice(rs.getBigDecimal("unit_price"));
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
    public boolean update(FuelConsumptionDTO obj) {
        Connection con;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "UPDATE fuelconsumption SET vehicle_id = ?, date = ?, miles_traveled = ?, unit_price = ?"
                    + " WHERE fc_id = ?");
            
            pstmt.setInt(1, obj.getVehicleId());
            pstmt.setDate(2, obj.getDate());
            pstmt.setBigDecimal(3, obj.getMilesTraveled());
            pstmt.setBigDecimal(4, obj.getUnitPrice());
            pstmt.setInt(5, obj.getFcId());
            
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
                    "DELETE FROM fuelconsumption WHERE fc_id = ?");
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
