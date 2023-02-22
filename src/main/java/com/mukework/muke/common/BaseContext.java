package com.mukework.muke.common;

/**
 * 将登录用户的id存储到ThreadLocal中 在公用字段中能够使用
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
