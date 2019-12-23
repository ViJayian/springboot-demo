package com.springbootdemo.boot.filter;

import com.alibaba.fastjson.JSONObject;
import com.springbootdemo.boot.common.api.AjaxResult;
import com.springbootdemo.boot.common.utils.JwtTokenUtils;
import com.springbootdemo.boot.pojo.UserInfo;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Description:
 * @Author: wangwenjie
 * @CreateTime: 2019-12-06 09:24
 */
/*@WebFilter(urlPatterns = "/*", initParams = {
        @WebInitParam(name = "excludesPaths", value = "/admin/login")
})*/
public class JwtFilter implements Filter {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    private String excludesPaths;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();

        if (excludesPaths.indexOf(uri) != -1) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        request.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        try {
            if (StringUtils.isEmpty(token)) {
                accessHandler(servletResponse);
            } else {
                UserInfo userInfo = jwtTokenUtils.parseToken(token);
                request.setAttribute("userInfo", userInfo);
                filterChain.doFilter(request, servletResponse);
            }
        } catch (SignatureException e) {
            accessHandler(servletResponse);
        } catch (ExpiredJwtException e) {
            expiredHandler(servletResponse);
        }

    }

    private void accessHandler(ServletResponse servletResponse) throws IOException {
        this.hanlder(servletResponse, AjaxResult.unAccess());
    }

    private void expiredHandler(ServletResponse servletResponse) throws IOException {
        this.hanlder(servletResponse, AjaxResult.accessExpired());
    }

    private void hanlder(ServletResponse servletResponse, AjaxResult<String> result) throws IOException {
        String responseResult = new String(JSONObject.toJSONString(result).getBytes(), "utf-8");
        servletResponse.getWriter().write(responseResult);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludesPaths = filterConfig.getInitParameter("excludesPaths");
    }

    @Override
    public void destroy() {

    }


}
