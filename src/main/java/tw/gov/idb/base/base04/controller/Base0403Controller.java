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
import tw.gov.idb.base.base04.cases.Base0403Case;
import tw.gov.idb.base.base04.service.Base0403Service;
import tw.gov.idb.base.framework.annotation.ApLog;
import tw.gov.idb.base.framework.controllers.BaseController;
import tw.gov.idb.base.util.BeanUtility;
import tw.gov.idb.base.util.ExceptionUtility;

@Slf4j
@Controller
@SessionAttributes(Base0403Controller.CASE_KEY)
public class Base0403Controller extends BaseController {
    public static final String CASE_KEY = "base0403";
    public static final String QUERY_PAGE = "/base0403/base0403n";
    public static final String ADD_UPD_PAGE = "/base0403/base0403c";
    public static final String AUTH_PAGE = "/base0403/base0403a";

    @Autowired
    Base0403Service base0403Service;

    /**
     * 進入頁面
     *
     * @return QUERY_PAGE
     */
    @ApLog
    @RequestMapping("/base0403_enter.action")
    public String enter(@ModelAttribute(CASE_KEY) Base0403Case caseData) {
        try {
            resetCaseData(caseData);
            base0403Service.selectRoleList(caseData);
        } catch (Exception e) {
            setSystemMessage("角色管理查詢失敗！");
            log.error("角色管理-/base0403_enter.action " + ExceptionUtility.getStackTrace(e));
        }
        return QUERY_PAGE;
    }

    @ApLog
    @RequestMapping("/base0403_enterAdd.action")
    public String enterAdd(@ModelAttribute(CASE_KEY) Base0403Case caseData) {
        base0403Service.enterAdd(caseData);
        return ADD_UPD_PAGE;
    }

    @ApLog
    @RequestMapping("/base0403_add.action")
    public String add(@Validated @ModelAttribute(CASE_KEY) Base0403Case caseData, BindingResult result) {
        try {

            if (base0403Service.isDuplicate(caseData)) {
                result.rejectValue("roleId", StringUtils.EMPTY, "角色ID 不可重複");
            }

            if (result.hasErrors()) {
                return ADD_UPD_PAGE;
            }

            base0403Service.insertRole(caseData);
            setSystemMessage("新增成功！");

            //回去前重查
            enter(caseData);
        } catch (Exception e) {
            setSystemMessage("角色管理新增失敗！");
            log.error("角色管理-/base0403_add.action " + ExceptionUtility.getStackTrace(e));
        }
        return QUERY_PAGE;
    }

    @ApLog
    @RequestMapping("/base0403_enterUpd.action")
    public String enterUpd(@ModelAttribute(CASE_KEY) Base0403Case caseData) {
        try {
            base0403Service.enterUpd(caseData);
        } catch (Exception e) {
            log.error("角色管理-/base0403_enterUpd.action " + ExceptionUtility.getStackTrace(e));
        }
        return ADD_UPD_PAGE;
    }

    @ApLog
    @RequestMapping("/base0403_upd.action")
    public String upd(@Validated @ModelAttribute(CASE_KEY) Base0403Case caseData, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return ADD_UPD_PAGE;
            }
            base0403Service.updateRole(caseData);
            setSystemMessage("修改成功！");
            //回去前重查
            enter(caseData);
        } catch (Exception e) {
            setSystemMessage("角色管理修改失敗！");
            log.error("角色管理-/base0403_upd.action " + ExceptionUtility.getStackTrace(e));
        }
        return QUERY_PAGE;
    }

    @ApLog
    @RequestMapping("/base0403_delete.action")
    public String delete(@ModelAttribute(CASE_KEY) Base0403Case caseData) {
        try {
            base0403Service.deleteRole(caseData);
            setSystemMessage("刪除成功！");
            //回去前重查
            enter(caseData);
        } catch (Exception e) {
            setSystemMessage("角色管理刪除失敗！");
            log.error("角色管理-/base0403_delete.action " + ExceptionUtility.getStackTrace(e));
        }
        return QUERY_PAGE;
    }

    @ApLog
    @RequestMapping("/base0403_enterAuth.action")
    public String enterAuth(@ModelAttribute(CASE_KEY) Base0403Case caseData) {
        try {
            base0403Service.selectItemList(caseData);
        } catch (Exception e) {
            setSystemMessage("權限管理查詢失敗！");
            log.error("角色管理-/base0403_enterAuth.action " + ExceptionUtility.getStackTrace(e));
        }
        return AUTH_PAGE;
    }

    @ApLog
    @RequestMapping("/base0403_grantAuth.action")
    public String grantAuth(@ModelAttribute(CASE_KEY) Base0403Case caseData) {
        try {
            base0403Service.insertAuth(caseData);
            //回本頁重查
            base0403Service.selectItemList(caseData);
        } catch (Exception e) {
            setSystemMessage("權限管理新增失敗！");
            log.error("角色管理-/base0403_grantAuth.action " + ExceptionUtility.getStackTrace(e));
        }
        return AUTH_PAGE;
    }

    @ApLog
    @RequestMapping("/base0403_deleteAuth.action")
    public String deleteAuth(@ModelAttribute(CASE_KEY) Base0403Case caseData) {
        try {
            base0403Service.deleteAuth(caseData);
            //回本頁重查
            base0403Service.selectItemList(caseData);
        } catch (Exception e) {
            setSystemMessage("權限管理刪除失敗！");
            log.error("角色管理-/base0403_deleteAuth.action " + ExceptionUtility.getStackTrace(e));
        }
        return AUTH_PAGE;
    }

    @ModelAttribute(CASE_KEY)
    public Base0403Case getCaseData() {
        Base0403Case caseData = new Base0403Case();
        return caseData;
    }

    private void resetCaseData(Base0403Case caseData) {
        BeanUtility.copyProperties(caseData, new Base0403Case());
    }

}
