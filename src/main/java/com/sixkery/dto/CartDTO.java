package com.sixkery.dto;

import lombok.Data;

/**
 * @author sixkery
 * @date 2019/11/12
 */
// 购物车对象
@Data
public class CartDTO {
    private String productId; // 商品 id
    private Integer productQuantity; // 数量

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
