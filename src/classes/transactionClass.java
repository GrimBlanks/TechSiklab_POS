package classes;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import posConfig.posConfig;

public class transactionClass {

    databaseCore dbCore = new databaseCore();
    logging logs = new logging();
    posConfig posCon = new posConfig();
    coreClass core = new coreClass();

    public void createTransHeader(String transType, String accountID) {
        try {
            logs.setupLogger();
            String tranNo = "" + (core.getTransNo(accountID) + 1);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd");
            String times = sdf.format(new java.util.Date());
            String dates = sd.format(new java.util.Date());
            String query = "INSERT INTO transHeader (storeID, workstationNumber, businessDate, transDate, endTime, transNumber, transType, accID) "
                    + "VALUES('" + posCon.getStoreID() + "', '" + posCon.getPosNumber() + "', '" + dates + "', '" + dates + "', "
                    + "'" + times + "', '" + tranNo + "', '" + transType + "', '" + accountID + "')";
            if (transType.equalsIgnoreCase("Login")) {
                core.setSignedOn(accountID, times, dates);
            } else {
                core.setSignedOff(accountID, times, dates);
            }
            dbCore.execute(query);
            dbCore.closeConnection();
        } catch (Exception e) {
            logs.logger.log(Level.SEVERE, "An exception occurred", e);
        }
    }

}
