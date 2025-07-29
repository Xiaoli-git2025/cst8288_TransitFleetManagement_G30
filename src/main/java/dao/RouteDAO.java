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
import model.RouteDTO;
/**
 * DAO implements DAOInterface
 * @author 
 */
public class RouteDAO implements DAOInterface<RouteDTO>{
    /**
     * get all 
     * @return list
     * @throws SQLException throw SQLExpection
     */
    @Override
    public List<RouteDTO> getAll()throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<RouteDTO> objs = null;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT route_id, route_number, description FROM route ORDER BY route_id");
            rs = pstmt.executeQuery();
            objs = new ArrayList<RouteDTO>();
            while (rs.next()) {
                RouteDTO obj = new RouteDTO();
                obj.setRouteId(rs.getInt("route_id"));
                obj.setRouteNumber(rs.getInt("route_number"));
                obj.setDescription(rs.getString("description"));
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
    public boolean add(RouteDTO obj) {
        Connection con =null;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement("INSERT INTO route (route_number,description) VALUES(?, ?)");
            
            pstmt.setInt(1, obj.getRouteNumber());
            pstmt.setString(2, obj.getDescription());   
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
    public RouteDTO getById(int objId) {
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        RouteDTO obj = null;

        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM route WHERE route_id = ?");
            pstmt.setInt(1, objId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                obj = new RouteDTO();
                obj.setRouteNumber(rs.getInt("route_number"));
                obj.setDescription(rs.getString("description"));   

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
    public boolean update(RouteDTO obj) {
        Connection con;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "UPDATE route SET route_number = ?, description = ? WHERE route_id = ?");
            pstmt.setInt(1, obj.getRouteNumber());
            pstmt.setString(2, obj.getDescription()); 
            pstmt.setInt(3, obj.getRouteId());

            int rows = pstmt.executeUpdate();
            if (rows == 0) {
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
                    "DELETE FROM route WHERE route_id = ?");
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
