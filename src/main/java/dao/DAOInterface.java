/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Student ID: 040469755
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for DAO
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 * @param <T> :DTO
 */
public interface DAOInterface<T> {
    /**
     * get all objects
     * @return list
     * @throws java.sql.SQLException throw SQLExpection
     */
    List<T> getAll() throws SQLException;
    
    /**
     * add object
     * @param obj obj added
     * @return true for success, false for fail
     */
    boolean add(T obj);
    
    /**
     * get by ID
     * @param id: id
     * @return object
     */
    T getById(int id);
    
    /**
     * update 
     * @param obj: the obj be updated 
     * @return true for success, false for fail
     */
    boolean update(T obj);
    
    /**
     * delete 
     * @param id: id
     * @return true for success, false for fail
     */
    boolean delete(int id);
    
}
