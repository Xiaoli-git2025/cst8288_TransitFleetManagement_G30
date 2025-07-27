package model;

/**
 * AlertDTO is a Data Transfer Object for the 'Alert' table.
 * It encapsulates the details of system alerts such as maintenance warnings or emergency notifications.
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

    public AlertDTO(int alertId, String alertType, String alertDescription) {
        this.alertId = alertId;
        this.alertType = alertType;
        this.alertDescription = alertDescription;
    }

    // Getters and Setters
    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getAlertDescription() {
        return alertDescription;
    }

    public void setAlertDescription(String alertDescription) {
        this.alertDescription = alertDescription;
    }
}
