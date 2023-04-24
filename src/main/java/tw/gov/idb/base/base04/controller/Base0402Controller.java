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
import tw.gov.idb.base.base04.cases.Base0402Case;
import tw.gov.idb.base.base04.service.Base0402Service;
import tw.gov.idb.base.framework.annotation.ApLog;
import tw.gov.idb.base.framework.controllers.BaseController;
import tw.gov.idb.base.framework.enums.ItemTypeEnum;
import tw.gov.idb.base.util.BeanUtility;
import tw.gov.idb.base.util.ExceptionUtility;

import java.util.regex.Pattern;

@Slf4j
@Controller
@SessionAttributes(Base0402Controller.CASE_KEY)
public class Base0402Controller extends BaseController {
    public static final String CASE_KEY = "base0402";
    public static final String QUERY_PAGE = "/base0402/base0402n";
    public static final String LIST_PAGE = "/base0402/base0402q";
    public static final String ADD_UPD_PAGE = "/base0402/base0402c";
    final Pattern REGEXP_URL = Pattern.compile("(.*)_enter.action");

    @Autowired
    Base0402Service base0402Service;

    /**
     * 進入頁面
     *
     * @return QUERY_PAGE
     */
    @ApLog
    @RequestMapping("/base0402_enter.action")
    public String enter(@ModelAttribute(CASE_KEY) Base0402Case caseData) {
        resetCaseData(caseData);
        base0402Service.initSelectOptions(caseData);
        return QUERY_PAGE;
    }

    @ApLog
    @RequestMapping("/base0402_query.action")
    public String query(@ModelAttribute(CASE_KEY) Base0402Case caseData) {
        try {
            if (!base0402Service.queryItemList(caseData)) {
                setSystemMessage("查無資料！");
                return QUERY_PAGE;
            }
        } catch (Exception e) {
            setSystemMessage("查詢失敗！");
            log.error("功能項目管理-/base0402_query.action " + ExceptionUtility.getStackTrace(e));
        }
        return LIST_PAGE;
    }

    @ApLog
    @RequestMapping("/base0402_enterAdd.action")
    public String enterAdd(@ModelAttribute(CASE_KEY) Base0402Case caseData) {
        try {
            resetCaseData(caseData);
            base0402Service.enterAdd(caseData);
        } catch (Exception e) {
            log.error("功能項目管理-/base0402_enterAdd.action " + ExceptionUtility.getStackTrace(e));
        }
        return ADD_UPD_PAGE;
    }

    @ApLog(fields = "addAndUpdCase")
    @RequestMapping("/base0402_add.action")
    public String add(@Validated @ModelAttribute(CASE_KEY) Base0402Case caseData, BindingResult result) {
        try {
            //			   1. 功能項ID 必須為父節點+2位數字
            //             2. 功能項ID 不可重複
            //			   3. 種類為「功能列」需有值  種類為「標題列」URL需為空
            final Pattern REGEXP_ITEMID = Pattern.compile(caseData.getAddAndUpdCase().getItemIdParent() + "[0-9]{2}");

            if (!REGEXP_ITEMID.matcher(caseData.getAddAndUpdCase().getItemId()).matches()) {
                result.rejectValue("addAndUpdCase.itemId", StringUtils.EMPTY, "功能項ID 必須為父節點+2位數字");
            }

            //URL:  必須為_enter.action 結尾
            if (!REGEXP_URL.matcher(caseData.getAddAndUpdCase().getUrl()).matches()) {
                result.rejectValue("addAndUpdCase.url", StringUtils.EMPTY, "功能項URL 必須為_enter.action結尾");
            }

            if (base0402Service.isDuplicate(caseData)) {
                result.rejectValue("addAndUpdCase.itemId", StringUtils.EMPTY, "功能項ID 不可重複");
            }

            urlValidated(caseData, result);

            if (result.hasErrors()) {
                return ADD_UPD_PAGE;
            }

            base0402Service.insertItem(caseData);
            setSystemMessage("新增成功！");
            //回去前重查
            enter(caseData);
            return QUERY_PAGE;
        } catch (Exception e) {
            setSystemMessage("新增失敗！");
            log.error("功能項目管理-/base0402_add.action " + ExceptionUtility.getStackTrace(e));
        }
        return ADD_UPD_PAGE;
    }

