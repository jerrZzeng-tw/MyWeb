package tw.gov.idb.base.base04.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.idb.base.base04.cases.Base0403Case;
import tw.gov.idb.base.base04.cases.Base0403Case.AuthCase;
import tw.gov.idb.base.framework.dao.ItemDao;
import tw.gov.idb.base.framework.dao.RoleDao;
import tw.gov.idb.base.framework.dao.RoleItemDao;
import tw.gov.idb.base.framework.domain.Item;
import tw.gov.idb.base.framework.domain.Role;
import tw.gov.idb.base.framework.domain.RoleItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Base0403Service {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private RoleItemDao roleItemDao;

    @Transactional(readOnly = true)
    public void selectRoleList(Base0403Case caseData) throws Exception {
        List<Role> roles = roleDao.selectRoles(caseData.getRoleId());
        List<Base0403Case> base0403Cases = new ArrayList<>();

        roles.stream().forEach(t -> {
            base0403Cases.add(Base0403Case.builder()
                    .roleId(t.getRoleId())
                    .roleName(t.getRoleName())
                    .roleDesc(t.getRoleDesc())
                    .build());
        });
        caseData.setBase0403Cases(base0403Cases);
    }

    public void enterAdd(Base0403Case caseData) {
        caseData.setStatus("add");
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertRole(Base0403Case caseData) throws Exception {
        roleDao.insertData(Role.builder()
                .roleId(caseData.getRoleId())
                .roleName(caseData.getRoleName())
                .roleDesc(caseData.getRoleDesc())
                .build());
    }

    public void enterUpd(Base0403Case caseData) {
        caseData.setStatus("upd");

        caseData.setRoleId(caseData.getBase0403Cases().get(caseData.getListIndex()).getRoleId());
        caseData.setRoleName(caseData.getBase0403Cases().get(caseData.getListIndex()).getRoleName());
        caseData.setRoleDesc(caseData.getBase0403Cases().get(caseData.getListIndex()).getRoleDesc());

    }

    @Transactional(rollbackFor = Exception.class)
    public void updateRole(Base0403Case caseData) throws Exception {
        roleDao.updateData(Role.builder()
                .roleId(caseData.getRoleId())
                .roleName(caseData.getRoleName())
                .roleDesc(caseData.getRoleDesc())
                .build());
    }

    public boolean isDuplicate(Base0403Case caseData) {
        return Optional.ofNullable(roleDao.countRoleId(caseData.getRoleId())).map(t -> t > 0).orElse(false);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Base0403Case caseData) throws Exception {
        roleDao.deleteData(
                Role.builder().roleId(caseData.getBase0403Cases().get(caseData.getListIndex()).getRoleId()).build());
        //同步刪除 role_item 這RoleId所有權限
        roleItemDao.deleteDataByRoleId(RoleItem.builder()
                .roleId(caseData.getBase0403Cases().get(caseData.getListIndex()).getRoleId())
                .build());
    }

    @Transactional(readOnly = true)
    public void selectItemList(Base0403Case caseData) throws Exception {
        List<AuthCase> authCases = new ArrayList<>();
        //找全部子節點item type  = '1'
        List<Item> allItems = itemDao.selectChildnodes();
        allItems.stream().forEach(item -> {
            AuthCase authCase = new AuthCase();
            authCase.setItemId(item.getItemId());
            authCase.setItemName(item.getItemName());
            authCase.setUrl(item.getUrl());
            authCases.add(authCase);
        });

        //找這個roleId 有的權限
        List<RoleItem> roleItems =
                roleItemDao.selectRoleItems(caseData.getBase0403Cases().get(caseData.getListIndex()).getRoleId());
        //已經有權限的給tag ,show 已啟用 停用
        authCases.stream()
                .filter(all -> roleItems.stream().anyMatch(auth -> auth.getItemId().equals(all.getItemId())))
                .collect(Collectors.toList())
                .stream()
                .forEach(t -> {
                    t.setStatus(true);
                    t.setShowStatus("已啟用");
                });
        caseData.setAuthCases(authCases);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertAuth(Base0403Case caseData) throws Exception {
        //id 是自增組鍵 auto_increment
        roleItemDao.insertData(RoleItem.builder()
                .itemId(caseData.getAuthCases().get(caseData.getAuthListIndex()).getItemId())
                .roleId(caseData.getBase0403Cases().get(caseData.getListIndex()).getRoleId())
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAuth(Base0403Case caseData) throws Exception {
        //delete  role_item by itemId 刪除權限功能
        roleItemDao.deleteDataByItemId(RoleItem.builder()
                .itemId(caseData.getAuthCases().get(caseData.getAuthListIndex()).getItemId())
                .build());
    }

}
