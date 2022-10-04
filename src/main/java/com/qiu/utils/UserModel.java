package com.qiu.utils;

import com.qiu.pojo.User;
import lombok.Data;

/**
 * @author qiu
 * @create 2022/9/27 19:47
 **/

@Data
public class UserModel {

    private String userName;

    private String userIdStr;

    private String trueName;

    public static UserModel buildUserInfo(User user){
        UserModel userModel = new UserModel();
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getUserName());
        return userModel;
    }

}
