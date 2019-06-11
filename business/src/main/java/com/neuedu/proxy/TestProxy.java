package com.neuedu.proxy;

import com.neuedu.service.IUserService;
import com.neuedu.service.impl.UserServiceImpl;

public class TestProxy {

    public static void main(String[] args) {


        UserServiceImpl userService=new UserServiceImpl();
        DynamicProxy dynamicProxy=new DynamicProxy(userService);
        IUserService userService1=(IUserService) dynamicProxy.getProxyInstance();
        userService1.testAOP("haha");

    }
}
