package tw.gov.idb.base.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Quartz job 一定要繼承這個才會自動注入
 */
public abstract class QuartzJob extends QuartzJobBean {

    public static final String PARAM_KEY = "paramKey";
    public static final String RESULT_KEY = "resultKey";

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        handler(context);
    }

    protected abstract void handler(JobExecutionContext context) throws JobExecutionException;

}
