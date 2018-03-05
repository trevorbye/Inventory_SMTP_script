import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ApachePOIExcelBuilder {

    private static int getSpecColumnIndex(Row row, int endPosition, String spec) {
        int targetIndex = 0;

        for (int x = 11; x <= endPosition; x++) {
            //get contents
            String contents = row.getCell(x).getStringCellValue();
            //split and get spec
            String rowSpec = contents.split("-")[1];

            if (rowSpec.equals(spec)) {
                targetIndex = x;
                break;
            }
        }
        return targetIndex;
    }

    public static String buildExcelFile(List<QueryRecordEntity> resultSet, String recipient) {
        String fileNameWithoutDate;
        String dateAsString = new SimpleDateFormat("MMddyyyy").format(new Date());
        String fullFileName;

        switch (recipient) {
            case "Irene":
                fileNameWithoutDate = "Lynden powder as of ";
                break;
            case "Nate":
                fileNameWithoutDate = "Sunnyside 310724, 310726, 310015 as of ";
                break;
            case "Nate-Jerome"  :
                fileNameWithoutDate = "Jerome 310724, 310726, 310015 as of ";
                break;
            case "Crystal":
                fileNameWithoutDate = "Sunnyside 310448, 310447 as of ";
                break;
            default:
                fileNameWithoutDate = "";
                break;
        }

        fullFileName = fileNameWithoutDate + dateAsString + ".xlsx";

        //build workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        //set sheet settings
        sheet.setAutoFilter(CellRangeAddress.valueOf("A:AZ"));
        sheet.createFreezePane(0, 1);

        Row headerRow = sheet.createRow(0);

        int numSpecColumnsToBuild = QueryRecordEntity.getMaxSpecCountFromQuery(resultSet);
        List<String> fullUniqueSpecList = QueryRecordEntity.getFullUniqueSpeclist(resultSet);

        //build header row
        headerRow.createCell(0).setCellValue("Age-Days");
        headerRow.createCell(1).setCellValue("Whse");

        headerRow.createCell(2).setCellValue("Whse Name");
        sheet.setColumnWidth(2, 30*256);

        headerRow.createCell(3).setCellValue("Item#");

        headerRow.createCell(4).setCellValue("Item Description");
        sheet.setColumnWidth(4, 20*256);

        headerRow.createCell(5).setCellValue("Lot Status");

        headerRow.createCell(6).setCellValue("Lot#");
        sheet.setColumnWidth(6, 12*256);

        headerRow.createCell(7).setCellValue("Sublot#");
        sheet.setColumnWidth(7, 12*256);

        headerRow.createCell(8).setCellValue("Grade");
        headerRow.createCell(9).setCellValue("Pounds");
        headerRow.createCell(10).setCellValue("Qty_2");

        int cellPlaceholder = 11;
        //build number of spec columns from spec count and tack on spec label
        for (String spec : fullUniqueSpecList) {
            headerRow.createCell(cellPlaceholder).setCellValue("Spec-" + spec);
            sheet.setColumnWidth(cellPlaceholder, 14*256);
            cellPlaceholder++;
        }

        headerRow.createCell(cellPlaceholder).setCellValue("Prod Plant");
        sheet.setColumnWidth(cellPlaceholder, 40*256);

        headerRow.createCell(cellPlaceholder + 1).setCellValue("Major Prd Grp");
        sheet.setColumnWidth(cellPlaceholder + 1, 20*256);

        headerRow.createCell(cellPlaceholder + 2).setCellValue("Lot Make Date");
        sheet.setColumnWidth(cellPlaceholder + 2, 15*256);

        //loop through resultSet and populate sheet
        int queryRowTracker = 1;
        for (QueryRecordEntity entity : resultSet) {
            Row runningRow = sheet.createRow(queryRowTracker);

            runningRow.createCell(0).setCellValue(entity.getAgeDays());

            if (NumberUtils.isDigits(entity.getWhseId())) {
                runningRow.createCell(1).setCellValue(Long.parseLong(entity.getWhseId()));
            } else {
                runningRow.createCell(1).setCellValue(entity.getWhseId());
            }

            runningRow.createCell(2).setCellValue(entity.getWhseName());

            if (NumberUtils.isDigits(entity.getItemNum())) {
                runningRow.createCell(3).setCellValue(Long.parseLong(entity.getItemNum()));
            } else {
                runningRow.createCell(3).setCellValue(entity.getItemNum());
            }

            runningRow.createCell(4).setCellValue(entity.getItemDesc());
            runningRow.createCell(5).setCellValue(entity.getLotStatus());

            if (NumberUtils.isDigits(entity.getLotNum())) {

                runningRow.createCell(6).setCellValue(Long.parseLong(entity.getLotNum()));
            } else {
                runningRow.createCell(6).setCellValue(entity.getLotNum());
            }

            if (NumberUtils.isDigits(entity.getSublotNum())) {
                runningRow.createCell(7).setCellValue(Long.parseLong(entity.getSublotNum()));
            } else {
                runningRow.createCell(7).setCellValue(entity.getSublotNum());
            }

            if (NumberUtils.isDigits(entity.getQcGrade())) {
                runningRow.createCell(8).setCellValue(Long.parseLong(entity.getQcGrade()));
            } else {
                runningRow.createCell(8).setCellValue(entity.getQcGrade());
            }

            runningRow.createCell(9).setCellValue(entity.getQtyPounds());
            runningRow.createCell(10).setCellValue(entity.getQty2());

            if (entity.getSpecList() != null) {
                for (String spec : entity.getSpecList()) {
                    //find correct spec column and set val
                    int specColumnIndex = getSpecColumnIndex(headerRow, cellPlaceholder - 1, spec);
                    runningRow.createCell(specColumnIndex).setCellValue(spec);
                }
            }

            runningRow.createCell(cellPlaceholder).setCellValue(entity.getProdPlant());
            runningRow.createCell(cellPlaceholder + 1).setCellValue(entity.getProdGroup());
            runningRow.createCell(cellPlaceholder + 2).setCellValue(entity.getLotMakeDate());

            //change to correct date style
            CellStyle style = workbook.createCellStyle();
            CreationHelper helper = workbook.getCreationHelper();
            style.setDataFormat(helper.createDataFormat().getFormat("m/dd/yyyy"));
            runningRow.getCell(cellPlaceholder + 2).setCellStyle(style);

            queryRowTracker++;
        }

        //write to output stream
        String dir = "C:\\Users\\s-tbye\\scripts\\ID_inv_extracts\\";
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(dir + fullFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //use @return value as file attachment name in SMTP call
        return fullFileName;
    }
}
