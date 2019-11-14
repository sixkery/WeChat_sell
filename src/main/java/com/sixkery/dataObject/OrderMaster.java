package com.sixkery.dataObject;

import com.sixkery.enums.OrderStatusEnum;
import com.sixkery.enums.PayStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author sixkery
 * @date 2019/11/10
 */
// 订单主表
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@DynamicUpdate
public class OrderMaster {
    @Id
    private String orderId;  // 订单 id
    private String buyerName; // 买家名字
    private String buyerPhone; // 买家手机号
    private String buyerAddress; // 买家地址
    // 买家微信 openid
    private String buyerOpenid;
    // 订单总金额
    private BigDecimal orderAmount;

    // 订单状态 , 默认为 0 新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    // 支付状态，默认为 0 末支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    private Date createTime;
    private Date updateTime;


}
