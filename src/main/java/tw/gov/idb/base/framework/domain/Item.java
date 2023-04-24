package tw.gov.idb.base.framework.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.io.Serializable;

@Alias("Item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -5714843836001021691L;
    private String itemId;//功能項ID
    private String itemIdParent;//父功能項ID
    private String levelNo;//功能項層數
    private String itemName;//功能項名稱
    private String url;//功能項 URL
    private int sortOrder;//功能項 排序
    private String type;//功能項種類1:功能列  0:標題列

    public boolean isFunction() {
        return "1".equals(type);
    }

    /**
     * 取得功能項目URL前綴.
     * 相同功能的URL都必須有相同的前綴.
     *
     * @return
     */
    public String getUrlPref() {
        String urlPref = StringUtils.substring(url, 0, StringUtils.lastIndexOf(url, "_"));
        return StringUtils.startsWith(urlPref, "/") ? urlPref : "/" + urlPref;
    }
}
