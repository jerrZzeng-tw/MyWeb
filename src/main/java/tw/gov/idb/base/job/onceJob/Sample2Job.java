package tw.gov.idb.base.job.onceJob;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import tw.gov.idb.base.job.QuartzJob;
import tw.gov.idb.base.util.ExceptionUtility;

@Slf4j
public class Sample2Job extends QuartzJob {

    @Override
    protected void handler(JobExecutionContext context) throws JobExecutionException {
        String jobId = context.getJobDetail().getKey().getName();//排程JOBNAME
        try {
            log.info("Sample2Job開始 jobId:" + jobId);
            log.info("param:" + context.getJobDetail().getJobDataMap().get(PARAM_KEY));
            throw new RuntimeException(
                    "Sample2Job發生錯誤 param:" + context.getJobDetail().getJobDataMap().get(PARAM_KEY));
        } catch (Exception e) {
            log.error(ExceptionUtility.getStackTrace(e));
            throw new JobExecutionException(e.getMessage());
        }
    }
}
