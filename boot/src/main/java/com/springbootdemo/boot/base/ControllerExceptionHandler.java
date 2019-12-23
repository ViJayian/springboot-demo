package com.springbootdemo.boot.base;

import com.springbootdemo.boot.common.api.AjaxResult;
import com.springbootdemo.boot.exception.AccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * controller全局异常处理
 *
 * @author wangwenjie
 * @date 2019-12-23
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(AccessException.class)
    public AjaxResult accessError(AccessException e) {
        log.info("获取用户信息失败");//获取用户信息失败
        log.info(e.getMessage());//获取用户信息异常
        return AjaxResult.unAccess();
    }

    @ExceptionHandler(RuntimeException.class)
    public AjaxResult runtimeException() {
        log.info("运行时异常");
        return AjaxResult.error();
    }
}
