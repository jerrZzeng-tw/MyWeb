package tw.gov.idb.base.framework.helper;

import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.InputStream;

/**
 * 用於無法使用 Spring IOC 注入 Bean Instance 時, 取得定義於 Spring Config 檔中的 Bean Instance
 *
 *  
 */
@Slf4j
public class SpringHelper {

    /**
     * ServletContext 將由 <code>tw.gov.bli.common.listener.SystemInitialListener</code> 進行初始化
     */
    private static ServletContext ctx = null;

    /**
     * 取得定義於 Spring Config 檔中的 Bean Instance
     *
     * @param id 於 Spring Config 定義的 Bean ID
     * @return 取得的 Bean Instance
     */
    public static Object getBeanById(String id) {
        return WebApplicationContextUtils.getWebApplicationContext(ctx).getBean(id);
    }

    /**
     * 取得定義於 Spring Config 檔中的 Bean Instance
     *
     * @param classType 於 Spring Config 定義的 Bean
     * @return 取得的 Bean Instance
     */
    public static <T> T getBeanByClass(Class<T> classType) {
        return WebApplicationContextUtils.getWebApplicationContext(ctx).getBean(classType);
    }

    /**
     * 設定 ServletContext <br>
     * 將由 <code>tw.gov.bli.common.listener.SystemInitialListener</code> 進行初始化
     *
     * @param ctx ServletContext
     */
    public static void setCtx(ServletContext ctx) {
        if (SpringHelper.ctx == null)
            SpringHelper.ctx = ctx;
    }

    public static InputStream getXlsResourceAsStream(String filePath) {
        if (ctx != null) {
            return ctx.getResourceAsStream("/xlsx/" + filePath);
        } else {
            return null;
        }
    }

    public static InputStream getResourceAsStream(String filePath) {
        if (ctx != null) {
            return ctx.getResourceAsStream(filePath);
        } else {
            return null;
        }
    }
}
