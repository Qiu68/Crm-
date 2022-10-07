package com.qiu.query;

import com.qiu.bash.BaseQuery;
import lombok.Data;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * @author qiu
 * @create 2022/10/7 12:45
 **/
@Data
public class CusDevPlanQuery extends BaseQuery {
    private Integer saleChanceId;
}
