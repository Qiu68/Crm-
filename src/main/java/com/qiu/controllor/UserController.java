package com.qiu.controllor;

import com.qiu.bash.ResultInfo;
import com.qiu.exceptions.ParamsException;
import com.qiu.pojo.User;
import com.qiu.service.impl.UserServiceImpl;
import com.qiu.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author qiu
 * @create 2022/9/25 17:01
 **/

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo checkUserLogin(String userName,String userPwd){
        ResultInfo resultInfo = new ResultInfo();
        if (userName == null || userPwd == null){
            resultInfo.setCode(300);
            resultInfo.setMsg("用户名或密码为空");
            return resultInfo;
        }
        String pUserPwd = Md5Util.encode(userPwd);
        try {
           User user = userServiceImpl.checkUserLogin(userName, pUserPwd);
           resultInfo.setResult(UserModel.buildUserInfo(user));
        }
        catch (ParamsException e){
            resultInfo.setCode(300);
            resultInfo.setMsg("用户名或密码错误");
            return resultInfo;
        }
        return resultInfo;
    }

    @GetMapping("/toPasswordPage")
    public String changePassword(){
        return "password";
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public ResultInfo updatePassword(String  oldPassword, String  newPassword, String confirmPassword, HttpServletRequest request) {
        ResultInfo resultInfo = new ResultInfo();
        int userid = 0;
        userid=LoginUserUtil.releaseUserIdFromCookie(request);
        System.out.println(oldPassword+","+ newPassword+","+confirmPassword);
        System.out.println("------------------");
        System.out.println(userid);
        System.out.println("------------------");
        if (userid == 0){
            resultInfo.setCode(300);
            resultInfo.setMsg("您还没有登录!");
            return resultInfo;
        }
        User user = null;
        user = userServiceImpl.selectUserById(userid);

        if (oldPassword == null ||  newPassword == null || confirmPassword == null) {
            System.out.println("--------------");
            AssertUtil.isTrue(false, "参数为空！！！！");
            resultInfo.setCode(300);
            resultInfo.setMsg("参数为空");
        }
        else if (userServiceImpl.selectUserById(userid) == null) {
            AssertUtil.isTrue(false, "用户不存在");
            resultInfo.setCode(300);
            resultInfo.setMsg("用户不存在");
        }
        else if (!Md5Util.encode(oldPassword).equals(user.getUserPwd())) {
            AssertUtil.isTrue(false, "旧密码输入错误");
            System.out.println("------");
            System.out.println(user.getUserPwd());
            resultInfo.setCode(300);
            resultInfo.setMsg("旧密码输入错误");
        }
        else if (Md5Util.encode( newPassword).equals(user.getUserPwd())) {
            AssertUtil.isTrue(false, "新密码和旧密码相同");
            System.out.println("------");
            System.out.println(user.getUserPwd());
            resultInfo.setCode(300);
            resultInfo.setMsg("新密码和旧密码相同");
        }
        else if (! newPassword.equals(confirmPassword)) {
            AssertUtil.isTrue(false, "两次新密码输入不同");
            resultInfo.setCode(300);
            resultInfo.setMsg("两次新密码输入不同");
        }
        else {

            int i = 0;
            try {
                i = userServiceImpl.updateUserPassword(userid, Md5Util.encode( newPassword));
                if (i == 0) {
                    AssertUtil.isTrue(false, "更新错误");
                    resultInfo.setCode(300);
                    resultInfo.setMsg("更新错误");
                }
                else {
                    resultInfo.setCode(200);
                    resultInfo.setMsg("更新成功");
                }
            }
            catch (Exception e){
                resultInfo.setCode(300);
                System.out.println("更新错误");
            }


        }

        return resultInfo;
    }

    @RequestMapping("/queryAllSales")
    @ResponseBody
    public List<String> queryAllSales(){
        List<String> userList = userServiceImpl.selectUserAllName();
        return userList;
    }

}
