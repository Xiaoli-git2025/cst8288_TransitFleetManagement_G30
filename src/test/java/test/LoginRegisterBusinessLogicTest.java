package test;

import business.LoginRegisterBusinessLogic;
import model.UserDTO;
import dao.UserDAO;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit tests for LoginRegisterBusinessLogic
 */
public class LoginRegisterBusinessLogicTest {

    private LoginRegisterBusinessLogic logic;

    @Before
    public void setUp() {
        logic = new LoginRegisterBusinessLogic();
    }

    @After
    public void tearDown() throws SQLException {
        // Clean up test user if needed
        UserDAO dao = new UserDAO();
        List<UserDTO> users = dao.getAll();
        for (UserDTO user : users) {
            if (user.getEmail().equals("junituser@example.com")) {
                dao.delete(user.getUser_id());
            }
        }
    }

    @Test
    public void testCheckAccount_Success() {
        System.out.println("checkAccount - success case");
        
        // These should match an existing user in your database
        String email = "test@example.com";
        String password = "password123";

        String result = logic.checkAccount(email, password);
        assertNotNull(result);
        assertTrue(result.contains("operator"));  // assuming role is operator
    }

    @Test
    public void testAddUser_Success() {
        System.out.println("AddUser - success case");

        String email = "junituser@example.com";
        String name = "JUnit User";
        String password = "junitpass";
        String user_type = "operator";
        int router_id = 1; // assume route ID 1 exists

        boolean result = logic.AddUser(email, name, password, user_type, router_id);
        assertTrue(result);
    }
}
