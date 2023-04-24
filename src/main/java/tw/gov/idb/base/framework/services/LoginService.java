package tw.gov.idb.base.framework.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.idb.base.framework.config.BaseProperties;
import tw.gov.idb.base.framework.dao.ItemDao;
import tw.gov.idb.base.framework.dao.UserDao;
import tw.gov.idb.base.framework.domain.FrameworkUserInfoBean;
import tw.gov.idb.base.framework.domain.Item;
import tw.gov.idb.base.framework.domain.Menu;
import tw.gov.idb.base.framework.domain.MenuItem;
import tw.gov.idb.base.framework.domain.User;
import tw.gov.idb.base.framework.domain.UserInfo;
import tw.gov.idb.base.framework.helper.UserSessionHelper;
import tw.gov.idb.base.util.DateUtility;
import tw.gov.idb.base.util.HttpUtility;
import tw.gov.idb.base.util.PasswordUtility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 檢核使用者 Portal 登入狀態並取得使用者相關資料及權限
 *
 *  
 */
@Slf4j
@Service
public class LoginService {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BaseProperties basePops;

    public boolean login(UserInfo userData, HttpServletRequest request) {
        //todo 未來須修改為各系統之登入邏輯
        String id = StringUtils.defaultString((String) request.getParameter("ID"));
        String pw = StringUtils.defaultString((String) request.getParameter("PW"));

        User user = userDao.selectUser(id, PasswordUtility.hashSHA1(pw + basePops.getPwSalt()));

        if (user != null) {
            HttpSession session = request.getSession();
            String userId = user.getId(); // 使用者代碼
            String userName = user.getName(); // 使用者名稱
            String deptId = ""; // 部門代碼
            String empNo = user.getEmpNo(); // 員工編號
            String loginIP = ""; // 使用者 IP
            String token = ""; // 檢查資訊 Token

            loginIP = StringUtils.defaultString(HttpUtility.getClientIP(request)); // 使用者 IP
            // 取得 登入日期 及 登入時間
            String loginDateTime = DateUtility.getNowChineseDateTime(false);

            List<Item> itemList = itemDao.selectUserItems(id);

            // 設定 Framework 使用者資料
            FrameworkUserInfoBean frameworkUserInfoBean = new FrameworkUserInfoBean();
            frameworkUserInfoBean.setUserId(userId);
            frameworkUserInfoBean.setUserName(userName);
            frameworkUserInfoBean.setDeptId(deptId);
            frameworkUserInfoBean.setEmpNo(empNo);
            frameworkUserInfoBean.setLoginIP(loginIP);
            frameworkUserInfoBean.setToken(token);
            frameworkUserInfoBean.setLoginDate(DateUtility.splitChineseDateTime(loginDateTime, true));
            frameworkUserInfoBean.setLoginTime(DateUtility.splitChineseDateTime(loginDateTime, false));
            frameworkUserInfoBean.setMenu(generateUserMenu(itemList));// 設定使用者功能TREE
            frameworkUserInfoBean.initUrlPrefSet(itemList);// 設定使用者可使用的功能項目前綴
            frameworkUserInfoBean.getUrlPrefSet().add(basePops.getCommonAjaxPref());// 通用AJAX權限
            //            log.info();
            //            frameworkUserInfoBean.setHeaderFileName(getHeaderFileName());

            // 設定應用系統使用者資料
            userData.setUserId(userId);
            userData.setUserName(userName);
            userData.setDeptId(deptId);
            userData.setEmpNo(empNo);
            userData.setLoginIP(loginIP);
            userData.setLoginDate(DateUtility.splitChineseDateTime(loginDateTime, true));
            userData.setLoginTime(DateUtility.splitChineseDateTime(loginDateTime, false));

            // 將使用者資料存入 Session (底層再用的不可拿掉)
            UserSessionHelper.setFrameworkUserData(request, frameworkUserInfoBean);

            // 將應用系統使用者物件存入 Session
            UserSessionHelper.setUserData(request, userData);
            return true;
        }
        return false;
    }

    /**
     * 產生使用者功能選單物件
     *
     * @param itemList 從資料庫撈出來的使用者選單授權資料
     * @return
     */
    private Menu generateUserMenu(List<Item> itemList) {
        Menu menu = new Menu();

        List<MenuItem> menuItemList = new ArrayList<>();

        HashMap<String, MenuItem> map = new HashMap<>();
        String previousLevel1Id = null;

        for (Item item : itemList) {
            String itemId = item.getItemId();
            String itemIdP = item.getItemIdParent();
            String levelNo = item.getLevelNo();
            int sortOrder = item.getSortOrder();
            MenuItem menuItem = new MenuItem(levelNo, item.getItemName(),
                    StringUtils.defaultString(StringUtils.trimToNull(item.getUrl()), "#"), sortOrder,
                    item.isFunction());

            if (StringUtils.equals(levelNo, "1")) {
                if (previousLevel1Id == null) {
                    previousLevel1Id = itemId;
                }

                if (!map.isEmpty()) {
                    menuItemList.add(map.get(previousLevel1Id));
                    map = new HashMap<>();
                    previousLevel1Id = itemId;
                }

                map.put(item.getItemId(), menuItem);
            } else {
                if (map.get(itemIdP) != null) {
                    map.get(itemIdP).addSub(menuItem);
                }

                map.put(itemId, menuItem);
            }
        }

        if (!map.isEmpty()) {
            menuItemList.add(map.get(previousLevel1Id));
        }

        // 排除沒有子功能的項目
        removeEmptyItem(menuItemList);

        // 加上系統登出項目
        menuItemList.add(new MenuItem("1", "登出系統", "/Logout.action", 999999999, true));
        // 排序列表
        menuItemList = sortMenu(menuItemList);
        menu.setMenuItemList(menuItemList);

        return menu;
    }

    /**
     * 根據設定的sortOrder 排序
     *
     * @param list
     * @return
     */
    private List<MenuItem> sortMenu(List<MenuItem> list) {
        list.forEach(t -> {
            if (t.getSub() != null) {
                t.setSub(sortMenu(t.getSub()));
            }
        });

        return list.stream()
                .sorted(Comparator.comparing(MenuItem::getOrder))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void removeEmptyItem(List<MenuItem> menuItemList) {
        MenuItem root = new MenuItem();
        root.setSub(menuItemList);
        removeEmptyItem(root);

    }

    private boolean removeEmptyItem(MenuItem menuItem) {
        if (menuItem.isFunction()) {
            return false;
        }

        List<MenuItem> removeList = new ArrayList<>();
        if (menuItem.getSub() != null && !menuItem.getSub().isEmpty()) {
            for (MenuItem item : menuItem.getSub()) {
                if (removeEmptyItem(item)) {
                    removeList.add(item);
                }
            }
            for (MenuItem remove : removeList) {
                menuItem.getSub().remove(remove);
            }

            return menuItem.getSub().isEmpty();

        } else {
            return true;
        }

    }

}
