package tw.gov.idb.base.framework;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Component
public class CacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return target.getClass().getSimpleName() + "#" + method.getName() + "(" + StringUtils.arrayToDelimitedString(
                params, ",") + ")";
    }

}
