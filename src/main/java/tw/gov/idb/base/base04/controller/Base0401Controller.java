package tw.gov.idb.base.base04.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tw.gov.idb.base.base04.cases.Base0401Case;
import tw.gov.idb.base.base04.cases.Base0401Case.addCase;
import tw.gov.idb.base.base04.cases.Base0401Case.updCase;
import tw.gov.idb.base.base04.service.Base0401Service;
import tw.gov.idb.base.framework.annotation.ApLog;
import tw.gov.idb.base.framework.controllers.BaseController;
import tw.gov.idb.base.util.BeanUtility;
import tw.gov.idb.base.util.ExceptionUtility;

@Slf4j
@Controller
@SessionAttributes(Base0401Controller.CASE_KEY)
public class Base0401Controller extends BaseController {
    public static final String CASE_KEY = "base0401";
    public static final String QUERY_PAGE = "/base0401/base0401n";
    public static final String ADD_UPD_PAGE = "/base0401/base0401c";

    @Autowired
    Base0401Service base0401Service;

    /**
     * 進入頁面
     *
     * @return QUERY_PAGE
     */
    @ApLog
    @RequestMapping("/base0401_enter.action")
    public String enter(@ModelAttribute(CASE_KEY) Base0401Case caseData) {
        try {
            resetCaseData(caseData);
            base0401Service.queryUserList(caseData);
        } catch (Exception e) {
            setSystemMessage("帳號管理查詢失敗！");
            log.error("帳號管理-/base0401_enter.action " + ExceptionUtility.getStackTrace(e));
        }
        return QUERY_PAGE;
    }

    //查詢條件
    @ApLog
    @RequestMapping("/base0401_query.action")
    public String query(@ModelAttribute(CASE_KEY) Base0401Case caseData) {
        try {
            base0401Service.queryUserList(caseData);
            setSystemMessage("查詢成功！");

        } catch (Exception e) {
            setSystemMessage("查詢失敗！");
            log.error("帳號管理-/base0401_query.action " + ExceptionUtility.getStackTrace(e));
        }
        return QUERY_PAGE;
    }

    @ApLog
    @RequestMapping("/base0401_enterAdd.action")
    public String enterAdd(@ModelAttribute(CASE_KEY) Base0401Case caseData) {
        try {
            resetCaseData(caseData);
            base0401Service.enterAdd(caseData);
        } catch (Exception e) {
            log.error("帳號管理-/base0401_enterAdd.action " + ExceptionUtility.getStackTrace(e));
        }
        return ADD_UPD_PAGE;
    }

    @ApLog(fields = "addAndUpdCase")
    @RequestMapping("/base0401_add.action")
    public String add(@Validated(addCase.class) @ModelAttribute(CASE_KEY) Base0401Case caseData, BindingResult result) {
        try {

            if (base0401Service.isDuplicate(caseData)) {
                result.rejectValue("addAndUpdCase.id", StringUtils.EMPTY, "使用者ID 不可重複");
            }

            if (result.hasErrors()) {
                return ADD_UPD_PAGE;
            }

            base0401Service.insertUser(caseData);
            setSystemMessage("新增成功！");
            //回去前重查
            enter(caseData);
            return QUERY_PAGE;
        } catch (Exception e) {
            setSystemMessage("新增失敗！");
            log.error("帳號管理-/base0401_add.action " + ExceptionUtility.getStackTrace(e));
        }
        return ADD_UPD_PAGE;
    }

    @ApLog
    @RequestMapping("/base0401_enterUpd.action")
    public String enterUpd(@ModelAttribute(CASE_KEY) Base0401Case caseData) {
        try {
            base0401Service.enterUpd(caseData);
        } catch (Exception e) {
            log.error("帳號管理-/base0401_enterUpd.action " + ExceptionUtility.getStackTrace(e));
        }
        return ADD_UPD_PAGE;
    }

    @ApLog(fields = "addAndUpdCase")
    @RequestMapping("/base0401_upd.action")
    public String upd(@Validated(updCase.class) @ModelAttribute(CASE_KEY) Base0401Case caseData, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return ADD_UPD_PAGE;
            }
            base0401Service.updateUser(caseData);
            setSystemMessage("修改成功！");
            //回去前重查
            enter(caseData);
            return QUERY_PAGE;
        } catch (Exception e) {
            setSystemMessage("修改失敗！");
            log.error("帳號管理-/base0401_upd.action " + ExceptionUtility.getStackTrace(e));
        }
        return ADD_UPD_PAGE;
    }

    @ApLog
    @RequestMapping("/base0401_delete.action")
    public String delete(@ModelAttribute(CASE_KEY) Base0401Case caseData) {
        try {
            base0401Service.deleteUser(caseData);
            setSystemMessage("刪除成功！");
            //回去前重查
            enter(caseData);
        } catch (Exception e) {
            setSystemMessage("刪除失敗！");
            log.error("帳號管理-/base0401_delete.action " + ExceptionUtility.getStackTrace(e));
        }
        return QUERY_PAGE;
    }

    @ModelAttribute(CASE_KEY)
    public Base0401Case getCaseData() {
        Base0401Case caseData = new Base0401Case();
        return caseData;
    }

    private void resetCaseData(Base0401Case caseData) {
        BeanUtility.copyProperties(caseData, new Base0401Case());
    }
}
