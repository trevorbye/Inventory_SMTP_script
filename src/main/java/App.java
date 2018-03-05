import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {

        SQLServerConnectionService sqlService = new SQLServerConnectionService();
        SMTPService smtpService = new SMTPService();

        Connection connection = sqlService.connectToSQLServer();
        List<QueryRecordEntity> ireneQuery = new ArrayList<>();
        List<QueryRecordEntity> nateQuery = new ArrayList<>();
        List<QueryRecordEntity> nateJeromeQuery = new ArrayList<>();
        List<QueryRecordEntity> crystalQuery = new ArrayList<>();

        try {
            ireneQuery = sqlService.getQueryResults(connection,"Irene");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            nateQuery = sqlService.getQueryResults(connection, "Nate");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            crystalQuery = sqlService.getQueryResults(connection, "Crystal");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            nateJeromeQuery = sqlService.getQueryResults(connection, "Nate-Jerome");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //overwrite whse names
        ireneQuery = QueryListUtilityMethods.replaceWhseNames(ireneQuery);
        nateQuery = QueryListUtilityMethods.replaceWhseNames(nateQuery);
        crystalQuery = QueryListUtilityMethods.replaceWhseNames(crystalQuery);
        nateJeromeQuery = QueryListUtilityMethods.replaceWhseNames(nateJeromeQuery);

        //delete all excel files in dir
        String dir = "C:\\Users\\s-tbye\\scripts\\ID_inv_extracts";

        try {
            FileUtils.cleanDirectory(new File(dir));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //pass queryLists to ApachePOI to build excel files
        //builder returns filename as String and passes to SMTP method
        /*
        smtpService.sendEmail("Irene", ApachePOIExcelBuilder.buildExcelFile(ireneQuery, "Irene"), smtpService.connectToSMTP());
        smtpService.sendEmail("Nate", ApachePOIExcelBuilder.buildExcelFile(nateQuery, "Nate"), smtpService.connectToSMTP());
        smtpService.sendEmail("Nate-Jerome", ApachePOIExcelBuilder.buildExcelFile(nateJeromeQuery, "Nate-Jerome"), smtpService.connectToSMTP());
        */
        smtpService.sendEmail("Crystal", ApachePOIExcelBuilder.buildExcelFile(crystalQuery, "Crystal"), smtpService.connectToSMTP());
    }
}