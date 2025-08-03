/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.sql.Date;
import java.sql.Time;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Time getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Time arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Time getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Time departTime) {
        this.departTime = departTime;
    }

    public Time getScheduleArriveTime() {
        return scheduleArriveTime;
    }

    public void setScheduleArriveTime(Time scheduleArriveTime) {
        this.scheduleArriveTime = scheduleArriveTime;
    }

    public Time getScheduleDepartTime() {
        return scheduleDepartTime;
    }

    public void setScheduleDepartTime(Time scheduleDepartTime) {
        this.scheduleDepartTime = scheduleDepartTime;
    }
}

