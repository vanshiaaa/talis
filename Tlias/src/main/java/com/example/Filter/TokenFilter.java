package com.example.Filter;


import com.example.utils.CurrentHolder;
import com.example.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求路径
        String url = request.getRequestURL().toString();
        //2.判断是否是登录请求
        if(url.contains("/login")){
            log.info("登录请求");
            filterChain.doFilter(request,response);
            return;
        }
        //3.获取请求头的token
        String token = request.getHeader("token");
        //4.判断token是否存在
        if(!StringUtils.hasLength(token)){ //jwt为空
            log.info("获取到jwt令牌为空, 返回错误结果");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return;
        }
        //5.解析token
        try {
            Claims claims = JWTUtils.parseJWT(token);
            Integer empid = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败, 返回错误结果");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return;
        }
        //6.放行
        log.info("令牌合法放行");
        filterChain.doFilter(request,response);


        //7.清除当前线程的用户id
            CurrentHolder.remove();
    }
}
