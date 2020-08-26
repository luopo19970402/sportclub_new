package com.sportClub.provider.service;

import com.sportClub.common.vo.R;
import com.sportClub.pojo.OrderFlashsale;

import java.util.List;

/**
 * @Author: YangF
 * @Date: 2020-08-21 22:07
 * @description:
 */
public interface OrderFlashsaleService {
  

    R saveOrder(Long id, String token);


    R findAll(String token);
}
