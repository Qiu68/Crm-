package com.qiu;

import com.alibaba.fastjson.JSON;
import com.qiu.bash.ResultInfo;
import com.qiu.exceptions.NoLoginException;
import com.qiu.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;

/**
 * @author qiu
 * @create 2022/10/2 9:58
 **/
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    /**
     * 全局异常处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @return modeAndView
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
        //登录异常
        if (e instanceof NoLoginException){
            NoLoginException ne = (NoLoginException) e;
            mv.setViewName("no_login");
            mv.addObject("msg",ne.getMsg());
            mv.addObject("ctx",httpServletRequest.getContextPath());
            return mv;
        }
        mv.setViewName("error");
        mv.addObject("code",400);
        mv.addObject("msg","系统异常请重试......");

        //异常返回视图
        if (o instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) o;
            //通过反射获取方法注解
            ResponseBody responseBody = handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);
            //如果没有responseBody注解，则返回数据
            if (responseBody == null){
                if (e instanceof ParamsException){
                    ParamsException pm = (ParamsException) e;
                    mv.addObject("msg", pm.getMsg());
                    mv.addObject("code", pm.getCode());
                }
                return mv;
            }
            else{
                ResultInfo resultInfo = new ResultInfo();
                if (e instanceof ParamsException){
                    ParamsException pm = (ParamsException) e;
                    mv.addObject("msg", pm.getMsg());
                    mv.addObject("code", pm.getCode());
                    resultInfo.setCode(pm.getCode());
                    resultInfo.setMsg(pm.getMsg());
                }
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                PrintWriter pw = null;
                try {
                    pw = httpServletResponse.getWriter();
                    pw.write(JSON.toJSONString(resultInfo));
                    pw.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                finally {
                    if (pw != null){
                        pw.close();
                    }
                }
                return null;
            }
        }
        else {
            return mv;
        }
    }
}
