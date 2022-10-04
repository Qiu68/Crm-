package com.qiu.controllor;

import com.qiu.bash.BaseController;
import com.qiu.bash.ResultInfo;
import com.qiu.pojo.SaleChance;
import com.qiu.query.SaleChanceQuery;
import com.qiu.service.SaleChanceService;
import com.qiu.service.impl.SaleChanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author qiu
 * @create 2022/10/2 15:56
 **/
@Controller
@RequestMapping("/sale_chance")
public class SaleChanceController extends BaseController {

    @Autowired
    private SaleChanceServiceImpl saleChanceServiceImpl;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery){
        return saleChanceServiceImpl.queryByParamsForTable(saleChanceQuery);
    }

    @RequestMapping("/all")
    @ResponseBody
    public List<SaleChance> querySaleChancesAll(SaleChanceQuery saleChanceQuery){
        return saleChanceServiceImpl.queryTest(saleChanceQuery);
    }

    @RequestMapping("/index")
    public String toSale_chanceIndex(){
        return "saleChance/sale_chance";
    }

    @RequestMapping("/addOrUpdateSaleChancePage")

    public String addOrUpdateSaleChancePage(){
        return "saleChance/add_update";
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo save(SaleChance saleChance, HttpServletRequest request){
        ResultInfo resultInfo = new ResultInfo();
        int i = saleChanceServiceImpl.addSaleChance(saleChance, request);
        if (i == 1){
            resultInfo.setCode(200);
            resultInfo.setMsg("添加成功");
        }
        else{
            resultInfo.setCode(300);
            resultInfo.setMsg("添加错误");
        }
        return resultInfo;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo update(SaleChance saleChance){
        ResultInfo resultInfo = new ResultInfo();
        saleChanceServiceImpl.updateSaleChance(saleChance);
        resultInfo.setMsg("更新成功");
        return  resultInfo;
    }

}