package tw.gov.idb.base.framework.helper;

import tw.gov.idb.base.framework.domain.FrameworkUserInfoBean;
import tw.gov.idb.base.framework.domain.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 用來將使用者物件存放到 Session 及自 Session 中取出之 Helper Class,<br>
 * 使用者物件需實做 <code>tw.gov.bli.common.domain.UserInfo</code> Interface
 *
 *  
 */
@Slf4j
public class UserSessionHelper {

    public static final String FRAMEWORK_USER_INFO = "__scope_frameworkUserInfo";
    public static final String USER_INFO = "__scope_userInfo";

    /**
     * 將 Framework 使用者資料存到 Session 中 (應用系統請勿呼叫)
     *
     * @param request  <code>HttpServletRequest</code>
     * @param userData <code>tw.gov.bli.common.domain.FrameworkUserInfoBean</code>
     */
    public static void setFrameworkUserData(HttpServletRequest request, FrameworkUserInfoBean userData) {
        HttpSession session = request.getSession();
        session.setAttribute(FRAMEWORK_USER_INFO, userData);
    }

    /**
     * 從 Session 中取得 Framework 使用者資料 (應用系統請勿呼叫)
     *
     * @param request <code>HttpServletRequest</code>
     * @return Framework 用的使用者物件
     */
    public static FrameworkUserInfoBean getFrameworkUserData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (FrameworkUserInfoBean) session.getAttribute(FRAMEWORK_USER_INFO);
    }

    /**
     * 將應用系統使用者資料存到 Session 中
     *
     * @param request  <code>HttpServletRequest</code>
     * @param userData 應用系統實作 <code>tw.gov.bli.common.domain.UserInfo</code> 的使用者物件
     */
    public static void setUserData(HttpServletRequest request, UserInfo userData) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_INFO, (Serializable) userData); // Fortify
    }

    /**
     * 從 Session 中取得應用系統使用者資料
     *
     * @param request <code>HttpServletRequest</code>
     * @return 應用系統用的使用者物件
     */
    public static UserInfo getUserData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (UserInfo) session.getAttribute(USER_INFO);
    }

    /**
     * 使用者登出，移除使用者資料並將 Session 銷毀
     *
     * @param request <code>HttpServletRequest</code>
     */
    public static void logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //        log.info("logout session id:" + session.getId());
        if (session != null) {
            session.removeAttribute(FRAMEWORK_USER_INFO);
            session.removeAttribute(USER_INFO);
            session.invalidate();
        }
    }

}
