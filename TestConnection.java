/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestConnection;

import inventory.DatabaseConnection;

/**
 *
 * @author MICHAE LOU LIM
 */
public class TestConnection {

    public static void main(String[] args) {
        if (DatabaseConnection.getConnection() != null) {
            System.out.println("✅ Connected to MySQL!");
        } else {
            System.out.println("❌ Connection failed.");
        }
    }
}
