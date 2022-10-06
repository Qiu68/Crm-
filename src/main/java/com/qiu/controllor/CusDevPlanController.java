package com.qiu.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qiu
 * @create 2022/10/6 15:20
 **/
@Controller
@RequestMapping("/cus_dev_plan")
public class CusDevPlanController {
    @RequestMapping("/index")
    public String toIndex(){
        return "cusDevPlan/cus_dev_plan";
    }
}
