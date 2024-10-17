package classes;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class logging {

    public static final Logger logger = Logger.getLogger(logging.class.getName());
    private static FileHandler fileHandler;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String LOG_FILE_PATH = "src/logs/error-log-" + sdf.format(new Date()) + ".txt";

    public void setupLogger() throws IOException {
        // Ensure the log directory exists
        File logFile = new File(LOG_FILE_PATH);
        File logDir = logFile.getParentFile();
        if (!logDir.exists()) {
            logDir.mkdirs(); // Create the directory if it does not exist
        }

        // Create a FileHandler to write logs to a file
        fileHandler = new FileHandler(LOG_FILE_PATH, true);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL);
    }

    public void closeLogger() {
        if (fileHandler != null) {
            fileHandler.close();
            logger.removeHandler(fileHandler);
        }
    }
}
