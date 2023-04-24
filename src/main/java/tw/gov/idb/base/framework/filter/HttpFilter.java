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

import java.io.IOException;


@Slf4j
public class HttpFilter implements Filter {
    //private static final List<String> RejectParametersBlackList = Collections.singletonList(")(");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Strict-Transport-Security", "max age=31536000;includeSubdomains; preload");
        res.setHeader("Content-Security-Policy", "frame-ancestors 'self'");
        //        if (isInRejectParametersBlackList(req)) {
        //            return;
        //        }
        chain.doFilter(request, response);
    }

    //    private boolean isInRejectParametersBlackList(final HttpServletRequest req) {
    //        for (String[] values : req.getParameterMap().values()) {
    //            for (String value : values) {
    //                for (String wrong : RejectParametersBlackList) {
    //                    if (StringUtils.contains(value, wrong)) {
    //                        log.error("Found request with parameters in rejection list!");
    //                        return true;
    //                    }
    //                }
    //            }
    //        }
    //        return false;
    //    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
