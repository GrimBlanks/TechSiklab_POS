package classes;

import forms.loginForm;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mainClass {

    public static void main(String[] args) {
        new loginForm().setVisible(true);
        createTransFolderToday();
    }

    private static void createTransFolderToday() {
        // Define the path to the transactions folder
        String transactionsPath = "src/transactions";

        // Get today's date in a specific format (e.g., yyyy-MM-dd)
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // Create the directory for today's transactions
        File todayFolder = new File(transactionsPath, today);

        // Check if the directory already exists, if not, create it
        if (!todayFolder.exists()) {
            todayFolder.mkdirs();
        }
    }
}
