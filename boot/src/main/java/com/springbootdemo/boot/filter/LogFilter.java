package com.springbootdemo.boot.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 日志
 *
 * @author wangwenjie
 * @date 2019-12-13
 */
@Slf4j
public class LogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("request url: [{}] , methods: [{}],", request.getRequestURL(), request.getMethod());

        long startTime = System.currentTimeMillis();

        filterChain.doFilter(request, response);


        log.info("ending request url: [{}] , methods: [{}] , status: [{}] , usage : [{}] ms",
                request.getRequestURL(), request.getMethod(), response.getStatus(), (System.currentTimeMillis() - startTime));
    }
}
