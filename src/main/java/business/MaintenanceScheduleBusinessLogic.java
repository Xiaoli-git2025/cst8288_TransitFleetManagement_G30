
package business;
import dao.MaintenanceScheduleDAO;
import java.sql.SQLException;
import java.util.List;
import model.MaintenanceScheduleDTO;


/**
 * business logic for alert and schedule
 * @author Xiaoli He
 */
public class MaintenanceScheduleBusinessLogic {
    /**
     * DAO instance variable for Title
     */
    private MaintenanceScheduleDAO objDao = null;

    /**
     * Constructor for BusinessLogic
     */
    public MaintenanceScheduleBusinessLogic() {
        objDao = new MaintenanceScheduleDAO();
    }

    /**
     * get all objects
     * @return all titles
     * @throws SQLException throw exception
     */
    public List<MaintenanceScheduleDTO> getAllObjects() throws SQLException {
        return objDao.getAll();
    }
    
    /**
     * get obj by id
     * @param id id
     * @return object
     */
    public MaintenanceScheduleDTO getObjById(int id) {
        return objDao.getById(id);
    }

    /**
     * add object
     * @param obj: object that be added
     * @return true for success, false for fail
     */
    public boolean addObject(MaintenanceScheduleDTO obj) {
        return objDao.add(obj);
    }
    
    /**
     * update object
     * @param obj: object that be updated
     * @return true for success, false for fail
     */
    public boolean updateObject(MaintenanceScheduleDTO obj) {
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
