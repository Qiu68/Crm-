package com.qiu.interceptor;

import com.qiu.exceptions.NoLoginException;
import com.qiu.service.UserService;
import com.qiu.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qiu
 * @create 2022/10/2 10:34
 **/
public class NoLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        if (userId == 0 || userService.selectUserById(userId) == null){
            throw new NoLoginException();
        }

        return true;
    }
}
