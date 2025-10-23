package com.shymoy.util;

import com.shymoy.exception.BusinessException;
import org.springframework.stereotype.Component;

/**
 * 用户上下文工具类
 * 从 ThreadLocal 中获取当前登录用户信息
 * 
 * @Author: shymoy
 */
@Component
public class UserContext {

    /**
     * 获取当前登录用户ID（必须登录）
     * 
     * @return 用户ID
     * @throws BusinessException 未登录时抛出异常
     */
    public Long getCurrentUserId() {
        Long userId = UserHolder.getUserId();
        if (userId == null) {
            throw new BusinessException("未登录或登录已过期");
        }
        return userId;
    }

    /**
     * 获取当前登录用户ID（允许为空）
     * 用于不需要登录也能访问的接口
     * 
     * @return 用户ID，未登录返回null
     */
    public Long getCurrentUserIdOrNull() {
        return UserHolder.getUserId();
    }
}
