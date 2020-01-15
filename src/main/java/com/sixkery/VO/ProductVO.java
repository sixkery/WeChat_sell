package com.sixkery.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品(包含类目)
 *
 * @author sixkery
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 8490352378244213262L;
    /**
     * 返回到前端是 name
     */
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
