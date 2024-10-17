package classes;

import forms.mainPOS;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import posConfig.posConfig;

public class transactionClass {

    databaseCore dbCore = new databaseCore();
    logging logs = new logging();
    posConfig posCon = new posConfig();
    coreClass core = new coreClass();
    public static double amount;

    public void createTransHeader(String transType, String accountID) {
        try {
            String tranNo = "" + (core.getTransNo(accountID) + 1);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd");
            String times = sdf.format(new java.util.Date());
            String dates = sd.format(new java.util.Date());
            String query = "INSERT INTO transHeader (storeID, workstationNumber, businessDate, transDate, endTime, transNumber, transType, accID) "
                    + "VALUES('" + posCon.getStoreID() + "', '" + posCon.getPosNumber() + "', '" + mainPOS.finalBusDate + "', '" + dates + "', "
                    + "'" + times + "', '" + tranNo + "', '" + transType + "', '" + accountID + "')";
            dbCore.execute(query);
            if (transType.equalsIgnoreCase("Login")) {
                core.setSignedOn(accountID, times, dates);
            } else if (transType.equalsIgnoreCase("Logout")) {
                core.setSignedOff(accountID, times, dates);
            } else if (transType.equalsIgnoreCase("Declare")) {
                core.setSignedOff(accountID, times, dates);
                core.cashierDeclaration(accountID, dates, times, tranNo, amount);
            }
            dbCore.closeConnection();
        } catch (Exception e) {
            logs.logger.log(Level.SEVERE, "An exception occurred", e);
        } finally {
            logs.closeLogger();
        }
    }
}
