package com.qiu.mapper;

import com.qiu.bash.BaseMapper;
import com.qiu.pojo.SaleChance;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleChanceMapper extends BaseMapper<SaleChance,Integer> {


    /**
     * 更新记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(SaleChance record);

    /**
     * 根据id更改开发结果
     * @param sid
     * @param devResult
     * @return
     */
    Integer updateCusDevPlanResultById(Integer sid,Integer devResult);
}