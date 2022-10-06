package com.qiu.query;

import com.qiu.bash.BaseQuery;
import lombok.Data;

/**
 * @author qiu
 * @create 2022/10/3 14:10
 **/
@Data
public class SaleChanceQuery extends BaseQuery {

    //营销机会

    private String customerName;
    private String createMan;
    private String state;

    //客户开发计划

    private String assignMan;
    private String devResult;


}
