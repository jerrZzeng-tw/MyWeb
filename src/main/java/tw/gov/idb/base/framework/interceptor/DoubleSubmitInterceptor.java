package tw.gov.idb.base.framework.interceptor;

import tw.gov.idb.base.framework.websocket.WebSocketMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class DoubleSubmitInterceptor implements HandlerInterceptor {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        String user = null;
        HttpSession session = request.getSession();
        if (session != null) {
            user = session.getId();
        }

        // 改用 WebSocket 處理畫面 Unlock
        if (user != null && messagingTemplate != null) {
            messagingTemplate.convertAndSendToUser(user, "/queue/unlock", new WebSocketMessage("Unlock"));
        }
    }
}
