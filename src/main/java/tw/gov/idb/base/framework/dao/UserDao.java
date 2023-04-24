package tw.gov.idb.base.framework.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import tw.gov.idb.base.framework.domain.User;

@Mapper
public interface UserDao {
    User selectUser(String id, String pw);
    
    List<User> selectUsers(@Param("id")String id,@Param("name")String name);
    
    void insertData(User user);
    
    Optional<User> seletOneUser(@Param("id")String id);

    void updateData(User user);
    
    void deleteData(String id);

    Integer countUserId(@Param("id")String id);

}
