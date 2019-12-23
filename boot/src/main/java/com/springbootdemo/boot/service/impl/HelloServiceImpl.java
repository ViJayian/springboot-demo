package com.springbootdemo.boot.service.impl;

import com.springbootdemo.boot.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * impl
 *
 * @author: wangwenjie
 * @date: 2019-12-13
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String h1() {
        return "h1";
    }
}
