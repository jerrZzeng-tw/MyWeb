package tw.gov.idb.base.job.service;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.gov.idb.base.dao.ScheduleDao;
import tw.gov.idb.base.domain.Schedule;
import tw.gov.idb.base.enums.JobStatus;
import tw.gov.idb.base.framework.config.QuartzConfig;
import tw.gov.idb.base.job.JobParam;
import tw.gov.idb.base.job.QuartzJob;
import tw.gov.idb.base.job.util.CronExpressionUtility;
import tw.gov.idb.base.util.DateUtility;

import java.util.UUID;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
@Transactional(rollbackFor = Exception.class)
public class JobService {
    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    @Autowired
    private ScheduleDao scheduleDao;

    /**
     * 新增排程,使用預設群組
     *
     * @param param    排程參數
     * @param jobClass 排程執行的class
     * @return 排程ID
     * @throws SchedulerException
     */
    public String addJob(JobParam param, Class<? extends QuartzJob> jobClass) throws SchedulerException {
        return addJob(param, jobClass, QuartzConfig.COMMON_GROUP);
    }

    /**
     * 新增排程,指定群組
     *
     * @param param    排程參數
     * @param jobClass 排程執行的class
     * @param group    排程群組
     * @return 排程ID
     * @throws SchedulerException
     */
    public String addJob(JobParam param, Class<? extends QuartzJob> jobClass, String group) throws SchedulerException {
        String jobId = UUID.randomUUID().toString();
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail jobDetail = newJob(jobClass).withIdentity(jobId, group).build();

        if (param.getJobParamMap() != null) {
            param.getJobParamMap().forEach((key, val) -> {
                jobDetail.getJobDataMap().put(key, val);
            });
        }
        // 加入quartz排程
        String cronExpression =
                CronExpressionUtility.generateCronExr(param.getYear(), param.getDayOfWeek(), param.getMonth(),
                        param.getDayOfMonth(), param.getHours(), param.getMinutes(), param.getSeconds());
        Trigger trigger = newTrigger().withIdentity(jobId, group)
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing())
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        // 新增排程監控資料
        scheduleDao.scheduleJob(Schedule.builder()
                .jobId(jobId)
                .jobName(param.getJobName())
                .param(param.getJobParamMap().get(QuartzJob.PARAM_KEY))
                .description(param.getDescription())
                .scheduleTime(DateUtility.parseDateToWestDateTime(trigger.getNextFireTime(), true))
                .status(JobStatus.WAITING.getCode())
                .addId(param.getAddId())
                .addTime(DateUtility.getNowWestDateTime(true))
                .build());
        return jobId;
    }

    /**
     * 刪除排程,預設群組
     *
     * @param jobId    排程ID
     * @param cancelId 刪除人員ID
     * @throws SchedulerException
     */
    public void delete(String jobId, String cancelId) throws SchedulerException {
        delete(jobId, QuartzConfig.COMMON_GROUP, cancelId);
    }

    /**
     * 刪除排程,指定群組
     *
     * @param jobId    排程ID
     * @param group    排成群組
     * @param cancelId 刪除人員ID
     * @throws SchedulerException
     */
    public void delete(String jobId, String group, String cancelId) throws SchedulerException {
        // 取消quartz排程
        schedulerFactory.getScheduler().deleteJob(new JobKey(jobId, group));
        // 更新排程監控資料
        scheduleDao.updateJob(Schedule.builder()
                .jobId(jobId)
                .status(JobStatus.CANCELED.getCode())
                .cancelId(cancelId)
                .cancelTime(DateUtility.getNowWestDateTime(true))
                .build());
    }

}
