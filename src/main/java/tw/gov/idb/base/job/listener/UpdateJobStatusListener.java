package tw.gov.idb.base.job.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tw.gov.idb.base.dao.ScheduleDao;
import tw.gov.idb.base.domain.Schedule;
import tw.gov.idb.base.enums.JobStatus;
import tw.gov.idb.base.job.QuartzJob;
import tw.gov.idb.base.util.DateUtility;


@Component
@Slf4j
public class UpdateJobStatusListener implements JobListener {
    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public String getName() {
        return "UpdateJobStatusListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobId = context.getJobDetail().getKey().getName();
        scheduleDao.updateJob(Schedule.builder()
                .jobId(jobId)
                .exeStart(DateUtility.getNowWestDateTime(true))
                .status(JobStatus.EXECUTING.getCode())
                .build());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        String jobId = context.getJobDetail().getKey().getName();
        if (jobException != null) {
            scheduleDao.updateJob(Schedule.builder()
                    .jobId(jobId)
                    .exeEnd(DateUtility.getNowWestDateTime(true))
                    .status(JobStatus.FAILURE.getCode())
                    .result(jobException.getMessage())
                    .build());
        } else {
            scheduleDao.updateJob(Schedule.builder()
                    .jobId(jobId)
                    .exeEnd(DateUtility.getNowWestDateTime(true))
                    .status(JobStatus.SUCCESS.getCode())
                    .result((String) context.getJobDetail().getJobDataMap().get(QuartzJob.RESULT_KEY))
                    .build());
        }
    }
}
