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
import model.RouteScheduleDTO;
/**
 * DAO implements DAOInterface
 * @author 
 */
public class RouteScheduleDAO implements DAOInterface<RouteScheduleDTO>{
    /**
     * get all 
     * @return list
     * @throws SQLException throw SQLExpection
     */
    @Override
    public List<RouteScheduleDTO> getAll()throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<RouteScheduleDTO> objs = null;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM routeschedule ORDER BY schedule_id");
            rs = pstmt.executeQuery();
            objs = new ArrayList<RouteScheduleDTO>();
            while (rs.next()) {
                RouteScheduleDTO obj = new RouteScheduleDTO();
                obj.setScheduleId(rs.getInt("schedule_id"));
                obj.setRouteId(rs.getInt("route_id"));
                obj.setStationId(rs.getInt("station_id"));
                obj.setScheduleNumber(rs.getInt("schedule_number"));
                obj.setScheduleArriveTime(rs.getTime("schedule_arrive_time"));
                obj.setScheduleDepartTime(rs.getTime("schedule_depart_time"));
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
    
    public List<RouteScheduleDTO> getByStationIdAndRouteId(int station_id, int route_id)throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<RouteScheduleDTO> objs = null;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM routeschedule WHERE station_id = ? && route_id = ? order by schedule_number");
            pstmt.setInt(1, station_id);
            pstmt.setInt(2, route_id);
            rs = pstmt.executeQuery();
            objs = new ArrayList<RouteScheduleDTO>();
            while (rs.next()) {
                RouteScheduleDTO obj = new RouteScheduleDTO();
                obj.setScheduleId(rs.getInt("schedule_id"));
                obj.setRouteId(rs.getInt("route_id"));
                obj.setStationId(rs.getInt("station_id"));
                obj.setScheduleNumber(rs.getInt("schedule_number"));
                obj.setScheduleArriveTime(rs.getTime("schedule_arrive_time"));
                obj.setScheduleDepartTime(rs.getTime("schedule_depart_time"));
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
    public boolean add(RouteScheduleDTO obj) {
        Connection con =null;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement("INSERT INTO routeschedule (route_id,station_id,schedule_number,schedule_arrive_time,schedule_depart_time) VALUES(?, ?, ?,?,?)");
            
            pstmt.setInt(1, obj.getRouteId());
            pstmt.setInt(2, obj.getStationId());
            pstmt.setInt(3, obj.getScheduleNumber());
            pstmt.setTime(4, obj.getScheduleArriveTime());
            pstmt.setTime(5, obj.getScheduleDepartTime());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                ret = true;
            }
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
    public RouteScheduleDTO getById(int objId) {
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        RouteScheduleDTO obj = null;

        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM routeschedule WHERE schedule_id = ?");
            pstmt.setInt(1, objId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                obj = new RouteScheduleDTO();
                obj.setScheduleId(rs.getInt("schedule_id"));
                obj.setRouteId(rs.getInt("route_id"));
                obj.setStationId(rs.getInt("station_id"));
                obj.setScheduleNumber(rs.getInt("schedule_number"));
                obj.setScheduleArriveTime(rs.getTime("schedule_arrive_time"));
                obj.setScheduleDepartTime(rs.getTime("schedule_depart_time"));
                
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
    public boolean update(RouteScheduleDTO obj) {
        Connection con;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "UPDATE routeschedule SET route_id = ?, station_id = ?, schedule_number = ?, schedule_arrive_time = ?, "
                    + "schedule_depart_time = ? WHERE schedule_id = ?");
            
            pstmt.setInt(1, obj.getRouteId());
            pstmt.setInt(2, obj.getStationId());
            pstmt.setInt(3, obj.getScheduleNumber());
            pstmt.setTime(4, obj.getScheduleArriveTime());
            pstmt.setTime(5, obj.getScheduleDepartTime());
            pstmt.setInt(6,obj.getScheduleId());
            
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
                    "DELETE FROM routeschedule WHERE schedule_id = ?");
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
