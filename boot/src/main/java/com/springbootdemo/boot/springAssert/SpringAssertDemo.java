package com.springbootdemo.boot.springAssert;

import io.jsonwebtoken.lang.Assert;

/**
 * spring assert使用
 *
 * @author wangwenjie
 * @date 2019-12-20
 */
public class SpringAssertDemo {
    public static void f(Object a) {
        Assert.notNull(a, "不能为空");
    }

    public static void main(String[] args) {
        f(null);
    }
}
