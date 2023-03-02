package com.yupi.usercenter.service;
import java.time.LocalDateTime;

import com.yupi.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


/**
 * Created by 赖学军
 * 用户服务测试
 * @Date 2023/2/14 13:21
 * @Version 1.0
 */
@SpringBootTest
class IUserServiceTest {

    @Resource
    private IUserService userService;
    @Test
    public void addUser() {

        User user = new User();
        user.setUsername("yupi88");
        user.setUserAccount("888");
        user.setAvatarUrl("https://img0.baidu.com/it/u=4197611983,2572788594&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setPhone("123");
        user.setEmail("456");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);

    }
    @Test
    void userRegister() {
        String userAccount = "yupi";
        //非空（密码不能为空）
        String userPassword = "";
        String checkPassword = "123456";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        //账号长度不小于4位
        userAccount = "yu";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        //密码不小于8位
        userAccount = "yupi";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        //账号不包含特殊字符
        userAccount = "yu pi";
        //密码和校验密码相同
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        //密码和校验密码相同
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        //账号不能重复
        userAccount = "dogYupi";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        //注册成功
        userAccount = "yupi";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1 ,result);


    }
}