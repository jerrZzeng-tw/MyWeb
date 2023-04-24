package tw.gov.idb.base.framework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import tw.gov.idb.base.framework.annotation.BackupData;
import tw.gov.idb.base.framework.domain.Role;

@Mapper
public interface RoleDao {

    List<Role> selectRoles(@Param("roleId") String roleId);

    void insertData(Role role);

    @BackupData
    void updateData(Role role);
    
    @BackupData
    void deleteData(Role role);

    Integer countRoleId(@Param("roleId")String roleId);

    void backupData(Role role);

}
