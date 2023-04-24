package tw.gov.idb.base.base01.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import tw.gov.idb.base.base01.cases.Base0101Case;
import tw.gov.idb.base.base01.service.Base0101Service;
import tw.gov.idb.base.enums.TestEnum;
import tw.gov.idb.base.framework.annotation.ApLog;
import tw.gov.idb.base.framework.controllers.BaseController;
import tw.gov.idb.base.framework.support.FileOutputView;
import tw.gov.idb.base.util.BeanUtility;
import tw.gov.idb.base.util.ExceptionUtility;

@Slf4j
@Controller
@SessionAttributes(Base0101Controller.CASE_KEY)
public class Base0101Controller extends BaseController {
	public static final String CASE_KEY = "base0101";
	public static final String QUERY_PAGE = "/base0101/base0101n";
	public static final String LIST_PAGE = "/base0101/base0101q";

	@Autowired
	private Base0101Service base0101Service;

	/**
	 * 進入頁面
	 *
	 * @return QUERY_PAGE
	 */
	@ApLog
	@RequestMapping("/base0101_enter.action")
	public String enter(@ModelAttribute(CASE_KEY) Base0101Case caseData) {
		resetCaseData(caseData);
		return QUERY_PAGE;
	}

	@ApLog
	@RequestMapping("/base0101_query.action")
	public String query(@ModelAttribute(CASE_KEY) Base0101Case caseData) {
		try {
			base0101Service.query(caseData);
		} catch (Exception e) {
			setSystemMessage("執行失敗！");
			log.error("基本範例-/base0101_query.action " + ExceptionUtility.getStackTrace(e));
		}
		return LIST_PAGE;
	}

	@ApLog
	@RequestMapping("/base0101_download.action")
	public ModelAndView download(@ModelAttribute(CASE_KEY) Base0101Case caseData) {
		try {

			byte[] file = base0101Service.downloadFile(caseData);

			ByteArrayOutputStream baoOutput = new ByteArrayOutputStream();

			if (file != null && file.length > 0) {
				baoOutput.write(file);
				return new ModelAndView(
						new FileOutputView(baoOutput, caseData.getFileName(), FileOutputView.GENERAL_FILE));
			} else {
				setSystemMessage("檔案不存在或已遺失！");
				return new ModelAndView(LIST_PAGE);
			}
		} catch (Exception e) {
			setSystemMessage("下載失敗！");
			log.error("基本範例-/base0101_download.action " + ExceptionUtility.getStackTrace(e));
		}
		return new ModelAndView(LIST_PAGE);
	}

	@Override
	@ModelAttribute(CASE_KEY)
	public Base0101Case getCaseData() {
		return new Base0101Case();
	}

	private void resetCaseData(Base0101Case caseData) {
		BeanUtility.copyProperties(caseData, new Base0101Case());
		List<TestEnum> options = TestEnum.getList();
		caseData.setTestOptions(options);
	}

}
