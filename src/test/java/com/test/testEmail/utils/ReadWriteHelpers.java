package com.test.testEmail.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * Helper class
 */
public class ReadWriteHelpers {

    private static Logger logger = LoggerFactory.getLogger(ReadWriteHelpers.class);

    /**
     * Close workbook instance
     *
     * @param workbook
     * @throws IOException
     */
    public static void closeWorkbook(Workbook workbook) throws IOException {
        logger.info("Closing workbook");
        workbook.close();
    }

    /**
     * Method to resize sheet column to wrap content
     *
     * @param length
     * @param sheet
     */
    public static void resizeSheetColumns(int length, Sheet sheet){
        logger.info("Resizing column");
        for(int j=0;j<length;j++){
            sheet.autoSizeColumn(j);
        }
    }

}