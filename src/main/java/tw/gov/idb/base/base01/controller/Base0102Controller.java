package tw.gov.idb.base.base01.controller;


import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tw.gov.idb.base.base01.cases.Base0102Case;
import tw.gov.idb.base.base01.service.Base0102Service;
import tw.gov.idb.base.framework.annotation.ApLog;
import tw.gov.idb.base.framework.controllers.BaseController;
import tw.gov.idb.base.util.BeanUtility;
import tw.gov.idb.base.util.ExceptionUtility;

@Slf4j
@Controller
@SessionAttributes(Base0102Controller.CASE_KEY)
public class Base0102Controller extends BaseController {
    public static final String CASE_KEY = "base0102";
    public static final String QUERY_PAGE = "/base0102/base0102n";
    public static final String LIST_PAGE = "/base0102/base0102q";
    public static final String ADD_UPD_PAGE = "/base0102/base0102c";
    final Pattern REGEXP_UNIFORMNUMBERS = Pattern.compile("[0-9]{8}$");//統一編號格式

    @Autowired
    private Base0102Service base0102Service;

    /**
     * 進入頁面
     *
     * @return QUERY_PAGE
     */
    @ApLog
    @RequestMapping("/base0102_enter.action")
    public String enter(@ModelAttribute(CASE_KEY) Base0102Case caseData) {
        resetCaseData(caseData);
        return QUERY_PAGE;
    }

    @ApLog
    @RequestMapping("/base0102_query.action")
    public String query(@Validated(Base0102Case.queryValid.class) @ModelAttribute(CASE_KEY) Base0102Case caseData,BindingResult result) {
        try {
        	 if (result.hasErrors()) {
                 return QUERY_PAGE;
             }

            if (!base0102Service.queryCompanyList(caseData)) {
                setSystemMessage("查無資料！");
                return QUERY_PAGE;
            }
        } catch (Exception e) {
            setSystemMessage("查詢失敗！");
            log.error("完整範例-/base0102_query.action " + ExceptionUtility.getStackTrace(e));
        }
        return LIST_PAGE;
    }

    @ApLog
    @RequestMapping("/base0102_enterAdd.action")
    public String enterAdd(@ModelAttribute(CASE_KEY) Base0102Case caseData) {
        try {
            resetCaseData(caseData);
            base0102Service.enterAdd(caseData);
        } catch (Exception e) {
            log.error("完整範例-/base0102_enterAdd.action " + ExceptionUtility.getStackTrace(e));
        }
        return ADD_UPD_PAGE;
    }

    @ApLog(fields = "addAndUpdCase")
    @RequestMapping("/base0102_add.action")
    public String add(@Validated(Base0102Case.addAndUpdValid.class) @ModelAttribute(CASE_KEY) Base0102Case caseData, BindingResult result) {
        try {
            if (StringUtils.isNoneBlank(caseData.getAddAndUpdCase().getUniformNumbers())) {
                valid_uniformNumbers(caseData, result);
            }

            if (result.hasErrors()) {
                return ADD_UPD_PAGE;
            }

            base0102Service.insertCompany(caseData);
            setSystemMessage("新增成功！");
            //回去前重查
            enter(caseData);
            return QUERY_PAGE;
        } catch (Exception e) {
            setSystemMessage("新增失敗！");
            log.error("完整範例-/base0102_add.action " + ExceptionUtility.getStackTrace(e));
        }
        return ADD_UPD_PAGE;
    }

    @ApLog
    @RequestMapping("/base0102_enterUpd.action")
    public String enterUpd(@ModelAttribute(CASE_KEY) Base0102Case caseData) {
        try {
            base0102Service.enterUpd(caseData);
        } catch (Exception e) {
            log.error("完整範例-/base0102_enterUpd.action " + ExceptionUtility.getStackTrace(e));
        }
        return ADD_UPD_PAGE;
    }

    @ApLog(fields = "addAndUpdCase")
    @RequestMapping("/base0102_upd.action")
    public String upd(@Validated(Base0102Case.addAndUpdValid.class) @ModelAttribute(CASE_KEY) Base0102Case caseData, BindingResult result) {
        try {
            if (StringUtils.isNoneBlank(caseData.getAddAndUpdCase().getUniformNumbers())) {
                valid_uniformNumbers(caseData, result);
            }

            if (result.hasErrors()) {
                return ADD_UPD_PAGE;
            }

            base0102Service.updateCompany(caseData);
            setSystemMessage("修改成功！");
            //回去前重查
            query(caseData,result);
            return LIST_PAGE;
        } catch (Exception e) {
            setSystemMessage("修改失敗！");
            log.error("完整範例-/base0102_upd.action " + ExceptionUtility.getStackTrace(e));
        }
        return ADD_UPD_PAGE;
    }

    @ApLog(fields = "deleteData")
    @RequestMapping("/base0102_delete.action")
    public String delete(@ModelAttribute(CASE_KEY) Base0102Case caseData) {
        try {
            base0102Service.deleteCompany(caseData);
            //回去前重查
            if (!base0102Service.queryCompanyList(caseData)) {
                setSystemMessage("刪除成功！");
                return QUERY_PAGE;
            }
            setSystemMessage("刪除成功！");
            return LIST_PAGE;
        } catch (Exception e) {
            setSystemMessage("刪除失敗！");
            log.error("完整範例-/base0102_delete.action " + ExceptionUtility.getStackTrace(e));
        }
        return QUERY_PAGE;
    }


    @Override
    @ModelAttribute(CASE_KEY)
    public Base0102Case getCaseData() {
        return new Base0102Case();
    }

    private void resetCaseData(Base0102Case caseData) {
        BeanUtility.copyProperties(caseData, new Base0102Case());
    }

    /**
     * 驗證 統一編號
     *
     * @param Base0102Case
     * @param result
     */
    private void valid_uniformNumbers(Base0102Case caseData, BindingResult result) {
        //統一編號(格式為字串,內容須為8碼數字)
        if (!(REGEXP_UNIFORMNUMBERS.matcher(caseData.getAddAndUpdCase().getUniformNumbers()).matches())) {
            result.rejectValue("addAndUpdCase.uniformNumbers", StringUtils.EMPTY,
                    "統一編號(格式為字串,內容須為8碼數字)");
        }
    }

}
