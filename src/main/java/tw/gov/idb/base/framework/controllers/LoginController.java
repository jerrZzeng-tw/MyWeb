package tw.gov.idb.base.framework.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tw.gov.idb.base.framework.annotation.ApLog;
import tw.gov.idb.base.framework.cases.BaseCase;
import tw.gov.idb.base.framework.domain.UserBean;
import tw.gov.idb.base.framework.helper.UserSessionHelper;
import tw.gov.idb.base.framework.services.LoginService;
import tw.gov.idb.base.util.ExceptionUtility;

/**
 * 使用者登入
 *
 *  
 */
@Slf4j
@Controller
public class LoginController extends BaseController {
    private static final String INDEX_PAGE = "/index";
    private static final String LOGIN_PAGE = "/login";
    private static final String LOGOUT_PAGE = "/logout";

    private static final String ERROR_PAGE = "/error";

    private static final String UNAUTHORIZED_PAGE = "/unauthorized";
    private final LoginService loginService;

    private final UserBean userData;

    public LoginController(LoginService loginService, UserBean userData) {
        this.loginService = loginService;
        this.userData = userData;
    }


    @RequestMapping("/")
    public String index() {
        return LOGIN_PAGE;
    }


    @RequestMapping("/LoginForward.action")
    public String loginForward() {
        return INDEX_PAGE;
    }

    @ApLog
    @RequestMapping("/Login.action")
    public String Login(HttpServletRequest request) {
        //todo 未來須修改為各系統之登入邏輯
        try {
            if (loginService.login(userData, request)) {
                // 將使用者資料存入 Session
                UserSessionHelper.setUserData(request, userData);
                return INDEX_PAGE;
            }
            setSystemMessage("帳號或密碼錯誤!", true);
            return LOGIN_PAGE;
        } catch (Exception e) {
            log.error("使用者登入失敗 - {}", ExceptionUtility.getStackTrace(e));
            return ERROR_PAGE;
        }
    }

    @ApLog
    @RequestMapping("/Logout.action")
    public String logout(HttpServletRequest request) {
        try {
            UserSessionHelper.logoutUser(request);

            return LOGOUT_PAGE;
        } catch (Exception e) {
            log.error("使用者登出失敗 - {}", ExceptionUtility.getStackTrace(e));
            return LOGOUT_PAGE;
        }
    }

    @RequestMapping("/Unauthorized.action")
    public String unauthorized(HttpServletRequest request) {
        return UNAUTHORIZED_PAGE;
    }

    @Override
    public BaseCase getCaseData() {
        return new BaseCase();
    }
}
