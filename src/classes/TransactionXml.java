package classes;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import forms.mainPOS;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import posConfig.posConfig;

// Header class
class TransHeader {

    String transNumber;
    String businessDate;
    String transType;
    String workstationNumber;
    String storeID;
    String transDate;
    String endTime;
    String accountID;

    public TransHeader(String storeID, String workstationNumber, String businessDate, String transDate, String endTime, String transNumber, String transType, String accountID) {
        this.transNumber = transNumber;
        this.businessDate = businessDate;
        this.transType = transType;
        this.workstationNumber = workstationNumber;
        this.storeID = storeID;
        this.transDate = transDate;
        this.endTime = endTime;
        this.accountID = accountID;
    }

    public String getTransNumber() {
        return this.transNumber;
    }

    public String getBusinessDate() {
        return this.businessDate;
    }

    public String getTransType() {
        return this.transType;
    }

    public String getWorkstationNumber() {
        return this.workstationNumber;
    }

    public String getStoreID() {
        return this.storeID;
    }

    public String getTransDate() {
        return this.transDate;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getAccountID() {
        return this.accountID;
    }
}

// Detail class
class TransDetail {

    String itemName;
    double price;
    int quantity;
    String description;

    public TransDetail(String itemName, double price, int quantity, String description) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    // Getters
    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }
}

// Create a new class for the root element
class Transaction {

    private TransHeader header;
    private List<TransDetail> details; // Use a list for multiple details

    public Transaction(TransHeader header) {
        this.header = header;
        this.details = new ArrayList<>();
    }

    public TransHeader getHeader() {
        return header;
    }

    public void setHeader(TransHeader header) {
        this.header = header;
    }

    public List<TransDetail> getDetails() {
        return details;
    }

    public void addDetail(TransDetail detail) {
        this.details.add(detail);
    }
}

public class TransactionXml {

//    static posConfig posCon = new posConfig();
//
//    public static void main(String[] args) {
//        // Create an instance of Header
//        TransHeader transHeader = new TransHeader("123", "2024-10-16", "Login", "2", "1");
//
//        // Create a Transaction object with the Header
//        Transaction transaction = new Transaction(transHeader);
//
//        // Add item details to the transaction
//        transaction.addDetail(new TransDetail("Service A", 100.00, 1, "Description of Service A"));
//        transaction.addDetail(new TransDetail("Service B", 150.75, 2, "Description of Service B"));
//
//        // Create an instance of XmlMapper
//        XmlMapper xmlMapper = new XmlMapper();
//
//        try {
//            // Serialize the Transaction object to XML and save it to a file
//            xmlMapper.writeValue(new File("src/transactions/" + mainPOS.finalBusDate + "/" + posCon.getStoreID() + "_" + posCon.getPosNumber() + "_" + mainPOS.finalBusDate + ".xml"), transaction);
//            System.out.println("XML file created successfully: transaction.xml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
