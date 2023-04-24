package tw.gov.idb.base.framework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import tw.gov.idb.base.framework.annotation.BackupData;
import tw.gov.idb.base.framework.domain.RoleItem;

@Mapper
public interface RoleItemDao {
    
    List<RoleItem> selectRoleItems(@Param("roleId") String roleId);
    
    void insertData(RoleItem roleItem);
    
    @BackupData
    void deleteDataByItemId(RoleItem roleItem);
    
    @BackupData
    void deleteDataByRoleId(RoleItem roleItem);
    
    void backupData(RoleItem roleItem);

}
