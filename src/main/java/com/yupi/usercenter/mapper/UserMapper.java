package com.yupi.usercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.usercenter.model.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author jun
 * @since 2023-02-14
 */
//@Mapper //加注解是映射到UserMapper.xml文件中去关联，就不需要设置namespace规则，属于mybatis的配置方式。如果用mybatisplus就不需要配置注解，直接设置namespace规则就好
public interface UserMapper extends BaseMapper<User> {

}
