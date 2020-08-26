package com.sportClub.web.controller;

import com.sportClub.common.constant.SystemConstant;
import com.sportClub.common.dto.UserInfoDto.UpdateUserInfoDto;
import com.sportClub.common.vo.R;
import com.sportClub.provider.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-08-25
 * Time: 19:16
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("userInfo/")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "查询用户信息",notes = "查询用户信息")
    @GetMapping("api/selectUserById.do")
    public R selectUserById(HttpServletRequest request){
        return userInfoService.selectUserByPhone(request.getHeader(SystemConstant.TOKEN_HEADER));
    }
    @ApiOperation(value = "修改用户信息",notes = "修改用户信息")
    @PostMapping("api/updateUserInfo.do")
    public R updateUserInfo(@RequestBody UpdateUserInfoDto updateUserInfoDto, HttpServletRequest request) throws IOException {
        return userInfoService.updateUserInfo(updateUserInfoDto,request.getHeader(SystemConstant.TOKEN_HEADER));
    }

}
