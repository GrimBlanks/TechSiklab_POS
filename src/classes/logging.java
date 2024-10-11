package classes;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class logging {

    // Create a Logger instance
    public final Logger logger = Logger.getLogger(logging.class.getName());

    private final String LOG_FILE_PATH = "src/logs/error-log.txt";

    public void setupLogger() throws IOException {
        // Ensure the log directory exists
        File logFile = new File(LOG_FILE_PATH);
        File logDir = logFile.getParentFile();
        if (!logDir.exists()) {
            logDir.mkdirs(); // Create the directory if it does not exist
        }

        // Create a FileHandler to write logs to a file
        FileHandler fileHandler;
        if (logFile.exists()) {
            // Append to the existing file
            fileHandler = new FileHandler(LOG_FILE_PATH, true);
        } else {
            // Create a new file and write logs
            fileHandler = new FileHandler(LOG_FILE_PATH);
        }

        // Create a SimpleFormatter to format the log messages
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);

        // Add the FileHandler to the logger
        logger.addHandler(fileHandler);

        // Set the log level (optional)
        logger.setLevel(Level.ALL);
    }
}
