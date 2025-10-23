package com.shymoy.util;

/**
 * 用户信息持有者
 * 使用 ThreadLocal 存储当前登录用户信息
 * 
 * @Author: shymoy
 */
public class UserHolder {

    private static final ThreadLocal<Long> USER_ID_HOLDER = new ThreadLocal<>();

    /**
     * 设置当前用户ID
     * 
     * @param userId 用户ID
     */
    public static void setUserId(Long userId) {
        USER_ID_HOLDER.set(userId);
    }

    /**
     * 获取当前用户ID
     * 
     * @return 用户ID，未登录返回null
     */
    public static Long getUserId() {
        return USER_ID_HOLDER.get();
    }

    /**
     * 移除当前用户ID
     * 必须在请求结束后调用，防止内存泄漏
     */
    public static void remove() {
        USER_ID_HOLDER.remove();
    }
}
