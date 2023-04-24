package tw.gov.idb.base.framework.listener;

import tw.gov.idb.base.framework.helper.SpringHelper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContextListener implements ServletContextListener, ServletContextAttributeListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // TODO Auto-generated method stub
        ServletContextListener.super.contextInitialized(sce);
        log.info("===== Framework 環境 初始化開始 =====");
        ServletContext ctx = sce.getServletContext();
        // 設定 SpringHelper 中的 ServletContext
        SpringHelper.setCtx(ctx);
        log.info("設定 SpringHelper 完成");


        log.info("===== Framework 環境 初始化完成! =====");
        //System.out.println("ServletContextListener: Context Initialized");
    }

    ServletContext servletContext;


    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        // TODO Auto-generated method stub
        ServletContextAttributeListener.super.attributeAdded(scae);
        //System.out.println("ServletContextAttributeListener: Context Attribute Added");
    }


    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        // TODO Auto-generated method stub
        ServletContextAttributeListener.super.attributeRemoved(scae);
        //System.out.println("ServletContextAttributeListener: Context Attribute Removed");
    }


    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        // TODO Auto-generated method stub
        ServletContextAttributeListener.super.attributeReplaced(scae);
        //System.out.println("ServletContextAttributeListener: Context Attribute Replaced");
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
        ServletContextListener.super.contextDestroyed(sce);
        //System.out.println("ServletContextListener: Context Destroyed");
    }

}