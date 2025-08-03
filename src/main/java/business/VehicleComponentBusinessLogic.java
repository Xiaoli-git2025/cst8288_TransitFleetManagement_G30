/* File: VehicleComponentLogic.java
 * Author: Shan Cai
 * Date: 2025/7/29
 * Description: logic of vehicle component
 */
package business;
import dao.VehicleComponentDAO;
import java.sql.SQLException;
import java.util.List;
import model.VehicleComponentDTO;

/**
 *
 * @author shano
 */
    public class VehicleComponentBusinessLogic {
    /**
     * DAO instance variable for Title
     */
    private VehicleComponentDAO objDao = null;

    /**
     * Constructor for BusinessLogic
     */
    public VehicleComponentBusinessLogic() {
        objDao = new VehicleComponentDAO();
    }

    /**
     * get all objects VehicleComponentDTO
     * @return all titles
     * @throws SQLException throw exception
     */
    public List<VehicleComponentDTO> getAllObjects() throws SQLException {
        return objDao.getAll();
    }
    
    /**
     * get obj by id
     * @param id id
     * @return object
     */
    public VehicleComponentDTO getObjById(int id) {
        return objDao.getById(id);
    }

    /**
     * add object VehicleComponentDTO
     * @param obj: object that be added
     * @return true for success, false for fail
     */
    public boolean addObject(VehicleComponentDTO obj) {
        return objDao.add(obj);
    }
    
    /**
     * update object VehicleComponentDTO
     * @param obj: object that be updated
     * @return true for success, false for fail
     */
    public boolean updateObject(VehicleComponentDTO obj) {
        return objDao.update(obj);
    }
    
    /**
     * delete object VehicleComponentDTO
     * @param id: id 
     * @return true for success, false for fail
     */
    public boolean deleteObject(int id) {
        return objDao.delete(id);
    }
}

