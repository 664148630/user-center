package com.yupi.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**用户登录请求体
 * Created by 赖学军
 *
 * @Date 2023/2/15 17:26
 * @Version 1.0
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 7851750037521868036L;

    private String userAccount;

    private String userPassword;
}
