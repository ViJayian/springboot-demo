package com.springbootdemo.boot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * 启动监听类
 *
 * @author wangwenjie
 * @date 2019-12-20
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        //获取容器上下文
        ConfigurableApplicationContext context = event.getApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();
        String port = environment.getProperty("server.port");
        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        Map<String, Object> systemProperties = environment.getSystemProperties();
        log.info("spring boot run listen : {}", port);
    }
}
