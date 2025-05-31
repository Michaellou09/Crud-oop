/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author MICHAE LOU LIM
 */
public class DatabaseConnection {
     private static final String URL = "jdbc:mysql://localhost:3306/inventorydb"; // replace inventorydb with your DB name
    private static final String USER = "root";  // your MySQL username
    private static final String PASSWORD = "root";  // your MySQL password

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected!");
            } catch (SQLException e) {
                System.err.println("Cannot connect the database!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection(Connection conn) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing the database connection: " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error closing database connection: " + e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}