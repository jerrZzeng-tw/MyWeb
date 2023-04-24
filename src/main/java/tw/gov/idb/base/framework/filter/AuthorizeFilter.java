package tw.gov.idb.base.framework.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import tw.gov.idb.base.framework.config.BaseProperties;
import tw.gov.idb.base.framework.domain.FrameworkUserInfoBean;
import tw.gov.idb.base.framework.helper.SpringHelper;
import tw.gov.idb.base.framework.helper.UserSessionHelper;

import java.io.IOException;
import java.util.List;

@Slf4j
public class AuthorizeFilter implements Filter {


    private List<String> loginUrls; // 系統登入頁面或用來處理登入資訊的 Action URL

    private String unauthorizedUrl; // 「沒有權限之訊息頁面」的位址

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        BaseProperties baseProperties = (BaseProperties) SpringHelper.getBeanById("baseProperties");
        loginUrls = baseProperties.getLoginUrl();
        unauthorizedUrl = baseProperties.getUnauthorizedUrl();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //        HttpSession session = req.getSession();
        //        log.info("AuthorizeFilter session id:" + session.getId());
        String path = req.getServletPath();
        if (isLoginUrl(path)) { // begin ... [
            // 如果是登入相關頁面或 Action 則不進行檢核
            chain.doFilter(request, response);
        } else {
            // 不是「沒有權限之訊息頁面」再做檢核
            if (StringUtils.indexOf(req.getRequestURI(), unauthorizedUrl) == -1) {
                FrameworkUserInfoBean userData = UserSessionHelper.getFrameworkUserData(req);
                // 檢查使用者是否有登入
                if (userData != null) {
                    // 檢查使用者是否有該URL權限
                    if (userData.hasPrivilege(path)) {
                        chain.doFilter(request, response);
                    } else {
                        // 使用者沒有執行此URL的權限
                        res.sendRedirect(req.getContextPath() + unauthorizedUrl);
                    }
                } else {
                    // 使用者未登入
                    res.sendRedirect(req.getContextPath() + unauthorizedUrl);
                }
            } else {
                // 代表此頁為「沒有權限之訊息頁面」
                chain.doFilter(request, response);
            }
        }

    }

    /**
     * 檢核使用者執行的功能的 ServletPath 是否為登入相關頁面或 Action
     *
     * @param path 使用者執行的功能的 ServletPath
     * @return 若為登入相關頁面或 Action 回傳 <code>true</code>, 否則回傳 <code>false</code>
     */
    public boolean isLoginUrl(String path) {
        return loginUrls != null && loginUrls.contains(path);
    }
}
