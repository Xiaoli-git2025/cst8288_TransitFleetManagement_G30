package business;

import dao.AlertDAO;
import java.sql.SQLException;
import java.util.List;
import model.AlertDTO;

/**
 * The {@code AlertTypeBusinessLogic} class acts as the business layer for managing alert types.
 * <p>
 * It encapsulates business logic operations related to alerts and delegates data access operations
 * to the {@link dao.AlertDAO}. This includes retrieving, adding, updating, and deleting alert records.
 * </p>
 *
 * <p><b>Responsibilities:</b></p>
 * <ul>
 *   <li>Retrieve all alert types</li>
 *   <li>Get a specific alert by ID</li>
 *   <li>Add a new alert</li>
 *   <li>Update an existing alert</li>
 *   <li>Delete an alert by ID</li>
 * </ul>
 *
 * <p>This class is used in the service/controller layer to interact with the alert data stored in the database.</p>
 *
 * @author elton
 * @version 1.0
 * @since 2025-08-03
 */
public class AlertTypeBusinessLogic {
    
    /** Data access object for alert operations. */
    private AlertDAO alertDao = null;
    
    /**
     * Constructs a new {@code AlertTypeBusinessLogic} instance and initializes the DAO.
     */
    public AlertTypeBusinessLogic(){
        alertDao = new AlertDAO();
    }

    /**
     * Retrieves all alert records from the database.
     *
     * @return a list of {@link model.AlertDTO} objects
     * @throws SQLException if a database access error occurs
     */
    public List<AlertDTO> getAllAlert() throws SQLException {
        return alertDao.getAll();
    }

    /**
     * Retrieves a specific alert by its ID.
     *
     * @param id the ID of the alert to retrieve
     * @return the {@link model.AlertDTO} if found, otherwise {@code null}
     */
    public AlertDTO getAlertById(int id) {
        return alertDao.getById(id);
    }

    /**
     * Adds a new alert to the database.
     *
     * @param alert the {@link model.AlertDTO} to be added
     * @return {@code true} if the alert was successfully added, {@code false} otherwise
     */
    public boolean addAlert(AlertDTO alert) {
        return alertDao.add(alert);
    }

    /**
     * Updates an existing alert in the database.
     *
     * @param alert the {@link model.AlertDTO} containing updated values
     * @return {@code true} if the update was successful, {@code false} otherwise
     */
    public boolean updateAlert(AlertDTO alert) {
        return alertDao.update(alert);
    }

    /**
     * Deletes an alert by its ID.
     *
     * @param id the ID of the alert to delete
     * @return {@code true} if the deletion was successful, {@code false} otherwise
     */
    public boolean deleteAlert(int id) {
        return alertDao.delete(id);
    }
}
