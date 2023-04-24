package tw.gov.idb.base.job.onceJob;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import tw.gov.idb.base.job.QuartzJob;
import tw.gov.idb.base.util.ExceptionUtility;

@Slf4j
public class Sample1Job extends QuartzJob {

    @Override
    protected void handler(JobExecutionContext context) throws JobExecutionException {
        String jobId = context.getJobDetail().getKey().getName();//排程JOBNAME
        try {
            log.info("Sample1Job開始 jobId:" + jobId);
            log.info("param:" + context.getJobDetail().getJobDataMap().get(PARAM_KEY));
            //            throw new RuntimeException("OnceJob發生錯誤");
            Thread.sleep(5 * 1000);
            context.getJobDetail()
                    .getJobDataMap()
                    .put(RESULT_KEY, "Sample1Job結束 param:" + context.getJobDetail().getJobDataMap().get(PARAM_KEY));
            log.info("Sample1Job結束 jobId:" + jobId);
        } catch (Exception e) {
            log.error(ExceptionUtility.getStackTrace(e));
            throw new JobExecutionException(e.getMessage());
        }
    }
}
