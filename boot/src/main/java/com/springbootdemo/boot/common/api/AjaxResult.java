package com.springbootdemo.boot.common.api;

/**
 * @Description: 返回结果集
 * @Author: wangwenjie
 * @CreateTime: 2019-12-05 09:47
 */
public class AjaxResult<T> {
    private Integer code;
    private String message;

    private T data;

    private AjaxResult(){}


    private AjaxResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private AjaxResult(Integer code, String message) {
        this(code, message, null);
    }

    public static <T> AjaxResult<T> success(T data) {
        return success(ResponseCode.SUCCESS, data);
    }

    public static <T> AjaxResult<T> success(String message, T data) {
        return new AjaxResult<T>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static <T> AjaxResult<T> error() {
        return error(ResponseCode.ERROR);
    }

    private static <T> AjaxResult<T> success(ResponseCode responseCode, T data) {
        return new AjaxResult<T>(responseCode.getCode(), responseCode.getMessage(), data);
    }

    private static <T> AjaxResult<T> error(ResponseCode responseCode) {
        return new AjaxResult<T>(responseCode.getCode(), responseCode.getMessage());
    }


    public static <T> AjaxResult<T> unAccess() {
        return new AjaxResult<T>(ResponseCode.UNACCESS.getCode(), ResponseCode.UNACCESS.getMessage());
    }

    public static <T> AjaxResult<T> accessExpired() {
        return new AjaxResult<T>(ResponseCode.ACCESSEXPIRED.getCode(), ResponseCode.ACCESSEXPIRED.getMessage());
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
