package model;
import java.sql.Date;

/**
 * Data Transfer Object for the MaintenanceAlert table.
 * <p>
 * Represents a maintenance alert raised for a specific vehicle component,
 * reported by a user, and optionally linked to a predefined alert.
 * </p>
 * 
 * Table structure:
 * <pre>
 * CREATE TABLE MaintenanceAlert (
 *     maintenance_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 *     alert_id INT,
 *     component_id INT,
 *     alert_date DATE,
 *     reporter_id INT,
 *     resolved BOOLEAN,
 *     FOREIGN KEY (alert_id) REFERENCES Alert(alert_id),
 *     FOREIGN KEY (component_id) REFERENCES VehicleComponent(component_id),
 *     FOREIGN KEY (reporter_id) REFERENCES Users(user_id)
 * );
 * </pre>
 * 
 * @author YourName
 */
public class MaintenanceAlertDTO {

    /** Primary key of the maintenance alert (auto-incremented) */
    private int maintenanceId;

    /** Optional reference to a predefined alert */
    private int alertId;

    /** The ID of the vehicle component related to this maintenance alert */
    private int componentId;

    /** The date when the alert was reported */
    private Date alertDate;

    /** The user ID of the person who reported the alert */
    private int reporterId;

    /** Whether the alert has been resolved */
    private boolean resolved;

    /**
     * Default constructor.
     */
    public MaintenanceAlertDTO() {
    }

    /**
     * Full constructor.
     * 
     * @param maintenanceId the primary key of the alert
     * @param alertId       optional linked alert ID
     * @param componentId   the component related to the alert
     * @param alertDate     the date of the alert
     * @param reporterId    the user who reported it
     * @param resolved      resolution status
     */
    public MaintenanceAlertDTO(int maintenanceId, int alertId, int componentId, Date alertDate, int reporterId, boolean resolved) {
        this.maintenanceId = maintenanceId;
        this.alertId = alertId;
        this.componentId = componentId;
        this.alertDate = alertDate;
        this.reporterId = reporterId;
        this.resolved = resolved;
    }

    /**
     * Gets the maintenance alert ID.
     * 
     * @return the maintenance ID
     */
    public int getMaintenanceId() {
        return maintenanceId;
    }

    /**
     * Sets the maintenance alert ID.
     * 
     * @param maintenanceId the ID to set
     */
    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    /**
     * Gets the linked alert ID.
     * 
     * @return the alert ID
     */
    public int getAlertId() {
        return alertId;
    }

    /**
     * Sets the linked alert ID.
     * 
     * @param alertId the alert ID to set
     */
    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    /**
     * Gets the component ID.
     * 
     * @return the vehicle component ID
     */
    public int getComponentId() {
        return componentId;
    }

    /**
     * Sets the component ID.
     * 
     * @param componentId the component ID to set
     */
    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    /**
     * Gets the alert date.
     * 
     * @return the date of the alert
     */
    public Date getAlertDate() {
        return alertDate;
    }

    /**
     * Sets the alert date.
     * 
     * @param alertDate the date to set
     */
    public void setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
    }

    /**
     * Gets the reporter user ID.
     * 
     * @return the user ID of the reporter
     */
    public int getReporterId() {
        return reporterId;
    }

    /**
     * Sets the reporter user ID.
     * 
     * @param reporterId the user ID to set
     */
    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    /**
     * Checks if the alert has been resolved.
     * 
     * @return {@code true} if resolved; {@code false} otherwise
     */
    public boolean isResolved() {
        return resolved;
    }

    /**
     * Sets the resolved status of the alert.
     * 
     * @param resolved {@code true} if resolved; {@code false} otherwise
     */
    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
