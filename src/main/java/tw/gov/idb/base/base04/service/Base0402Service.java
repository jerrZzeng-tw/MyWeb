package tw.gov.idb.base.base04.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.idb.base.base04.cases.Base0402Case;
import tw.gov.idb.base.base04.cases.Base0402Case.AddAndUpdCase;
import tw.gov.idb.base.base04.cases.Base0402Case.Base0402qCase;
import tw.gov.idb.base.framework.dao.ItemDao;
import tw.gov.idb.base.framework.dao.RoleItemDao;
import tw.gov.idb.base.framework.domain.Item;
import tw.gov.idb.base.framework.domain.RoleItem;
import tw.gov.idb.base.framework.enums.ItemTypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class Base0402Service {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private RoleItemDao roleItemDao;

    public boolean queryItemList(Base0402Case caseData) throws Exception {
        List<Item> items = itemDao.selectItems(caseData.getItemIdParent());
        List<Base0402qCase> base0402qCases = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(items)) {
            items.stream().forEach(t -> {
                base0402qCases.add(Base0402qCase.builder()
                        .itemId(t.getItemId())
                        .itemIdParent(t.getItemIdParent())
                        .levelNo(t.getLevelNo())
                        .itemName(t.getItemName())
                        .url(t.getUrl())
                        .sortOrder(t.getSortOrder())
                        .type(t.getType())
                        .build());
            });
            caseData.setBase0402qCases(base0402qCases);
            return true;
        }
        return false;
    }

    public void initSelectOptions(Base0402Case caseData) {
        List<String> options = itemDao.selectOptions();
        caseData.setOptions(options);
    }

    public void enterAdd(Base0402Case caseData) {
        initSelectOptions(caseData);
        caseData.setStatus("add");
    }


    @Transactional(readOnly = true)
    public void enterUpd(Base0402Case caseData) {
        Optional<Item> opt =
                itemDao.seletOneItem(caseData.getBase0402qCases().get(caseData.getListIndex()).getItemId());
        if (opt.isPresent()) {
            caseData.setAddAndUpdCase(AddAndUpdCase.builder()
                    .itemId(opt.get().getItemId())
                    .itemIdParent(opt.get().getItemIdParent())
                    .levelNo(opt.get().getLevelNo())
                    .itemName(opt.get().getItemName())
                    .url(opt.get().getUrl())
                    .sortOrder(opt.get().getSortOrder())
                    .type(opt.get().getType())
                    .build());
        }
        caseData.setStatus("upd");
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertItem(Base0402Case caseData) throws Exception {
        caseData.setStatus("add");
        //新增自動帶入值: 功能項層數= 父功能項層數+1 
        Optional<String> levelNo =
                itemDao.selectLevelNo(caseData.getAddAndUpdCase().getItemIdParent()).stream().findFirst();
        itemDao.insertData(Item.builder()
                .itemId(caseData.getAddAndUpdCase().getItemId())
                .itemIdParent(caseData.getAddAndUpdCase().getItemIdParent())
                .levelNo(Integer.toString(Integer.parseInt(levelNo.get()) + 1))
                .itemName(caseData.getAddAndUpdCase().getItemName())
                .url(caseData.getAddAndUpdCase().getUrl())
                .sortOrder(caseData.getAddAndUpdCase().getSortOrder())
                .type(caseData.getAddAndUpdCase().getType())
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateItem(Base0402Case caseData) throws Exception {
        caseData.setStatus("upd");

        itemDao.updateData(Item.builder()
                .itemId(caseData.getAddAndUpdCase().getItemId())
                .itemName(caseData.getAddAndUpdCase().getItemName())
                .url(caseData.getAddAndUpdCase().getUrl())
                .sortOrder(caseData.getAddAndUpdCase().getSortOrder())
                .type(caseData.getAddAndUpdCase().getType())
                .build());
    }

    public boolean isDuplicate(Base0402Case caseData) {
        return Optional.ofNullable(itemDao.countItemId(caseData.getAddAndUpdCase().getItemId()))
                .map(t -> t > 0)
                .orElse(false);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteItem(Base0402Case caseData) throws Exception {
        //刪item 若type=1 (子節點) 可以直接刪 , 若type=0 ,先看有沒有被參照到 沒有的話也可以刪
        if (StringUtils.equals(caseData.getBase0402qCases().get(caseData.getListIndex()).getType(),
                ItemTypeEnum.FUN.getCode())) {
            //若type=1 (子節點) 可以直接刪
            itemDao.deleteType1(
                    StringUtils.trim(caseData.getBase0402qCases().get(caseData.getListIndex()).getItemId()));
            //同步刪role_item
            delSyncRoleItem(caseData.getBase0402qCases().get(caseData.getListIndex()).getItemId());
            return true;
        } else { //若type=0 ,先看有沒有被參照到  count row
            Integer delRowCount =
                    itemDao.deleteType0(caseData.getBase0402qCases().get(caseData.getListIndex()).getItemId());
            //delRowCount >0 代表可以刪 也已刪 代表沒有子節點 要同步刪role_item
            if (delRowCount > 0) {
                delSyncRoleItem(
                        StringUtils.trim(caseData.getBase0402qCases().get(caseData.getListIndex()).getItemId()));
                return true;
            } else {
                //if row =0 下面還有子節點 不能刪
                return false;
            }
        }

    }

    public void delSyncRoleItem(String itemId) {
        roleItemDao.deleteDataByItemId(RoleItem.builder().itemId(itemId).build());
    }
}
