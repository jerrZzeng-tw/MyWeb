package tw.gov.idb.base.framework.tag.func;

import tw.gov.idb.base.framework.helper.HttpHelper;
import tw.gov.idb.base.util.ExceptionUtility;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.owasp.encoder.Encode;

/**
 * TagLib - 系統訊息以 Alert 顯示
 *
 *  
 */
@Slf4j
public class SystemAlertMessageTag extends TagSupport {

    private static final long serialVersionUID = -8214470323566537837L;

    public static final String SYSTEM_ALERT_MESSAGE_SESSION_KEY = "___systemAlertMsg";

    public int doStartTag() {
        try {
            HttpSession session = HttpHelper.getHttpRequest().getSession();

            if (session != null) {
                String message = (String) session.getAttribute(SYSTEM_ALERT_MESSAGE_SESSION_KEY);
                session.removeAttribute(SYSTEM_ALERT_MESSAGE_SESSION_KEY);

                JspWriter out = pageContext.getOut();
                // repalce 可以解FORTIFY
                out.print(Encode.forHtml(StringUtils.replaceAll(StringUtils.defaultString(message), "\r\n", "")));
            }
        } catch (Exception e) {
            log.error("Taglib 發生錯誤, Tag: SystemAlertMessageTag 原因: {}", ExceptionUtility.getStackTrace(e));
        }

        return SKIP_BODY;
    }

}
