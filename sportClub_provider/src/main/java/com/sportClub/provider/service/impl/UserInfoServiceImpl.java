package com.sportClub.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.sportClub.common.baidu.CensorUtil;
import com.sportClub.common.config.RedisKeyConfig;
import com.sportClub.common.dto.UserInfoDto.UpdateUserInfoDto;
import com.sportClub.common.vo.R;
import com.sportClub.pojo.User;
import com.sportClub.provider.config.JedisCore;
import com.sportClub.provider.dao.UserInfoDao;
import com.sportClub.provider.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-08-25
 * Time: 19:10
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private JedisCore jedisCore;

    @Override
    public R selectUserByPhone(String token) {
        if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){
            System.out.println("token存在========"+token);
            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
            User user1 = userInfoDao.selectUserByPhone(user.getUserPhone());
            return R.ok("查询成功",user1);
        }else {
            return R.fail("查询失败");
        }
    }
    @Override
    public R updateUserInfo(UpdateUserInfoDto updateUserInfoDto, String token) throws IOException {
        if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){
            System.out.println("token存在========"+token);
            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
            //用户名校验
            boolean b = CensorUtil.checkText(updateUserInfoDto.getUserNickname());
            //图片校验
//            FileInputStream fis=new FileInputStream(new File(updateUserInfoDto.getImg()));
//            ByteArrayOutputStream baos=new ByteArrayOutputStream();
//            byte[] arr=new byte[1024];
//            int len;
//            while ((len=fis.read(arr))!=-1){
//                baos.write(arr,0,len);
//            }
//            boolean c = CensorUtil.checkImg(baos.toByteArray());
            if (b) {
                user.setUserNickname(updateUserInfoDto.getUserNickname());
                user.setSex(updateUserInfoDto.getSex());
                user.setBirthday(updateUserInfoDto.getBirthday());
                user.setHeight(updateUserInfoDto.getHeight());
                user.setWeight(updateUserInfoDto.getWeight());
                user.setImg(updateUserInfoDto.getImg());
                userInfoDao.updateUserInfo(user);
                jedisCore.set(RedisKeyConfig.TOKEN_USER + token, JSON.toJSONString(user), RedisKeyConfig.TOKEN_TIME);
                jedisCore.set(RedisKeyConfig.PHONE_TOKEN + user.getUserPhone(), token, RedisKeyConfig.TOKEN_TIME);
                return R.ok("修改用户信息成功");
            }else {
                return R.fail("用户名信息敏感或图片信息敏感");
            }
        }
        return R.fail("登录超时请重新登录");
    }


}