package com.springbootdemo.boot;

import com.springbootdemo.boot.pojo.SpringBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

//扫描filter
@ServletComponentScan
@SpringBootApplication
/**
 * 可以通过extends SpringBootServletInitializer重写方法引入外部容器
 */
@Slf4j
public class BootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws UnknownHostException {
        //springboot配置上下文
        ConfigurableApplicationContext configurableApplicationContext
                = SpringApplication.run(BootApplication.class, args);
        //配置环境参数
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();

        //获取运行ip和端口
        String host = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        log.info("SpringBoot demo run in http://{}:{}", host, port);
    }

    @Bean
    public SpringBean springBean() {
        SpringBean springBean = new SpringBean();
        springBean.setBeanName("mybean");
        springBean.setBeanValue("bean value");
        return springBean;
    }


}
