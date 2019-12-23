package com.springbootdemo.boot.config;

import com.springbootdemo.boot.pojo.Car;
import com.springbootdemo.boot.pojo.FatherBean;
import com.springbootdemo.boot.pojo.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.theme.FixedThemeResolver;

/**
 * bean配置项
 * @Configuration 中的所有bean都会被动态代理，获取的实例都是同一个
 *
 * @author wangwenjie
 * @date 2019-12-20
 */
@Configuration
public class BeanConfiguration {
    @Bean
    public FatherBean f1() {
        return new FatherBean("f1");
    }

    /**
     * @return
     * @Primary 优先注入f2
     */
    @Bean
    @Primary
    public FatherBean f2() {
        return new FatherBean("f2");
    }

    @Bean
    public FatherBean f3() {
        return new FatherBean("f3");
    }

    @Bean
    public FatherBean f4() {
        return new FatherBean("f4");
    }

    /**
     * component修饰bean
     */
    @Bean
    public Person personC(){
        Person person = new Person();
        person.setCar(carC());
        return person;
    }

    @Bean
    public Car carC(){
        Car car = new Car();
        car.setName("凯迪拉克");
        return car;
    }
}
