package classes;

import java.sql.ResultSet;
import java.util.logging.Level;

public class threadClass {

    databaseCore dbCore = new databaseCore();
    databaseServerCore dbServCore = new databaseServerCore();
    ResultSet rs;
    logging logs = new logging();

    public void getAccountDetail(String accountID) {
        //Get details from local db and put it in a single string separated by comma
        String accountDetails = null;
        try {
            logs.setupLogger();
            if (!accountID.isBlank() || !accountID.isEmpty()) {
                String query = "SELECT * "
                        + "FROM accountdetail "
                        + "WHERE accountID = '" + accountID + "' ";
                dbCore.setQuery(query);
                rs = dbCore.getResultSet();
                if (rs.next()) {
                    accountDetails = rs.getString("signedOnTo") + "," + rs.getString("profileID") + "," + rs.getString("dateSignedOn") + "," + rs.getString("dateSignedOff") + ","
                            + rs.getString("timeSignedOff") + "," + rs.getString("timeSignedOn") + "," + rs.getString("businessDate");
                    System.out.println(accountDetails + "------AccID: " + accountID);
                    updateServerAccDetails(accountDetails, accountID);
                }
                rs.close();
                dbCore.closeConnection();
            }
        } catch (Exception e) {
            logging.logger.log(Level.SEVERE, "An exception occurred", e);
        } finally {
            logs.closeLogger();
        }
    }

    private void updateServerAccDetails(String accDetails, String accountID) {
        //Update the account details on the server

        String[] accountDetails = accDetails.split(",");
        try {
            logs.setupLogger();
            String query = "UPDATE accountdetail "
                    + "SET signedOnTo = '" + accountDetails[0] + "', profileID = '" + accountDetails[1] + "', dateSignedOn = '" + accountDetails[2] + "', dateSignedOff = '" + accountDetails[3] + "', "
                    + "timeSignedOff = '" + accountDetails[4] + "', timeSignedOn = '" + accountDetails[5] + "', businessDate = '" + accountDetails[6] + "' "
                    + "WHERE accountID = '" + accountID + "'";
            dbServCore.executeUpdate(query);
        } catch (Exception e) {
//            logging.logger.log(Level.SEVERE, "An exception occurred", e);
        } finally {
            logs.closeLogger();
        }
    }
}
