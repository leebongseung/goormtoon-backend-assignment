package goorm.tricount.interceptor;

import goorm.tricount.exception.users.ForbiddenException;
import goorm.tricount.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheck implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("HandlerInterceptor.preHandle");
        // 세션에 로그인된 사용자인지 확인하는 로직
        HttpSession session = request.getSession(false);

        // 세션이 없거나, 있는데 세션의 이름이 loginMember가 아닐 경우
        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
            throw new ForbiddenException();
        }
        log.info("sessiond id ={}", session.getAttribute(SessionConst.LOGIN_MEMBER));
        // 리턴은 뭘 해주지
        // 시큐리티????????? 아규먼트 리졸버를 사용한다..
        // todo : 스레드 로컬 써봐라!
        return true;
    }
}
