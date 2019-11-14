package com.sixkery.dto;

import com.sixkery.dataObject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author sixkery
 * @date 2019/11/11
 */
@Data
public class OrderDTO {

    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    // 买家微信 openid
    private String buyerOpenid;
    // 订单总金额
    private BigDecimal orderAmount;

    // 订单状态 , 默认为 0 新下单
    private Integer orderStatus;
    // 支付状态，默认为 0 末支付
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;

    List<OrderDetail> orderDetailList;

}
