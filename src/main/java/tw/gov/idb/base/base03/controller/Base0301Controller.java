package tw.gov.idb.base.base03.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tw.gov.idb.base.base03.cases.Base0301Case;
import tw.gov.idb.base.base03.service.Base0301Service;
import tw.gov.idb.base.enums.JobStatus;
import tw.gov.idb.base.framework.annotation.ApLog;
import tw.gov.idb.base.framework.controllers.BaseController;
import tw.gov.idb.base.util.BeanUtility;
import tw.gov.idb.base.util.DateUtility;
import tw.gov.idb.base.util.ExceptionUtility;

@Slf4j
@Controller
@SessionAttributes(Base0301Controller.CASE_KEY)
public class Base0301Controller extends BaseController {
	public static final String CASE_KEY = "base0301";
	public static final String QUERY_PAGE = "/base0301/base0301n";
	public static final String ADD_QUERY_PAGE = "/base0301/base0301c";
	private static final String DATE_PATTERN = "yyyyMMddHHmmss";
	
	@Autowired
	Base0301Service base0301Service;

	/**
	 * 進入頁面
	 *
	 * @return QUERY_PAGE
	 */
	@ApLog
	@RequestMapping("/base0301_enter.action")
	public String enter(@ModelAttribute(CASE_KEY) Base0301Case caseData) {
		try {
			resetCaseData(caseData);
			base0301Service.queryList(caseData);
		} catch (Exception e) {
			setSystemMessage("排程範例進入頁面失敗！");
			log.error("排程範例-/base0301_enter.action " + ExceptionUtility.getStackTrace(e));
		}
		return QUERY_PAGE;
	}

	// 查詢條件
	@ApLog
	@RequestMapping("/base0301_query.action")
	public String query(@ModelAttribute(CASE_KEY) Base0301Case caseData) {
		try {
			if (base0301Service.queryList(caseData)) {
				setSystemMessage("查詢成功！");
			} else {
				setSystemMessage("查無資料！");
				resetCaseData(caseData);//清空下面list
			}
		} catch (Exception e) {
			setSystemMessage("查詢失敗！");
			log.error("排程範例-/base0301_query.action " + ExceptionUtility.getStackTrace(e));
		}
		return QUERY_PAGE;
	}

	@ApLog
	@RequestMapping("/base0301_enterAdd.action")
	public String enterAdd(@ModelAttribute(CASE_KEY) Base0301Case caseData) {
		try {
			resetCaseData(caseData);
			base0301Service.enterAdd(caseData);
		} catch (Exception e) {
			log.error("排程範例-/base0301_enterAdd.action " + ExceptionUtility.getStackTrace(e));
		}
		return ADD_QUERY_PAGE;
	}

	@ApLog(fields = "addAndUpdCase")
	@RequestMapping("/base0301_add.action")
	public String add(@Validated @ModelAttribute(CASE_KEY) Base0301Case caseData, BindingResult result) {
		try {
	        if (StringUtils.isNoneBlank(caseData.getAddAndQueryCase().getScheduleTime())) {
	        	scheduleTimeValidated(caseData.getAddAndQueryCase().getScheduleTime(),result);
	        }
	        
			if (result.hasErrors()) {
				return ADD_QUERY_PAGE;
			}

			base0301Service.insertSchedule(caseData);
			setSystemMessage("新增成功！");
			//回去重查
			enter(caseData);
			return QUERY_PAGE;
		} catch (Exception e) {
			setSystemMessage("新增失敗！");
			log.error("排程範例-/base0301_add.action " + ExceptionUtility.getStackTrace(e));
		}
		return QUERY_PAGE;
	}

	@ApLog
	@RequestMapping("/base0301_enterQuery.action")
	public String enterQueryOne(@ModelAttribute(CASE_KEY) Base0301Case caseData) {
		try {
			base0301Service.enterQuery(caseData);
		} catch (Exception e) {
			log.error("排程範例-/base0301_enterQuery.action " + ExceptionUtility.getStackTrace(e));
		}
		return ADD_QUERY_PAGE;
	}
	
	 @ApLog
	    @RequestMapping("/base0301_delete.action")
	    public String delete(@ModelAttribute(CASE_KEY) Base0301Case caseData) {
	        try {
	        	base0301Service.deleteSchedule(caseData);
	            //回去前重查
	            String rtnPage = enter(caseData);
	            setSystemMessage("取消成功！");
	            return rtnPage;
	        } catch (Exception e) {
	            setSystemMessage("取消失敗！");
	            log.error("排程範例-/base0301_delete.action " + ExceptionUtility.getStackTrace(e));
		        return QUERY_PAGE;
	        }
	    }

	@ModelAttribute(CASE_KEY)
	public Base0301Case getCaseData() {
		Base0301Case caseData = new Base0301Case();
		return caseData;
	}

	private void resetCaseData(Base0301Case caseData) {
		BeanUtility.copyProperties(caseData, new Base0301Case());
		List<JobStatus> options = JobStatus.getList();
		caseData.setStatusOptions(options);
	}
	
	 /**
     * 驗證 scheduleTime 排定時間
     *
     * @param Base0301Case
     * @param result
     */
    private void scheduleTimeValidated(String scheduleTime, BindingResult result) {
        // 格式-YYYYMMDDHHmmSS
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        try {
            LocalDateTime.parse(scheduleTime, formatter);
        } catch (DateTimeParseException e) {
            result.rejectValue("addAndQueryCase.scheduleTime", StringUtils.EMPTY, "「排定時間」格式為 YYYYMMDDHHmmSS");
        }
        if (Long.valueOf(scheduleTime) <= Long.valueOf(DateUtility.getNowWestDateTime(true))) {
            result.rejectValue("addAndQueryCase.scheduleTime",StringUtils.EMPTY,"「排定時間」需大於系統時間！");
        }
    }

}
