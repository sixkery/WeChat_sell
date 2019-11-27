package com.sixkery.service;

import com.sixkery.dto.OrderDTO;

/**
 * 买家
 *
 * @author sixkery
 * @date 2019/11/18
 */

public interface BuyerService {
    /**
     * 查询单个订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOrderOne(String openid, String orderId);

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid, String orderId);
}
