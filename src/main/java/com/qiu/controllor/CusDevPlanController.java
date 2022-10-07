package com.qiu.controllor;

import com.qiu.query.CusDevPlanQuery;
import com.qiu.query.SaleChanceQuery;
import com.qiu.service.impl.CusDevPlanServiceImpl;
import com.qiu.service.impl.SaleChanceServiceImpl;
import com.qiu.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author qiu
 * @create 2022/10/6 15:20
 **/
@Controller
@RequestMapping("/cus_dev_plan")
public class CusDevPlanController {

    @Autowired
    private SaleChanceServiceImpl saleChanceService;
    @Autowired
    private CusDevPlanServiceImpl cusDevPlanService;

    @RequestMapping("/index")
    public String toIndex(){
        return "cusDevPlan/cus_dev_plan";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> querySaleChancesByParams(Integer sid,CusDevPlanQuery cusDevPlanQuery){
        cusDevPlanQuery.setSaleChanceId(sid);
        System.out.println(cusDevPlanQuery);
        return cusDevPlanService.queryByParamsForTable(cusDevPlanQuery);
    }


    @RequestMapping("/toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Integer sid, Model model){
        model.addAttribute("saleChance",saleChanceService.selectByPrimaryKey(sid));
        return "cusDevPlan/cus_dev_plan_data";
    }
}
