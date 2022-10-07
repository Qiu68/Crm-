package com.qiu.query;

import com.qiu.bash.BaseQuery;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * @author qiu
 * @create 2022/10/7 12:45
 **/
public class CusDevPlanQuery extends BaseQuery {

    private Integer sid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "CusDevPlanQuery{" +
                "sid=" + sid +
                '}';
    }
}
