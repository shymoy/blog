package com.shymoy.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回结果封装类
 *
 * @Author: shymoy
 */
@Data
public class Result<T> {

    private Integer code; //状态码
    private T data; //数据
    private String msg; //消息

    // 成功
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    // 失败
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }
}



