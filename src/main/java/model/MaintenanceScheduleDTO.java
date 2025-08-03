package model;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
/**
 * MaintenanceScheduleDTO is a Data Transfer Object for the 'MaintenanceSchedule' table.
 * It represents scheduled maintenance events linked to maintenance alerts.
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
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
    
    private boolean completed;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean isCompleted) {
        this.completed = isCompleted;
    }

    // Constructors
    public MaintenanceScheduleDTO() {}

     /**
     * Constructs a MaintenanceScheduleDTO with all fields.
     *
     * @param scheduleId       the unique ID of the maintenance schedule
     * @param maintenanceId    the ID of the related maintenance entry
     * @param scheduleDate     the date the maintenance is scheduled
     * @param note             additional notes related to the maintenance
     * @param maintenanceCost  the estimated or actual cost of the maintenance
     */
    public MaintenanceScheduleDTO(int scheduleId, int maintenanceId, java.sql.Date scheduleDate,
                                  String note, java.math.BigDecimal maintenanceCost) {
        this.scheduleId = scheduleId;
        this.maintenanceId = maintenanceId;
        this.scheduleDate = scheduleDate;
        this.note = note;
        this.maintenanceCost = maintenanceCost;
    }

    /**
     * Gets the schedule ID.
     *
     * @return the schedule ID
     */
    public int getScheduleId() {
        return scheduleId;
    }

    /**
     * Sets the schedule ID.
     *
     * @param scheduleId the schedule ID to set
     */
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    /**
     * Gets the maintenance ID.
     *
     * @return the maintenance ID
     */
    public int getMaintenanceId() {
        return maintenanceId;
    }

    /**
     * Sets the maintenance ID.
     *
     * @param maintenanceId the maintenance ID to set
     */
    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    /**
     * Gets the scheduled maintenance date.
     *
     * @return the scheduled date
     */
    public java.sql.Date getScheduleDate() {
        return scheduleDate;
    }

    /**
     * Sets the scheduled maintenance date.
     *
     * @param scheduleDate the scheduled date to set
     */
    public void setScheduleDate(java.sql.Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    /**
     * Gets the note associated with the maintenance schedule.
     *
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note for the maintenance schedule.
     *
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Gets the cost of the maintenance.
     *
     * @return the maintenance cost
     */
    public java.math.BigDecimal getMaintenanceCost() {
        return maintenanceCost;
    }

    /**
     * Sets the cost of the maintenance.
     *
     * @param maintenanceCost the maintenance cost to set
     */
    public void setMaintenanceCost(java.math.BigDecimal maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    /**
     * Gets the completion status of the maintenance schedule.
     * 
     * @return the completed status
     * @throws NullPointerException if the 'completed' field is not declared
     */
    public boolean getCompleted() {
        return completed; // ⚠ Missing declaration: This field must be declared.
    }
}
