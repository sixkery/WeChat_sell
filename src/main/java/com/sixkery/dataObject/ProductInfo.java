package com.sixkery.dataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductInfo {
    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock; // 库存
    private String productDescription; // 描述
    private String productIcon; // 小图
    private Integer productStatus; // 商品状态 0 正常，1 下架
    private Integer categoryType; // 类目编号 商品和类目的关系

}
