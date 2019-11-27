package com.sixkery.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sixkery
 * @date 2019/11/12
 */
// 购物车对象
@Data
@Accessors(chain = true)
public class CartDTO {
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 数量
     */
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
