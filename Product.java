/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory;

/**
 *
 * @author MICHAE LOU LIM
 */
public class Product {

     private String id;
    private String name;
    private double quantity;
    private double price;

    // This is the constructor that InventoryFrame should be using
    // Make sure this is the *only* constructor with String, String, double, double parameters
    public Product(String id, String name, double quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // You had another constructor:
    // public Product(String id, String name, int quantity, double price, String category) {
    //     this.id = id;
    //     this.name = name;
    //     this.quantity = quantity;
    //     this.price = price;
    // }
    // If you are not using this constructor, you can safely remove it to simplify.
    // If you do use it, ensure 'quantity' is correctly handled (double vs int).
    // For consistency with your InventoryFrame, I recommend only keeping the 4-parameter constructor.


    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", quantity=" + quantity
                + ", price=" + price
                + '}';
    }
}