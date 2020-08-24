package com.sportClub.web.controller;

import com.sportClub.common.constant.SystemConstant;
import com.sportClub.common.vo.R;
import com.sportClub.provider.service.UserSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: 破晓
 * @date: 2020/8/22 17:44
 * @description:
 */
@Api(tags = "用户签到")
@Slf4j
@RestController
@RequestMapping("userSign/")
public class UserSignController {
    @Resource
    private UserSignService userSignService;

    @ApiOperation("查询用户签到信息")
    @GetMapping("api/findUserSign.do")
    public R findUserSign(HttpServletRequest request){
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        System.err.println("token："+token);
        return userSignService.findUserSignIn(token);
    }

    @ApiOperation("打卡")
    @PostMapping("api/userSignIn.do")
    public R userSignIn(HttpServletRequest request) {
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        System.err.println("token：" + token);
        return userSignService.userSignIn(token);
    }




}
