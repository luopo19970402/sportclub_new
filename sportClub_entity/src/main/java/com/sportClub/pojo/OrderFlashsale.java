package com.sportClub.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: 破晓
 * @date: 2020/8/20 21:49
 * @description:
 */
    
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderFlashsale implements Serializable {
    private Integer id;

    /**
     * 商品id
     */
    private Integer orderId;

    /**
     * 秒杀开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date startTime;

    /**
     * 秒杀实际开始时间 秒杀开始后通过业务逻辑记录实际开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date actualStartTime;

    /**
     * 秒杀结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date endTime;

    /**
     * 秒杀实际结束时间 秒杀结束后通过业务逻辑记录实际结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date actualEndTime;

    /**
     * 商品的剩余数量
     * */
    private int stockCount;
    /**
     * 秒杀的状态
     * */
    private String status;

    private static final long serialVersionUID = 1L;
}