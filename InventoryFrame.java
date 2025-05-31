/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import inventory.Product;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter; // This import is not strictly needed if not used
//import java.awt.event.MouseEvent;   // This import is not strictly needed if not used
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import inventory.ProductDAO; // Assuming ProductDAO class is in the inventory package// This is crucial for handling database errors
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException; // Import SQLException

/**
 *
 * @author MICHAE LOU LIM
 */
public class InventoryFrame extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private ArrayList<Object[]> originalTableData; // Store the original, unfiltered data
    private ProductDAO productDAO; // Instantiate your DAO

    private JTextField idField;        // Will map to jTextField4
    private JTextField nameField;      // Will map to jTextField2
    private JTextField quantityField; // Will map to jTextField3
    private JTextField priceField;    // Will map to jTextField1
    private JTextField searchField;    // Will map to jTextField6

    private DecimalFormat quantityFormat = new DecimalFormat(""); // Unused, can be removed if not for future use
    private DecimalFormat priceFormat = new DecimalFormat("");

    /**
     * Creates new form InventoryFrame
     */
    public InventoryFrame() {
        initComponents(); // Initializes jTable1, jTextField1-4, etc.

        productDAO = new ProductDAO();

        // Assign the JTextFields from initComponents() to our local fields
        idField = jTextField4;
        nameField = jTextField2;
        quantityField = jTextField3;
        priceField = jTextField1;
        searchField = jTextField6;

        String[] columnNames = {"ID", "Name", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        jTable1.setModel(tableModel);

        originalTableData = new ArrayList<>();

        // THIS IS THE CRUCIAL CALL:
        refreshTableData(); // <--- This method now correctly loads data

        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);

        // Add ListSelectionListener for table row selection
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Avoids multiple events for a single click
                    int selectedRow = jTable1.getSelectedRow();
                    if (selectedRow != -1) { // A row is selected
                        // Ensure to get values from the table model, not directly from the database load
                        idField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                        nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                        quantityField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                        priceField.setText(tableModel.getValueAt(selectedRow, 3).toString());
                        btnUpdate.setEnabled(true);
                        btnDelete.setEnabled(true);
                    } else { // No row is selected
                        clearFields(); // Clear fields and disable buttons
                        btnUpdate.setEnabled(false); // Explicitly disable if nothing selected
                        btnDelete.setEnabled(false); // Explicitly disable if nothing selected
                    }
                }
            }
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable(searchField.getText());
            }
        });

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnClear2 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 100, 30));

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 100, 30));

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 100, 30));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 130, 40));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 220, 40));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 50, 40));

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 50, 40));

        jLabel1.setText("PRICE:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 60, 30));

        jLabel2.setText("QUANTITY:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 60, 30));

        jLabel3.setText("NAME:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 60, 30));

        jLabel4.setText("ID:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 40, 30));

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 100, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NAME", "QUANTITY", "PRICE"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 390, 310));

        btnClear2.setText("Clear");
        btnClear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, 80, 30));

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 350, 210, 30));

        jLabel5.setText("SEARCH HERE:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, 90, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFields();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String idToDelete = idField.getText().trim();

        if (idToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an item from the table or enter its ID to delete.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete product with ID: " + idToDelete + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // No need for a try-catch specifically for SQLException here,
            // as ProductDAO's deleteProduct already handles and displays messages.
            if (productDAO.deleteProduct(idToDelete)) {
                JOptionPane.showMessageDialog(this, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                refreshTableData(); // Refresh the table after deletion
            } else {
                // ProductDAO already displays error messages, so this else might be redundant if DAO handles all messages.
                // However, it's good as a fallback or for a generic "failed" message.
                JOptionPane.showMessageDialog(this, "Failed to delete product. Product with ID '" + idToDelete + "' might not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String id = idField.getText().trim(); // Use assigned fields
        String name = nameField.getText().trim();
        String quantityStr = quantityField.getText().trim();
        String priceStr = priceField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an item to update and fill all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double quantity = Double.parseDouble(quantityStr);
            double price = Double.parseDouble(priceStr);

            Product updatedProduct = new Product(id, name, quantity, price); // ID is used for WHERE clause

            if (productDAO.updateProduct(updatedProduct)) {
                JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                refreshTableData(); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update item. Check if ID exists.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Quantity and Price.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred while updating the item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed

    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed

    }//GEN-LAST:event_jTextField4ActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String id = idField.getText().trim(); // Use assigned fields
        String name = nameField.getText().trim();
        String quantityStr = quantityField.getText().trim();
        String priceStr = priceField.getText().trim();

        // Basic input validation
        if (id.isEmpty() || name.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double quantity = Double.parseDouble(quantityStr);
            double price = Double.parseDouble(priceStr);

            // Additional validation for positive numbers
            if (quantity <= 0 || price <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity and Price must be positive numbers.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Product newProduct = new Product(id, name, quantity, price);

            // No need for a try-catch for SQLException here as DAO handles it internally with JOptionPane.
            // If the DAO method returns false, it means the operation failed (e.g., duplicate ID, which DAO already messages).
            if (productDAO.addProduct(newProduct)) {
                JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                refreshTableData(); // Refresh the table
            } else {
                // ProductDAO's addProduct already shows specific error messages (e.g., duplicate ID).
                // This else block acts as a general fallback if addProduct returns false for other reasons.
                // You can remove this generic message if you rely solely on DAO for error popups.
                // JOptionPane.showMessageDialog(this, "Failed to add product.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Quantity and Price.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // This catches any other unexpected errors during the process
            JOptionPane.showMessageDialog(this, "An unexpected error occurred while adding the item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnClear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear2ActionPerformed
        searchField.setText("");
        filterTable("");
    }//GEN-LAST:event_btnClear2ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        //getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 220, 40));
        filterTable(searchField.getText());

    }//GEN-LAST:event_jTextField6ActionPerformed

    private void filterTable(String searchText) {
        tableModel.setRowCount(0); // Clear current table data

        String lowerCaseSearchText = searchText.toLowerCase();

        // Iterate through the original, unfiltered data
        for (Object[] row : originalTableData) {
            String id = row[0].toString().toLowerCase();
            String name = row[1].toString().toLowerCase();

            if (id.contains(lowerCaseSearchText) || name.contains(lowerCaseSearchText)) {
                tableModel.addRow(row); // Add matching row back to table
            }
        }
    }

    // Helper to remove item from originalTableData (by ID)
    private void removeItemFromOriginalData(String idToRemove) {
        originalTableData.removeIf(row -> row[0].toString().equalsIgnoreCase(idToRemove));
    }

    private void updateItemInOriginalData(String originalId, String newId, String newName, double newQuantity, double newPrice) {
        for (int i = 0; i < originalTableData.size(); i++) {
            Object[] row = originalTableData.get(i);
            if (row[0].toString().equalsIgnoreCase(originalId)) {
                // Update the existing row with new values
                originalTableData.set(i, new Object[]{newId, newName, newQuantity, newPrice});
                return;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InventoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventoryFrame().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear2;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

//    private void deleteItem() {
//        int selectedRow = jTable1.getSelectedRow();
//        if (selectedRow == -1) {
//            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this item?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
//        if (confirm == JOptionPane.YES_OPTION) {
//            String deletedId = tableModel.getValueAt(selectedRow, 0).toString();
//            boolean success = productDAO.deleteProduct(deletedId);
//            if (success) {
//                loadItemsFromDatabase(); // Reload all data to refresh table and originalTableData
//                clearFields(); // Clear input fields
//                JOptionPane.showMessageDialog(this, "Item deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(this, "Failed to delete item from database.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//    private void updateItem() {
//        String updatedId = idField.getText().trim();
//        String updatedName = nameField.getText().trim();
//        double updatedQuantity;
//        double updatedPrice;
//
//        try {
//            updatedQuantity = Double.parseDouble(quantityField.getText().trim());
//            updatedPrice = Double.parseDouble(priceField.getText().trim());
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Quantity and Price must be valid numbers for update.", "Input Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        // Create a Product object with the updated details
//        Product productToUpdate = new Product(updatedId, updatedName, updatedQuantity, updatedPrice);
//
//        try {
//            boolean success = productDAO.updateProduct(productToUpdate);
//            if (success) {
//                JOptionPane.showMessageDialog(this, "Item updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                loadItemsFromDatabase(); // Refresh the table
//                clearFields();
//            } else {
//                JOptionPane.showMessageDialog(this, "Failed to update item.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error updating item: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
//        }
//    }
//
    private void clearFields() {
        idField.setText(""); // Use idField
        nameField.setText(""); // Use nameField
        quantityField.setText(""); // Use quantityField
        priceField.setText(""); // Use priceField
        jTable1.clearSelection(); // Deselect any selected row
        btnUpdate.setEnabled(false); // Disable update/delete when fields are cleared
        btnDelete.setEnabled(false);
    }

    private void refreshTableData() {
        tableModel.setRowCount(0);
        originalTableData.clear();

        List<Product> products = productDAO.getAllProducts();

        for (Product product : products) {
            Object[] rowData = new Object[]{
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice()
            };
            originalTableData.add(rowData); // Add to original data list
        }
        filterTable(searchField.getText());
    }

}

//    // Helper method to add items to both the table and original data
//
//    private void loadItemsFromDatabase() {
//        // Load initial sample data directly into originalTableData
//        // addDataToTableAndOriginal("01", "Laptop", 10.0, 30000.00);
//        //addDataToTableAndOriginal("02", "Mouse", 50.0, 1500.00);
//        //addDataToTableAndOriginal("03", "Keyboard", 30.0, 2000.00);
//        //addDataToTableAndOriginal("04", "USB Drive", 5.5, 450.00);
//        //addDataToTableAndOriginal("05", "Cord", 8.0, 500.00); // Added for testing name priority
//        //filterTable("");
//
//        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//        model.setRowCount(0); // Clear existing rows from displayed table
//
//        originalTableData.clear(); // Clear the in-memory master list
//
//        List<Product> products = productDAO.getAllProducts();
//
//        for (Product product : products) {
//            // Add to originalTableData (the master list)
//            originalTableData.add(new Object[]{
//                product.getId(),
//                product.getName(),
//                product.getQuantity(),
//                product.getPrice()
//            });
//        }
//        filterTable(searchField.getText()); // Apply current search filter (or show all if empty)
//    }
//
//// Ensure this helper method correctly adds to originalTableData
//    private void addDataToTableAndOriginal(String id, String name, double quantity, double price) {
//        Object[] rowData = {id, name, quantity, price};
//        originalTableData.add(rowData); // Add to original data list
//    }
//
//    private void AddItem() {
//        String itemId = idField.getText().trim();
//        String itemName = nameField.getText().trim();
//        String quantityText = quantityField.getText().trim();
//        String priceText = priceField.getText().trim();
//
//        if (itemId.isEmpty() || itemName.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Input Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        double parsedItemQuantity;
//        double parsedItemPrice;
//
//        try {
//            parsedItemQuantity = Double.parseDouble(quantityText);
//            parsedItemPrice = Double.parseDouble(priceText);
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Quantity and Price must be valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        if (parsedItemQuantity <= 0 || parsedItemPrice <= 0) {
//            JOptionPane.showMessageDialog(this, "Quantity and Price must be positive numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        try {
//            // Create a Product object from the text field values
//            Product newProduct = new Product(itemId, itemName, parsedItemQuantity, parsedItemPrice);
//
//            // Call the addProduct method from ProductDAO, passing the Product object
//            boolean success = productDAO.addProduct(newProduct);
//
//            if (success) {
//                JOptionPane.showMessageDialog(this, "Item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                loadItemsFromDatabase(); // Refresh the table
//                clearFields();           // Clear input fields
//            } else {
//                // Error message will be handled by the ProductDAO's JOptionPane
//            }
//        } catch (Exception e) { // Catch any unexpected exceptions
//            JOptionPane.showMessageDialog(this, "An unexpected error occurred while adding the item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
//        }
//    }
//
//    private void showMessage(String message, String title, int messageType) {
//        JOptionPane.showMessageDialog(this, message, title, messageType);
//    }
//
