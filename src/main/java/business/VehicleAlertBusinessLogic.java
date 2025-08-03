package business;

import dao.VehicleAlertDAO;
import java.sql.SQLException;
import java.util.List;
import model.VehicleAlertDTO;

/**
 *
 * @author Administrator
 */
public class VehicleAlertBusinessLogic {
    /**
     * DAO instance variable for Title
     */
    private VehicleAlertDAO objDao = null;

    /**
     * Constructor for BusinessLogic
     */
    public VehicleAlertBusinessLogic() {
        objDao = new VehicleAlertDAO();
    }

    /**
     * get all objects
     * @return all titles
     * @throws SQLException throw exception
     */
    public List<VehicleAlertDTO> getAllObjects() throws SQLException {
        return objDao.getAll();
    }
    
    /**
     * get obj by id
     * @param id id
     * @return object
     * @throws java.sql.SQLException
     */
    public List<VehicleAlertDTO> getObjById(int id) throws SQLException {
        return objDao.getAlertsByUserId(id);
    }
}

