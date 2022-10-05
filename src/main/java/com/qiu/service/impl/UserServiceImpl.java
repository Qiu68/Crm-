package com.qiu.service.impl;

import com.qiu.mapper.UserMapper;
import com.qiu.pojo.User;
import com.qiu.service.UserService;
import com.qiu.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qiu
 * @create 2022/9/25 16:58
 **/

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUserLogin(String userName, String pwd){
        User user = userMapper.userLogin(userName, pwd);
        AssertUtil.isTrue(user == null,"账号或密码错误");
        return user;
    }


    /**
     * 根据用户id查询用户数据
     *
     * @param id
     * @return
     */
    @Override
    public User selectUserById(Integer id) {
        User user = userMapper.selectUserById(id);
        return user;
    }

    /**
     * 修改用户密码
     *
     * @param userid
     * @param password
     * @return
     */
    @Override
    @Transactional()
    public int updateUserPassword(int userid, String password) {
            int i = userMapper.updateUserPassword(userid, password);
            AssertUtil.isTrue(i==0,"不存在");
            return i;
    }

    /**
     * 获得所有userName
     *
     * @return
     */
    @Override
    public List<Map<String,Object>> selectUserAllName() {
        List<Map<String,Object>> userMap = userMapper.selectAllUserName();
        return userMap;
    }


}
