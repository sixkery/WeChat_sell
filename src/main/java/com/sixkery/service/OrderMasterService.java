package com.sixkery.service;

import com.sixkery.dataObject.OrderMaster;
import com.sixkery.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author sixkery
 * @date 2019/11/11
 */
public interface OrderMasterService {
    // 创建订单
    OrderDTO create(OrderDTO orderDTO);

    // 查询单个订单
    OrderDTO findOne(String orderId);

    // 分页查询订单
    Page<OrderMaster> findList(Pageable pageable);

    // 完结订单
    OrderDTO finish(OrderDTO orderDTO);

    // 支付订单
    OrderDTO paid(OrderDTO orderDTO);

    // 取消订单
    OrderDTO cancel(OrderDTO orderDTO);

}
