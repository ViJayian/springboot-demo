package com.springbootdemo.boot.exception;

import com.springbootdemo.boot.base.BaseBootException;
import org.springframework.http.HttpStatus;

/**
 * 获取用户信息失败异常
 *
 * @author wangwenjie
 * @date 2019-12-23
 */
public class AccessException extends BaseBootException {
    private String message;
    private int code;

    public AccessException(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase());
        this.message = httpStatus.getReasonPhrase();
        this.code = httpStatus.value();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
