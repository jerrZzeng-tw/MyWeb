package tw.gov.idb.base.framework.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.iisigroup.easyreport.xls.XlsReport;

import tw.gov.idb.base.util.DateUtility;
import tw.gov.idb.base.util.NumberUtility;

/**
 * Report Base for Excel Report
 *
 *  
 */
public class ExcelReportBase extends XlsReport {
    public boolean isProtectSheet = false; // 是否加密報表

    public ExcelReportBase() {
        super();
    }

    public ExcelReportBase(boolean fileFlag) {
        super(fileFlag);
    }

    public ExcelReportBase(String filePath) {
        super(filePath);
    }

    public void setCellValue(Row row, Integer cellPosition, Object cellValue) {
        setCellValue(row, cellPosition, String.valueOf(cellValue), row.getCell(cellPosition).getCellStyle());
    }
    
    /**
     * 若轉數值轉換異常(非數值或null)則以字串格式印出空白，否則該cell以數值型態印出
     * @param row
     * @param cellPosition
     * @param cellValue
     * @param cellStyle
     */
    public void setCellStringOrNumValue(Row row, Integer cellPosition, String cellValue, CellStyle cellStyle) {
        try {
            setCellValue(row, cellPosition, NumberUtility.toDouble(cellValue),cellStyle);
        }catch (Exception  e) {
            setCellValue(row, cellPosition, "",cellStyle);
        }
    }
    
    /**
     * 清除 template sheet，並回傳 stream
     *
     * @return
     * @throws IOException
     */
    protected ByteArrayOutputStream doEndStep() throws IOException {
        workbook.removeSheetAt(0);
        Sheet sheet = workbook.getSheetAt(0);
        sheet.setSelected(true);
        sheet.setFitToPage(true);
        sheet.setAutobreaks(true);
        sheet.setMargin(HSSFSheet.TopMargin, 0);
        sheet.setMargin(HSSFSheet.BottomMargin, 0);
        sheet.setMargin(HSSFSheet.LeftMargin, 0);
        sheet.setMargin(HSSFSheet.RightMargin, 0);
        // workbook.getSheetAt(0).protectSheet(RPT_LOCK_PW);
        sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
        sheet.getPrintSetup().setFitWidth((short) 1);
        sheet.getPrintSetup().setFitHeight((short) 0);
        if (isProtectSheet) {
            workbook.getSheetAt(0).protectSheet(DateUtility.getNowWestDateTimeMs());
        }
        workbook.write(bao);
        workbook.close();
        return bao;
    }
    
    /**
     * 清除 template sheet，並回傳 stream
     *
     * @return
     * @throws IOException
     */
    protected ByteArrayOutputStream getByteArray() throws IOException {
        workbook.removeSheetAt(0);
        Sheet sheet = workbook.getSheetAt(0);
        sheet.setSelected(true);
        sheet.setFitToPage(true);
        sheet.setAutobreaks(true);
        sheet.setMargin(HSSFSheet.TopMargin, 0);
        sheet.setMargin(HSSFSheet.BottomMargin, 0);
        sheet.setMargin(HSSFSheet.LeftMargin, 0);
        sheet.setMargin(HSSFSheet.RightMargin, 0);
        // workbook.getSheetAt(0).protectSheet(RPT_LOCK_PW);
        sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
        sheet.getPrintSetup().setFitWidth((short) 1);
        sheet.getPrintSetup().setFitHeight((short) 0);
        if (isProtectSheet) {
            workbook.getSheetAt(0).protectSheet(DateUtility.getNowWestDateTimeMs());
        }
        workbook.write(bao);
        workbook.close();
        return bao;
    }
}
