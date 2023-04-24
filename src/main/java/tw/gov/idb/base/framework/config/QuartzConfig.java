package tw.gov.idb.base.framework.config;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import tw.gov.idb.base.job.listener.UpdateJobStatusListener;
import tw.gov.idb.base.job.repeatJob.RepeatJob;

import javax.sql.DataSource;

@Configuration
public class QuartzConfig {
    @Autowired
    DataSource dataSource;
    public static final String COMMON_GROUP = "Common";
    @Autowired
    private UpdateJobStatusListener updateJobStatusListener;

    @Bean
    public SchedulerFactoryBean scheduler(CronTrigger repeatTrigger) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
        schedulerFactory.setTriggers(repeatTrigger);
        schedulerFactory.setDataSource(dataSource);
        // 每次更新排程時間
        schedulerFactory.setOverwriteExistingJobs(true);
        // 設定
        schedulerFactory.setGlobalJobListeners(updateJobStatusListener);
        return schedulerFactory;
    }

    /**
     * 範例排程 jobdetail
     *
     * @return
     */
    @Bean
    public JobDetailFactoryBean repeatJobDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(RepeatJob.class);
        jobDetailFactoryBean.setDescription("範例排程");
        jobDetailFactoryBean.setName("RepeatJob");
        jobDetailFactoryBean.setGroup(COMMON_GROUP);
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }

    /**
     * 範例排程 TRIGGER
     *
     * @param repeatJobDetail
     * @return
     */
    @Bean
    public CronTriggerFactoryBean repeatTrigger(JobDetail repeatJobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(repeatJobDetail);
        cronTriggerFactoryBean.setGroup(COMMON_GROUP);
        cronTriggerFactoryBean.setCronExpression("0 0/30 * * * ?"); //每30分鐘執行一次任務
        return cronTriggerFactoryBean;
    }
}
