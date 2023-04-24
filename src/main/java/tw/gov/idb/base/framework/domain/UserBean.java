package tw.gov.idb.base.framework.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import tw.gov.idb.base.util.DateUtility;

import java.io.Serial;
import java.io.Serializable;

/**
 * 一般開發使用的登入者資訊
 */
@Data
@NoArgsConstructor
@Component("userData")
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserBean implements UserInfo, Serializable {

    @Serial
    private static final long serialVersionUID = 7245620384307255819L;

    private String userId; // 使用者代碼
    private String userName; // 使用者名稱
    private String deptId; // 部門代碼
    private String empNo; // 員工編號
    private String loginIP; // 使用者 IP
    private String loginDate; // 登入日期
    private String loginTime; // 登入時間

    /**
     * 取得登入日期格式化字串
     *
     * @return 登入日期格式化字串
     */
    @Override
    public String getLoginDateString() {
        return DateUtility.formatChineseDateString(loginDate, true);
    }

    /**
     * 取得登入時間格式化字串
     *
     * @return 登入時間格式化字串
     */
    @Override
    public String getLoginTimeString() {
        return DateUtility.formatTimeString(loginTime, false);
    }

}
