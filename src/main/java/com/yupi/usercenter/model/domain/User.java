package com.yupi.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author jun
 * @since 2023-02-14
 */
@Getter
@Setter
  @ApiModel(value = "User对象", description = "用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("用户昵称")
      private String username;

      @ApiModelProperty("账号")
      private String userAccount;

      @ApiModelProperty("头像")
      private String avatarUrl;

      @ApiModelProperty("性别")
      private Integer gender;

      @ApiModelProperty("密码")
      private String userPassword;

      @ApiModelProperty("电话")
      private String phone;

      @ApiModelProperty("邮箱")
      private String email;

      @ApiModelProperty("状态（0-正常）")
      private Integer userStatus;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;

      @ApiModelProperty("修改时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("是否删除")
      @TableLogic //逻辑删除的注解
      private Integer isDelete;

      @ApiModelProperty("用户角色：0-普通用户 1-管理员")
      private Integer userRole;


}
