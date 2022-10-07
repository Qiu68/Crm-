package com.qiu.mapper;

import com.qiu.bash.BaseMapper;
import com.qiu.pojo.SaleChance;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleChanceMapper extends BaseMapper<SaleChance,Integer> {



    int updateByPrimaryKey(SaleChance record);
}