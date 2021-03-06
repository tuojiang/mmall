package com.mmall.util;

import com.mmall.common.RedisPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @program: mmall
 * @Date: 2018/8/14
 * @Author: chandler
 * @Description:
 */
@Slf4j
public class RedisPoolUtil {
    /**
     * 设置key的有效期
     * @param key
     * @param exTime
     * @return
     */
    public static Long expire(String key,int exTime){
        Jedis jedis=null;
        Long result=null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.expire(key,exTime);
        }catch (Exception e){
            log.error("expire key:{} error",key,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.retunrResource(jedis);
        return result;
    }

    /**
     * 设置key的值和有效期
     * @param key
     * @param value
     * @param exTime
     * @return
     */
    public static String setEx(String key,String value,int exTime){
        Jedis jedis=null;
        String result=null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.setex(key,exTime,value);
        }catch (Exception e){
            log.error("expire key:{} value:{} error",key,value,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.retunrResource(jedis);
        return result;
    }
    /**
     * 获取key对应的值
     * @param key
     * @return
     */
    public static String get(String key){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error",key,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.retunrResource(jedis);
        return result;
    }

    /**
     * 修改key中的值
     * @param key
     * @param value
     * @return
     */
    public static String set(String key,String value){
        Jedis jedis=null;
        String result=null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.set(key,value);
        }catch (Exception e){
            log.error("set key:{} value:{} error",key,value,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.retunrResource(jedis);
        return result;
    }

    /**
     * 删除某个key值
     * @param key
     * @return
     */
    public static Long del(String key){
        Jedis jedis=null;
        Long result=null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        }catch (Exception e){
            log.error("del key:{}error",key,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.retunrResource(jedis);
        return result;
    }

    public static void main(String[] args) {
        Jedis jedis = RedisPool.getJedis();

        RedisPoolUtil.set("keyTest","value");

        String value = RedisPoolUtil.get("keyTest");

        RedisPoolUtil.setEx("keyex","valueex",60*10);

        RedisPoolUtil.expire("keyTest",60*20);

        RedisPoolUtil.del("keyTest");


    }
}
