package com.qiu.service;

import com.qiu.pojo.User;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * @author qiu
 * @create 2022/9/27 20:21
 **/

public interface UserService {
    /**
     * 验证用户登录
     * @param userName
     * @param pwd
     * @return
     */
    User checkUserLogin(String userName, String pwd);

    /**
     * 根据用户id查询用户数据
     * @param id
     * @return
     */
    User selectUserById(Integer id);


    /**
     * 修改用户密码
     * @param userid
     * @param password
     * @return
     */
    int  updateUserPassword(int userid,String password);

    /**
     * 获得所有userName
     * @return
     */
    List<String> selectUserAllName();
}
