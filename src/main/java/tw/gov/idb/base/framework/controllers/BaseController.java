package tw.gov.idb.base.framework.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import tw.gov.idb.base.framework.cases.BaseCase;
import tw.gov.idb.base.framework.helper.SystemMessageHelper;

import java.util.Locale;

/**
 * BaseController
 * <p>
 * 提供存取預設系統操作訊息及 MessageSource 的方法
 *
 *  
 */
public abstract class BaseController {

    private static final boolean DEFAULT_SYSMSG_ALERT = false; // 預設情況下系統訊息是否以 Alert 的方式在前端呈現

    private static final String MSG_SAVE_SUCCESS = "msg.db.saveSuccess"; // 訊息: 資料新增成功
    private static final String MSG_UPDATE_SUCCESS = "msg.db.updateSuccess"; // 訊息: 資料更新成功
    private static final String MSG_DELETE_SUCCESS = "msg.db.deleteSuccess"; // 訊息: 資料刪除成功
    private static final String MSG_QUERY_SUCCESS = "msg.db.querySuccess"; // 訊息: 資料查詢成功

    private static final String MSG_SAVE_FAIL = "msg.db.saveFail"; // 訊息: 資料新增失敗
    private static final String MSG_UPDATE_FAIL = "msg.db.updateFail"; // 訊息: 資料更新失敗
    private static final String MSG_DELETE_FAIL = "msg.db.deleteFail"; // 訊息: 資料刪除失敗
    private static final String MSG_QUERY_FAIL = "msg.db.queryFail"; // 訊息: 資料查詢失敗
    private static final String MSG_QUERY_EMPTY = "msg.db.queryEmpty"; // 訊息: 無查詢資料

    private static final String MSG_REPORT_ERROR = "msg.report.error"; // 訊息: 報表產製件敗

    @Autowired
    public MessageSource messageSource;

    /**
     * 設定要顯示在系統訊息區的訊息 (依 <code>DEFAULT_SYSMSG_ALERT</code> 的設定來決定是否以 Alert 的方式在前端呈現)
     *
     * @param message 訊息內容
     */
    protected void setSystemMessage(String message) {
        setSystemMessage(message, DEFAULT_SYSMSG_ALERT);
    }

    /**
     * 設定要顯示在系統下方訊息區的訊息
     *
     * @param message 訊息內容
     * @param alert   <code>true</code> Alert 呈現；<code>false</code> 在訊息區顯示
     */
    protected void setSystemMessage(String message, boolean alert) {
        if (!alert) {
            SystemMessageHelper.setMessage(message);
        } else {
            SystemMessageHelper.setAlertMessage(message);
        }
    }

    // fortify
    final String[] DISALLOWED_FIELDS = new String[]{};

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields(DISALLOWED_FIELDS);
    }

    protected String getSaveSuccessMessage() {
        return getMessage(MSG_SAVE_SUCCESS);
    }

    protected String getUpdateSuccessMessage() {
        return getMessage(MSG_UPDATE_SUCCESS);
    }

    protected String getDeleteSuccessMessage() {
        return getMessage(MSG_DELETE_SUCCESS);
    }

    protected String getQuerySuccessMessage() {
        return getMessage(MSG_QUERY_SUCCESS);
    }

    protected String getSaveFailMessage() {
        return getMessage(MSG_SAVE_FAIL);
    }

    protected String getUpdateFailMessage() {
        return getMessage(MSG_UPDATE_FAIL);
    }

    protected String getDeleteFailMessage() {
        return getMessage(MSG_DELETE_FAIL);
    }

    protected String getQueryFailMessage() {
        return getMessage(MSG_QUERY_FAIL);
    }

    protected String getQueryEmptyMessage() {
        return getMessage(MSG_QUERY_EMPTY);
    }

    protected String getReportErrorMessage() {
        return getMessage(MSG_REPORT_ERROR);
    }

    protected String getMessage(String key) {
        if (StringUtils.isNotBlank(key)) {
            return messageSource.getMessage(key, null, key + " 未被定義", Locale.getDefault());
        } else {
            return "";
        }
    }

    protected String getMessage(String key, Object[] args) {
        if (StringUtils.isNotBlank(key)) {
            return messageSource.getMessage(key, args, key + " 未被定義", Locale.getDefault());
        } else {
            return "";
        }
    }

    abstract public <T extends BaseCase> T getCaseData();
}
