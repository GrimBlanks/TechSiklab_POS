package classes;

import java.sql.*;

public class databaseServerCore {

    Connection con;
    Statement st;
    public static ResultSet rs;
    String query = "";
    logging logs = new logging();

    private void connect() {
        try {
            logs.setupLogger();
            con = dbServerConnect.con();
            st = con.createStatement();
        } catch (Exception e) {
//            logs.logger.log(java.util.logging.Level.SEVERE, "An exception occurred", e);
        } finally {
            logs.closeLogger();
        }
    }

    public void closeConnection() {
        try {
            logs.setupLogger();
            rs.close();
        } catch (Exception ex) {
//            logs.logger.log(java.util.logging.Level.SEVERE, "An exception occurred", ex);
        } finally {
            logs.closeLogger();
        }
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ResultSet getResultSet() {
        try {
            logs.setupLogger();
            connect();
            rs = st.executeQuery(query);
        } catch (Exception e) {
//            logs.logger.log(java.util.logging.Level.SEVERE, "An exception occurred", e);
        } finally {
            logs.closeLogger();
        }
        return rs;
    }

    public void execute(String query) {
        try {
            logs.setupLogger();
            connect();
            st.execute(query);
        } catch (Exception e) {
//            logs.logger.log(java.util.logging.Level.SEVERE, "An exception occurred", e);
        } finally {
            logs.closeLogger();
        }
    }

    public void executeUpdate(String query) {
        try {
            logs.setupLogger();
            connect();
            st.executeUpdate(query);
        } catch (Exception e) {
//            logs.logger.log(java.util.logging.Level.SEVERE, "An exception occurred", e);
        } finally {
            logs.closeLogger();
        }
    }
}
