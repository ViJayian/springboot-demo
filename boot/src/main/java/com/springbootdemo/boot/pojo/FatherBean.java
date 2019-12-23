package com.springbootdemo.boot.pojo;

/**
 * spring注入
 *
 * @author wangwenjie
 * @date 2019-12-20
 */
public class FatherBean {
    private String name;

    public FatherBean() {
    }

    public FatherBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


