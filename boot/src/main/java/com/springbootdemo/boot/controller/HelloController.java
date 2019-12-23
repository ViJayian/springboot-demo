package com.springbootdemo.boot.controller;

import com.springbootdemo.boot.base.BaseController;
import com.springbootdemo.boot.common.api.AjaxResult;
import com.springbootdemo.boot.pojo.*;
import com.springbootdemo.boot.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * hello
 *
 * @author wangwenjie
 * @date 2019-12-13
 */
@RestController
@RequestMapping("/hello")
public class HelloController extends BaseController {

    /**
     * 使用final 引入spring容器对象，需要提供构造方法
     */
    private final HelloService helloService;

    /**
     * 使用构造器注入可以省略@AutoWired
     *
     * @param helloService
     */
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/h1")
    public AjaxResult<String> h1() {
        return AjaxResult.success(helloService.h1());
    }

    /**
     * valid 校验 参数需要放到实体类中
     *
     * @param vo
     * @author wangwenjie
     */
    @PostMapping("/h2")
    public AjaxResult<String> h2(@RequestBody @Valid ValidVo vo) {
        return AjaxResult.success(vo.getMessage());
    }

    /**
     * 校验无效
     *
     * @param message
     * @author wangwenjie
     */
    @PostMapping("/h3")
    public AjaxResult<String> h3(@RequestBody @Valid
                                 @NotBlank(message = "post 参数不能为空") String message) {
        return AjaxResult.success(message);
    }

    @GetMapping("/h4")
    public AjaxResult<SpringBean> h4() {
        return AjaxResult.success(getBean(SpringBean.class));
    }

    /**
     * @Autowired 优先使用byType进行注入的，然后是byName
     * 因此，当注入为名称时，也是可以的
     */
    @Autowired
    private FatherBean f1;


    /**
     * Field fatherBean in com.springbootdemo.boot.controller.HelloController
     * required a single bean, but 2 were found:
     * 这样注入采用bytype方式，找到两个相同的bean，需要使用@Primary或者@Qualifier区分优先级
     */
    @Autowired
    private FatherBean fatherBean;

    /**
     * @Qualifier 通过名称注入 与 @Resource类似
     */
    @Autowired
    @Qualifier("f4")
    private FatherBean bean;

    @GetMapping("/h5")
    public AjaxResult<String> h5() {
        return AjaxResult.success(f1.getName());//f1
    }

    @GetMapping("/h6")
    public AjaxResult<String> h6() {
        return AjaxResult.success(fatherBean.getName());//f2
    }

    @GetMapping("/h7")
    public AjaxResult<String> h7() {
        return AjaxResult.success(bean.getName());//f4
    }

    @Resource
    private FatherBean componentFatherBean;

    @GetMapping("/h8")
    public AjaxResult<String> h8() {
        return AjaxResult.success(componentFatherBean.getName());//father bean
    }

    @Resource
    private Person person;

    @Resource
    private Car car;

    @Autowired
    @Qualifier("personC")
    private Person personC;

    @Autowired
    @Qualifier("carC")
    private Car carC;

    /**
     * @return
     * @Component 注解修饰的bean会多次创建
     * @Configuration 修饰的bean会被cglib进行代理，bean只创建一次
     */
    @GetMapping("/h9")
    public AjaxResult<String> h9() {
        Car car = person.getCar();
        System.out.println(car == this.car);//false

        Car carC = personC.getCar();
        System.out.println(carC == this.carC);//true
        return AjaxResult.success("success");
    }

    @GetMapping("/h10")
    public AjaxResult h10() {
        throw new RuntimeException("error");
    }
}
