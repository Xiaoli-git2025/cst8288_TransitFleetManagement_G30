/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;
import dao.UserDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserDTO;

/**
 *
 * @author Xiaoli
 */
public class LoginRegisterBusinessLogic {
    public String checkAccount(String email, String password){
        String user_role = "not_found";
        try {
            UserDAO user_dao = new UserDAO();
            List<UserDTO> user_list = user_dao.getAll();
            for (UserDTO user : user_list) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                    user_role = user.getRole();
                    break;
                }
            }   
        } catch (SQLException ex) {
            Logger.getLogger(LoginRegisterBusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user_role;
    }
    
    public boolean AddUser(String email, String name, String password, String user_type, int router_id){
        UserDTO user= new UserDTO();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setRole(user_type);
        user.setRoute_id(router_id);
        UserDAO user_dao = new UserDAO();
        return user_dao.add(user);
    }
}
