package com.qiu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiu.bash.BaseQuery;
import com.qiu.mapper.UserMapper;
import com.qiu.pojo.User;
import com.qiu.query.UserQuery;
import com.qiu.service.UserService;
import com.qiu.utils.AssertUtil;
import com.qiu.utils.Md5Util;
import com.qiu.utils.PhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import javax.xml.crypto.Data;
import java.util.*;

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

    public Map<String, Object> queryByParamsForTable(UserQuery baseQuery) {
        Map<String,Object> result = new HashMap<String,Object>();
        PageHelper.startPage(baseQuery.getPage(),baseQuery.getLimit());
        PageInfo<User> pageInfo =new PageInfo<User>(userMapper.selectByParams(baseQuery));
        result.put("count",pageInfo.getTotal());
        result.put("data",pageInfo.getList());
        result.put("code",0);
        result.put("msg","");
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addUser(User user){
        Integer result = null;
        //将用户有效性默认值设为1
        user.setIsValid(1);
        //参数校验 不能为空
        paramsDecide(user);
        //判断数据库中是否存在该用户名
        AssertUtil.isTrue(selectUserByName(user.getUserName()) != null,"用户已存在！");
        user.setUserPwd(Md5Util.encode(user.getUserPwd()));
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        result = userMapper.addUser(user);
        AssertUtil.isTrue(result == null || result == 0,"添加失败");
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateUser(User user){
        Integer result = null;
        AssertUtil.isTrue(user.getId() == null || user.getId() == 0,"请输入id");
        paramsDecide(user);
        AssertUtil.isTrue(user.getIsValid() == null,"请输入用户状态");
        user.setUserPwd(Md5Util.encode(user.getUserPwd()));
        user.setUpdateDate(new Date());
        result = userMapper.updateUser(user);
        AssertUtil.isTrue(result == null || result != 1,"更新失败");
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteUser(Integer[] ids){
        AssertUtil.isTrue(ids == null || ids.length <1,"请输入待删除的id");
        Integer integer = userMapper.deleteUser(ids);
        return integer;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User selectUserByName(String userName){
        User user = userMapper.selectUserByName(userName);
        return user;
    }

    private void paramsDecide(User user){
        AssertUtil.stringIsBlank(user.getUserName(),"用户名输入错误");
        AssertUtil.stringIsBlank(user.getUserPwd(),"密码输入错误!");
        AssertUtil.stringIsBlank(user.getEmail(),"邮箱输入错误！");
        AssertUtil.stringIsBlank(user.getPhone(),"手机号码输入错误！");
        AssertUtil.isTrue(!PhoneUtil.isMobile(user.getPhone()),"请输入正确的手机号");

    }


}
