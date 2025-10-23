package com.shymoy.dto.response;

import lombok.Data;

/**
 * 用户信息VO
 * 用于返回给前端的用户信息，不包含密码等敏感信息
 * 
 * @Author: shymoy
 */
@Data
public class UserVO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像URL
     */
    private String avatar;
}

