package classes;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import posConfig.posConfig;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import javax.xml.transform.OutputKeys;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    public void createTransHeaderXML(String transType, String accountID) {
        try {
            logs.setupLogger();
            String tranNo = "" + (core.getTransNo(accountID) + 1);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd");
            String times = sdf.format(new java.util.Date());
            String dates = sd.format(new java.util.Date());

            // Create a DocumentBuilderFactory
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            // Create a new document
            Document document = documentBuilder.newDocument();

            // Create root element
            Element transaction = document.createElement("transaction");
            document.appendChild(transaction);

            // Create transactionHeader element
            Element transactionHeader = document.createElement("transactionHeader");
            transaction.appendChild(transactionHeader);

            // Create and append child elements to transactionHeader
            appendChildElement(document, transactionHeader, "storeId", "1");
            appendChildElement(document, transactionHeader, "workstation", "2");
            appendChildElement(document, transactionHeader, "businessDate", "2024-10-13");
            appendChildElement(document, transactionHeader, "transDate", "2024-10-13");
            appendChildElement(document, transactionHeader, "endTime", "16:51:13");
            appendChildElement(document, transactionHeader, "transNumber", "22");
            appendChildElement(document, transactionHeader, "tranType", "Logout");
            appendChildElement(document, transactionHeader, "accID", "1");

            try {
                // Create a Transformer to write the document to a file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                // Set output properties for pretty printing
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // Apache-specific property for indentation

                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File("src/transactions/" + posCon.getStoreID() + "_"
                        + posCon.getPosNumber() + "_" + tranNo + ".xml"));
                transformer.transform(domSource, streamResult);
            } catch (TransformerException ex) {
                Logger.getLogger(transactionClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException | ParserConfigurationException | DOMException e) {
            logs.logger.log(Level.SEVERE, "An exception occurred", e);
        }

    }

    private static void appendChildElement(Document document, Element parent, String tagName, String textContent) {
        Element element = document.createElement(tagName); // Create a new element with the specified tag name
        element.setTextContent(textContent);               // Set the text content for the new element
        parent.appendChild(element);                        // Append the new element to the parent
    }

}
