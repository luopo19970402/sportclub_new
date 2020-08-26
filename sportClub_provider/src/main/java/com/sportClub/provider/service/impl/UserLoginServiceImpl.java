package com.sportClub.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.sportClub.common.config.RedisKeyConfig;
import com.sportClub.common.dto.user.UserDto;
import com.sportClub.common.dto.user.UserLoginDto;
import com.sportClub.common.until.TokenUtil;
import com.sportClub.common.util.JwtUtil;
import com.sportClub.common.vo.R;
import com.sportClub.pojo.User;
import com.sportClub.provider.config.JedisCore;
import com.sportClub.provider.dao.UserLoginDao;
import com.sportClub.provider.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginDao userLoginDao;
    @Autowired
    private JedisCore jedisCore;

    /**
     * 登录
     *
     * @param userLoginDto 用户手机号和密码
     * @return
     */
    public R Login(UserLoginDto userLoginDto) {
        if (jedisCore.checkKey(RedisKeyConfig.PHONE_TOKEN + userLoginDto.getPhone())) {
            return R.fail("不好意思您已经登录");
        } else {
            User user = userLoginDao.selectPhone(userLoginDto.getPhone());
            if (user != null) {
                //比较密码
                if (JwtUtil.parseJWT(user.getUserPwd()).equals(userLoginDto.getPassword())) {
                    //正确生成令牌 设置有效期 存储到Redis
                    String token = TokenUtil.createToken(user.getUserId());
                    //token存储到Redis
                    jedisCore.set(RedisKeyConfig.PHONE_TOKEN + userLoginDto.getPhone(), token, RedisKeyConfig.TOKEN_TIME);
                    //存储用户信息到Redis
                    jedisCore.set(RedisKeyConfig.TOKEN_USER + token, JSON.toJSONString(user), RedisKeyConfig.TOKEN_TIME);
                    return R.ok(token);
                } else {
                    //错误登录失败
                    return R.fail("密码错误，登录失败");
                }
            }
            return R.fail("登录失败");
        }
    }

    /**
     * 检查手机号是否存在
     *
     * @param phone 输入的手机号
     * @return
     */
    @Override
    public R checkPhone(String phone) {
        User user = userLoginDao.selectPhone(phone);
        if (user != null) {
            return R.fail("手机号已经存在");
        } else {
            return R.ok("手机号不存在可以注册");
        }
    }

    /**
     * 注册
     *
     * @param userDto 注册用户信息(手机号，密码，昵称)
     * @return
     */
    @Override
    public R register(UserDto userDto) {
        if (checkPhone(userDto.getUserPhone()).getCode() == 200) {
            userDto.setUserPwd(JwtUtil.createJWT(userDto.getUserPwd()));
            User user = new User();
            user.setUserNickname(userDto.getUserNickname());
            user.setUserPhone(userDto.getUserPhone());
            user.setUserPwd(userDto.getUserPwd());
            userLoginDao.insert(user);
            return R.ok("注册成功");
        } else {
            return R.fail("您的手机号已被注册");
        }
    }
}
