package com.neuedu;

import com.google.common.collect.Lists;
import com.neuedu.interceptors.AdminAuthroityInterceptor;
import com.neuedu.interceptors.PortalAuthorityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootConfiguration
public class MySpringBootConfig implements WebMvcConfigurer {


    // 拦截后台请求,验证用户是否登录
    @Autowired
     AdminAuthroityInterceptor adminAuthroityInterceptor;

    @Autowired
    PortalAuthorityInterceptor portalAuthorityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        // manage/category  manage/product manage/order

        registry.addInterceptor(adminAuthroityInterceptor)
                .addPathPatterns("/manage/**");

        List<String>   addPatterns= Lists.newArrayList();
        addPatterns.add("/user/**");
        addPatterns.add("/cart/**");
        addPatterns.add("/order/**");
        addPatterns.add("/shipping/**");

        List<String> excludePathPaterns=Lists.newArrayList();
        excludePathPaterns.add("/user/register.do");
        excludePathPaterns.add("/user/login/**");
        excludePathPaterns.add("/user/forget_get_question/**");
        excludePathPaterns.add("/user/forget_check_answer.do");
        excludePathPaterns.add("/user/forget_reset_password.do");
        excludePathPaterns.add("/order/callback.do");


        registry.addInterceptor(portalAuthorityInterceptor).addPathPatterns(addPatterns)
                .excludePathPatterns(excludePathPaterns);

    }
}
