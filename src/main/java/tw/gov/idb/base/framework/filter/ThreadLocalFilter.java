package tw.gov.idb.base.framework.filter;

import tw.gov.idb.base.framework.helper.HttpHelper;
import tw.gov.idb.base.util.StringUtility;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Framework ThreadLocal Filter for <code>tw.gov.bli.common.helper.HttpHelper</code><br>
 * <br>
 * 欲使用本 Filter, 請於 <code>web.xml</code> 進行定義, 範例如下: <br>
 * <code><pre>
 *  &lt;filter&gt;
 *      &lt;filter-name&gt;FrameworkThreadLocalFilter&lt;/filter-name&gt;
 *      &lt;filter-class&gt;tw.gov.bli.common.filter.ThreadLocalFilter&lt;/filter-class&gt;
 *  &lt;/filter&gt;
 *
 *  &lt;filter-mapping&gt;
 *      &lt;filter-name&gt;FrameworkThreadLocalFilter&lt;/filter-name&gt;
 *      &lt;url-pattern&gt;*.do&lt;/url-pattern&gt;
 *  &lt;/filter-mapping&gt;
 * </pre></code>
 *
 *  
 */
@Slf4j
public class ThreadLocalFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug("設定 HttpServletRequest ThreadLocal, 路徑: {}",
                    StringUtility.safeLog(((HttpServletRequest) request).getServletPath()));
        }
        HttpHelper.setHttpRequest((HttpServletRequest) request);

        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void destroy() {

    }

}
