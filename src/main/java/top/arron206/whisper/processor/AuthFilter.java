package top.arron206.whisper.processor;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import top.arron206.whisper.pojo.MyToken;
import top.arron206.whisper.pojo.TokenManager;
import top.arron206.whisper.vo.AuthMessage;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthFilter extends BasicHttpAuthenticationFilter {


    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        return ((HttpServletRequest) request).getHeader("Authorization") != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response){
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");
        MyToken token = new MyToken(authorization.substring(7));
        getSubject(request, response).login(token);
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            if (isLoginAttempt(request, response)) {
                executeLogin(request, response);
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response){
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        AuthMessage authMessage = AuthMessage.getAuthMessage(
                "请先登录", "", "login /auth/login",
                "/auth/login", "login", "application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
        String ret = JSON.toJSONString(authMessage);
        httpServletResponse.setStatus(401);
        try {
            response.getWriter().write(ret);
            response.getWriter().close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
