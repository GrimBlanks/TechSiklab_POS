package classes;

import java.sql.*;

public class dbServerConnect {

    static logging logs = new logging();

    // Database URL, username, and password CL_POS_SERVER
    private static final String URL = "jdbc:mysql://CL_POS_SERVER:3306/cl_posmain";
    private static final String USER = "sa";
    private static final String PASSWORD = "tpdotnet";
    private static Connection connection = null;

    public static Connection con() {

        try {
            // Load the JDBC driver (not always necessary with modern drivers)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Perform database operations here...
        } catch (ClassNotFoundException | SQLException e) {
//            logs.logger.log(java.util.logging.Level.SEVERE, "An exception occurred", e);
        } finally {
            logs.closeLogger();
        }

        return connection;
    }
}
