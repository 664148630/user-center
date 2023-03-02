package com.yupi.usercenter.common;

import lombok.Data;

import java.io.Serializable;

/**通用返回类（返回给前端的对象）
 * Created by 赖学军
 *
 * @Date 2023/2/24 19:23
 * @Version 1.0
 */
@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 2507898595417511666L;

    private int code;

    private T data;

    private String message;

    private String description;

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data, String message) {
        //使用this调用当前类的其它构造函数
        this(code, data, message, "");
    }

    public BaseResponse(int code, T data) {
        //使用this调用当前类的其它构造函数
        this(code, data, "", "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }
}
