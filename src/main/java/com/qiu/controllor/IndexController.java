package com.qiu.controllor;

import com.qiu.bash.BaseController;
import com.qiu.service.UserService;
import com.qiu.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页控制器
 * @author qiu
 * @create 2022/9/25 15:54
 **/
@Controller
public class IndexController extends BaseController {

    @Autowired
    UserService userService;

     /**
         * 系统登录页
         * @return
         */
     @RequestMapping("index")
  public String index(){
    return "index";
  }

          // 系统界面欢迎页
           @RequestMapping("welcome")
   public String welcome(){
    return "welcome";
 }
   /**
      * 后端管理主页面
      * @return
      */
   @RequestMapping("main")
  public String main(HttpServletRequest request){
       int userId = LoginUserUtil.releaseUserIdFromCookie(request);
       request.setAttribute("user",userService.selectUserById(userId));
       return "main";

 }

}
