package posConfig;

import classes.databaseCore;
import classes.logging;
import java.sql.*;

public class posConfig {

    private final int storeID = 1;
    private final int posNumber = 2;
    logging logs = new logging();
    databaseCore dbCore = new databaseCore();
    ResultSet rs;

    public int getPosNumber() {
        return posNumber;
    }

    public int getStoreID() {
        return storeID;
    }

    public String getBusDate(String accountID) {
        String busDate = "";

        try {
            String query = "SELECT * "
                    + "FROM accountdetail ad "
                    + "JOIN accountheader ah "
                    + "ON ah.accountID = ad.accountID "
                    + "WHERE ah.deletedOn IS NULL "
                    + "AND ad.accountID = '" + accountID + "' ";
            dbCore.setQuery(query);
            rs = dbCore.getResultSet();
            if (rs.next()) {
                busDate = rs.getString("ad.businessDate");
            }
            rs.close();
            dbCore.closeConnection();
        } catch (Exception e) {
            logs.logger.log(java.util.logging.Level.SEVERE, "An exception occurred", e);
        } finally {
            logs.closeLogger();
        }

        return busDate;
    }
}
