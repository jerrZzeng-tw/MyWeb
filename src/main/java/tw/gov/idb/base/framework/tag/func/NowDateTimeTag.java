package tw.gov.idb.base.framework.tag.func;

import tw.gov.idb.base.util.DateUtility;
import tw.gov.idb.base.util.ExceptionUtility;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


/**
 * TagLib - 取得目前系統日期時間字串
 *
 *  
 */
@Slf4j
public class NowDateTimeTag extends TagSupport {

    private static final long serialVersionUID = -1953992288303496670L;

    @Setter
    private boolean chtType = false;

    public int doStartTag() {
        try {
            JspWriter out = pageContext.getOut();
            if (chtType)
                out.print("民國 " + DateUtility.formatNowChineseDateTimeString(chtType));
            else
                out.print(DateUtility.formatNowChineseDateTimeString(chtType));
        } catch (Exception e) {
            log.error("Taglib 發生錯誤, Tag: NowDateTimeTag 原因: {}", ExceptionUtility.getStackTrace(e));
        }

        return SKIP_BODY;
    }

}
