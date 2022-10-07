package com.qiu.controllor;

import com.qiu.service.impl.SaleChanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/index")
    public String toIndex(){
        return "cusDevPlan/cus_dev_plan";
    }

    /*@RequestMapping("/list")
    @ResponseBody
    public List<SaleChance> queryCusDevPlanByParams(CusDevPlanQuery cusDevPlanQuery){
        System.out.println(cusDevPlanQuery);
        return cusDevPlanService.selectAllUserById(cusDevPlanQuery);
    }*/


    @RequestMapping("/toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Integer sid, Model model){
        model.addAttribute("saleChance",saleChanceService.selectByPrimaryKey(sid));
        return "cusDevPlan/cus_dev_plan_data";
    }
}
