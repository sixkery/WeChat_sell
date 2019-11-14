package com.sixkery.dataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author sixkery
 * @date 2019/11/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class OrderDetail {
    @Id
    private String detailId;
    // 订单 id
    private String orderId;
    // 商品 id
    private String productId;
    // 商品名称
    private String productName;
    // 商品价格
    private BigDecimal productPrice;
    // 商品数量
    private Integer productQuantity;
    // 商品小图
    private String productIcon;
}
