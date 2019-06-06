package com.neuedu.listener;

import com.neuedu.utils.RedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

//监听项目的启动
public class AppClosedListener implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    RedisApi redisApi;
    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
       // System.out.println("======项目关闭====");
        redisApi.clear();

    }
}
