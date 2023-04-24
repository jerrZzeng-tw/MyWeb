package tw.gov.idb.base.framework.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import tw.gov.idb.base.framework.domain.Item;

@Mapper
public interface ItemDao {
    List<Item> selectUserItems(String userId);
    
    void insertData(Item item);
    
    List<String> selectOptions();//type=0 父節點
    
    void updateData(Item item);
    
    List<Item> selectItems(@Param("itemIdParent")String itemIdParent);
    
    Optional<Item> seletOneItem(@Param("itemId")String itemId);
    
    List<String> selectLevelNo(@Param("itemId")String itemId);
    
    Integer countItemId(@Param("itemId")String itemId);
    
    List<Item> selectChildnodes();//type=1 子節點
    
    void deleteType1(@Param("itemId")String itemId);//子節點刪除
    
    Integer deleteType0(@Param("itemId")String itemId);//父節點刪除
    
}