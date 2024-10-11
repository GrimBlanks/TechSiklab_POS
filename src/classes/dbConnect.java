package classes;

import java.io.IOException;
import java.sql.*;

public class dbConnect {

    static logging logs = new logging();

    // Database URL, username, and password
    private static final String URL = "jdbc:mysql://localhost:3306/cl_posmain";
    private static final String USER = "root";
    private static final String PASSWORD = "tpdotnet";
    private static Connection connection = null;

    public static Connection con() {

        try {
            logs.setupLogger();
            // Load the JDBC driver (not always necessary with modern drivers)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Perform database operations here...
        } catch (IOException | ClassNotFoundException | SQLException e) {
            logs.logger.log(java.util.logging.Level.SEVERE, "An exception occurred", e);
        }

        return connection;
    }
}
