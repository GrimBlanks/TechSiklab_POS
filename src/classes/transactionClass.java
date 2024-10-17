package classes;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import forms.mainPOS;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import posConfig.posConfig;

public class transactionClass {

    databaseCore dbCore = new databaseCore();
    logging logs = new logging();
    posConfig posCon = new posConfig();
    coreClass core = new coreClass();
    TransactionXml trans = new TransactionXml();
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
                // Create an instance of Header
                TransHeader transHeader = new TransHeader(posCon.getStoreID() + "", posCon.getPosNumber() + "", mainPOS.finalBusDate, dates, times, tranNo, "Login", accountID);

                // Create a Transaction object with the Header
                Transaction transaction = new Transaction(transHeader);

                // Create an instance of XmlMapper
                XmlMapper xmlMapper = new XmlMapper();

                try {
                    // Serialize the Transaction object to XML and save it to a file
                    xmlMapper.writeValue(new File("src/transactions/" + mainPOS.finalBusDate + "/" + posCon.getStoreID() + "_"
                            + posCon.getPosNumber() + "_" + tranNo + "_" + mainPOS.finalBusDate + "_" + times.replace(":", "-") + ".xml"), transaction);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (transType.equalsIgnoreCase("Logout")) {
                core.setSignedOff(accountID, times, dates);
                // Create an instance of Header
                TransHeader transHeader = new TransHeader(posCon.getStoreID() + "", posCon.getPosNumber() + "", mainPOS.finalBusDate, dates, times, tranNo, "Logout", accountID);

                // Create a Transaction object with the Header
                Transaction transaction = new Transaction(transHeader);

                // Create an instance of XmlMapper
                XmlMapper xmlMapper = new XmlMapper();

                try {
                    // Serialize the Transaction object to XML and save it to a file
                    xmlMapper.writeValue(new File("src/transactions/" + mainPOS.finalBusDate + "/" + posCon.getStoreID() + "_"
                            + posCon.getPosNumber() + "_" + tranNo + "_" + mainPOS.finalBusDate + "_" + times.replace(":", "-") + ".xml"), transaction);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (transType.equalsIgnoreCase("Declare")) {
                core.setSignedOff(accountID, times, dates);
                core.cashierDeclaration(accountID, dates, times, tranNo, amount);

                // Create an instance of Header
                TransHeader transHeader = new TransHeader(posCon.getStoreID() + "", posCon.getPosNumber() + "", mainPOS.finalBusDate, dates, times, tranNo, "Declare", accountID);

                // Create a Transaction object with the Header
                Transaction transaction = new Transaction(transHeader);

                // Create an instance of XmlMapper
                XmlMapper xmlMapper = new XmlMapper();

                try {
                    // Serialize the Transaction object to XML and save it to a file
                    xmlMapper.writeValue(new File("src/transactions/" + mainPOS.finalBusDate + "/" + posCon.getStoreID() + "_"
                            + posCon.getPosNumber() + "_" + tranNo + "_" + mainPOS.finalBusDate + "_" + times.replace(":", "-") + ".xml"), transaction);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            dbCore.closeConnection();
        } catch (Exception e) {
            logs.logger.log(Level.SEVERE, "An exception occurred", e);
        } finally {
            logs.closeLogger();
        }
    }
}
