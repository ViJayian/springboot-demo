package com.springbootdemo.boot.base;

/**
 * 异常基类
 *
 * @author wangwenjie
 * @date 2019-12-23
 */
public class BaseBootException extends Exception {
    public BaseBootException(String message) {
        super(message);
    }

    public BaseBootException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseBootException() {
        super();
    }
}
