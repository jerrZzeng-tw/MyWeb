package tw.gov.idb.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Class for Presentation Support
 *
 *  
 */
@Slf4j
public class PresentationUtility {

    // 隱藏 身分證號 後 X 碼
    private static final int IDN_SHADOW_LENGTH = 4;
    // 隱藏 身分證號 之替換字元
    private static final String IDN_SHADOW_CHAR = "*";

    /**
     * 隱藏 身分證號 後 X 碼 <br>
     * (X 由 PresentationUtility.IDN_SHADOW_LENGTH) 指定<br>
     *
     * @param idN 身分證號
     * @return 處理過後的 身分證號
     */
    public static String shadowIdN(String idN) {
        int length = StringUtils.length(idN);
        if (length <= IDN_SHADOW_LENGTH) {
            return idN;
        } else {
            return StringUtils.rightPad(idN.substring(0, length - IDN_SHADOW_LENGTH), length, IDN_SHADOW_CHAR);
        }
    }

}
