/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Student ID: 040469755
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
package dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.StationTimeDTO;

/**
 * DAO implements DAOInterface
 *
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class StationTimeDAO implements DAOInterface<StationTimeDTO> {

    /**
     * get all
     *
     * @return list
     * @throws SQLException throw SQLExpection
     */
    @Override
    public List<StationTimeDTO> getAll() throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<StationTimeDTO> objs = null;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM stationtime ORDER BY time_id");
            rs = pstmt.executeQuery();
            objs = new ArrayList<StationTimeDTO>();
            while (rs.next()) {
                StationTimeDTO obj = new StationTimeDTO();
                obj.setTimeId(rs.getInt("time_id"));
                obj.setLogDate(rs.getDate("log_date"));
                obj.setArriveTime(rs.getTime("arrive_time"));
                obj.setDepartTime(rs.getTime("depart_time"));
                obj.setNote(rs.getString("note"));
                obj.setScheduleId(rs.getInt("schedule_id"));
                obj.setUserId(rs.getInt("user_id"));
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
    public boolean add(StationTimeDTO obj) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement("INSERT INTO stationtime (log_date,arrive_time,depart_time,note,schedule_id,user_id) VALUES(?, ?, ?,?,?,?)");

            pstmt.setDate(1, obj.getLogDate());
            pstmt.setTime(2, obj.getArriveTime());
            pstmt.setTime(3, obj.getDepartTime());
            pstmt.setString(4, obj.getNote());
            pstmt.setInt(5, obj.getScheduleId());
            pstmt.setInt(6, obj.getUserId());
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
    @Override
    public StationTimeDTO getById(int objId) {
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StationTimeDTO obj = null;

        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM stationtime WHERE time_id = ?");
            pstmt.setInt(1, objId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                obj = new StationTimeDTO();
                obj.setTimeId(rs.getInt("time_id"));
                obj.setLogDate(rs.getDate("log_date"));
                obj.setArriveTime(rs.getTime("arrive_time"));
                obj.setDepartTime(rs.getTime("depart_time"));
                obj.setNote(rs.getString("note"));
                obj.setScheduleId(rs.getInt("schedule_id"));
                obj.setUserId(rs.getInt("user_id"));

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

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return obj;
    }

    /**
     * update object
     *
     * @param obj updated object
     * @return true for success, false for fail
     */
    @Override
    public boolean update(StationTimeDTO obj) {
        Connection con;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "UPDATE stationtime SET log_date = ?, arrive_time = ?, depart_time = ?, note = ?, schedule_id = ?, "
                    + "user_id = ? WHERE time_id = ?");
            pstmt.setDate(1, obj.getLogDate());
            pstmt.setTime(2, obj.getArriveTime());
            pstmt.setTime(3, obj.getDepartTime());
            pstmt.setString(4, obj.getNote());
            pstmt.setInt(5, obj.getScheduleId());
            pstmt.setInt(6, obj.getUserId());
            pstmt.setInt(7, obj.getTimeId());
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
    @Override
    public boolean delete(int objId) {
        Connection con;
        PreparedStatement pstmt = null;
        boolean ret = true;
        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement(
                    "DELETE FROM stationtime WHERE time_id = ?");
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

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ret;
    }

    public List<StationTimeDTO> getStationTimesByUserId(int operatorUserId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<StationTimeDTO> objs = new ArrayList<>();

        try {
            con = DataSource.getConnection();
            pstmt = con.prepareStatement("SELECT * FROM stationtime WHERE user_id = ? ORDER BY time_id");
            pstmt.setInt(1, operatorUserId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StationTimeDTO obj = new StationTimeDTO();
                obj.setTimeId(rs.getInt("time_id"));
                obj.setLogDate(rs.getDate("log_date"));
                obj.setArriveTime(rs.getTime("arrive_time"));
                obj.setDepartTime(rs.getTime("depart_time"));
                obj.setNote(rs.getString("note"));
                obj.setScheduleId(rs.getInt("schedule_id"));
                obj.setUserId(rs.getInt("user_id"));
                objs.add(obj);
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
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return objs;
    }

}
