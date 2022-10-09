用户登录模块
    1.参数校验
        用户名非空 密码非空
    2.根据用户名和密码查询数据库
    3.用户名和密码正确
        返回user对象 -> controller返回result对象
    4.不正确 
        提示密码或用户名错误 方法结束

用户修改密码模块
    1.验证cookie中的userId
        将userid与数据库的用户信息进行对比
    2.输入用户原密码进行比对
    3.新密码不能为空
    4.新密码不能喝旧密码一样
    5.再输入一次新密码进行校验
    6.完成用户密码修改

添加用户模块
    1.校验userName、user_pwd、email、phone等参数是否为空
    2.校验以上参数是否符合参数要求
        userName 3-6个字符 user_pwd 3-8个字符 email 6-13个字符 phone 11位数字
    3查询数据库中是否已存在userName
    4.将is_valid属性默认设为1
    5.将create_date设为当前系统时间
    6.添加记录

修改用户模块
    1.校验id、trueName、email、phone、isValid等参数是否为空
    2.校验phone参数是否符合要求
    3.将updateDate设置为当前系统时间
    4.更新数据

删除用户模块
    1.校验id是否为空
    2.批量删除id对应的记录

