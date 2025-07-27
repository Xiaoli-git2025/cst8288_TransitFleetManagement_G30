/* File: DataSource.java
 * Author: Xiaoli He
 * Date: 2025/7/22
 * Description: author dao
 */
package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for DAO
 * @author Xiaoli He
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
