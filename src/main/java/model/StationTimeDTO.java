package model;

import java.sql.Date;
import java.sql.Time;

/**
 * Data Transfer Object for the StationTime table.
 * <p>
 * Represents a station's arrival and departure time log
 * associated with a specific route schedule and user.
 * </p>
 * 
 * Table structure:
 * <pre>
 * CREATE TABLE StationTime (
 *     time_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 *     log_date DATE,
 *     arrive_time TIME,
 *     depart_time TIME,
 *     note TEXT,
 *     schedule_id INT,
 *     user_id INT,
 *     FOREIGN KEY (schedule_id) REFERENCES RouteSchedule(schedule_id),
 *     FOREIGN KEY (user_id) REFERENCES Users(user_id)
 * );
 * </pre>
 * 
 * @author YourName
 */
public class StationTimeDTO {

    /** Primary key (auto-incremented) */
    private int timeId;

    /** Date of the log record */
    private Date logDate;

    /** Time of arrival at the station */
    private Time arriveTime;

    /** Time of departure from the station */
    private Time departTime;

    /** Optional notes or comments */
    private String note;

    /** Foreign key referencing RouteSchedule */
    private int scheduleId;

    /** Foreign key referencing Users */
    private int userId;

    /**
     * Default constructor.
     */
    public StationTimeDTO() {
    }

    /**
     * Full constructor for StationTimeDTO.
     *
     * @param timeId      the primary key
     * @param logDate     the date the log was recorded
     * @param arriveTime  the arrival time at the station
     * @param departTime  the departure time from the station
     * @param note        any notes or comments
     * @param scheduleId  the related route schedule ID
     * @param userId      the user who logged the data
     */
    public StationTimeDTO(int timeId, Date logDate, Time arriveTime, Time departTime, String note, int scheduleId, int userId) {
        this.timeId = timeId;
        this.logDate = logDate;
        this.arriveTime = arriveTime;
        this.departTime = departTime;
        this.note = note;
        this.scheduleId = scheduleId;
        this.userId = userId;
    }

    /**
     * Gets the time ID.
     *
     * @return the time ID
     */
    public int getTimeId() {
        return timeId;
    }

    /**
     * Sets the time ID.
     *
     * @param timeId the time ID to set
     */
    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    /**
     * Gets the log date.
     *
     * @return the log date
     */
    public Date getLogDate() {
        return logDate;
    }

    /**
     * Sets the log date.
     *
     * @param logDate the log date to set
     */
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    /**
     * Gets the arrival time.
     *
     * @return the arrival time
     */
    public Time getArriveTime() {
        return arriveTime;
    }

    /**
     * Sets the arrival time.
     *
     * @param arriveTime the arrival time to set
     */
    public void setArriveTime(Time arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * Gets the departure time.
     *
     * @return the departure time
     */
    public Time getDepartTime() {
        return departTime;
    }

    /**
     * Sets the departure time.
     *
     * @param departTime the departure time to set
     */
    public void setDepartTime(Time departTime) {
        this.departTime = departTime;
    }

    /**
     * Gets the note.
     *
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note.
     *
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Gets the schedule ID.
     *
     * @return the schedule ID
     */
    public int getScheduleId() {
        return scheduleId;
    }

    /**
     * Sets the schedule ID.
     *
     * @param scheduleId the schedule ID to set
     */
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}