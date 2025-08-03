/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Student ID: 040469755
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
package model;
import java.sql.Date;
import java.sql.Time;
/**
 * Data Transfer Object (DTO) representing an operator's performance record.
 * Stores user information and actual vs. scheduled arrival and departure times.
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class OperatorPerformanceDTO {
    private int userId;
    private String userName;
    private String email;
    private Date logDate;
    private Time arriveTime;
    private Time departTime;
    private Time scheduleArriveTime;
    private Time scheduleDepartTime;

    // Getters and Setters

    /**
     * Gets the user's ID.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user's ID.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the user's name.
     *
     * @return the user's name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's name.
     *
     * @param userName the user name to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the user's email.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the log date of the performance entry.
     *
     * @return the log date
     */
    public Date getLogDate() {
        return logDate;
    }

    /**
     * Sets the log date of the performance entry.
     *
     * @param logDate the log date to set
     */
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    /**
     * Gets the actual arrival time.
     *
     * @return the actual arrival time
     */
    public Time getArriveTime() {
        return arriveTime;
    }

    /**
     * Sets the actual arrival time.
     *
     * @param arriveTime the arrival time to set
     */
    public void setArriveTime(Time arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * Gets the actual departure time.
     *
     * @return the actual departure time
     */
    public Time getDepartTime() {
        return departTime;
    }

    /**
     * Sets the actual departure time.
     *
     * @param departTime the departure time to set
     */
    public void setDepartTime(Time departTime) {
        this.departTime = departTime;
    }

    /**
     * Gets the scheduled arrival time.
     *
     * @return the scheduled arrival time
     */
    public Time getScheduleArriveTime() {
        return scheduleArriveTime;
    }

    /**
     * Sets the scheduled arrival time.
     *
     * @param scheduleArriveTime the scheduled arrival time to set
     */
    public void setScheduleArriveTime(Time scheduleArriveTime) {
        this.scheduleArriveTime = scheduleArriveTime;
    }

    /**
     * Gets the scheduled departure time.
     *
     * @return the scheduled departure time
     */
    public Time getScheduleDepartTime() {
        return scheduleDepartTime;
    }

    /**
     * Sets the scheduled departure time.
     *
     * @param scheduleDepartTime the scheduled departure time to set
     */
    public void setScheduleDepartTime(Time scheduleDepartTime) {
        this.scheduleDepartTime = scheduleDepartTime;
    }
}

   