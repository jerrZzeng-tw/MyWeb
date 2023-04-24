package tw.gov.idb.base.base02.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import tw.gov.idb.base.base02.cases.Base0201Case;
import tw.gov.idb.base.base02.report.Base0201ExcelReport;
import tw.gov.idb.base.base02.report.Base0201PdfReport;
import tw.gov.idb.base.base02.report.cases.Base0201RptCase;
import tw.gov.idb.base.base02.service.Base0201Service;
import tw.gov.idb.base.framework.annotation.ApLog;
import tw.gov.idb.base.framework.controllers.BaseController;
import tw.gov.idb.base.framework.support.FileOutputView;
import tw.gov.idb.base.util.BeanUtility;
import tw.gov.idb.base.util.ExceptionUtility;

import java.io.ByteArrayOutputStream;

@Slf4j
@Controller
@SessionAttributes(Base0201Controller.CASE_KEY)
public class Base0201Controller extends BaseController {
    public static final String CASE_KEY = "base0201";
    public static final String QUERY_PAGE = "/base0201/base0201n";

    @Autowired
    private Base0201Service base0201Service;

    /**
     * 進入頁面
     *
     * @return QUERY_PAGE
     */
    @ApLog
    @RequestMapping("/base0201_enter.action")
    public String enter(@ModelAttribute(CASE_KEY) Base0201Case caseData) {
        resetCaseData(caseData);
        return QUERY_PAGE;
    }

    /**
     * 查詢列印報表 PDF
     */
    @RequestMapping("/base0201_printPDF.action")
    public ModelAndView printPDF(@Validated @ModelAttribute(CASE_KEY) Base0201Case caseData, BindingResult result) {
        try {

            if (result.hasErrors()) {
                return new ModelAndView(QUERY_PAGE);
            }

            // 取得報表資料
            Base0201RptCase base0201RptCase = base0201Service.queryCompanyList(caseData);

            if (CollectionUtils.isEmpty(base0201RptCase.getRptCases())) {
                setSystemMessage("無符合資料！");
                return new ModelAndView(QUERY_PAGE);
            }

            //產製報表
            Base0201PdfReport report = new Base0201PdfReport();
            report.printPage(base0201RptCase);

            ByteArrayOutputStream baos = report.getOutputStream();
            return new ModelAndView(
                    new FileOutputView(baos, Base0201PdfReport.REPORT_NAME + ".pdf", FileOutputView.PDF_FILE));


        } catch (Exception e) {
            log.error("產製報表-base0201_printPDF.action :" + ExceptionUtility.getStackTrace(e));
            setSystemMessage("查詢失敗");
            return new ModelAndView(QUERY_PAGE);
        }
    }

    /**
     * 查詢列印報表 XLSX
     */
    @RequestMapping("/base0201_printXLSX.action")
    public ModelAndView printXLSX(@Validated @ModelAttribute(CASE_KEY) Base0201Case caseData, BindingResult result) {
        try {

            if (result.hasErrors()) {
                return new ModelAndView(QUERY_PAGE);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // 取得報表資料
            Base0201RptCase base0201RptCase = base0201Service.queryCompanyList(caseData);

            if (CollectionUtils.isEmpty(base0201RptCase.getRptCases())) {
                setSystemMessage("無符合資料！");
                return new ModelAndView(QUERY_PAGE);
            }
            //產製報表
            Base0201ExcelReport report = new Base0201ExcelReport(false);
            baos = report.doReport(base0201RptCase);
            return new ModelAndView(
                    new FileOutputView(baos, Base0201ExcelReport.REPORT_NAME + ".xlsx", FileOutputView.EXCEL_FILE));

        } catch (Exception e) {
            log.error("產製報表-base0201_printXLSX.action:" + ExceptionUtility.getStackTrace(e));
            setSystemMessage("查詢失敗");
            return new ModelAndView(QUERY_PAGE);
        }
    }

    @ModelAttribute(CASE_KEY)
    public Base0201Case getCaseData() {
        Base0201Case caseData = new Base0201Case();
        return caseData;
    }

    private void resetCaseData(Base0201Case caseData) {
        BeanUtility.copyProperties(caseData, new Base0201Case());
    }

}
