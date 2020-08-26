package com.sportClub.provider.service;

import com.sportClub.common.dto.UserInfoDto.UpdateUserInfoDto;
import com.sportClub.common.vo.R;

import java.io.IOException;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-08-25
 * Time: 19:07
 */
public interface UserInfoService {
    R selectUserByPhone(String token);
    R updateUserInfo(UpdateUserInfoDto updateUserInfoDto, String token) throws IOException;
}
