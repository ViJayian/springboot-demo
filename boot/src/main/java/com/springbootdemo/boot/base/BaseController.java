package com.springbootdemo.boot.base;

import com.springbootdemo.boot.exception.AccessException;
import com.springbootdemo.boot.pojo.SpringBean;
import com.springbootdemo.boot.pojo.UserInfo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @Description: 公用controller
 * @Author: wangwenjie
 * @CreateTime: 2019-12-06 11:12
 */
public class BaseController implements ApplicationContextAware {

    public ApplicationContext applicationContext;

    /**
     * RequestContextHolder 持有上下文request容器
     *
     * @return
     */
    public ServletRequestAttributes getRequsetAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 通过Optional获取request
     *
     * @return
     */
    public Optional<HttpServletRequest> getCurrentRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                //过滤出servletRequestAttributes的子类
                .filter(requestAttributes -> requestAttributes instanceof ServletRequestAttributes)
                //强转
                .map(requestAttributes -> (ServletRequestAttributes) requestAttributes)
                .map(ServletRequestAttributes::getRequest);
    }

    /**
     * 获取当前请求
     *
     * @return
     */
    public HttpServletRequest getRequestByOptional() {
        return getCurrentRequest().get();
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    public UserInfo getUserInfoByOptional() throws Exception {
        return (UserInfo) getCurrentRequest()
                .map(request -> request.getAttribute("userInfo"))
//                .orElseThrow(AccessException::new);
                .orElseThrow(() -> new AccessException(HttpStatus.FORBIDDEN));
    }


    //获取request
    public HttpServletRequest getRequest() {
        return getRequsetAttributes().getRequest();
    }

    //获取用户信息
    public UserInfo getUserInfo() {
        return (UserInfo) getRequest().getAttribute("userInfo");
    }

    //获取request域中的userinfo
    public UserInfo getUserInfoByAttribute() {
        return (UserInfo) getRequsetAttributes().getAttribute("userInfo", RequestAttributes.SCOPE_REQUEST);
    }

    //spring上下文
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public SpringBean getSpringBean() {
        return (SpringBean) applicationContext.getBean("springBean");
    }

    public <T> T getBean(Class<T> classT) {
        return (T) applicationContext.getBean(classT);
    }
}
