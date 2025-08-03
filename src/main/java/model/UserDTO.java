package model;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Student ID: 040469755
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
/**
 * Data Transfer Object (DTO) representing a user in the system.
 * Stores basic user information such as ID, name, email, password, role, and route ID.
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class UserDTO {

    private int user_id;
    private String name;
    private String email;
    private String password;
    private String role; // "Manager" or "Operator", "Admin"
    private int route_id;

    /**
     * Sets the route ID associated with the user.
     *
     * @param route_id the route ID to set
     */
    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    /**
     * Gets the route ID associated with the user.
     *
     * @return the route ID
     */
    public int getRoute_id() {
        return route_id;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Gets the user's name.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user's email.
     *
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user ID.
     *
     * @param user_id the user ID to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Gets the user's password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the user's role (e.g., "Manager" "Admin" or "Operator").
     *
     * @return the user's role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the user's email.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the user's password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the user's role (e.g., "Manager" "Admin" or "Operator").
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

}
