Inventory Management System
A simple Java Swing GUI application for managing product inventory, backed by a MySQL database. It supports basic CRUD (Create, Read, Update, Delete) operations and a search function.

Features
Add, View, Update, Delete Products
Search: Filter products by ID or Name.
Input Validation
Database Integration: MySQL

Technologies
Java Swing (GUI)
MySQL Database
JDBC
NetBeans IDE

Prerequisites
Java Development Kit (JDK 8+)
MySQL Server
MySQL Connector/J JDBC Driver (Download from MySQL Connector/J)

Setup & Run Instructions
1. Database Setup
Create Database: Open MySQL client and run:

CREATE DATABASE IF NOT EXISTS inventorydb;

Create User (Optional): If using inventory_user/inventory_pass, create user and grant privileges. Otherwise, ensure DatabaseConnection.java and ProductDAO.java match your MySQL credentials (e.g., root/root).

CREATE USER 'inventory_user'@'localhost' IDENTIFIED BY 'inventory_pass';
GRANT ALL PRIVILEGES ON inventorydb.* TO 'inventory_user'@'localhost';
FLUSH PRIVILEGES;

The products table will be created automatically by the application.

2. Project Setup (NetBeans)
Clone Repository: git clone <your_github_repository_url>
Open Project: Open the cloned project in NetBeans.
Add MySQL Connector/J: In NetBeans: Project Properties > Libraries > Add JAR/Folder... (select mysql-connector-java-X.Y.Z.jar).

3. Run from NetBeans
Clean and Build Project.
Right-click Project > Run. The InventoryFrame GUI will appear.

Usage
Add: Fill fields (ID, Name, Quantity, Price), click "Add".
View: Products are displayed in the table.
Update: Select row, modify fields, click "Update".
Delete: Select row, click "Delete", confirm.
Search: Type in "Search Here:" field to filter. "Clear" resets search.
