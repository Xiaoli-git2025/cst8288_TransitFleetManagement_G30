package dao;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Student ID: 040469755
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
import model.OperatorPerformanceDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Data Access Object class for retrieving operator performance
 *
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class OperatorPerformanceDAO {
    
     /**
     * Retrieves a list of performance logs for all operators.
     * Each log includes user information, actual arrival/departure times,
     * and scheduled arrival/departure times.
     *
     * @return a list of OperatorPerformanceDTO objects containing performance data
     */
    public List<OperatorPerformanceDTO> getAllOperatorPerformanceLogs() {
        List<OperatorPerformanceDTO> performanceList = new ArrayList<>();
        String sql
                = "SELECT "
                + "u.user_id, "
                + "u.user_name, "
                + "u.email, "
                + "st.log_date, "
                + "st.arrive_time, "
                + "st.depart_time, "
                + "rs.schedule_arrive_time, "
                + "rs.schedule_depart_time "
                + "FROM StationTime st "
                + "JOIN Users u ON st.user_id = u.user_id "
                + "JOIN RouteSchedule rs ON st.schedule_id = rs.schedule_id "
                + "WHERE u.user_type = 'operator' "
                + "ORDER BY u.user_id, st.log_date";

        try (Connection con = DataSource.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            int rowCount = 0;

            while (rs.next()) {
                OperatorPerformanceDTO dto = new OperatorPerformanceDTO();
                dto.setUserId(rs.getInt("user_id"));
                dto.setUserName(rs.getString("user_name"));
                dto.setEmail(rs.getString("email"));
                dto.setLogDate(rs.getDate("log_date"));
                dto.setArriveTime(rs.getTime("arrive_time"));
                dto.setDepartTime(rs.getTime("depart_time"));
                dto.setScheduleArriveTime(rs.getTime("schedule_arrive_time"));
                dto.setScheduleDepartTime(rs.getTime("schedule_depart_time"));
                performanceList.add(dto);
                rowCount++;
            }

            System.out.println("Fetched " + rowCount + " rows from OperatorPerformanceDAO");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return performanceList;
    }
     /**
     * Retrieves performance logs for a specific operator by their user ID.
     * Each log includes actual and scheduled arrival/departure times.
     *
     * @param userId the user ID of the operator
     * @return a list of OperatorPerformanceDTO objects for the specified operator
     */
    public List<OperatorPerformanceDTO> getPerformanceByUserId(int userId) {
        List<OperatorPerformanceDTO> performanceList = new ArrayList<>();
        String sql
                = "SELECT "
                + "u.user_id, "
                + "u.user_name, "
                + "u.email, "
                + "st.log_date, "
                + "st.arrive_time, "
                + "st.depart_time, "
                + "rs.schedule_arrive_time, "
                + "rs.schedule_depart_time "
                + "FROM StationTime st "
                + "JOIN Users u ON st.user_id = u.user_id "
                + "JOIN RouteSchedule rs ON st.schedule_id = rs.schedule_id "
                + "WHERE u.user_type = 'operator' AND u.user_id = ? "
                + "ORDER BY st.log_date DESC";

        try (Connection con = DataSource.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                OperatorPerformanceDTO dto = new OperatorPerformanceDTO();
                dto.setUserId(rs.getInt("user_id"));
                dto.setUserName(rs.getString("user_name"));
                dto.setEmail(rs.getString("email"));
                dto.setLogDate(rs.getDate("log_date"));
                dto.setArriveTime(rs.getTime("arrive_time"));
                dto.setDepartTime(rs.getTime("depart_time"));
                dto.setScheduleArriveTime(rs.getTime("schedule_arrive_time"));
                dto.setScheduleDepartTime(rs.getTime("schedule_depart_time"));
                performanceList.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return performanceList;
    }

}
