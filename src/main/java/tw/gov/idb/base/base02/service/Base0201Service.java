package tw.gov.idb.base.base02.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tw.gov.idb.base.base02.cases.Base0201Case;
import tw.gov.idb.base.base02.report.cases.Base0201RptCase;
import tw.gov.idb.base.dao.CompanyDao;
import tw.gov.idb.base.domain.Company;
import tw.gov.idb.base.util.DateUtility;

@Slf4j
@Service
public class Base0201Service {
    @Autowired
    private CompanyDao companyDao;

    public Base0201RptCase queryCompanyList(Base0201Case caseData) throws Exception {
        Base0201RptCase rptcase = new Base0201RptCase();
        List<Company> companys = companyDao.selectCompanysForReport(StringUtils.trim(caseData.getCompanyName()),
                StringUtils.trim(caseData.getOwner()));
        List<Base0201RptCase.rptCase> rptCases = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(companys)) {
            companys.stream().forEach(t -> {
                rptCases.add(Base0201RptCase.rptCase.builder()
                        .id(t.getId())
                        .companyName(StringUtils.defaultString(t.getCompanyName()))
                        .owner(StringUtils.defaultString(t.getOwner()))
                        .ownerIdn(StringUtils.defaultString(t.getOwnerIdn()))
                        .email(StringUtils.defaultString(t.getEmail()))
                        .website(StringUtils.defaultString(t.getWebsite()))
                        .uniformNumbers(StringUtils.defaultString(t.getUniformNumbers()))
                        .persons(t.getPersons() != null ? String.valueOf(t.getPersons()) : StringUtils.EMPTY)
                        .capital(t.getCapital() != null ? String.valueOf(t.getCapital()) : StringUtils.EMPTY)
                        .city(StringUtils.defaultString(t.getCity()))
                        .area(StringUtils.defaultString(t.getArea()))
                        .addr(StringUtils.defaultString(t.getAddr()))
                        .issueDate(StringUtils.defaultString(DateUtility.changeDateTypeToChineseDate(t.getIssueDate())))
                        .updateTime(StringUtils.defaultString(
                                DateUtility.formatChineseDateTimeString(t.getUpdateTime(), false)))
                        .build());
            });
            rptcase.setRptCases(rptCases);
        }
        return rptcase;
    }
}
