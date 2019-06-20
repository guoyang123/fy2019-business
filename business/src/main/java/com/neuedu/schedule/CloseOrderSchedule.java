package com.neuedu.schedule;

import com.google.common.collect.Maps;
import com.neuedu.service.IOrderService;
import com.neuedu.utils.Const;
import com.neuedu.utils.ShardedRedisApi;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class CloseOrderSchedule {


    @Value("${order.close.timeout}")
    private  int orderTimeout;
    @Value("${lock.timeout}")
    private int lockTimeout;

    @Autowired
    IOrderService orderService;

    @Autowired
    ShardedRedisApi shardedRedisApi;

    @Scheduled(cron = "0/10 * * * * ?")
    public  void   closeOrder(){
       //step1:获取redis的锁
      Long result=shardedRedisApi.setnx(Const.LOCK_KEY,String.valueOf(System.currentTimeMillis()+lockTimeout));
      if(result==1){ //获取redis锁
         //设置锁的过期时间
          close();
      }else{
          //是否有权利获取锁
          String value=shardedRedisApi.get(Const.LOCK_KEY);
          if(value==null||(value!=null&& System.currentTimeMillis()>Long.parseLong(value))){
              //当前线程有权利获取锁      getset
             String oldValue=shardedRedisApi.getset(Const.LOCK_KEY,String.valueOf(System.currentTimeMillis()+lockTimeout));
             if(value.equals(oldValue)){//同一个请求
                 close();
             }else{
                 System.out.println("没有获取到锁");
             }
          }
      }
    }

    public  void close(){
        shardedRedisApi.expire(Const.LOCK_KEY,20);
        //step1:
        Date closeOrderTime=DateUtils.addHours(new Date(),-orderTimeout);
        orderService.closeOrder(com.neuedu.utils.DateUtils.dateToStr(closeOrderTime));
        shardedRedisApi.del(Const.LOCK_KEY);
    }

}
