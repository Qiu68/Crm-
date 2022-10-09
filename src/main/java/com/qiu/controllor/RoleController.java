package com.qiu.controllor;

import com.qiu.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author qiu
 * @create 2022/10/9 17:18
 **/
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/role")
    @ResponseBody()
    public List<Map<String,Object>> queryAllRole(){
        return roleService.queryAllRole();
    }
}
