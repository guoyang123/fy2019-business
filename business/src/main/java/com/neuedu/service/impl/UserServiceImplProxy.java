package com.neuedu.service.impl;
/**
 * CGLIB
 * 动态代理 为目标对象动态生成一个代理对象；目标对象必须要实现接口
 *
 * */
public class UserServiceImplProxy extends  UserServiceImpl {

    @Override
    public String testAOP(String param) {
        Long start=System.currentTimeMillis();
        String result=super.testAOP(param);
        Long end= System.currentTimeMillis();
        System.out.println(end-start);
        return result;
    }

    public static void main(String[] args) {
        UserServiceImplProxy proxy=new UserServiceImplProxy();
        proxy.testAOP("zhangsan");
    }

}
