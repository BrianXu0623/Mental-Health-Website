package elec5619.sydney.edu.au.mental_health_support_website.db.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseHandler {
    static String jdbcUrl = "jdbc:mysql://localhost:3306/my_local_db";
    static String username = "simon";
    static String password = "#Hello12345";

    public static void connect() {

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(jdbcUrl);

            // Now you can use the 'connection' object to perform database operations

            // Don't forget to close the connection when you're done
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void create_table() {
        // SQL statement to create a table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS employees ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "first_name VARCHAR(255) NOT NULL,"
                + "last_name VARCHAR(255) NOT NULL,"
                + "email VARCHAR(255) NOT NULL UNIQUE,"
                + "hire_date DATE)";

        try {
            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a statement object
            Statement statement = connection.createStatement();

            // Execute the SQL statement to create the table
            statement.execute(createTableSQL);

            System.out.println("Table 'employees' created successfully!");

            // Close the resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



