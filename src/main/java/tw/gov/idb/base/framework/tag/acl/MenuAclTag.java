package tw.gov.idb.base.framework.tag.acl;

import tw.gov.idb.base.framework.domain.FrameworkUserInfoBean;
import tw.gov.idb.base.framework.helper.UserSessionHelper;
import tw.gov.idb.base.util.ExceptionUtility;
import tw.gov.idb.base.util.StringUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


/**
 * TagLib - 選單權限控管
 *
 *  
 */
@Slf4j
public class MenuAclTag extends TagSupport {

    private static final long serialVersionUID = -1267194287820429753L;

    @Setter
    private String itemId;

    public int doStartTag() {
        try {
            FrameworkUserInfoBean userData =
                    UserSessionHelper.getFrameworkUserData((HttpServletRequest) pageContext.getRequest());

            if (StringUtils.isBlank(itemId) || userData == null) {
                return SKIP_BODY;
            }

            String[] itemIds = StringUtility.splitTrim(StringUtils.upperCase(itemId), ",");

            for (String id : itemIds) {
                if (userData.hasPrivilege(id))
                    return EVAL_BODY_INCLUDE;
            }

            return SKIP_BODY;

        } catch (Exception e) {
            log.error("Taglib 發生錯誤, Tag: MenuAclTag 原因: {}", ExceptionUtility.getStackTrace(e));
            return SKIP_BODY;
        }
    }

    public int doEndTag() throws JspException {
        try {
            return EVAL_PAGE;
        } catch (Exception e) {
            log.error("Taglib 發生錯誤, Tag: MenuAclTag 原因: {}", ExceptionUtility.getStackTrace(e));
            return EVAL_PAGE;
        }
    }

}
