package com.qiu.pojo;

import lombok.Data;

/**
 * @author qiu
 * @create 2022/9/25 16:40
 **/

@Data
public class User {

    private Integer id;

    private String userName;

    private String userPwd;

    private String email;

    private String phone;

    private Integer isValid;

    private String createDate;

    private String updateDate;
}
