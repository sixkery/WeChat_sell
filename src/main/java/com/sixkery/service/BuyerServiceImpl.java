package com.sixkery.service;

import com.sixkery.dataObject.OrderMaster;
import com.sixkery.dto.OrderDTO;
import com.sixkery.enums.ResultEnum;
import com.sixkery.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sixkery
 * @date 2019/11/18
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private BuyerService buyerService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到该订单，orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderMasterService.create(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderMasterService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        /**判断是否是自己的订单*/
        if (!orderDTO.getBuyerOpenid().equals(orderId)) {
            log.error("【查询订单】订单的 openid 不一致。openid={},orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;

    }
}
