package com.zxy.interceptor;

//dependencies
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//block admin pages from public access
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //if the attempted accessor is not a valid user with admin status
        if (request.getSession().getAttribute("user") == null) {
            //direct request to login page
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
