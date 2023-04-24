package tw.gov.idb.base.framework.tag.func;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import lombok.extern.slf4j.Slf4j;
import org.owasp.encoder.Encode;

import java.lang.management.ManagementFactory;

/**
 * TagLib - 主機資訊
 *
 *  
 */
@Slf4j
public class ServerInfoTag extends TagSupport {

    private static final long serialVersionUID = 759594226069183493L;

    public int doStartTag() {
        try {
            JspWriter out = pageContext.getOut();
            Encode.forJava(out, "PID: " + ManagementFactory.getRuntimeMXBean().getName());
        } catch (Exception e) {
            log.error("ServerInfoTag.doStartTag() 發生錯誤");
        }

        return SKIP_BODY;
    }

}
