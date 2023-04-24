package tw.gov.idb.base.framework.config;

import tw.gov.idb.base.framework.interceptor.BindingResultInterceptor;
import tw.gov.idb.base.framework.interceptor.DoubleSubmitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    BindingResultInterceptor bindingResultInterceptor;
    @Autowired
    DoubleSubmitInterceptor doubleSubmitInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/html/**").addResourceLocations("/html/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bindingResultInterceptor).addPathPatterns("/*.action");
        registry.addInterceptor(doubleSubmitInterceptor).addPathPatterns("/*.action");
    }
}
