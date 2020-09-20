package com.test.testEmail.excelUtils;

import com.test.testEmail.utils.Constants;
import com.test.testEmail.utils.ReadWriteHelpers;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ExcelWriter {

    Logger logger = LoggerFactory.getLogger(ExcelWriter.class);

    private String from;
    private String subject;
    private String body;

    private Workbook writerWorkbook;

    public ExcelWriter(String from,String subject,String body){
        this.body=body;
        this.subject=subject;
        this.from=from;
    }

    public void readWriteExcel() throws IOException {
        writerWorkbook = new XSSFWorkbook();
        Sheet outputSheet = createSheetAndHeader();
        writeSheetRows(outputSheet);
        writeDateToSheet();
    }

    /**
     * Method to create header cells
     *
     * @return sheet
     */
    private Sheet createSheetAndHeader() {
        logger.info("Creating sheet header");

        Sheet sheet = writerWorkbook.getSheet("Email_Output");
        if (sheet == null) {
            logger.info("Steep already exists in workbook");
            sheet = writerWorkbook.createSheet("Email_Output");
        }
        Font headerFont = writerWorkbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);

        CellStyle headerCellStyle = writerWorkbook.createCellStyle();
        headerCellStyle.setFillBackgroundColor(IndexedColors.GREY_80_PERCENT.getIndex());

        headerCellStyle.setFont(headerFont);
        Row outputHeaderRow = sheet.createRow(0);

        Cell outputCellFrom = outputHeaderRow.createCell(0);
        outputCellFrom.setCellValue("Email From");
        outputCellFrom.setCellStyle(headerCellStyle);

        Cell outputCellSubject = outputHeaderRow.createCell(1);
        outputCellSubject.setCellValue("Subject");
        outputCellSubject.setCellStyle(headerCellStyle);


        Cell outputCellBody = outputHeaderRow.createCell(2);
        outputCellBody.setCellValue("Body");
        outputCellBody.setCellStyle(headerCellStyle);

        //Resize column
        ReadWriteHelpers.resizeSheetColumns(3, sheet);
        return sheet;
    }

    private void writeSheetRows(Sheet outputSheet) {
        logger.info("Write data rows to output sheet rowNumber:::{}", 1);

        Row outputRow = outputSheet.createRow(1);
        for(int i=0;i<3;i++){
            Cell outputCell = outputRow.createCell(i);
            if(i==0){
                outputCell.setCellValue(from);
            }else if(i==1){
                outputCell.setCellValue(subject);
            }else{
                outputCell.setCellValue(body);
            }
        }
        ReadWriteHelpers.resizeSheetColumns(1, outputSheet);
    }

    private void writeDateToSheet() throws IOException {
        logger.info("Write data to output sheet and close file IO");

        File testResultsFolder = new File(Constants.OUTPUT_FILE_NAME);
        FileOutputStream fileOut = new FileOutputStream(testResultsFolder);
        writerWorkbook.write(fileOut);
        fileOut.close();
        ReadWriteHelpers.closeWorkbook(writerWorkbook);
    }
}