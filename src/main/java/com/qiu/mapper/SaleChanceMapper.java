package com.qiu.mapper;

import com.qiu.pojo.SaleChance;

public interface SaleChanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaleChance record);

    int insertSelective(SaleChance record);

    SaleChance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleChance record);

    int updateByPrimaryKey(SaleChance record);
}