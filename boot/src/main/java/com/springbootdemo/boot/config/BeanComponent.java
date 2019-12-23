package com.springbootdemo.boot.config;

import com.springbootdemo.boot.pojo.Car;
import com.springbootdemo.boot.pojo.FatherBean;
import com.springbootdemo.boot.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * component使用
 *
 * @author wangwenjie
 * @date 2019-12-20
 */
@Component
public class BeanComponent {
    @Bean
    public FatherBean componentFatherBean() {
        return new FatherBean("father bean");
    }

    /**
     * component修饰bean
     */
    @Bean
    public Person person(){
        Person person = new Person();
        person.setCar(car());
        return person;
    }

    @Bean
    public Car car(){
        Car car = new Car();
        car.setName("凯迪拉克");
        return car;
    }

}
