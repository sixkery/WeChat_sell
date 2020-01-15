package com.sixkery.repository;

import com.sixkery.dataObject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sixkery
 * @date 2019/11/11
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    /**
     * 根据订单 ID 查询订单详情
     *
     * @param orderId 订单 ID
     * @return 订单详情列表
     */
    List<OrderDetail> findByOrderId(String orderId);

}
