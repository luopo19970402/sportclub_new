package com.sportClub.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class UserSign implements Serializable {
    /**
    * 签到id
    */
    private Integer signId;

    /**
    * 用户id
    */
    private Integer userId;

    /**
    * 连续签到天数
    */
    private Integer signCountDays;

    /**
    * 签到时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date signDatetime;

    private static final long serialVersionUID = 1L;
}