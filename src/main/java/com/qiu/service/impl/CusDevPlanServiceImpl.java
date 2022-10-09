package com.qiu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiu.bash.BaseQuery;
import com.qiu.bash.BaseService;
import com.qiu.mapper.CusDevPlanMapper;
import com.qiu.mapper.SaleChanceMapper;
import com.qiu.pojo.CusDevPlan;
import com.qiu.query.CusDevPlanQuery;
import com.qiu.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiu
 * @create 2022/10/7 14:04
 **/
@Service
public class CusDevPlanServiceImpl extends BaseService<CusDevPlan,Integer> {

    @Autowired
    private CusDevPlanMapper cusDevPlanMapper;

    @Autowired
    private SaleChanceMapper saleChanceMapper;

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

    public Integer add(CusDevPlan cusDevPlan){
        paramsDecide(cusDevPlan);
        Integer integer = cusDevPlanMapper.insertSelective(cusDevPlan);
        AssertUtil.isTrue(integer == null || integer == 0,"系统错误");
        return integer;
    }

    public Integer update(CusDevPlan cusDevPlan){
        paramsDecide(cusDevPlan);
        cusDevPlan.setUpdateDate(new Date());
        Integer integer = cusDevPlanMapper.updateByPrimaryKeySelective(cusDevPlan);
        AssertUtil.isTrue(integer == null || integer == 0,"系统错误");
        return integer;
    }

    /**
     * 根据主键id删除记录
     * @param id
     * @return
     */
    public Integer delete(Integer id){
        AssertUtil.isTrue(id == null,"id错误");
        Integer result = cusDevPlanMapper.deleteByPrimaryKey(id);
        AssertUtil.isTrue(result == null || result == 0,"查询错误");
        return result;
    }



    private void paramsDecide(CusDevPlan cusDevPlan) {
        List<CusDevPlan> cusDevPlan1 = cusDevPlanMapper.checkSaleChanceId(cusDevPlan.getSaleChanceId());
        AssertUtil.isTrue(cusDevPlan1 == null ,"没有该记录");
        AssertUtil.stringIsBlank(cusDevPlan.getPlanItem(),"计划项内容为空");
        AssertUtil.stringIsBlank(cusDevPlan.getExeAffect(),"执行效果为空");
        AssertUtil.stringIsBlank(String.valueOf(cusDevPlan.getPlanDate()),"计划项时间为空");
    }

}
