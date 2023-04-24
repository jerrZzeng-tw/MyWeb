package tw.gov.idb.base.framework.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import tw.gov.idb.base.framework.annotation.BackupData;
import tw.gov.idb.base.util.ExceptionUtility;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class BackDataAdvice {

    @Before("@annotation(tw.gov.idb.base.framework.annotation.BackupData)")
    public void doApLog(JoinPoint pointcut) {
        try {
            MethodSignature methodSig = (MethodSignature) pointcut.getSignature();
            Object targetObject = pointcut.getTarget();
            Method m = targetObject.getClass()
                    .getMethod(getBackupMethod(methodSig), methodSig.getMethod().getParameterTypes());
            m.invoke(targetObject, pointcut.getArgs());
        } catch (Exception e) {
            log.error("do backup data error : {}", ExceptionUtility.getStackTrace(e));

        }
    }

    public String getBackupMethod(MethodSignature methodSig) {
        BackupData ann = methodSig.getMethod().getAnnotation(BackupData.class);
        return ann.value();
    }
}
