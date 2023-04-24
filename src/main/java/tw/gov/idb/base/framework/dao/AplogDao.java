package tw.gov.idb.base.framework.dao;

import tw.gov.idb.base.framework.domain.Aplog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AplogDao {
    public Aplog insertAplog(Aplog aplog);
}
