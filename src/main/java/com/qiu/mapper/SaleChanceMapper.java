package com.qiu.mapper;

import com.qiu.bash.BaseMapper;
import com.qiu.pojo.SaleChance;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleChanceMapper extends BaseMapper<SaleChance,Integer> {

    @Override
    Integer deleteByPrimaryKey(Integer id);

    int insert(SaleChance record);

    @Override
    Integer insertSelective(SaleChance record);

    @Override
    SaleChance selectByPrimaryKey(Integer id);

    @Override
    Integer updateByPrimaryKeySelective(SaleChance record);

    int updateByPrimaryKey(SaleChance record);
}