    @ApLog
    @RequestMapping("/base0402_enterUpd.action")
    public String enterUpd(@ModelAttribute(CASE_KEY) Base0402Case caseData) {
        try {
            base0402Service.enterUpd(caseData);
        } catch (Exception e) {
            log.error("功能項目管理-/base0402_enterUpd.action " + ExceptionUtility.getStackTrace(e));
        }
        return ADD_UPD_PAGE;
    }

    @ApLog(fields = "addAndUpdCase")
    @RequestMapping("/base0402_upd.action")
    public String upd(@Validated @ModelAttribute(CASE_KEY) Base0402Case caseData, BindingResult result) {
        try {
            urlValidated(caseData, result);

            if (result.hasErrors()) {
                return ADD_UPD_PAGE;
            }
            base0402Service.updateItem(caseData);
            setSystemMessage("修改成功！");
            //回去前重查
            query(caseData);
            return LIST_PAGE;
        } catch (Exception e) {
            setSystemMessage("修改失敗！");
            log.error("功能項目管理-/base0402_upd.action " + ExceptionUtility.getStackTrace(e));
        }
        return ADD_UPD_PAGE;
    }

    @ApLog
    @RequestMapping("/base0402_delete.action")
    public String delete(@ModelAttribute(CASE_KEY) Base0402Case caseData) {
        try {
            Boolean status = base0402Service.deleteItem(caseData);
            if (status) {
                setSystemMessage("刪除成功！");
                //回去前重查
                enter(caseData);
                return QUERY_PAGE;
            } else {
                setSystemMessage("尚存在子節點 無法刪除！");
                return LIST_PAGE;
            }

        } catch (Exception e) {
            setSystemMessage("刪除失敗！");
            log.error("功能項目管理-/base0402_delete.action " + ExceptionUtility.getStackTrace(e));
        }
        return QUERY_PAGE;
    }

    @ModelAttribute(CASE_KEY)
    public Base0402Case getCaseData() {
        Base0402Case caseData = new Base0402Case();
        return caseData;
    }

    private void resetCaseData(Base0402Case caseData) {
        BeanUtility.copyProperties(caseData, new Base0402Case());
    }

    /**
     * 驗證Type
     *
     * @param Base0402Case
     * @param result
     */
    private void urlValidated(Base0402Case caseData, BindingResult result) {
        // 種類為「功能列」需有值 種類為「標題列」URL需為空
        if (StringUtils.equals(ItemTypeEnum.FUN.getCode(), caseData.getAddAndUpdCase().getType())) {
            if (StringUtils.isBlank(caseData.getAddAndUpdCase().getUrl())) {
                result.rejectValue("addAndUpdCase.url", StringUtils.EMPTY, "種類為「功能列」 URL需有值");
            } else {
                //URL:  必須為_enter.action 結尾
                if (!REGEXP_URL.matcher(caseData.getAddAndUpdCase().getUrl()).matches()) {
                    result.rejectValue("addAndUpdCase.url", StringUtils.EMPTY, "功能項URL 必須為_enter.action結尾");
                }
            }
        }

        if (StringUtils.equals(ItemTypeEnum.TITLE.getCode(), caseData.getAddAndUpdCase().getType())) {
            if (StringUtils.isNotBlank(caseData.getAddAndUpdCase().getUrl())) {
                result.rejectValue("addAndUpdCase.url", StringUtils.EMPTY, "種類為「標題列」 URL需為空");
            }
        }
    }

}
