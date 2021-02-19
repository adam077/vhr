package org.javaboy.vhr.utils;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedisUtils {
    /*
    redis todo
     */

    void do1() {
        Config config = new Config();
        // 使用单机Redis服务
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        // 创建Redisson客户端
        RedissonClient redisson = Redisson.create(config);
        do2(redisson);
        do3(redisson);
    }

    void do2(RedissonClient redisson) {
        /*
        Redisson将Redis中的字符串数据结构封装成了RBucket
         */
        RBucket<String> nameRBucket = redisson.getBucket("name");
        // 只设置value，key不过期
        nameRBucket.set("四哥");
        // 设置value和key的有效期
        nameRBucket.set("四哥", 30, TimeUnit.SECONDS);
        // 通过key获取value
        redisson.getBucket("name").get();

    }

    static class Student {
        int ID;
        String Name;
    }

    void do3(RedissonClient redisson) {
        /*
        Redisson将Redis中的字符串数据结构封装成了RBucket
         */
        Student tom = new Student();
        tom.ID = 1243;
        tom.Name = "胡哈哈";

        // 通过key获取RBucket对象实例
        RBucket<Student> studentRBucket = redisson.getBucket("student");
        // 设置value和有效期
        studentRBucket.set(tom, 300, TimeUnit.SECONDS);
        // 通过key获取value
        redisson.getBucket("student").get();

    }


}
