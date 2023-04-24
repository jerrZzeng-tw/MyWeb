package tw.gov.idb.base.framework.tag.acl;

import tw.gov.idb.base.util.ExceptionUtility;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class GetProgIdTag extends TagSupport {

    private static final long serialVersionUID = -157502058655084681L;

    public int doStartTag() {
        try {
            String progId = StringUtils.defaultString((String) pageContext.getAttribute("_page_progId"));
            JspWriter out = pageContext.getOut();

            if (progId.equals("")) {
                log.error("Taglib 發生錯誤, Tag: GetProgIdTag 原因: progId 未定義");
                out.print("<font color='red'>Undefined progId</font>");
            } else {
                out.print(progId);
            }
        } catch (Exception e) {
            log.error("Taglib 發生錯誤, Tag: GetProgIdTag 原因: {}", ExceptionUtility.getStackTrace(e));
        }

        return SKIP_BODY;
    }

}
