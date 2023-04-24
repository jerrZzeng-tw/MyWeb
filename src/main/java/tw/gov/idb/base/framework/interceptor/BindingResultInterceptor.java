package tw.gov.idb.base.framework.interceptor;

import tw.gov.idb.base.framework.tag.func.ValidationMessageTag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class BindingResultInterceptor implements HandlerInterceptor {


    @Autowired
    private MessageSource messageSource;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        List<String> list = new ArrayList<String>();

        if (modelAndView != null && modelAndView.getModel() != null) {
            Set<String> keySet = modelAndView.getModel().keySet();

            keySet.stream()
                    .filter(key -> StringUtils.startsWith(key, BindingResult.MODEL_KEY_PREFIX))
                    .map(key -> (BindingResult) modelAndView.getModel().get(key))
                    .forEach(bindingResult -> bindingResult.getAllErrors().forEach(objectError -> {
                        String message = messageSource.getMessage(objectError, null);
                        if (StringUtils.isBlank(message))
                            message = objectError.getCode();
                        list.add(message);
                    }));
        }

        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute(ValidationMessageTag.VALIDATION_MESSAGE_SESSION_KEY);
            session.setAttribute(ValidationMessageTag.VALIDATION_MESSAGE_SESSION_KEY, list);
        }
    }
}
