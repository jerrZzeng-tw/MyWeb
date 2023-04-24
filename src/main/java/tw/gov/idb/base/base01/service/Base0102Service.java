package tw.gov.idb.base.base01.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tw.gov.idb.base.base01.cases.Base0102Case;
import tw.gov.idb.base.base01.cases.Base0102Case.AddAndUpdCase;
import tw.gov.idb.base.base01.cases.Base0102Case.Base0102qCase;
import tw.gov.idb.base.dao.CompanyDao;
import tw.gov.idb.base.domain.Company;
import tw.gov.idb.base.util.DateUtility;


@Slf4j
@Service
public class Base0102Service {
    @Autowired
    private CompanyDao companyDao;

    // 查詢公司資訊
    public boolean queryCompanyList(Base0102Case caseData) throws Exception {
        List<Company> companys = companyDao.selectCompanys(StringUtils.trim(caseData.getCompanyName()),
                StringUtils.trim(caseData.getOwner()),StringUtils.trim(caseData.getCapital()));
        List<Base0102qCase> base0102qCases = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(companys)) {
            companys.stream().forEach(t -> {
                base0102qCases.add(Base0102qCase.builder()
                        .id(t.getId())
                        .companyName(t.getCompanyName())
                        .owner(t.getOwner())
                        .ownerIdn(t.getOwnerIdn())
                        .email(t.getEmail())
                        .website(t.getWebsite())
                        .uniformNumbers(t.getUniformNumbers())
                        .persons(t.getPersons())
                        .capital(t.getCapital())
                        .city(t.getCity())
                        .area(t.getArea())
                        .addr(t.getAddr())
                        .issueDate(t.getIssueDate())
                        .updateTime(t.getUpdateTime())
                        .build());
            });
            caseData.setBase0102qCases(base0102qCases);
            return true;
        }
        return false;
    }

    public void enterAdd(Base0102Case caseData) throws Exception {
        caseData.setStatus("add");
    }


    @Transactional(readOnly = true)
    public void enterUpd(Base0102Case caseData) {
        Optional<Company> opt =
                companyDao.seletOneCompany(caseData.getBase0102qCases().get(caseData.getListIndex()).getId());
        if (opt.isPresent()) {
            caseData.setAddAndUpdCase(AddAndUpdCase.builder()
                    .id(opt.get().getId())
                    .companyName(opt.get().getCompanyName())
                    .owner(opt.get().getOwner())
                    .ownerIdn(opt.get().getOwnerIdn())
                    .email(opt.get().getEmail())
                    .website(opt.get().getWebsite())
                    .uniformNumbers(opt.get().getUniformNumbers())
                    .persons(opt.get().getPersons() != null ? opt.get().getPersons().toString() : StringUtils.EMPTY)
                    .capital(opt.get().getCapital() != null ? opt.get().getCapital().toString() : StringUtils.EMPTY)
                    .city(opt.get().getCity())
                    .area(opt.get().getArea())
                    .addr(opt.get().getAddr())
                    .issueDate(opt.get().getIssueDate())
                    .updateTime(opt.get().getUpdateTime())
                    .build());
        }
        caseData.setStatus("upd");
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertCompany(Base0102Case caseData) throws Exception {
        caseData.setStatus("add");

        companyDao.insertData(Company.builder()
                .companyName(caseData.getAddAndUpdCase().getCompanyName())
                .owner(caseData.getAddAndUpdCase().getOwner())
                .ownerIdn(caseData.getAddAndUpdCase().getOwnerIdn())
                .email(caseData.getAddAndUpdCase().getEmail())
                .website(caseData.getAddAndUpdCase().getWebsite())
                .uniformNumbers(caseData.getAddAndUpdCase().getUniformNumbers())
                .persons(caseData.getAddAndUpdCase().getPersons() != StringUtils.EMPTY ? Integer.parseInt(
                        caseData.getAddAndUpdCase().getPersons()) : null)
                .capital(caseData.getAddAndUpdCase().getCapital() != StringUtils.EMPTY ? Integer.parseInt(
                        caseData.getAddAndUpdCase().getCapital()) : null)
                .city(caseData.getAddAndUpdCase().getCity())
                .area(caseData.getAddAndUpdCase().getArea())
                .addr(caseData.getAddAndUpdCase().getAddr())
                .issueDate(caseData.getAddAndUpdCase().getIssueDate())
                .updateTime(DateUtility.getNowWestDateTime(true))
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCompany(Base0102Case caseData) throws Exception {
        caseData.setStatus("upd");

        companyDao.updateData(Company.builder()
                .id(caseData.getAddAndUpdCase().getId())
                .companyName(caseData.getAddAndUpdCase().getCompanyName())
                .owner(caseData.getAddAndUpdCase().getOwner())
                .ownerIdn(caseData.getAddAndUpdCase().getOwnerIdn())
                .email(caseData.getAddAndUpdCase().getEmail())
                .website(caseData.getAddAndUpdCase().getWebsite())
                .uniformNumbers(caseData.getAddAndUpdCase().getUniformNumbers())
                .persons(caseData.getAddAndUpdCase().getPersons() != StringUtils.EMPTY ? Integer.parseInt(
                        caseData.getAddAndUpdCase().getPersons()) : null)
                .capital(caseData.getAddAndUpdCase().getCapital() != StringUtils.EMPTY ? Integer.parseInt(
                        caseData.getAddAndUpdCase().getCapital()) : null)
                .city(caseData.getAddAndUpdCase().getCity())
                .area(caseData.getAddAndUpdCase().getArea())
                .addr(caseData.getAddAndUpdCase().getAddr())
                .issueDate(caseData.getAddAndUpdCase().getIssueDate())
                .updateTime(DateUtility.getNowWestDateTime(true))
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteCompany(Base0102Case caseData) throws Exception {
        companyDao.deleteData(
                Company.builder().id(caseData.getBase0102qCases().get(caseData.getListIndex()).getId()).build());
    }

}
