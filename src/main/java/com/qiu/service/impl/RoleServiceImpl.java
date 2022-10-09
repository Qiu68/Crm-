package com.qiu.service.impl;

import com.qiu.bash.BaseService;
import com.qiu.mapper.RoleMapper;
import com.qiu.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author qiu
 * @create 2022/10/9 17:12
 **/
@Service
public class RoleServiceImpl extends BaseService<Role,Integer> {

    @Autowired
    private RoleMapper roleMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Map<String,Object>> queryAllRole(){
       return roleMapper.queryAllRole();
    }
}
