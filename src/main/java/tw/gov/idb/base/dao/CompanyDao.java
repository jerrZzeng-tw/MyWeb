package tw.gov.idb.base.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tw.gov.idb.base.domain.Company;
import tw.gov.idb.base.framework.annotation.BackupData;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CompanyDao {

    void insertData(Company company);

    @BackupData(value = "backupData")
    void updateData(Company company);

    List<Company> selectCompanys(@Param("companyName") String companyName, @Param("owner") String owner,
                                 @Param("capital") String capital);

    List<Company> selectCompanysForReport(@Param("companyName") String companyName, @Param("owner") String owner);

    Optional<Company> seletOneCompany(@Param("id") String id);

    @BackupData
    void deleteData(Company company);

    void backupData(Company company);
}