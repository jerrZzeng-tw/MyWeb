package tw.gov.idb.base.framework.config;

import tw.gov.idb.base.framework.filter.AuthorizeFilter;
import tw.gov.idb.base.framework.filter.HttpFilter;
import tw.gov.idb.base.framework.filter.ThreadLocalFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;


@Configuration
public class AppConfig {

    @Autowired
    BaseProperties baseProperties;

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("global-messages", "messages", "/ValidationMessages");
        return messageSource;
    }

    @Bean
    public FilterRegistrationBean<ThreadLocalFilter> threadLocalFilter() {
        final FilterRegistrationBean<ThreadLocalFilter> registrationBean =
                new FilterRegistrationBean<>(new ThreadLocalFilter());
        registrationBean.setName("threadLocalFilter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizeFilter> authorizeFilter() {
        final FilterRegistrationBean<AuthorizeFilter> registrationBean =
                new FilterRegistrationBean<>(new AuthorizeFilter());
        registrationBean.setName("authorizeFilter");
        registrationBean.addUrlPatterns("*.action", "*.jsp");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<HttpFilter> httpFilter() {
        final FilterRegistrationBean<HttpFilter> registrationBean = new FilterRegistrationBean<>(new HttpFilter());
        registrationBean.setName("httpFilter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(3);
        return registrationBean;
    }

}
