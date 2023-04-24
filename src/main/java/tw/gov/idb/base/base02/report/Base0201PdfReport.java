package tw.gov.idb.base.base02.report;

import com.iisigroup.easyreport.pdf.ReportBase;
import com.iisigroup.easyreport.pdf.exception.ReportException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import tw.gov.idb.base.base02.report.cases.Base0201RptCase;
import tw.gov.idb.base.util.DateUtility;
import tw.gov.idb.base.util.ExceptionUtility;

import java.util.List;

@Slf4j
public class Base0201PdfReport extends ReportBase {

    public static final String TITLE = "工業局";
    public static final String REPORT_NAME = "公司明細表";
    public static final String REPORT_CODE = "BASE0201";
    private static final float PAGEWIDTH = 100f;
    private static final int ALL_COL = 100;
    private PdfPTable table = new PdfPTable(ALL_COL); // 新建table

    private static final int TITLE_FONT_SIZE = 14;
    private static final int DEFAULT_FONT_SIZE = 10;

    public Base0201PdfReport() throws ReportException {
        super();
    }

    public Base0201PdfReport(String filename) throws ReportException {
        super(filename);
    }

    //A4橫印
    @Override
    public Document createDocument() {
        // 參數說明: Document(頁面大小, 左邊空白, 右邊空白, 上面空白, 下面空白)
        return new Document(PageSize.A4.rotate(), 35, 35, 10, 0);
    }

    //A4直印
    //	@Override
    //	    public Document createDocument() { // 調整為A4直印(預設橫印)，四周邊界調整
    //	        return new Document(PageSize.A4, 20, 20, 10, 10);
    //	}

    public void printPage(Base0201RptCase rptCase) throws Exception {
        try {
            document.open();
            table.setWidthPercentage(PAGEWIDTH);
            header(table);
            body(table, rptCase.getRptCases());
            document.add(table);
        } catch (Exception e) {
            log.error("Base0201PdfReport 公司明細表  err!" + ExceptionUtility.getStackTrace(e));
        } finally {
            document.close();
        }
    }

    private void header(PdfPTable table) {
        addCell(table, 100, 1, TITLE, TITLE_FONT_SIZE, 0, Element.ALIGN_CENTER);
        addCell(table, 100, 1, REPORT_NAME, TITLE_FONT_SIZE, 0, Element.ALIGN_CENTER);
        // row
        addCell(table, 100, 1, "印表日期：" + DateUtility.formatChineseDateString(DateUtility.getNowWestDate(), false),
                DEFAULT_FONT_SIZE, 0, Element.ALIGN_LEFT);

    }

    private void body(PdfPTable table, List<Base0201RptCase.rptCase> rptCases) {
        int num = 1;

        // row 同一行 總和為100 根據你設定的 ALL_COL
        addCell(table, 5, 1, "序號", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        addCell(table, 7, 1, "公司名稱", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        addCell(table, 7, 1, "負責人姓名", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        addCell(table, 7, 1, "負責人IDN", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        addCell(table, 10, 1, "公司EMAIL", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        addCell(table, 10, 1, "公司網址	", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        addCell(table, 7, 1, "統一編號", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        addCell(table, 5, 1, "員工人數", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        addCell(table, 5, 1, "資本額", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        addCell(table, 5, 1, "縣市", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        addCell(table, 5, 1, "鄉鎮市", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        addCell(table, 10, 1, "地址", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        addCell(table, 7, 1, "公司成立日期", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        addCell(table, 10, 1, "資料異動時間", DEFAULT_FONT_SIZE, 1, Element.ALIGN_CENTER);
        if (CollectionUtils.isNotEmpty(rptCases)) {
            // row
            for (Base0201RptCase.rptCase rpt : rptCases) {
                addCell(table, 5, 1, String.valueOf(num), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                addCell(table, 7, 1, rpt.getCompanyName(), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                addCell(table, 7, 1, rpt.getOwner(), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                addCell(table, 7, 1, rpt.getOwnerIdn(), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                addCell(table, 10, 1, rpt.getEmail(), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                addCell(table, 10, 1, rpt.getWebsite(), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                addCell(table, 7, 1, rpt.getUniformNumbers(), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                addCell(table, 5, 1, rpt.getPersons(), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                addCell(table, 5, 1, rpt.getCapital(), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                addCell(table, 5, 1, rpt.getCity(), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                addCell(table, 5, 1, rpt.getArea(), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                addCell(table, 10, 1, rpt.getAddr(), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                addCell(table, 7, 1, rpt.getIssueDate(), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                addCell(table, 10, 1, rpt.getUpdateTime(), DEFAULT_FONT_SIZE, 1, Element.ALIGN_LEFT);
                num++;
            }
        }
    }

}