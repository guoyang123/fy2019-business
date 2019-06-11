package com.neuedu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理对象生成类
 * */
public class DynamicProxy implements InvocationHandler{

      private Object target;

      public DynamicProxy(Object target){
          this.target=target;
      }

      /**
       * 生成代理对象
       *
       * */
      public Object  getProxyInstance(){

          return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                  target.getClass().getInterfaces(),this);
      }




    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long start=System.currentTimeMillis();
        System.out.println(method.invoke(target,args));
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        return null;
    }
}
