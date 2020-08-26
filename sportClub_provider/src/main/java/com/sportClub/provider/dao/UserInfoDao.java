package com.sportClub.provider.dao;

import com.sportClub.pojo.User;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-08-25
 * Time: 19:07
 */
public interface UserInfoDao {
    User selectUserByPhone(String phone);
    void updateUserInfo(User user);
}
