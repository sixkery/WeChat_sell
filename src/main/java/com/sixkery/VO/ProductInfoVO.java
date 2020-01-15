package com.sixkery.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品详情
 *
 * @author sixkery
 */
@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = 336281244010317957L;
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private String productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;


}
