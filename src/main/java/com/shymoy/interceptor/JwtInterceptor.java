package com.shymoy.interceptor;

import com.shymoy.util.JwtUtil;
import com.shymoy.util.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JWT拦截器
 * 拦截请求，解析JWT Token，将用户ID存入ThreadLocal
 * 
 * @Author: shymoy
 */
@Slf4j
@Component
@AllArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler) {
        // 获取请求头中的 Token
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // 去掉 "Bearer " 前缀

            try {
                // 验证 Token 是否有效
                if (jwtUtil.validateToken(token)) {
                    // 从 Token 中获取用户ID
                    Long userId = jwtUtil.getUserIdFromToken(token);

                    // 存入 ThreadLocal
                    UserHolder.setUserId(userId);

                    log.debug("用户 {} 的请求已通过JWT验证", userId);
                } else {
                    log.warn("Token已过期或无效");
                }
            } catch (Exception e) {
                log.error("Token解析失败: {}", e.getMessage());
            }
        }

        // 放行请求（不阻止未登录用户访问）
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler, @Nullable Exception ex) {
        // 请求结束后清除 ThreadLocal，防止内存泄漏
        UserHolder.remove();
    }
}
