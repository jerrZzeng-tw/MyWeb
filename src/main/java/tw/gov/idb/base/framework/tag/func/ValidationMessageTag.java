package tw.gov.idb.base.framework.tag.func;

import tw.gov.idb.base.framework.helper.HttpHelper;
import tw.gov.idb.base.util.ExceptionUtility;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.owasp.encoder.Encode;

import java.util.List;

/**
 * TagLib - Validation 訊息顯示
 *
 *  
 */
@Slf4j
public class ValidationMessageTag extends TagSupport {

    private static final long serialVersionUID = -77428040634207385L;

    public static final String VALIDATION_MESSAGE_SESSION_KEY = "___validationMsg";
    public static final String VALIDATION_MESSAGE_SEPERATOR = "|||";

    @SuppressWarnings("unchecked")
    public int doStartTag() {
        try {
            HttpSession session = HttpHelper.getHttpRequest().getSession();

            if (session != null) {
                List<String> list = (List<String>) session.getAttribute(VALIDATION_MESSAGE_SESSION_KEY);
                session.removeAttribute(VALIDATION_MESSAGE_SESSION_KEY);

                if (list != null && list.size() > 0) {
                    JspWriter out = pageContext.getOut();

                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < list.size(); i++) {
                        str.append(list.get(i));
                        if (i != (list.size() - 1))
                            str.append(VALIDATION_MESSAGE_SEPERATOR);
                    }
                    // repalce 可以解FORTIFY
                    out.print(Encode.forHtml(
                            StringUtils.replaceAll(str.toString(), "\r\n", VALIDATION_MESSAGE_SEPERATOR)));
                }
            }
        } catch (Exception e) {
            log.error("Taglib 發生錯誤, Tag: ValidationMessageTag 原因: {}", ExceptionUtility.getStackTrace(e));
        }

        return SKIP_BODY;
    }
}
