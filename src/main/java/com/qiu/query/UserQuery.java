package com.qiu.query;

import com.qiu.bash.BaseQuery;
import lombok.Data;

/**
 * @author qiu
 * @create 2022/10/8 17:47
 **/
@Data
public class UserQuery extends BaseQuery {
    private String userName;
    private String email;
    private String phone;
}
