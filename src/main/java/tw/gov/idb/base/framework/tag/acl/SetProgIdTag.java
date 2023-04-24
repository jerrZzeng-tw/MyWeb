package tw.gov.idb.base.framework.tag.acl;

import tw.gov.idb.base.framework.domain.FrameworkUserInfoBean;
import tw.gov.idb.base.framework.helper.UserSessionHelper;
import tw.gov.idb.base.util.ExceptionUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.tagext.TagSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class SetProgIdTag extends TagSupport {

    private static final long serialVersionUID = 2695175632149846997L;


    private String progId;

    public int doStartTag() {
        try {
            pageContext.setAttribute("_page_progId", StringUtils.upperCase(progId));
            // 更新使用者物件中目前執行的功能的 頁面程式名稱
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            if (request != null) {
                FrameworkUserInfoBean userData = UserSessionHelper.getFrameworkUserData(request);
                if (userData != null) {
                    userData.setProgId(StringUtils.upperCase(progId));
                }
            }

        } catch (Exception e) {
            log.error("Taglib 發生錯誤, Tag: SetProgIdTag 原因: " + ExceptionUtility.getStackTrace(e));
        }

        return SKIP_BODY;
    }

    public void setProgId(String progId) {
        this.progId = progId;
    }

}