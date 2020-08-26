package com.sportClub.provider.dao;

import com.sportClub.pojo.OrderFlashsale;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: YangF
 * @Date: 2020-08-24 18:22
 * @description:
 */
@Repository
public interface OrderFlashsaleDao {
    List<OrderFlashsale> findAll();
//    List<OrderFlashsale> selectByExample(OrderFlashsale example);

}
