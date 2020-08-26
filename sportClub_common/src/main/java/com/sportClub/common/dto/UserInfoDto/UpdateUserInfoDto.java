package com.sportClub.common.dto.UserInfoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-08-25
 * Time: 19:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserInfoDto {
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

}