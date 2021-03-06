package com.neuedu.controller;

import com.neuedu.common.RedisPool;
import com.neuedu.dao.CartMapper;
import com.neuedu.pojo.Cart;
import com.neuedu.service.IUserService;
import com.neuedu.utils.RedisApi;
import com.neuedu.utils.ShardedRedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    CartMapper cartMapper;
    @RequestMapping("/login/{username}/{password}")
    public List<Cart> testrestful(@PathVariable("username") String username, @PathVariable("password")String password){

        List<Cart> userList= cartMapper.selectAll();
        return userList;

    }

    @RequestMapping("/testlog")
    public List<Cart> testlog(){

        List<Cart> userList= cartMapper.selectAll();
        int a=3/0;
        return userList;

    }


    @Value("${server.port}")
    int port;
    @RequestMapping("/test")
    public String test(){

        return  port+"";

    }


    @Autowired
    RedisApi redisApi;

    @RequestMapping("/testjedis")
    public  String  testJedis(){

       redisApi.set("java","jsp");
       return redisApi.get("java");
    }


    @Autowired
    ShardedRedisApi shardRedisApi;

    @RequestMapping("/testshardedjedis")
    public  String  testShardedJedis(){

        for(int i=0;i<20;i++){
            shardRedisApi.set("key"+i,"value"+i);
        }

        return shardRedisApi.get("key1");
    }


    @Autowired
    IUserService userService;
    @RequestMapping("/testaop")
    public  String  testAop(){


        return userService.testAOP("xxxx");
    }

}
