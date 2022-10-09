package com.qiu.mapper;

import com.qiu.bash.BaseMapper;
import com.qiu.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Xu
 */
@Repository
public interface RoleMapper extends BaseMapper<Role,Integer> {

    /**
     * 查询所有角色
     * @return
     */
    List<Map<String,Object>> queryAllRole();
}