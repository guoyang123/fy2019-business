package com.neuedu.interceptors;


import com.neuedu.common.ResponseCode;
import com.neuedu.common.RoleEnum;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.User;
import com.neuedu.utils.Const;
import com.neuedu.utils.JsonUtils;
import com.neuedu.utils.RedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *  拦截所有请求
 * */
@Component
public class CommonInterceptor implements HandlerInterceptor{



    @Autowired
    RedisApi redisApi;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnsupportedEncodingException{


        Cookie[] cookies=request.getCookies();
        if(cookies==null||cookies.length==0){
            return true;
        }

        for(Cookie c:cookies){
            if(c.getName().equals(Const.CURRENT_USER)){
                String token=c.getValue();//用户存在redis中的key
                String userjson=redisApi.get(token);
                if(userjson!=null){
                   Long expire= redisApi.expire(userjson,1800);
                    System.out.println("userjson"+userjson+"  "+expire);

                }

            }
        }

        return true;
    }




    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("===postHandle===");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("===afterCompletion===");
    }

}
