package tw.gov.idb.base.framework.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import tw.gov.idb.base.util.DateUtility;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 底層所使用的使用者物件<br>
 * Framework 須仰賴此類別實作之功能, 因此若各系統欲擴充使用者物件之功能需繼承此類別<br>
 *
 *  
 */
@Data
public class FrameworkUserInfoBean implements UserInfo, Serializable {

    private static final long serialVersionUID = 3808413122318195294L;


    /**
     * 使用者代碼
     */
    private String userId;

    /**
     * 使用者名稱
     */
    private String userName;

    /**
     * 部門代碼
     */
    private String deptId;

    /**
     * 員工編號
     */
    private String empNo;

    /**
     * 使用者 IP
     */
    private String loginIP;

    /**
     * 檢查資訊 Token
     */
    private String token;

    /**
     * 使用者可執行之所有url prefix
     */
    private Set<String> urlPrefSet;

    //    /**
    //     * for Framework Logging, 代表目前使用者作業之 Protal Log Id
    //     */
    //    private BigDecimal sysId;
    //
    //    /**
    //     * for Framework Logging, 使用者目前執行的功能的 應用系統功能代號
    //     */
    //    private String apFunctionCode;
    //
    //    /**
    //     * for Framework Logging, 使用者目前執行的功能的 應用系統功能名稱
    //     */
    //    private String apFunctionName;

    /**
     * for Framework Logging, 使用者目前執行的功能的 頁面程式代號 - 螢幕編號
     */
    private String progId;

    /**
     * 登入日期
     */
    private String loginDate;

    /**
     * 登入時間
     */
    private String loginTime;

    /**
     * 系統要用的 Header 圖檔 (節日變換用)
     */
    private String headerFileName;

    /**
     * 使用者選單
     */
    private Menu menu;

    /**
     * 使用者選單 JSON 字串
     */
    private String menuJson;

    public String getMenuJson() {
        if (menu != null && StringUtils.isBlank(menuJson)) {
            try {
                // JSON
                ObjectMapper mapper = new ObjectMapper();
                menuJson = mapper.writeValueAsString(menu);
            } catch (Exception e) {
                // Should not happen
            }
        }

        return menuJson;
    }

    /**
     * 使用者是否有執行某項功能之權限
     *
     * @param url
     * @return 有回傳 <code>true</code>; 沒有回傳 <code>false</code>
     */
    public boolean hasPrivilege(String url) {
        if (StringUtils.defaultString(url).equals(""))
            return false;
        String urlPref = StringUtils.substring(url, 0, StringUtils.lastIndexOf(url, "_"));
        return urlPrefSet.contains(urlPref);
    }

    /**
     * 取得登入日期格式化字串
     *
     * @return 登入日期格式化字串
     */
    public String getLoginDateString() {
        return DateUtility.formatChineseDateString(loginDate, true);
    }

    /**
     * 取得登入時間格式化字串
     *
     * @return 登入時間格式化字串
     */
    public String getLoginTimeString() {
        return DateUtility.formatTimeString(loginTime, false);
    }

    public FrameworkUserInfoBean() {
        urlPrefSet = new HashSet<String>();
        menu = new Menu();
    }

    public void initUrlPrefSet(List<Item> itemList) {
        itemList.forEach(t -> {
            if (t.isFunction()) {
                urlPrefSet.add(t.getUrlPref());
            }
        });
    }

}
