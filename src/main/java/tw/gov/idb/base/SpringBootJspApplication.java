package tw.gov.idb.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = {"tw.gov.idb.base.dao", "tw.gov.idb.base.framework.dao"})
@SpringBootApplication(scanBasePackages = "tw.gov.idb.base")
@EnableConfigurationProperties
@EnableCaching
public class SpringBootJspApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootJspApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringBootJspApplication.class);
    }

}