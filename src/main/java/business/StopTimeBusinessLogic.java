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
 *
 * @author Administrator
 */
public class StopTimeBusinessLogic {
    
    private UserDAO userDao = null;
    private StationDAO stationDao = null;
    private RouteScheduleDAO scheduleDao = null;
    private RouteDAO routeDao = null;
    private StationTimeDAO timeDao = null;
    
    public StopTimeBusinessLogic(){
        userDao = new UserDAO();
        stationDao = new StationDAO();
        scheduleDao = new RouteScheduleDAO();
        routeDao = new RouteDAO();
        timeDao = new StationTimeDAO();
    }
    
    public RouteDTO getRouteByUserId(int user_id){
        return routeDao.getById(userDao.getById(user_id).getRoute_id());
    }
    
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
    
    public RouteDTO getRouteByScheduleId(int schedule_id){
        return routeDao.getById(scheduleDao.getById(schedule_id).getRouteId());
    }
    
    public StationDTO getStationByScheduleId(int schedule_id){
        return stationDao.getById(scheduleDao.getById(schedule_id).getStationId());
    }
    
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
    
    public RouteScheduleDTO getScheduleById(int schedule_id){
        return scheduleDao.getById(schedule_id);
    }
    
    public boolean LogStopTime(StationTimeDTO time){
        return timeDao.add(time);
    }
    
    public boolean UpdateStopTime(StationTimeDTO time){
        return timeDao.update(time);
    }
    
    public List<RouteScheduleDTO> getSchedulesByStationId_routeid(int station_id, int route_id)throws SQLException{
        return scheduleDao.getByStationIdAndRouteId(station_id, route_id);
    }
}