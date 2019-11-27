package com.sixkery.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sixkery.dataObject.OrderDetail;
import com.sixkery.enums.OrderStatusEnum;
import com.sixkery.enums.PayStatusEnum;
import com.sixkery.utils.EnumUtil;
import com.sixkery.utils.serializer.Date2LongSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author sixkery
 * @date 2019/11/11
 */
@Data
@Accessors(chain = true)
/**@JsonInclude(JsonInclude.Include.NON_NULL) 前端不要求返回 orderDetail*/
public class OrderDTO {

    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    /**
     * 买家微信 openid
     */
    private String buyerOpenid;
    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态 , 默认为 0 新下单
     */
    private Integer orderStatus;
    /**
     * 支付状态，默认为 0 末支付
     */
    private Integer payStatus;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;

    @JsonIgnore // 对象转成 json 忽略这个方法
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }

}
