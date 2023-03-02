package com.yupi.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**用户注册请求体
 * Created by 赖学军
 *
 * @Date 2023/2/15 17:04
 * @Version 1.0
 */
@Data
public class UserRegisterRequest implements Serializable {
    //涉及网络的传输对象需要序列化，也需要一个序列化ID
    private static final long serialVersionUID = 5579580129348873892L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
