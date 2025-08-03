/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;
import model.*;
import dao.*;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
/**
 * business logic for station time functions
 * @author Xiaoli He
 */
public class StopTimeBusinessLogic {
    
    /**
     * user dao
     */
    private UserDAO userDao = null;
    /**
     * station dao
     */
    private StationDAO stationDao = null;
    /**
     * route schedule dao
     */
    private RouteScheduleDAO scheduleDao = null;
    /**
     * route dao
     */
    private RouteDAO routeDao = null;
    /**
     * station time dao
     */
    private StationTimeDAO timeDao = null;
    
    /**
     * constructor, new dao
     */
    public StopTimeBusinessLogic(){
        userDao = new UserDAO();
        stationDao = new StationDAO();
        scheduleDao = new RouteScheduleDAO();
        routeDao = new RouteDAO();
        timeDao = new StationTimeDAO();
    }
    
    /**
     * get route by user
     * @param user_id user id
     * @return route
     */
    public RouteDTO getRouteByUserId(int user_id){
        return routeDao.getById(userDao.getById(user_id).getRoute_id());
    }
    
    /**
     * get stations by route
     * @param route_id route id
     * @return stations
     * @throws SQLException SQLException
     */
    public List<StationDTO> getStationsbyRouteId(int route_id)throws SQLException{
        List<StationDTO> stations = new ArrayList<>();
        List<RouteScheduleDTO> all = scheduleDao.getAll();
        Iterator<RouteScheduleDTO> iterator = all.iterator();
        while (iterator.hasNext()) {
            RouteScheduleDTO schedule = iterator.next();
            int routeid = schedule.getRouteId();
            if (routeid == route_id) {
                stations.add(stationDao.getById(schedule.getStationId()));
            }
        }
        stations.sort(Comparator.comparing(StationDTO::getStationId));
        return stations;
    }
    
    /**
     * get route by schedule 
     * @param schedule_id schedule id
     * @return route
     */
    public RouteDTO getRouteByScheduleId(int schedule_id){
        return routeDao.getById(scheduleDao.getById(schedule_id).getRouteId());
    }
    
    /**
     * get station by schedule
     * @param schedule_id schedule id
     * @return station
     */
    public StationDTO getStationByScheduleId(int schedule_id){
        return stationDao.getById(scheduleDao.getById(schedule_id).getStationId());
    }
    
    /**
     * get station time by schedule and user and log date
     * @param schedule_id schedule id
     * @param date log date
     * @param user_id user id
     * @return station times
     * @throws SQLException SQLException
     */
    public List<StationTimeDTO> getLogTimeByScheduleId_Date_UserId(int schedule_id, java.sql.Date date, int user_id)throws SQLException{
        List<StationTimeDTO> all = timeDao.getAll();
        Iterator<StationTimeDTO> iterator = all.iterator();
        while (iterator.hasNext()) {
            StationTimeDTO time = iterator.next();
            if (time.getScheduleId() != schedule_id || time.getUserId() != user_id || !time.getLogDate().equals(date)) {
                iterator.remove();
            }
        }
        all.sort(Comparator.comparing(StationTimeDTO::getTimeId));
        return all;
    }
    
    /**
     * get schedule by id
     * @param schedule_id schedule id
     * @return schedule
     */
    public RouteScheduleDTO getScheduleById(int schedule_id){
        return scheduleDao.getById(schedule_id);
    }
    
    /**
     * log station time
     * @param time station time
     * @return if add successfully
     */
    public boolean LogStopTime(StationTimeDTO time){
        return timeDao.add(time);
    }
    
    /**
     * update station time
     * @param time station time
     * @return if update successfully
     */
    public boolean UpdateStopTime(StationTimeDTO time){
        return timeDao.update(time);
    }
    
    /**
     * get schedules by station and route
     * @param station_id station id
     * @param route_id route id
     * @return route schedules
     * @throws SQLException SQLException
     */
    public List<RouteScheduleDTO> getSchedulesByStationId_routeid(int station_id, int route_id)throws SQLException{
        return scheduleDao.getByStationIdAndRouteId(station_id, route_id);
    }
}