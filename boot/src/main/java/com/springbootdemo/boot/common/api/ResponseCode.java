package com.springbootdemo.boot.common.api;

public enum ResponseCode {
    SUCCESS(200, "操作成功"),
    UNACCESS(403, "令牌校验不通过"),
    ACCESSEXPIRED(405, "令牌过期"),
    ERROR(500, "操作失败");

    private int code;
    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
