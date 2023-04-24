package tw.gov.idb.base.job.repeatJob;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import tw.gov.idb.base.job.QuartzJob;
import tw.gov.idb.base.util.ExceptionUtility;

@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class RepeatJob extends QuartzJob {

    @Override
    protected void handler(JobExecutionContext context) throws JobExecutionException {
        String jobId = context.getJobDetail().getKey().getName();//排程JOBNAME
        try {
            log.info("RepeatJob開始 jobId:" + jobId);
            Thread.sleep(3000);
            log.info("RepeatJob結束 jobId:" + jobId);
        } catch (Exception e) {
            log.error(ExceptionUtility.getStackTrace(e));
            throw new JobExecutionException(e.getMessage());
        }
    }
}
