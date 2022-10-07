package com.qiu.controllor;

import com.qiu.bash.ResultInfo;
import com.qiu.pojo.CusDevPlan;
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
    public Map<String,Object> querySaleChancesByParams(Integer sid,CusDevPlanQuery cusDevPlanQuery,HttpServletRequest request){
        cusDevPlanQuery.setSaleChanceId(sid);
        request.setAttribute("sId",sid);
        System.out.println("----797978686------");
        System.out.println(cusDevPlanQuery);
        System.out.println(sid);

        return cusDevPlanService.queryByParamsForTable(cusDevPlanQuery);
    }


    @RequestMapping("/toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Integer sid, HttpServletRequest request){
        request.removeAttribute("cusDevPlan");
        request.setAttribute("sId",sid);
        request.setAttribute("saleChance",saleChanceService.selectByPrimaryKey(sid));
        return "cusDevPlan/cus_dev_plan_data";
    }

    @RequestMapping("/addOrUpdateCusDevPlanPage")
    public String toAdd(Integer sid,Integer id,HttpServletRequest request){
//        request.removeAttribute("id");
        request.removeAttribute("cusDevPlan");
        request.setAttribute("sId",sid);
        request.setAttribute("cusDevPlan",cusDevPlanService.selectByPrimaryKey(id));
        System.out.println("---------");
        System.out.println(id);
        System.out.println("_____!____");
        return "cusDevPlan/add_update";
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo add(CusDevPlan cusDevPlan){
        ResultInfo resultInfo = new ResultInfo();
        Integer save = cusDevPlanService.add(cusDevPlan);
        return  resultInfo;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(CusDevPlan cusDevPlan){
        ResultInfo resultInfo = new ResultInfo();
        Integer save = cusDevPlanService.update(cusDevPlan);
        return  resultInfo;
    }



}
