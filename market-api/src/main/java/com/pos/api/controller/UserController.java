package com.pos.api.controller;

import com.pos.api.service.ApiSmsService;
import com.pos.api.service.ApiUserService;
import com.wz.cashloan.core.common.util.ServletUtils;
import com.wz.cashloan.core.common.web.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Author: HBX
 * <p>Time: 2018/8/24 21:30
 */
@Scope("prototype")
@Controller
@RequestMapping("/api")
public class UserController extends BaseController {
    @Resource
    private ApiUserService apiUserService;
    @Resource
    private ApiSmsService apiSmsService;

    /**
     * 注册
     * @param loginName
     * @param loginPwd
     * @param code
     */
    @RequestMapping("/user/register.htm")
    public void list(@RequestParam(value = "loginName") String loginName,
                     @RequestParam(value = "loginPwd") String loginPwd,
                     @RequestParam(value = "code") String code)

    {
        Map<String, Object> result = new HashMap<>();
        result = apiUserService.register(loginName, loginPwd, code);
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 登录
     * @param loginName
     * @param loginPwd
     */
    @RequestMapping("/user/login.htm")
    public void login(@RequestParam(value = "loginName") String loginName,
                      @RequestParam(value = "loginPwd") String loginPwd)

    {
        Map<String, Object> result = new HashMap<>();
        result = apiUserService.login(loginName, loginPwd);
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 重置密码
     * @param loginName
     * @param loginPwd
     */
    @RequestMapping("/user/resetPassword.htm")
    public void resetPassword(@RequestParam(value = "loginName") String loginName,
                              @RequestParam(value = "loginPwd") String loginPwd)

    {
        Map<String, Object> result = new HashMap<>();
        result = apiUserService.resetPassword(loginName, loginPwd);
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 获取短息验证码
     *
     * @param loginName
     * @param type
     */
    @RequestMapping("/user/sendSms.htm")
    public void sendSms(@RequestParam(value = "loginName") String loginName,
                        @RequestParam(value = "type") String type)

    {
        Map<String, Object> result = new HashMap<>();
        result = apiSmsService.sendSms(loginName, type);
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 忘记密码
     *
     * @param loginName
     * @param code
     * @param type
     */
    @RequestMapping("/user/forgetPwd.htm")
    public void sendSms(@RequestParam(value = "loginName") String loginName,
                        @RequestParam(value = "code") String code,
                        @RequestParam(value = "type") String type)

    {
        Map<String, Object> result = new HashMap<>();
        result = apiSmsService.checkCode(loginName, type, code);
        ServletUtils.writeToResponse(response, result);
    }

}
