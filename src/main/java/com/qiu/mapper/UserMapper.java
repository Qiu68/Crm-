package com.qiu.mapper;

import com.qiu.bash.BaseQuery;
import com.qiu.pojo.User;
import com.qiu.query.UserQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.management.Query;
import java.util.List;
import java.util.Map;

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
    List<Map<String,Object>> selectAllUserName();

    /**
     * 分页多条件查询用户记录
     * @param baseQuery
     * @return
     */
    List<User> selectByParams(UserQuery baseQuery);

    /**
     * 添加user记录
     * @param user
     * @return
     */
    Integer addUser(User user);

    /**
     * 根据user id 更新对应记录
     * @param user
     * @return
     */
    Integer updateUser(User user);


    /**
     * 批量删除用户记录
     * @param ids
     * @return
     */
    Integer deleteUser(Integer[] ids);

    /**
     * 根据用户名查询用户记录
     * @param userName
     * @return
     */
    User selectUserByName(String userName);
}
