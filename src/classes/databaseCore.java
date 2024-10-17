/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.sql.*;

/**
 *
 * @author MIS
 */
public class databaseCore {

    Connection con;
    Statement st;
    public static ResultSet rs;
    String query = "";
    logging logs = new logging();

    private void connect() {
        try {
            con = dbConnect.con();
            st = con.createStatement();
        } catch (SQLException e) {
            logs.logger.log(java.util.logging.Level.SEVERE, "An exception occurred", e);
        }
    }

    public void closeConnection() {
        try {
            rs.close();
        } catch (SQLException ex) {
            logs.logger.log(java.util.logging.Level.SEVERE, "An exception occurred", ex);
        } finally {
            logs.closeLogger();
        }
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ResultSet getResultSet() {
        try {
            connect();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            logs.logger.log(java.util.logging.Level.SEVERE, "An exception occurred", e);
        } finally {
            logs.closeLogger();
        }
        return rs;
    }

    public void execute(String query) {
        try {
            connect();
            st.execute(query);
        } catch (SQLException e) {
            logs.logger.log(java.util.logging.Level.SEVERE, "An exception occurred", e);
        } finally {
            logs.closeLogger();
        }
    }

    public void executeUpdate(String query) {
        try {
            connect();
            st.executeUpdate(query);
        } catch (SQLException e) {
            logs.logger.log(java.util.logging.Level.SEVERE, "An exception occurred", e);
        } finally {
            logs.closeLogger();
        }
    }
}
