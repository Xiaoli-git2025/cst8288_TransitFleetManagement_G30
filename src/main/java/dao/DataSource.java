/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Student ID: 040469755
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;

/**
 * This class is used to connect to the database, using Singleton pattern
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class DataSource {

    /**
     * database connect
     */
    private static Connection connection = null;

    /**
     * Only use one connection for this application, prevent memory leaks.
     * @return database connection
     */    
    public static Connection getConnection() {
        //read database property from config file 
        String[] connectionInfo = openPropsFile();
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(connectionInfo[0], connectionInfo[1], connectionInfo[2]);
                //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transitsystem", "cst8288", "8288");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    /**
     * close connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * read database property from configuration file
     * @return connection information
     */
    private static String[] openPropsFile() {
        // added use of Properties and try-with-resources - kriger
        Properties props = new Properties();

        //one is for desktop app, ther other for web app.
        //try (InputStream in = Files.newInputStream(Paths.get("src/main/java/database.properties"));) {
        try (InputStream in = DataSource.class.getClassLoader().getResourceAsStream("database.properties")) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }// catch()

        String connectionString = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        String[] info = new String[3];
        info[0] = connectionString;
        info[1] = username;
        info[2] = password;

        return info;
    }

    public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
