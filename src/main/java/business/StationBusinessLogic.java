
package business;
import dao.StationDAO;
import java.sql.SQLException;
import java.util.List;
import model.StationDTO;

/**
 *
 * @author Administrator
 */
public class StationBusinessLogic {
    /**
     * DAO instance variable for Title
     */
    private StationDAO objDao = null;

    /**
     * Constructor for BusinessLogic
     */
    public StationBusinessLogic() {
        objDao = new StationDAO();
    }

    /**
     * get all objects
     * @return all titles
     * @throws SQLException throw exception
     */
    public List<StationDTO> getAllObjects() throws SQLException {
        return objDao.getAll();
    }
    
    /**
     * get obj by id
     * @param id id
     * @return object
     */
    public StationDTO getObjById(int id) {
        return objDao.getById(id);
    }

    /**
     * add object
     * @param obj: object that be added
     * @return true for success, false for fail
     */
    public boolean addObject(StationDTO obj) {
        return objDao.add(obj);
    }
    
    /**
     * update object
     * @param obj: object that be updated
     * @return true for success, false for fail
     */
    public boolean updateObject(StationDTO obj) {
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
