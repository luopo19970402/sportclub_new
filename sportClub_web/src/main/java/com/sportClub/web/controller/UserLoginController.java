package com.sportClub.web.controller;

import com.sportClub.common.dto.user.UserDto;
import com.sportClub.common.dto.user.UserLoginDto;
import com.sportClub.common.vo.R;
import com.sportClub.provider.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户登录注册")
@Slf4j
@RestController
@RequestMapping("tologin/")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    @ApiOperation("查询手机号是否存在")
    @GetMapping("api/checkPhone/{phone}")
    public R checkPhone(@PathVariable String phone) {
        return userLoginService.checkPhone(phone);
    }
    @ApiOperation("注册")
    @PostMapping("api/register")
    public R register(@RequestBody UserDto userDto){
        return userLoginService.register(userDto);
    }

    @ApiOperation("登录")
    @PostMapping("api/login")
    public R login(@RequestBody UserLoginDto userLoginDto){
        return userLoginService.Login(userLoginDto);
    }
}
