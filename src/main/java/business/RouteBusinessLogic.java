
package business;
import dao.RouteDAO;
import model.RouteDTO;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Administrator
 */
public class RouteBusinessLogic {
    /**
     * DAO instance variable for Title
     */
    private RouteDAO objDao = null;

    /**
     * Constructor for BusinessLogic
     */
    public RouteBusinessLogic() {
        objDao = new RouteDAO();
    }

    /**
     * get all objects
     * @return all titles
     * @throws SQLException throw exception
     */
    public List<RouteDTO> getAllObjects() throws SQLException {
        return objDao.getAll();
    }
    
    /**
     * get obj by id
     * @param id id
     * @return object
     */
    public RouteDTO getObjById(int id) {
        return objDao.getById(id);
    }

    /**
     * add object
     * @param obj: object that be added
     * @return true for success, false for fail
     */
    public boolean addObject(RouteDTO obj) {
        return objDao.add(obj);
    }
    
    /**
     * update object
     * @param obj: object that be updated
     * @return true for success, false for fail
     */
    public boolean updateObject(RouteDTO obj) {
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
