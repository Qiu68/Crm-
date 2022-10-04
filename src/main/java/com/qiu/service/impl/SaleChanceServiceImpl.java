package com.qiu.service.impl;


import com.qiu.bash.BaseService;
import com.qiu.mapper.SaleChanceMapper;
import com.qiu.mapper.UserMapper;
import com.qiu.pojo.SaleChance;
import com.qiu.pojo.User;
import com.qiu.query.SaleChanceQuery;
import com.qiu.utils.AssertUtil;
import com.qiu.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author qiu
 * @create 2022/10/2 15:52
 **/
@Service
public class SaleChanceServiceImpl extends BaseService<SaleChance,Integer>{

    @Autowired
    private SaleChanceMapper saleChanceMapper;

    @Autowired
    private UserMapper userMapper;

    public List<SaleChance> queryTest(SaleChanceQuery saleChanceQuery){
        return saleChanceMapper.selectByParams(saleChanceQuery);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int addSaleChance(SaleChance saleChance, HttpServletRequest request){
        int result = 0;
        //在cookie中获取userId,用于设置创建人
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userMapper.selectUserById(userId);
        //判断参数是否为空
        paramDecide(saleChance.getCustomerName(),saleChance.getLinkMan(),saleChance.getLinkPhone());
        saleChance.setCreateMan(user.getUserName());
        //指派人为空的情况下，设置初值
        if (saleChance.getAssignMan() == null){
            saleChance.setState(0);
            saleChance.setAssignTime(null);
            saleChance.setDevResult(0);
        }
        else {
            //指派人不为空的情况下设置初值
            saleChance.setState(1);
            saleChance.setAssignTime(new Date());
            saleChance.setDevResult(1);
        }
        /**
         * 设置开发属性
         * 0 失效
         * 1 有效
         * 2 开发中
         * 4 已完成
         */
        saleChance.setIsValid(1);
        //设置创建时间,默认当前系统时间
        saleChance.setCreateDate(new Date());
        //设置修改时间,默认当前系统时间
        saleChance.setUpdateDate(new Date());
        result = saleChanceMapper.insertSelective(saleChance);
        AssertUtil.isTrue(result == 0,"更新失败");
        return result;
    }

    /**
     * 1.判断saleChance.id 是否为空
     * 2.判断数据库中是否存在该记录
     * 3.获取该记录
     * 4.判断saleChance.customerName是否为空
     * 5.判断saleChance.linkName是否为空
     * 6.判断saleChance.linkPhone是否为空
     * 7.判断原指派人是否为空
     *      为空 新数据也为空
     *          不更新指派时间 不更新指派人
     *      为空 新数据不为空
     *          更新指派时间 更新指派人数据
     *       不为空 新数据为空
     *          不更新指派人 不更新指派时间
     *       不为空 新数据不为空
     *          判断指派人是否相同
     *              相同
     *                  不更新指派人 不更新指派时间
     *              不相同
     *                  更新指派人 更新指派时间
     * 8.设置更新时间为当前系统时间
     * 9.更新数据
     *
     * @param newSaleChance
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSaleChance(SaleChance newSaleChance){
        int result = 0;
        //根据新saleChance的id获取原saleChance的记录
        SaleChance oldSaleChance = saleChanceMapper.selectByPrimaryKey(newSaleChance.getId());
        AssertUtil.isTrue(newSaleChance.getId() == null,"没有该记录");
        AssertUtil.isTrue(oldSaleChance == null,"没有该记录");
        paramDecide(newSaleChance.getCustomerName(), newSaleChance.getLinkMan(), newSaleChance.getLinkPhone());

        if (oldSaleChance.getAssignMan() == null){
            if (newSaleChance.getAssignMan() != null){
                newSaleChance.setAssignTime(new Date());
                newSaleChance.setState(1);
                newSaleChance.setDevResult(1);
            }
        }
        else{
           if (!newSaleChance.getAssignMan().equals(oldSaleChance.getAssignMan())){
               newSaleChance.setAssignTime(new Date());
               newSaleChance.setState(1);
               newSaleChance.setDevResult(1);
           }
        }
        newSaleChance.setUpdateDate(new Date());
        AssertUtil.isTrue(saleChanceMapper.updateByPrimaryKeySelective(newSaleChance) != 1,"更新失败");
    }

    /**
     * 判断参数是否为空
     * @param customerName
     * @param linkMan
     * @param linkPhone
     */
    public void paramDecide(String customerName,String linkMan,String linkPhone){
        AssertUtil.stringIsBlank(customerName,"客户名为空");
        AssertUtil.stringIsBlank(linkMan,"联系人为空");
        AssertUtil.stringIsBlank(linkPhone,"联系电话为空");
    }
}
