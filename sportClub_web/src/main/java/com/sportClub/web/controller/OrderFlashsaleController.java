package com.sportClub.web.controller;

import com.sportClub.common.constant.SystemConstant;
import com.sportClub.common.vo.R;
import com.sportClub.provider.service.OrderFlashsaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: YangF
 * @Date: 2020-08-24 20:15
 * @description:
 */
@Api(tags = "秒杀")
@Slf4j
@RestController
@RequestMapping("seckill")
public class OrderFlashsaleController {
    @Autowired
    private OrderFlashsaleService orderFlashsaleService;

    @ApiOperation("查询秒基本信息（秒杀初始时间，结束时间，商品声誉数量，状态）")
    @GetMapping("/findAll")
    public R findAll(HttpServletRequest request){
        String token=request.getHeader(SystemConstant.TOKEN_HEADER);
        System.out.println("token"  + token);
        return orderFlashsaleService.findAll(token);
    }

    @PostMapping("/saveOrder/{id}")
    public R saveOrder(@PathVariable("id") Long id,HttpServletRequest request){
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        return orderFlashsaleService.saveOrder(id, token);
    }


}
