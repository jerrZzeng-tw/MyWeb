package tw.gov.idb.base.framework.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用者功能選單項目
 *
 *  
 */
@Data
public class MenuItem implements Serializable {

    @Serial
    private static final long serialVersionUID = -7091480687177810884L;

    private String levelNo; // Menu Level
    private String functionName; // 中文功能名稱
    private String url; // URL
    private int order;
    private List<MenuItem> sub; // 子選單項目

    private boolean isFunction; // 是否為功能項

    public void addSub(MenuItem menuItem) {
        if (sub == null) {
            sub = new ArrayList<MenuItem>();
        }
        sub.add(menuItem);
    }

    public MenuItem() {

    }

    public MenuItem(String levelNo, String functionName, String url, int order, boolean isFunction) {
        this.levelNo = levelNo;
        this.functionName = functionName;
        this.url = url;
        this.order = order;
        this.isFunction = isFunction;
    }

}
