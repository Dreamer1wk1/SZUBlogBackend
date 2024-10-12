package com.dreamer.demoproject;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public  void testThreadLocalSetAndGet(){
        //提供ThreadLocal对象
        ThreadLocal tl=new ThreadLocal();
        //开启两个线程
        new Thread(()->{
            tl.set("小明");
            System.out.println(Thread.currentThread().getName()+" : "+tl.get());
        },"蓝色").start();
        new Thread(()->{
            tl.set("小蓝");
            System.out.println(Thread.currentThread().getName()+" : "+tl.get());
        },"绿色").start();
    }
}
