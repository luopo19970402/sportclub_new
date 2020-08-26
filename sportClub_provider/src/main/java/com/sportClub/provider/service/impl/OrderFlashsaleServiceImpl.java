package com.sportClub.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.sportClub.common.config.RedisKeyConfig;
import com.sportClub.common.vo.R;
import com.sportClub.pojo.OrderFlashsale;
import com.sportClub.pojo.User;
import com.sportClub.provider.config.JedisCore;
import com.sportClub.provider.dao.OrderFlashsaleDao;
import com.sportClub.provider.service.OrderFlashsaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: YangF
 * @Date: 2020-08-21 22:08
 * @description:
 */
@Service
public class OrderFlashsaleServiceImpl implements OrderFlashsaleService {
    @Autowired
    private JedisCore jedisCore;
    @Autowired
    private OrderFlashsaleDao orderFlashsaleDao;
    /**
     * 查询所有话题
     *
     * @return 返回值为  类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R findAll(String token) {
        System.err.println("token: "+token);
       // if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER + token)){
            System.err.println("true");
        System.out.println(orderFlashsaleDao.findAll());
            return R.ok(orderFlashsaleDao.findAll());
      //  }else {
        //    return R.fail("请先登录");
       // }

    }

    @Override
    public R saveOrder(Long id, String token) {
        return null;
    }


}
