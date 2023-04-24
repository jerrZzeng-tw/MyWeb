package tw.gov.idb.base.base03.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.idb.base.base03.cases.Base0301Case;
import tw.gov.idb.base.base03.cases.Base0301Case.AddAndQueryCase;
import tw.gov.idb.base.base03.cases.Base0301Case.Base0301qCase;
import tw.gov.idb.base.dao.ScheduleDao;
import tw.gov.idb.base.domain.Schedule;
import tw.gov.idb.base.framework.domain.UserBean;
import tw.gov.idb.base.job.JobParam;
import tw.gov.idb.base.job.QuartzJob;
import tw.gov.idb.base.job.service.JobService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class Base0301Service {
    @Autowired
    private ScheduleDao scheduleDao;
    @Autowired
    private JobService jobService;
    @Autowired
    private UserBean userBean;

    public boolean queryList(Base0301Case caseData) throws Exception {
        List<Schedule> schedules =
                scheduleDao.selectSchedules(caseData.getJobName(), caseData.getAddId(), caseData.getStatus());
        List<Base0301qCase> base0301qCases = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(schedules)) {
            schedules.stream().forEach(t -> {
                base0301qCases.add(Base0301qCase.builder()
                        .jobId(t.getJobId())
                        .jobName(t.getJobName())
                        .scheduleTime(t.getScheduleTime())
                        .exeStart(t.getExeStart())
                        .exeEnd(t.getExeEnd())
                        .status(t.getStatus())
                        .result(t.getResult())
                        .build());
            });
            caseData.setBase0301qCases(base0301qCases);
            return true;
        }
        return false;
    }

    public void enterAdd(Base0301Case caseData) {
        caseData.setCondition("add");
    }


    @Transactional(readOnly = true)
    public void enterQuery(Base0301Case caseData) {
        caseData.setCondition("query");
        Optional<Schedule> opt =
                scheduleDao.seletOneSchedule(caseData.getBase0301qCases().get(caseData.getListIndex()).getJobId());
        if (opt.isPresent()) {
            caseData.setAddAndQueryCase(AddAndQueryCase.builder()
                    .jobId(opt.get().getJobId())
                    .jobName(opt.get().getJobName())
                    .description(opt.get().getDescription())
                    .scheduleTime(opt.get().getScheduleTime())
                    .param(opt.get().getParam())
                    .exeStart(opt.get().getExeStart())
                    .exeEnd(opt.get().getExeEnd())
                    .status(opt.get().getStatus())
                    .result(opt.get().getResult())
                    .addId(opt.get().getAddId())
                    .addTime(opt.get().getAddTime())
                    .cancelId(opt.get().getCancelId())
                    .cancelTime(opt.get().getCancelTime())
                    .build());
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void insertSchedule(Base0301Case caseData) throws Exception {
        String className =
                StringUtils.join("tw.gov.idb.base.job.onceJob.", caseData.getAddAndQueryCase().getJobClassName());
        Class<?> clazz = Class.forName(className);
        // 取得Job排程參數
        JobParam jobParam =
                getJobParam(caseData.getAddAndQueryCase().getScheduleTime(), caseData.getAddAndQueryCase().getJobName(),
                        caseData.getAddAndQueryCase().getDescription(), userBean.getUserId());
        // 設定Job執行時所需資訊
        jobParam.getJobParamMap().put(QuartzJob.PARAM_KEY, caseData.getAddAndQueryCase().getParam());
        // 排定Job
        jobService.addJob(jobParam, (Class<? extends QuartzJob>) clazz);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteSchedule(Base0301Case caseData) throws Exception {
        jobService.delete(caseData.getBase0301qCases().get(caseData.getListIndex()).getJobId(), userBean.getUserId());
    }

    JobParam getJobParam(String scheduleTime, String jobName, String description, String addId) {
        return JobParam.builder()
                .year(StringUtils.substring(scheduleTime, 0, 4))
                .month(StringUtils.substring(scheduleTime, 4, 6))
                .dayOfMonth(StringUtils.substring(scheduleTime, 6, 8))
                .hours(StringUtils.substring(scheduleTime, 8, 10))
                .minutes(StringUtils.substring(scheduleTime, 10, 12))
                .seconds(StringUtils.substring(scheduleTime, 12, 14))
                .jobName(jobName)
                .description(description)
                .addId(addId)
                .jobParamMap(new HashMap<>())
                .build();
    }

}
