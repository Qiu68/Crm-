package com.qiu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiu.bash.BaseQuery;
import com.qiu.bash.BaseService;
import com.qiu.pojo.CusDevPlan;
import com.qiu.query.CusDevPlanQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiu
 * @create 2022/10/7 14:04
 **/
@Service
public class CusDevPlanServiceImpl extends BaseService<CusDevPlan,Integer> {

    public Map<String, Object> queryByParamsForTable(CusDevPlanQuery baseQuery) {
        Map<String,Object> result = new HashMap<String,Object>();
        PageHelper.startPage(baseQuery.getPage(),baseQuery.getLimit());
        PageInfo<CusDevPlan> pageInfo =new PageInfo<CusDevPlan>(selectByParams(baseQuery));
        result.put("count",pageInfo.getTotal());
        result.put("data",pageInfo.getList());
        result.put("code",0);
        result.put("msg","");
        return result;
    }

}
