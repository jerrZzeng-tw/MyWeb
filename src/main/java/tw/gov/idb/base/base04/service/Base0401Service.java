package tw.gov.idb.base.base04.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.idb.base.base04.cases.Base0401Case;
import tw.gov.idb.base.base04.cases.Base0401Case.AddAndUpdCase;
import tw.gov.idb.base.base04.cases.Base0401Case.Base0401qCase;
import tw.gov.idb.base.framework.config.BaseProperties;
import tw.gov.idb.base.framework.dao.UserDao;
import tw.gov.idb.base.framework.domain.User;
import tw.gov.idb.base.util.DateUtility;
import tw.gov.idb.base.util.PasswordUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class Base0401Service {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BaseProperties basePops;

    public boolean queryUserList(Base0401Case caseData) throws Exception {
        List<User> users = userDao.selectUsers(caseData.getId(), caseData.getName());
        List<Base0401qCase> base0401qCases = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(users)) {
            users.stream().forEach(t -> {
                base0401qCases.add(Base0401qCase.builder()
                        .id(t.getId())
                        .name(t.getName())
                        .pw(t.getPw())
                        .mail(t.getMail())
                        .empNo(t.getEmpNo())
                        .memo(t.getMemo())
                        .build());
            });
            caseData.setBase0401qCases(base0401qCases);
            return true;
        }
        return false;
    }

    public void enterAdd(Base0401Case caseData) {
        caseData.setStatus("add");
    }


    @Transactional(readOnly = true)
    public void enterUpd(Base0401Case caseData) {
        Optional<User> opt = userDao.seletOneUser(caseData.getBase0401qCases().get(caseData.getListIndex()).getId());
        if (opt.isPresent()) {
            caseData.setAddAndUpdCase(AddAndUpdCase.builder()
                    .id(opt.get().getId())
                    .name(opt.get().getName())
                    .mail(opt.get().getMail())
                    .empNo(opt.get().getEmpNo())
                    .memo(opt.get().getMemo())
                    .build());
        }
        caseData.setStatus("upd");
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertUser(Base0401Case caseData) throws Exception {
        caseData.setStatus("add");

        userDao.insertData(User.builder()
                .id(caseData.getAddAndUpdCase().getId())
                .name(caseData.getAddAndUpdCase().getName())
                .pw(PasswordUtility.hashSHA1(
                        StringUtils.trim(caseData.getAddAndUpdCase().getPw()) + basePops.getPwSalt()))//加密
                .mail(caseData.getAddAndUpdCase().getMail())
                .empNo(caseData.getAddAndUpdCase().getEmpNo())
                .memo(caseData.getAddAndUpdCase().getMemo())
                .updateTime(DateUtility.getNowWestDateTime(true))
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUser(Base0401Case caseData) throws Exception {
        caseData.setStatus("upd");

        //密碼沒輸入 就不要改
        String pw = StringUtils.isNotBlank(caseData.getAddAndUpdCase().getPw()) ? PasswordUtility.hashSHA1(
                StringUtils.trim(caseData.getAddAndUpdCase().getPw()) + basePops.getPwSalt()) : StringUtils.EMPTY;

        userDao.updateData(User.builder()
                .id(caseData.getAddAndUpdCase().getId())
                .name(caseData.getAddAndUpdCase().getName())
                .pw(pw)//加密
                .mail(caseData.getAddAndUpdCase().getMail())
                .empNo(caseData.getAddAndUpdCase().getEmpNo())
                .memo(caseData.getAddAndUpdCase().getMemo())
                .updateTime(DateUtility.getNowWestDateTime(true))
                .build());
    }

    public boolean isDuplicate(Base0401Case caseData) {
        return Optional.ofNullable(userDao.countUserId(caseData.getAddAndUpdCase().getId()))
                .map(t -> t > 0)
                .orElse(false);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Base0401Case caseData) throws Exception {
        userDao.deleteData(StringUtils.trim(caseData.getBase0401qCases().get(caseData.getListIndex()).getId()));
    }

}
