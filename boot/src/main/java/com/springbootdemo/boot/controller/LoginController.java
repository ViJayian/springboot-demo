package com.springbootdemo.boot.controller;

import com.springbootdemo.boot.base.BaseController;
import com.springbootdemo.boot.common.api.AjaxResult;
import com.springbootdemo.boot.common.utils.JwtTokenUtils;
import com.springbootdemo.boot.pojo.SpringBean;
import com.springbootdemo.boot.pojo.UserInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: wangwenjie
 * @CreateTime: 2019-12-05 09:46
 */
@RequestMapping("/admin")
@RestController
public class LoginController extends BaseController {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @RequestMapping("/login")
    public AjaxResult<String> login(@RequestParam String username, @RequestParam String password) {
        try {
            if ("admin".equals(username) && "admin".equals(password)) {
                UserInfo userInfo = new UserInfo("admin", "00001", "admin", "byte admin");
                String token = jwtTokenUtils.generateToken(userInfo);
                return AjaxResult.success(token);
            } else {
                return AjaxResult.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

    @RequestMapping("/getUserInfo")
    public AjaxResult<UserInfo> getUserInfo(HttpServletRequest request) throws Exception {
//        try {
//            UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        UserInfo userInfo = getUserInfo();
        UserInfo userInfoByAttribute = getUserInfoByAttribute();
        SpringBean springBean = getSpringBean();
        UserInfo userInfoByOptional = getUserInfoByOptional();
        return AjaxResult.success(userInfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return AjaxResult.error();
//        }
    }
}
