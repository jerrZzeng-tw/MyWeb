package tw.gov.idb.base.framework.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用者功能選單
 *
 *  
 */
@Data
public class Menu implements Serializable {

    @Serial
    private static final long serialVersionUID = -2974842076132900032L;

    private List<MenuItem> menuItemList;

    public Menu() {
        menuItemList = new ArrayList<MenuItem>();
    }

}
