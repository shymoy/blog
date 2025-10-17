package com.shymoy.exception;

/**
 * 业务异常类
 *
 * @Author: shymoy
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}