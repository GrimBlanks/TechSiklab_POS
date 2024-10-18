package classes;

import java.util.ArrayList;
import java.util.List;

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

}
