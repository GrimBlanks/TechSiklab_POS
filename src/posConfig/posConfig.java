/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package posConfig;

import java.io.File;
import java.util.logging.FileHandler;

/**
 *
 * @author MIS
 */
public class posConfig {

    public static final int storeID = 1;
    public static final int posNumber = 1;
    public static String busDate = "";
    private String path = "src/posConfig/";

    public int getPosNumber() {
        return posNumber;
    }

    public int getStoreID() {
        return storeID;
    }
}
