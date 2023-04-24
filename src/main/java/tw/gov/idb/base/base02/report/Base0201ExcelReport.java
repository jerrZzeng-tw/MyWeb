package tw.gov.idb.base.base02.report;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import tw.gov.idb.base.base02.report.cases.Base0201RptCase;
import tw.gov.idb.base.base02.report.cases.Base0201RptCase.rptCase;
import tw.gov.idb.base.framework.report.ExcelReportBase;
import tw.gov.idb.base.util.DateUtility;

import java.io.ByteArrayOutputStream;

public class Base0201ExcelReport extends ExcelReportBase {
    public static String REPORT_NAME = "公司明細表";
    private Sheet tempSheet;
    private Sheet targetSheet;

    private static final int COPY_COL_LENTH = 14;

    public Base0201ExcelReport(boolean isProtectSheet) throws Exception {
        super("Base0201RptCaseExcel.xlsx");//使用template用
        tempSheet = workbook.getSheetAt(0);
        workbook.createSheet(REPORT_NAME);
        targetSheet = workbook.getSheetAt(1);
        this.isProtectSheet = isProtectSheet;
    }

    public ByteArrayOutputStream doReport(Base0201RptCase rptCase) throws Exception {
        //templete(第0張表)從第0行到第3行 複製到 (第1張表)的第0行開始
        copyRows(workbook.getSheetName(0), workbook.getSheetName(1), 0, 3, 0, COPY_COL_LENTH);
        targetSheet.getRow(1).getCell(13).setCellValue("印表日期：" + DateUtility.formatNowChineseDateString(false));

        //備註: Style 可以在 templete 例如:設定靠右 並且千分位 
        int i = 3;//從第幾行開始
        int num = 1;
        for (rptCase rpt : rptCase.getRptCases()) {
            copyRows(workbook.getSheetName(0), workbook.getSheetName(1), 3, 3, i, COPY_COL_LENTH);
            Row row = targetSheet.getRow(i);

            row.getCell(0).setCellValue(num);
            row.getCell(1).setCellValue(rpt.getCompanyName());
            row.getCell(2).setCellValue(rpt.getOwner());
            row.getCell(3).setCellValue(rpt.getOwnerIdn());
            row.getCell(4).setCellValue(rpt.getEmail());
            row.getCell(5).setCellValue(rpt.getWebsite());
            row.getCell(6).setCellValue(rpt.getUniformNumbers());
            row.getCell(7).setCellValue(rpt.getPersons());
            row.getCell(8).setCellValue(rpt.getCapital());
            row.getCell(9).setCellValue(rpt.getCity());
            row.getCell(10).setCellValue(rpt.getArea());
            row.getCell(11).setCellValue(rpt.getAddr());
            row.getCell(12).setCellValue(rpt.getIssueDate());
            row.getCell(13).setCellValue(rpt.getUpdateTime());

            i++;
            num++;
        }
        return doEndStep();
    }
}
