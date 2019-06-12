package com.neuedu.utils;

import com.neuedu.common.ShardedRedisPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;

@Component
public class ShardedRedisApi {


    @Autowired
    ShardedRedisPool shardedRedisPool;

    /**
     * 字符串
     * */

    public String  set(String key,String value){

        ShardedJedis jedis=shardedRedisPool.getJedis();
        String result=null;
        try{
            result=jedis.set(key, value);
            shardedRedisPool.returnJedis(jedis);
        }catch (Exception e){
            shardedRedisPool.returnBrokenResource(jedis);
        }
     return result;
    }

    public String  get(String key){


        ShardedJedis jedis=shardedRedisPool.getJedis();
        String result=null;
        try{
            result=jedis.get(key);
            shardedRedisPool.returnJedis(jedis);
        }catch (Exception e){
            shardedRedisPool.returnBrokenResource(jedis);
        }
        return result;
    }

    public String  setex(String key,int timeout,String value){

        ShardedJedis jedis=shardedRedisPool.getJedis();
        String result=null;
        try{
            result=jedis.setex(key,timeout, value);
            shardedRedisPool.returnJedis(jedis);
        }catch (Exception e){
            shardedRedisPool.returnBrokenResource(jedis);
        }
        return result;
    }


    public Long  expire(String key,int timeout){

        ShardedJedis jedis=shardedRedisPool.getJedis();

        Long result=null;
        try{
            result=jedis.expire(key,timeout);
            shardedRedisPool.returnJedis(jedis);
        }catch (Exception e){
            shardedRedisPool.returnBrokenResource(jedis);
        }
        return result;
    }




}
