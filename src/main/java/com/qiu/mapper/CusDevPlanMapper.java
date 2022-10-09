package com.qiu.mapper;

import com.qiu.bash.BaseMapper;
import com.qiu.pojo.CusDevPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Xu
 */
@Repository
public interface CusDevPlanMapper extends BaseMapper<CusDevPlan,Integer> {
    /**
     * 根据saleChanceId查记录
     * @param saleChanceId
     * @return
     */
    List<CusDevPlan> checkSaleChanceId(Integer saleChanceId);

    /**
     * 根据id更改开发结果
     * @param id
     * @param devResult
     * @return
     */

}