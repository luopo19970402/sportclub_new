package com.sportClub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: 破晓
 * @date: 2020/8/20 21:49
 * @description:
 */
    
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer userId;

    /**
    * 用户手机号
    */
    private String userPhone;

    /**
    * 密码
    */
    private String userPwd;

    /**
    * 昵称
    */
    private String userNickname;

    /**
    * 性别
    */
    private String sex;

    /**
    * 出生日期
    */
    private Date birthday;

    /**
    * 身高
    */
    private Integer height;

    /**
    * 体重
    */
    private Integer weight;

    /**
    * 头像
    */
    private String img;

    /**
    * 健身豆
    */
    private Integer fitnessBean;

    private static final long serialVersionUID = 1L;

    public User(Integer userId, String userPhone, String userPwd, String userNickname, Integer fitnessBean) {
        this.userId = userId;
        this.userPhone = userPhone;
        this.userPwd = userPwd;
        this.userNickname = userNickname;
        this.fitnessBean = fitnessBean;
    }
}