package tw.gov.idb.base.framework.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tw.gov.idb.base.framework.annotation.ApLog;
import tw.gov.idb.base.framework.cases.BaseCase;
import tw.gov.idb.base.framework.config.BaseProperties;
import tw.gov.idb.base.framework.domain.Aplog;
import tw.gov.idb.base.framework.domain.FrameworkUserInfoBean;
import tw.gov.idb.base.framework.helper.HttpHelper;
import tw.gov.idb.base.framework.helper.UserSessionHelper;
import tw.gov.idb.base.framework.services.LogService;
import tw.gov.idb.base.util.BeanUtility;
import tw.gov.idb.base.util.DateUtility;
import tw.gov.idb.base.util.ExceptionUtility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Aspect
@Component
public class ApLogAdvice {
    @Autowired
    private BaseProperties basePops;
    @Autowired
    private LogService logService;

    @Before("@annotation(tw.gov.idb.base.framework.annotation.ApLog)")
    public void doApLog(JoinPoint pointcut) {
        try {
            //            log.info("aplog");
            HttpServletRequest request = HttpHelper.getHttpRequest();
            FrameworkUserInfoBean fuib = UserSessionHelper.getFrameworkUserData(request);
            //            MethodSignature methodSig = (MethodSignature) pointcut.getSignature();
            //            Method method = pointcut.getTarget()
            //                    .getClass()
            //                    .getMethod(pointcut.getSignature().getName(), methodSig.getMethod().getParameterTypes());

            Aplog aplog = null;
            // 使用者登入頁面
            if ("/Login.action".equals(request.getServletPath())) {
                aplog = Aplog.builder()
                        .userid(StringUtils.defaultString((String) request.getParameter("ID")))
                        .page("LOGIN")
                        .url(request.getServletPath())
                        .accessTime(DateUtility.getNowWestDateTime(true))
                        .build();
            }
            // 已經登入後的頁面(session中有FrameworkUserInfoBean)
            else {
                aplog = Aplog.builder()
                        .userid(fuib.getUserId())
                        .page(fuib.getProgId())
                        .url(request.getServletPath())
                        .accessTime(DateUtility.getNowWestDateTime(true))
                        .build();
            }
            // 系統設定需要紀錄輸入參數
            if (basePops.isLogModel()) {
                MethodSignature methodSig = (MethodSignature) pointcut.getSignature();
                BaseCase model = getCase(pointcut);
                String[] logField = getLogField(methodSig);
                // 本次方法有傳入輸入參數才需紀錄
                if (model != null && logField != null && logField.length > 0) {
                    Map<String, Object> data = new HashMap<>();
                    Arrays.stream(logField)
                            .forEach(field -> data.put(field, BeanUtility.getBeanProperty(model, field)));
                    aplog.setData(BeanUtility.objectToJson(data));
                }
            }
            logService.doAplog(aplog);
        } catch (Exception e) {
            log.error("do aplog error : {}", ExceptionUtility.getStackTrace(e));

        }

    }

    public String[] getLogField(MethodSignature methodSig) {
        ApLog ann = methodSig.getMethod().getAnnotation(ApLog.class);
        return (ann != null) ? ann.fields() : null;
    }

    public BaseCase getCase(JoinPoint pointcut) {
        Optional<Object> ob = Arrays.stream(pointcut.getArgs()).filter(t -> t instanceof BaseCase).findFirst();
        return (BaseCase) ob.orElse(null);
    }
}
