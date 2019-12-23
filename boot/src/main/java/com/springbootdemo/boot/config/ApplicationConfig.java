package com.springbootdemo.boot.config;

import com.springbootdemo.boot.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * 项目配置
 *
 * @author wangwenjie
 * @date 2019-12-13
 */
@Configuration
public class ApplicationConfig {

    /**
     * 注册自定义filter
     *
     * @author wangwenjie
     * @date 2019-12-13
     */
    @Bean
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> logFilter = new FilterRegistrationBean<>();
        logFilter.setOrder(Ordered.HIGHEST_PRECEDENCE);
        logFilter.setFilter(new LogFilter());
        logFilter.addUrlPatterns("/*");
        return logFilter;
    }
}
