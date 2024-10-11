/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package posConfig;

/**
 *
 * @author MIS
 */
public class posConfig {

    private final int storeID = 1;
    private final int posNumber = 1;
    public static String busDate = "";

    public int getPosNumber() {
        return posNumber;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setBusDate(String date) {
        busDate = date;
    }

    public String getBusDate() {
        return busDate;
    }
}
