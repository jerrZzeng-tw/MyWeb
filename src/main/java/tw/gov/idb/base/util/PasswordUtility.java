package tw.gov.idb.base.util;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * 備援登入方式使用
 *
 *  
 */
public class PasswordUtility {

    /**
     * SHA 加密
     *
     * @param info
     * @return
     */
    public static String hashSHA1(String info) {
        String re = null;
        try {
            String myinfo = info;
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            alga.update(myinfo.getBytes());
            byte[] digesta = alga.digest();
            re = byte2hex(digesta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

    /**
     * 二進位制轉字串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    /**
     * 產生 帳號 / 裝置 啟用碼（8 碼大小寫英數字）
     *
     * @return
     */
    public static String generateVcode() {
        StringBuffer str = new StringBuffer("");

        // 英數字共 49 碼（扣除 0 2 8 B I L O Z b i l o z 這幾個可能因字形影響而不易區別的字）
        String vcodeString = "1345679ACDEFGHJKMNPQRSTUVWXYacdefghjkmnpqrstuvwxy";

        for (int i = 0; i < 8; i++) {
            int codeSeq = (int) (Math.random() * 49 + 1);
            str.append(StringUtils.substring(vcodeString, codeSeq - 1, codeSeq));
        }

        return str.toString();
    }

    /**
     * 產生ID
     *
     * @param length ID長度
     * @return
     */
    public static String generateId(int length) {
        StringBuffer str = new StringBuffer("");
        // 英數字共 62 碼
        String vcodeString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < length; i++) {
            int codeSeq = (int) (Math.random() * 62 + 1);
            str.append(StringUtils.substring(vcodeString, codeSeq - 1, codeSeq));
        }

        return str.toString();
    }

}
