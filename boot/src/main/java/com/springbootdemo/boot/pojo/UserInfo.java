package com.springbootdemo.boot.pojo;

/**
 * @Description:
 * @Author: wangwenjie
 * @CreateTime: 2019-12-05 10:05
 */
public class UserInfo {
    private String username;
    private String usercode;
    private String role;
    private String department;

    public UserInfo(){}

    public UserInfo(String username, String usercode, String role, String department) {
        this.username = username;
        this.usercode = usercode;
        this.role = role;
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
