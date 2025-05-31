/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author MICHAE LOU LIM
 */
public class ProductDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/inventorydb"; // Renamed from URL to DB_URL for consistency
    private static final String USER = "inventory_user"; // The user you created
    private static final String PASSWORD = "inventory_pass"; // The password for that user

    public ProductDAO() {
        createTable(); // Call this when DAO is initialized to ensure table exists
    }

    private Connection getConnection() throws SQLException {
        // Ensure the JDBC driver is loaded. Not strictly necessary with modern JDBC,
        // but good practice for clarity.
        // Class.forName("com.mysql.cj.jdbc.Driver"); // Uncomment if you get driver errors
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS products (\n"
                + " id VARCHAR(50) PRIMARY KEY,\n"
                + " name VARCHAR(255) NOT NULL,\n"
                + " quantity DOUBLE NOT NULL,\n"
                + " price DOUBLE NOT NULL\n"
                + ");";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.execute();
            System.out.println("Products table ensured to exist.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error creating table: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

     public boolean addProduct(Product productToAdd) { // Correct and single addProduct method
        String sql = "INSERT INTO products(id, name, quantity, price) VALUES(?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productToAdd.getId());
            pstmt.setString(2, productToAdd.getName());
            pstmt.setDouble(3, productToAdd.getQuantity());
            pstmt.setDouble(4, productToAdd.getPrice());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Handle duplicate ID specifically if it's a primary key violation (MySQL error code 1062)
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Error: Item with ID '" + productToAdd.getId() + "' already exists.", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error adding product: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            }
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, quantity, price FROM products";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Product product;
                product = new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("quantity"),
                        rs.getDouble("price")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching products: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return products;
    }

    // ... (other methods will go here later: addProduct, updateProduct, deleteProduct) ...
    public boolean updateProduct(Product productToUpdate) { // Correct and single updateProduct method
        String sql = "UPDATE products SET name = ?, quantity = ?, price = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productToUpdate.getName());
            pstmt.setDouble(2, productToUpdate.getQuantity());
            pstmt.setDouble(3, productToUpdate.getPrice());
            pstmt.setString(4, productToUpdate.getId()); // This now correctly refers to the 'productToUpdate' parameter
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating product: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(String id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting product: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
}
