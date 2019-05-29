package com.neuedu.interceptors;

import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.User;
import com.neuedu.utils.Const;
import com.neuedu.utils.JsonUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * 拦截前台需要登录用户的请求
 * */
@Component
public class PortalAuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        HttpSession session=request.getSession();
        User user=(User) session.getAttribute(Const.CURRENT_USER);
        if(user==null){
             response.reset();
            try {
                PrintWriter printWriter=response.getWriter();
                ServerResponse serverResponse= ServerResponse.serverResponseByError(ResponseCode.NOT_LOGIN,"未登录");
                String json= JsonUtils.obj2String(serverResponse);
                printWriter.write(json);
                printWriter.flush();
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


             return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("=portal==postHandle===");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("=portal==afterCompletion===");
    }
}
