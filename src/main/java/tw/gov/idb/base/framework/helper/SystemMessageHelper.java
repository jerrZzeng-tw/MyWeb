package tw.gov.idb.base.framework.helper;

import tw.gov.idb.base.framework.tag.func.SystemAlertMessageTag;
import tw.gov.idb.base.framework.tag.func.SystemMessageTag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


/**
 * 系統訊息顯示處理
 *
 *  
 */
@Slf4j
public class SystemMessageHelper {

    public static void setMessage(String message) {
        if (StringUtils.isNotBlank(message)) {
            HttpSession session = HttpHelper.getHttpRequest().getSession();
            if (session != null) {
                session.removeAttribute(SystemMessageTag.SYSTEM_MESSAGE_SESSION_KEY);
                session.setAttribute(SystemMessageTag.SYSTEM_MESSAGE_SESSION_KEY, message);
            }
        }
    }

    public static void setAlertMessage(String message) {
        if (StringUtils.isNotBlank(message)) {
            HttpSession session = HttpHelper.getHttpRequest().getSession();
            if (session != null) {
                session.removeAttribute(SystemAlertMessageTag.SYSTEM_ALERT_MESSAGE_SESSION_KEY);
                session.setAttribute(SystemAlertMessageTag.SYSTEM_ALERT_MESSAGE_SESSION_KEY, message);
            }
        }
    }

}
