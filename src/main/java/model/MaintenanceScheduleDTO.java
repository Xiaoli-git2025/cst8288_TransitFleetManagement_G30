package model;

/**
 * MaintenanceScheduleDTO is a Data Transfer Object for the 'MaintenanceSchedule' table.
 * It represents scheduled maintenance events linked to maintenance alerts.
 */
public class MaintenanceScheduleDTO {

    // Unique ID for each maintenance schedule record (Primary Key, auto-incremented)
    private int scheduleId;

    // Foreign key referencing the maintenance alert associated with this schedule
    private int maintenanceId;

    // The scheduled date for the maintenance
    private java.sql.Date scheduleDate;

    // Additional notes or remarks about the maintenance schedule
    private String note;

    // The cost associated with this maintenance event
    private java.math.BigDecimal maintenanceCost;

    // Constructors
    public MaintenanceScheduleDTO() {}

    public MaintenanceScheduleDTO(int scheduleId, int maintenanceId, java.sql.Date scheduleDate,
                                  String note, java.math.BigDecimal maintenanceCost) {
        this.scheduleId = scheduleId;
        this.maintenanceId = maintenanceId;
        this.scheduleDate = scheduleDate;
        this.note = note;
        this.maintenanceCost = maintenanceCost;
    }

    // Getters and Setters
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public java.sql.Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(java.sql.Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public java.math.BigDecimal getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(java.math.BigDecimal maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }
}
