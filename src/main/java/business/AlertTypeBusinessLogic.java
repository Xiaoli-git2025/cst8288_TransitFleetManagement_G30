package business;

import dao.AlertDAO;
import java.sql.SQLException;
import java.util.List;
import model.AlertDTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elton
 */
public class AlertTypeBusinessLogic {
    private AlertDAO alertDao = null;
    
    public AlertTypeBusinessLogic(){
        alertDao = new AlertDAO();
    }
    public List<AlertDTO> getAllAlert()throws SQLException{
        return alertDao.getAll();
    }
    public AlertDTO getAlertById(int id){
        return alertDao.getById(id);
    }
    public boolean addAlert(AlertDTO alert){
        return alertDao.add(alert);
    }
    public boolean updateAlert(AlertDTO alert){
        return alertDao.update(alert);
    }
    public boolean deleteAlert(int id){
        return alertDao.delete(id);
    }
}
