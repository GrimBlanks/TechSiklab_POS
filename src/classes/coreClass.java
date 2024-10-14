/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import posConfig.posConfig;

/**
 *
 * @author MIS
 */
public class coreClass {

    ResultSet rs;
    databaseCore dbCore = new databaseCore();
    logging logs = new logging();
    posConfig posCon = new posConfig();
    String employeeID = null;
    String accountID = null;

    public boolean login(String username, String password) {
        boolean res = false;
        try {
            logs.setupLogger();
            String query = "SELECT  ah.* "
                    + "FROM accountheader ah "
                    + "JOIN accountdetail ad "
                    + "ON ah.accountID = ad.accountID "
                    + "WHERE ah.deletedOn IS NULL "
                    + "AND userName = '" + username + "' AND password = SHA2('" + password + "', 256) ";
            dbCore.setQuery(query);
            rs = dbCore.getResultSet();
            if (rs.next()) {
                res = true;
                setEmployeeID(rs.getString("employeeID"));
                setAccountID(rs.getString("accountID"));
            }
            rs.close();
            dbCore.closeConnection();
        } catch (IOException | SQLException e) {
            logs.logger.log(Level.SEVERE, "An exception occurred", e);
        }
        return res;
    }

    public boolean checkUser(String clerkID) {
        boolean res = false;
        try {
            logs.setupLogger();
            String query = "SELECT * "
                    + "FROM accountheader "
                    + "WHERE accountID = '" + clerkID + "' "
                    + "AND deletedOn IS NULL";
            dbCore.setQuery(query);
            rs = dbCore.getResultSet();
            if (rs.next()) {
                res = true;
            }
            rs.close();
            dbCore.closeConnection();
        } catch (IOException | SQLException e) {
            logs.logger.log(Level.SEVERE, "An exception occurred", e);
        }
        return res;
    }

    public String getCashierName() {
        String cashierName = null;
        try {
            logs.setupLogger();
            String query = "SELECT employeeID, CONCAT(lastName, \", \",firstName, IFNULL(middleName, \" \"), IFNULL(suffix, \" \")) AS FullName "
                    + "FROM employees "
                    + "WHERE employeeID = '" + this.employeeID + "'";
            dbCore.setQuery(query);
            rs = dbCore.getResultSet();
            if (rs.next()) {
                cashierName = rs.getString("FullName");
            }
            rs.close();
            dbCore.closeConnection();
        } catch (IOException | SQLException e) {
            logs.logger.log(Level.SEVERE, "An exception occurred", e);
        }
        return cashierName;
    }

    public int getTransNo(String accountID) {
        String tranNo = "";
        int finalTran = 0;

        try {
            logs.setupLogger();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String query = "SELECT COUNT(*) transNumber FROM transheader th "
                    + "WHERE th.storeID = '" + posCon.getStoreID() + "' "
                    + "AND th.workstationNumber = '" + posCon.getPosNumber() + "' ";
            dbCore.setQuery(query);
            rs = dbCore.getResultSet();
            if (rs.next()) {
                tranNo = rs.getString("transNumber");
                finalTran = Integer.parseInt(tranNo);
            } else {
                finalTran = 0;
            }
            
            if(finalTran == 9999){
                finalTran = 0;
            }
            rs.close();
            dbCore.closeConnection();
        } catch (IOException | NumberFormatException | SQLException e) {
            logs.logger.log(Level.SEVERE, "An exception occurred", e);
        }

        return finalTran;
    }

    public void setSignedOn(String accountID, String times, String dates) {
        try {
            logs.setupLogger();
            String query = "UPDATE accountdetail "
                    + "SET signedOnTo = '" + posCon.getPosNumber() + "', dateSignedOn = '" + dates + "', timeSignedOn = '" + times + "' "
                    + "WHERE accountID = '" + accountID + "'";
            dbCore.executeUpdate(query);
            dbCore.closeConnection();
        } catch (Exception e) {
            logs.logger.log(Level.SEVERE, "An exception occurred", e);
        }
    }

    public void setSignedOff(String accountID, String times, String dates) {
        try {
            logs.setupLogger();
            String query = "UPDATE accountdetail "
                    + "SET signedOnTo = 0, dateSignedOff = '" + dates + "', timeSignedOff = '" + times + "' "
                    + "WHERE accountID = '" + accountID + "'";
            dbCore.executeUpdate(query);
            dbCore.closeConnection();
        } catch (Exception e) {
            logs.logger.log(Level.SEVERE, "An exception occurred", e);
        }
    }

    public void setAccountID(String accID) {
        accountID = accID;
    }

    public String getAccountID() {
        return this.accountID;
    }

    public int getPOSNumber() {
        return posCon.getPosNumber();
    }

    public int getStoreID() {
        return posCon.getStoreID();
    }

    private void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String checkIfSignedOn(String username) {
        String isSignedOn = "0";

        try {
            logs.setupLogger();
            String query = "SELECT * "
                    + "FROM accountdetail ad "
                    + "JOIN accountheader ah "
                    + "ON ah.accountID = ad.accountID "
                    + "WHERE ah.deletedOn IS NULL "
                    + "AND ad.userName = '" + username + "' "
                    + "AND signedOnTo != 0";
            dbCore.setQuery(query);
            rs = dbCore.getResultSet();
            if (rs.next()) {
                isSignedOn = rs.getString("ad.signedOnTo");
            }
            rs.close();
            dbCore.closeConnection();
        } catch (Exception e) {
            logs.logger.log(Level.SEVERE, "An exception occurred", e);
        }
        return isSignedOn;
    }

    public String getLastSignedOn() {
        String accID = "";
        try {
            logs.setupLogger();
            String query = "SELECT * "
                    + "FROM accountdetail ad "
                    + "JOIN accountheader ah "
                    + "ON ad.accountID = ah.accountID "
                    + "WHERE ad.signedOnTo = " + posCon.getPosNumber() + " "
                    + "AND ah.storeID = " + posCon.getStoreID() + " "
                    + "AND ah.deletedOn IS NULL";
            dbCore.setQuery(query);
            rs = dbCore.getResultSet();
            if (rs.next()) {
                accID = rs.getString("userName");
            }
            rs.close();
            dbCore.closeConnection();
        } catch (Exception e) {
            logs.logger.log(Level.SEVERE, "An exception occurred", e);
        }
        return accID;
    }

    public boolean isWorkstationRegistered() {
        boolean isRegistered = false;
        try {
            logs.setupLogger();
            String query = "SELECT * "
                    + "FROM workstationheader "
                    + "WHERE workstationNumber = " + posCon.getPosNumber() + " "
                    + "AND storeID = " + posCon.getStoreID() + " "
                    + "AND deletedOn IS NULL";
            dbCore.setQuery(query);
            rs = dbCore.getResultSet();
            if (rs.next()) {
                isRegistered = true;
            }
            rs.close();
            dbCore.closeConnection();
        } catch (Exception e) {
            logs.logger.log(Level.SEVERE, "An exception occurred", e);
        }
        return isRegistered;
    }
}
