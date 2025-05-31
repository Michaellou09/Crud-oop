/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventorysystem;

import inventory.DatabaseConnection;
import java.sql.Connection;

/**
 *
 * @author MICHAE LOU LIM
 */
public class inventorysystem {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     Connection conn = DatabaseConnection.getConnection();
    if (conn != null) {
        System.out.println("Connection is successful!");
    }

    }
}

