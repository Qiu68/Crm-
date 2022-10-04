package com.qiu.mapper;

import com.qiu.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qiu
 * @create 2022/9/25 16:30
 **/

@Repository
public interface UserMapper  {

    /**
     * 验证用户登录
     * @param username
     * @param pwd
     * @return
     */
     User userLogin(@Param("userName") String username, @Param("pwd") String pwd);

    /**
     * 根据用户id查询用户数据
     * @param id
     * @return
     */
     User selectUserById(@Param("id") Integer id);

    /**
     * 更改用户密码
     * @param userid
     * @param password
     * @return
     */
    int updateUserPassword(int userid,String password);

    /**
     * 获取全部用户名
     * @return
     */
    List<User> selectAllUserName();
}
