package tw.gov.idb.base.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@PropertySource("classpath:base.properties")
@ConfigurationProperties(prefix = "base")
@Component
public class BaseProperties {
    private String sysId;
    // 系統登入頁面或用來處理登入資訊的 Action URL
    private List<String> loginUrl;

    private String unauthorizedUrl;

    private String commonAjaxPref;

    private String pwSalt;

    // APLOG 是否紀錄使用者輸入資料
    private boolean logModel;
}
