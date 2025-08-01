/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;
import dao.RouteScheduleDAO;
import java.sql.SQLException;
import java.util.List;
import model.RouteScheduleDTO;

/**
 *
 * @author Administrator
 */
public class RouteScheduleBusinessLogic {
    /**
     * DAO instance variable for Title
     */
    private RouteScheduleDAO objDao = null;

    /**
     * Constructor for BusinessLogic
     */
    public RouteScheduleBusinessLogic() {
        objDao = new RouteScheduleDAO();
    }

    /**
     * get all objects
     * @return all titles
     * @throws SQLException throw exception
     */
    public List<RouteScheduleDTO> getAllObjects() throws SQLException {
        return objDao.getAll();
    }
    
    /**
     * get obj by id
     * @param id id
     * @return object
     */
    public RouteScheduleDTO getObjById(int id) {
        return objDao.getById(id);
    }

    /**
     * add object
     * @param obj: object that be added
     * @return true for success, false for fail
     */
    public boolean addObject(RouteScheduleDTO obj) {
        return objDao.add(obj);
    }
    
    /**
     * update object
     * @param obj: object that be updated
     * @return true for success, false for fail
     */
    public boolean updateObject(RouteScheduleDTO obj) {
        return objDao.update(obj);
    }
    
    /**
     * delete object
     * @param id: id 
     * @return true for success, false for fail
     */
    public boolean deleteObject(int id) {
        return objDao.delete(id);
    }
}
