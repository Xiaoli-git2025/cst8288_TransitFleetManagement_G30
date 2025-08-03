package model;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
/**
 * AlertDTO is a Data Transfer Object for the 'Alert' table.
 * It encapsulates the details of system alerts such as maintenance warnings or emergency notifications.
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class AlertDTO {

    // Unique identifier for the alert (Primary Key, auto-incremented)
    private int alertId;

    // Type of alert (e.g., "Maintenance", "Delay", "Emergency")
    private String alertType;

    // Detailed description of the alert message
    private String alertDescription;

    // Constructors
    public AlertDTO() {
    }

   /**
     * Constructs an AlertDTO with all fields.
     *
     * @param alertId           the unique identifier for the alert
     * @param alertType         the type/category of the alert (e.g., "Warning", "Critical")
     * @param alertDescription  the detailed description of the alert
     */
    public AlertDTO(int alertId, String alertType, String alertDescription) {
        this.alertId = alertId;
        this.alertType = alertType;
        this.alertDescription = alertDescription;
    }

    /**
     * Gets the alert ID.
     *
     * @return the alert ID
     */
    public int getAlertId() {
        return alertId;
    }

    /**
     * Sets the alert ID.
     *
     * @param alertId the alert ID to set
     */
    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    /**
     * Gets the type of the alert.
     *
     * @return the alert type
     */
    public String getAlertType() {
        return alertType;
    }

    /**
     * Sets the type of the alert.
     *
     * @param alertType the alert type to set
     */
    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    /**
     * Gets the description of the alert.
     *
     * @return the alert description
     */
    public String getAlertDescription() {
        return alertDescription;
    }

    /**
     * Sets the description of the alert.
     *
     * @param alertDescription the alert description to set
     */
    public void setAlertDescription(String alertDescription) {
        this.alertDescription = alertDescription;
    }
}