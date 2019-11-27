package com.sixkery.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author sixkery
 * @date 2019/11/27
 */
@Data
public class ProductForm {

    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer categoryType;
}
