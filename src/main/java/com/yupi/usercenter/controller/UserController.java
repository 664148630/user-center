package com.yupi.usercenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.usercenter.common.BaseResponse;
import com.yupi.usercenter.common.ErrorCode;
import com.yupi.usercenter.common.ResultUtils;
import com.yupi.usercenter.exception.BusinessException;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.model.domain.request.UserLoginRequest;
import com.yupi.usercenter.model.domain.request.UserRegisterRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yupi.usercenter.service.IUserService;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.yupi.usercenter.contant.UserConstant.ADMIN_USER_ROLE;
import static com.yupi.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * <p>
 * 用户 前端控制器啊
 * </p>
 *
 * @author jun
 * @since 2023-02-14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
//            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请输入要注册的账号密码");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
//            return null;
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "注册的账号密码不能包含空格或空值");
        }
        long userRegister = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(userRegister);

    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
//            return null;
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请输入要登录的账号密码");
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
//            return null;
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "登录的账号密码不能包含空格或空值");
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);

    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
//            return null;
            throw new BusinessException(ErrorCode.NULL_ERROR, "request请求数据为null");
        }
        int userLogout = userService.userLogout(request);
        return ResultUtils.success(userLogout);

    }

    /**
     * 获取当前登录用户信息
     * @param request
     * @return
     */
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
//            return null;
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        Long userId = currentUser.getId();
        // TODO 检验用户是否合法
        User user = userService.getById(userId);
        //这里返回一个安全的脱敏信息
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        //鉴权；仅管理员可查询
        if (!isAdmin(request)) {
//            return Collections.emptyList();//Collections.emptyList()会抛出“java.lang.UnsupportedOperationException”的异常。
//            return new ArrayList<>();
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }

//        return userService.list(queryWrapper);

        //过滤不展示的数据
        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream().map(user -> {
//            user.setUserPassword(null); 查到的数据太多，脱敏一下
            return userService.getSafetyUser(user);
        }).collect(Collectors.toList());

        return ResultUtils.success(list);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        //鉴权；仅管理员可查询
        if (!isAdmin(request)) {
//            return null;
            throw new BusinessException(ErrorCode.NOT_AUTH);
        }

        if (id <= 0) {
//            return null;
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean removeById = userService.removeById(id);
        return ResultUtils.success(removeById);
    }

    /**
     * 是否为管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        //鉴权；仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;//这里返回的user需要判空一下，有可能返回的是空
//        if (user == null || user.getUserRole() != ADMIN_USER_ROLE) {
//            return false;
//        }
//        return true;
        //简写方式
        return user != null && user.getUserRole() == ADMIN_USER_ROLE;
    }

}

