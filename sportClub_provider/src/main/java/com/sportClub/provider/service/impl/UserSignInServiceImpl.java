package com.sportClub.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.sportClub.common.config.RedisKeyConfig;
import com.sportClub.common.vo.R;
import com.sportClub.pojo.User;
import com.sportClub.pojo.UserSign;
import com.sportClub.provider.config.JedisCore;
import com.sportClub.provider.dao.UserSignDao;
import com.sportClub.provider.service.UserSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: 破晓
 * @date: 2020/8/21 19:58
 * @description:
 */
@Service("userSignService")
public class UserSignInServiceImpl implements UserSignService {

    @Resource
    private UserSignDao userSignDao;
    @Autowired
    private JedisCore jedisCore;

    /**
     * 查询用户签到信息
     * @param token
     * @return
     */
    @Override
    public R findUserSignIn(String token) {
        System.err.println("token: "+token);
        if(jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){
            System.err.println("true");
            //通过token获取用户
            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
            Integer userId=user.getUserId();
            //获取
            UserSign userSign = userSignDao.findByUserId(userId);
            System.err.println(userSign);
            //判断用户是否打签到
            if(null!=userSign){
                //获取连续签到天数
                //签到间断：上一次签到时间到现在大于签到的有效期（连续签到时间为0）
                if(new Date().getTime()-userSign.getSignDatetime().getTime() > isContinuousTime(userId)*1000){
                    //设置签到天数为0
                    userSign.setSignCountDays(0);
                }else {
                    //签到未间断
                    int s = Integer.parseInt(jedisCore.get(RedisKeyConfig.COUNT_DAY_USER + userId));
                    //获取令牌的签到天数并赋值
                    userSign.setSignCountDays(s);
                }
                //返回签到数据
                return R.ok(userSign);
            }else {
                //用hi
                return R.fail("用户暂未签到");
            }
        }else {
            return R.fail("用户未登录");
        }
    }

    /**
     * 用户签到
     * @param token
     * @return
     */
    @Override
    public R userSignIn(String token) {

        if(jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){

            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
            Integer userId=user.getUserId();
            UserSign byUserId = userSignDao.findByUserId(userId);
            //首次签到
            if(null==byUserId){
                //说明首次签到 添加签到数据
                int row = userSignDao.addUserSign(userId);
                if(row == 1){
                    //生成连续签到令牌
                    Long count = jedisCore.incrKey(RedisKeyConfig.COUNT_DAY_USER + userId);
                    //设置有效期
                    jedisCore.expire(RedisKeyConfig.COUNT_DAY_USER+userId,isContinuousTime(userId));

                    //添加健身豆

                    return R.ok("签到成功");
                }else {
                    return R.fail("服务器异常");
                }

            }else {
             //下次签到
                if(jedisCore.ttl(RedisKeyConfig.COUNT_DAY_USER+userId) > RedisKeyConfig.ONE_DAY){
                    //表示当天已经打过卡
                    return R.fail("今日已签到，明天再来吧");
                }else {
                    //表示当天未签到
                    int row = userSignDao.updateSign(userId);
                    if(row == 1){
                        //连续签到天数自动计算
                        Long count=jedisCore.incrKey(RedisKeyConfig.COUNT_DAY_USER+userId);
                        //删除原有的有效期
                        jedisCore.persist(RedisKeyConfig.COUNT_DAY_USER + userId);
                        //重新赋予有效期
                        jedisCore.expire(RedisKeyConfig.COUNT_DAY_USER+userId,isContinuousTime(userId));

                        return R.ok(count);
                    }
                }
                return R.fail("服务器异常");
            }
        }else {
            return R.fail("用户未登录");
        }

    }

    /**
     * 设置签到令牌的有效期
     * @param userId
     * @return
     */
    public int isContinuousTime(Integer userId){
        UserSign byUserId = userSignDao.findByUserId(userId);
        Date signDatetime = byUserId.getSignDatetime();

        //日历类
        Calendar calendar = Calendar.getInstance();
        //设置时间为当前时间
        calendar.setTime(new Date());
        //设置（修改）时间的小时数为24
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        //设置（修改）时间的分钟数为0分
        calendar.set(Calendar.MINUTE, 0);
        //设置（修改）时间的秒数为0秒
        calendar.set(Calendar.SECOND, 0);
        //此时calendar.getTime().getTime()为当天24:00:00的时间戳

        //获取上一次签到时间到当天24:00:00之间的毫秒数再加上24小时
        long time = calendar.getTime().getTime() - signDatetime.getTime();
        //获取秒数
        double t = time * 1.0 / 1000L;
        //取整秒数
        int time1 = (int) Math.rint(t) + RedisKeyConfig.ONE_DAY;

        return time1;
    }

}
